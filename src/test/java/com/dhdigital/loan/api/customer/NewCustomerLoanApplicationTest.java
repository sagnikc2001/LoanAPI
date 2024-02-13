package com.dhdigital.loan.api.customer;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpointsAndSkip;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dhdigital.loan.api.customer.model.NewLoanApplicationRequestType;
import com.dhdigital.loan.api.customer.model.NewLoanApplicationResponseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

@MockEndpointsAndSkip("http://localhost:8080/api/loan/v1/NewLoanApplication.*|http://localhost:8082/api/connector/configstore.*")
//@MockEndpointsAndSkip("https://10fdf6f7-766e-44fe-b914-689f040264e9.mock.pstmn.io/api/loan/v1/NewLoanApplication.*|http://localhost:8082/api/connector/configstore.*")

@UseAdviceWith
@ImportResource({ "classpath:spring/camel-context.xml" })
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.dhdigital.loan.api.customer.*")
public class NewCustomerLoanApplicationTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	ApplicationContext applicationContext;

	@EndpointInject("mock://{{loanAPI.host}}{{loanAPI.contextPath}}NewLoanApplication")
	private MockEndpoint cdmockEndpoint;

//	@EndpointInject("mock://https:10fdf6f7-766e-44fe-b914-689f040264e9.mock.pstmn.io/api/loan/v1/NewLoanApplication")
//	private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://{{configStoreConnector.host}}{{configStoreConnector.contextPath}}")
	private MockEndpoint cdmockEndpoint2;

	@Test
	public void newCustomerLoanApplication_Success() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest.json"),
				Charsets.UTF_8);

		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);

		NewLoanApplicationResponseType oNewLoanApplicationResponseType = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				NewLoanApplicationResponseType.class);

		System.out.println("Response: "
				+ oNewLoanApplicationResponseType.getNewLoanApplicationResponse().getLoanStatus().toString());

		Assertions.assertTrue(
				null != oNewLoanApplicationResponseType.getNewLoanApplicationResponse().getLoanStatus().toString());
	}

	@Test
	public void newCustomerLoanApplication_Fault() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("Record not found"));
	}
	
	@Test
	public void newCustomerLoanApplication_fullname_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_fullName_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("fullName either null or empty"));
	}
	
	@Test
	public void newCustomerLoanApplication_phoneNumber_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_phoneNumber_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("phoneNumber either null or empty"));
	}
	
	@Test
	public void newCustomerLoanApplication_annualSalary_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_annualSalary_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("annualSalary either null or 0"));
	}
	
	@Test
	public void newCustomerLoanApplication_loanAmount_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_loanAmount_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("loanAmount either null or 0"));
	}
	
	@Test
	public void newCustomerLoanApplication_loanType_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_loanType_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("loanType either null or empty"));
	}
	
	@Test
	public void newCustomerLoanApplication_creditScore_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_creditScore_Fault.json"),
				Charsets.UTF_8);
		
		String newCustomerLoanApplicationResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/NewCustomerLoanApplication/response/NewCustomerLoanApplicationResponseBknd_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(newCustomerLoanApplicationResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		NewLoanApplicationRequestType oNewLoanApplicationRequestType = objectMapper
				.readValue(newCustomerLoanApplicationRequest, NewLoanApplicationRequestType.class);
		
		String faultResponse = producerTemplate.requestBody(
				"direct:newCustomerLoanApplication", oNewLoanApplicationRequestType,
				String.class);
		
		Assertions.assertTrue(faultResponse.contains("creditScore either null or 0"));
	}
}