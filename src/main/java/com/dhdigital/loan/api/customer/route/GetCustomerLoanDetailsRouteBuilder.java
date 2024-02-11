package com.dhdigital.loan.api.customer.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.dhdigital.loan.api.customer.model.GetCustomerLoanDetailsRequestType;
import com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsRequestBackend;
import com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsResponseBackend;

@Component
public class GetCustomerLoanDetailsRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration()  // Initializing REST Configuration.
		.bindingMode(RestBindingMode.json)
		.port(8081);
		
		rest("/api/customer")
		.post("v1/GetCustomerLoanDetails") // http://localhost:8081/api/customer/v1/GetCustomerLoanDetails
		.type(GetCustomerLoanDetailsRequestType.class)
		.consumes("application/json")
		.produces("application/json")
		.to("direct:getCustomerLoanDetails");
		
		
		onException(Exception.class) // Incase if there is any exception in Route
		.log("inside exception")
		.to("bean:utils?method=onException(${exchange},\"GetLoanDetailsResponse\",\"MW\")")
		.log("Exception"+"${exception}").handled(true)
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500));
		
		
		from("direct:getCustomerLoanDetails").routeId("getCustomerLoanDetails")
		.to("bean:getCustomerLoanDetailsService?method=setGetLoanDetailsRequestIn") // Saving Frontend request locally
		.to("bean:getCustomerLoanDetailsService?method=prepareGetLoanDetailsRequestBackend") // Converting Frontend request to Backend request
		.marshal(new JacksonDataFormat(GetLoanDetailsRequestBackend.class)) // Marshalling Backend request to json
		.log("Request Body - ${body}")
		.choice()
		
			.when().jsonpath("$.GetLoanDetailsRequest.CustomerInfo[?(@.fullname == null || @.fullname == '')]") // Typechecking if fullname is null or empty
				.to("bean:utils?method=prepareFaultNodeStr(\"GetLoanDetailsResponse\",\"INCORRECTVALUE\",\"fullName Field is not correct\",\"\",\"\",\"validationsCust\",${exchange})")
				
			.when().jsonpath("$.GetLoanDetailsRequest.CustomerInfo[?(@.phoneNumber == null || @.phoneNumber == '')]") // Typechecking if phoneNumber is null or empty
				.to("bean:utils?method=prepareFaultNodeStr(\"GetLoanDetailsResponse\",\"INCORRECTVALUE\",\"phoneNumber Field is not correct\",\"\",\"\",\"validationsCust\",${exchange})")
				
			.when().jsonpath("$.GetLoanDetailsRequest.LoanAccountInfo[?(@.accountNumber == null || @.accountNumber == 0)]") // Typechecking if accountNumber is null or empty
				.to("bean:utils?method=prepareFaultNodeStr(\"GetLoanDetailsResponse\",\"INCORRECTVALUE\",\"accountNumber Field is not correct\",\"\",\"\",\"validationsCust\",${exchange})")
			
		.otherwise()	
//			.to("{{loanAPI.host}}{{loanAPI.contextPath}}GetLoanDetails?bridgeEndpoint=true") // http://localhost:8080/api/loan/v1/GetLoanDetails?bridgeEndpoint=true
			.to("https://9b66fe71-04de-4d62-a371-7b362d126391.mock.pstmn.io/api/customer/v1/GetCustomerLoanDetails?bridgeEndpoint=true")
			.log("Response Body - ${body}")
			.choice()
			
			.when().jsonpath("$.GetLoanDetailsResponse.LoanInfo[?(@.accountNumber != null && @.accountNumber != 0)]") // Typechecking if Backend response accountNumber is null or 0
				.unmarshal(new JacksonDataFormat(GetLoanDetailsResponseBackend.class))	// Converting backend response to json	
				.to("bean:getCustomerLoanDetailsService?method=prepareGetCustomerLoanDetailsResponse") // Converting Backend response to Frontend response
				.setHeader("Content-Type", constant("application/json"))
				
			.otherwise()
				.to("bean:utils?method=prepareFaultNodeStr(\"GetLoanDetailsResponse\",\"RECORDNOTFOUND\",\"\",\"\",\"\",\"sysOrAppWithoutBkndError\",${exchange})")
		.end();
	}

}
