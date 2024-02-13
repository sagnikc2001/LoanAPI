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

//@MockEndpointsAndSkip("{{loanAPI.newLoanApplicationHost}}{{loanAPI.newLoanApplicationContextPath}}NewLoanApplication?bridgeEndpoint=true|{{configStoreConnector.host}}{{configStoreConnector.contextPath}}")
@MockEndpointsAndSkip("https://10fdf6f7-766e-44fe-b914-689f040264e9.mock.pstmn.io/api/loan/v1/NewLoanApplication.*|http:localhost:8082/api/connector/configstore.*")

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

	@EndpointInject("mock://{{loanAPI.newLoanApplicationHost}}{{loanAPI.newLoanApplicationContextPath}}NewLoanApplication")
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
		
		System.out.println("FaultRespone of Fault: "+faultResponse);
		
		Assertions.assertTrue(faultResponse.contains("Record not found"));
	}
	
	@Test
	public void newCustomerLoanApplication_personalDetails_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_personalDetails_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
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
		
		Assertions.assertTrue(faultResponse.contains("PersonalDetails fields not correct"));
	}
	
	@Test
	public void newCustomerLoanApplication_loanDetails_Validation() throws Exception {

		String newCustomerLoanApplicationRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/NewCustomerLoanApplication/request/NewCustomerLoanApplicationRequest_loanDetails_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "newCustomerLoanApplication", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:newCustomerLoanApplication");
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
		
		Assertions.assertTrue(faultResponse.contains("LoanDetails fields not correct"));
	}
}