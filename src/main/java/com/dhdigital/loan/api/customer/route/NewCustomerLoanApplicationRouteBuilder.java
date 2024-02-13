package com.dhdigital.loan.api.customer.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dhdigital.loan.api.customer.model.NewLoanApplicationRequestType;
import com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationRequestBackend;
import com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationResponseBackend;

@Component
public class NewCustomerLoanApplicationRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()  // Initializing REST Configuration.
		.bindingMode(RestBindingMode.json)
		.port(8081);
		
		rest("/api/customer")
		.post("v1/NewLoanApplication") // http://localhost:8081/api/customer/v1/NewLoanApplication
		.type(NewLoanApplicationRequestType.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:newCustomerLoanApplication");
		
		
		onException(Exception.class) // Incase if there is any exception in Route
		.log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"NewLoanApplicationResponse\",\"MW\")")
		.log("Exception"+"${exception}").handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));
		
		
		from("direct:newCustomerLoanApplication").routeId("newCustomerLoanApplication")
		.to("bean:newCustomerLoanApplicationService?method=setNewLoanApplicationRequestIn") // Saving Frontend request locally
		.to("bean:newCustomerLoanApplicationService?method=prepareNewLoanApplicationRequestBackend") // Converting Frontend request to Backend request
		.marshal(new JacksonDataFormat(NewLoanApplicationRequestBackend.class)) // Marshalling Backend request to json
		.log("Request Body - ${body}")
		.choice()
		
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.PersonalDetails.Applicant.fullName == null || @.PersonalDetails.Applicant.fullName == '')]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"fullName either null or empty\",\"\",\"\",\"validationsCust\",${exchange})")
				
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.PersonalDetails.Applicant.phoneNumber == null || @.PersonalDetails.Applicant.phoneNumber == '')]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"phoneNumber either null or empty\",\"\",\"\",\"validationsCust\",${exchange})")	
				
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.PersonalDetails.Applicant.annualSalary == null || @.PersonalDetails.Applicant.annualSalary == 0)]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"annualSalary either null or 0\",\"\",\"\",\"validationsCust\",${exchange})")		
				
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.LoanDetails.loanAmount == null || @.LoanDetails.loanAmount == 0)]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"loanAmount either null or 0\",\"\",\"\",\"validationsCust\",${exchange})")
				
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.LoanDetails.loanType == null || @.LoanDetails.loanType == '')]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"loanType either null or empty\",\"\",\"\",\"validationsCust\",${exchange})")	
				
			.when().jsonpath("$.NewLoanApplicationRequest[?(@.LoanDetails.creditScore == null || @.LoanDetails.creditScore == 0)]")
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"INCORRECTVALUE\",\"creditScore either null or 0\",\"\",\"\",\"validationsCust\",${exchange})")
				
		.otherwise()		
			.to("{{loanAPI.host}}{{loanAPI.contextPath}}NewLoanApplication?bridgeEndpoint=true") // http://localhost:8080/api/loan/v1/NewLoanApplication?bridgeEndpoint=true
		.log("Response Body - ${body}")
			.choice()
			
				.when().jsonpath("$.NewLoanApplicationResponse[?(@.LoanAccountDetails.accountNumber != null && @.LoanAccountDetails.accountNumber != '')]") // Typechecking backend response if accountNumber is null or empty
					.unmarshal(new JacksonDataFormat(NewLoanApplicationResponseBackend.class)) // Converting backend response to json
					.to("bean:newCustomerLoanApplicationService?method=prepareNewCustomerLoanApplicationResponse") // Converting Backend response to Frontend response
					.setHeader("Content-Type", constant("application/json"))
					
			.otherwise()
				.to("bean:utils?method=prepareFaultNodeStr(\"NewLoanApplicationResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.end();
		
	}

}
