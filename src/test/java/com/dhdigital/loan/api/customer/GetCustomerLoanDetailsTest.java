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

import com.dhdigital.loan.api.customer.model.GetCustomerLoanDetailsRequestType;
import com.dhdigital.loan.api.customer.model.GetCustomerLoanDetailsResponseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@CamelSpringBootTest
@SpringBootApplication
@WebAppConfiguration

//@MockEndpointsAndSkip("{{loanAPI.getCustomerLoanDetailsHost}}{{loanAPI.getCustomerLoanDetailsContextPath}}GetCustomerLoanDetails.*|{{configStoreConnector.host}}{{configStoreConnector.contextPath}}.*")
@MockEndpointsAndSkip("https://9b66fe71-04de-4d62-a371-7b362d126391.mock.pstmn.io/api/customer/v1/GetCustomerLoanDetails.*|http:localhost:8082/api/connector/configstore.*")

@UseAdviceWith
@ImportResource({ "classpath:spring/camel-context.xml" })
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("com.dhdigital.loan.api.customer.*")
public class GetCustomerLoanDetailsTest {

	@Autowired
	CamelContext camelContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	ApplicationContext applicationContext;

	@EndpointInject("mock://{{loanAPI.getCustomerLoanDetailsHost}}{{loanAPI.getCustomerLoanDetailsContextPath}}GetCustomerLoanDetails")
	private MockEndpoint cdmockEndpoint;

//    @EndpointInject("mock://https:9b66fe71-04de-4d62-a371-7b362d126391.mock.pstmn.io/api/customer/v1/GetCustomerLoanDetails")
//    private MockEndpoint cdmockEndpoint;

	@EndpointInject("mock://{{configStoreConnector.host}}{{configStoreConnector.contextPath}}")
	private MockEndpoint cdmockEndpoint2;

	@Test
	public void getCustomerLoanDetails_Success() throws Exception {

		String getCustomerLoanDetailsRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/GetCustomerLoanDetails/request/GetCustomerLoanDetailsRequestType.json"),
				Charsets.UTF_8);

		String getLoanDetailsResponseBknd = Resources.toString(
				Resources.getResource(
						"mock/backend/GetCustomerLoanDetails/response/GetCustomerLoanDetailsResponseBknd.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "getCustomerLoanDetails", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:getCustomerLoanDetails");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLoanDetailsResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerLoanDetailsRequestType oGetCustomerLoanDetailsRequestType = objectMapper
				.readValue(getCustomerLoanDetailsRequest, GetCustomerLoanDetailsRequestType.class);

		GetCustomerLoanDetailsResponseType oGetCustomerLoanDetailsResponseType = producerTemplate.requestBody(
				"direct:getCustomerLoanDetails", oGetCustomerLoanDetailsRequestType,
				GetCustomerLoanDetailsResponseType.class);

		System.out.println("Response:" + oGetCustomerLoanDetailsResponseType.getGetLoanDetailsResponse()
				.getCustomerLoanInfo().getLoanAccountNumber().toString());

		Assertions.assertTrue(null != oGetCustomerLoanDetailsResponseType.getGetLoanDetailsResponse()
				.getCustomerLoanInfo().getLoanAccountNumber());

	}

	@Test
	public void getCustomerLoanDetails_Fault() throws Exception {

		String getCustomerLoanDetailsRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/GetCustomerLoanDetails/request/GetCustomerLoanDetailsRequestType.json"),
				Charsets.UTF_8);

		String getLoanDetailsResponseBknd = Resources.toString(Resources.getResource(
				"mock/backend/GetCustomerLoanDetails/response/GetCustomerLoanDetailsResponseBknd_Response_Fault.json"),
				Charsets.UTF_8);

		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);

		AdviceWith.adviceWith(camelContext, "getCustomerLoanDetails", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:getCustomerLoanDetails");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLoanDetailsResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();

		GetCustomerLoanDetailsRequestType oGetCustomerLoanDetailsRequestType = objectMapper
				.readValue(getCustomerLoanDetailsRequest, GetCustomerLoanDetailsRequestType.class);

		String faultResponse = producerTemplate.requestBody("direct:getCustomerLoanDetails",
				oGetCustomerLoanDetailsRequestType, String.class);
		
		System.out.println(faultResponse);

		Assertions.assertTrue(faultResponse.contains("Record not found"));
	}

	@Test
	public void getCustomerLoanDetails_fullname_Validation() throws Exception {
		
		String getCustomerLoanDetailsRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/GetCustomerLoanDetails/request/GetCustomerLoanDetailsRequestType_fullName_Fault.json"),
				Charsets.UTF_8);
		
		String getLoanDetailsResponseBknd = Resources.toString(Resources.getResource(
				"mock/backend/GetCustomerLoanDetails/response/GetCustomerLoanDetailsResponseBknd_Response_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);
		
		AdviceWith.adviceWith(camelContext, "getCustomerLoanDetails", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:getCustomerLoanDetails");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLoanDetailsResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		GetCustomerLoanDetailsRequestType oGetCustomerLoanDetailsRequestType = objectMapper
				.readValue(getCustomerLoanDetailsRequest, GetCustomerLoanDetailsRequestType.class);
		
		String faultResponse = producerTemplate.requestBody("direct:getCustomerLoanDetails",
				oGetCustomerLoanDetailsRequestType, String.class);
				
		Assertions.assertTrue(faultResponse.contains("fullName Field is not correct"));
	}
	
	@Test
	public void getCustomerLoanDetails_phoneNumber_Validation() throws Exception {
		
		String getCustomerLoanDetailsRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/GetCustomerLoanDetails/request/GetCustomerLoanDetailsRequestType_phoneNumber_Fault.json"),
				Charsets.UTF_8);
		
		String getLoanDetailsResponseBknd = Resources.toString(Resources.getResource(
				"mock/backend/GetCustomerLoanDetails/response/GetCustomerLoanDetailsResponseBknd_Response_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);
		
		AdviceWith.adviceWith(camelContext, "getCustomerLoanDetails", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:getCustomerLoanDetails");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLoanDetailsResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		GetCustomerLoanDetailsRequestType oGetCustomerLoanDetailsRequestType = objectMapper
				.readValue(getCustomerLoanDetailsRequest, GetCustomerLoanDetailsRequestType.class);
		
		String faultResponse = producerTemplate.requestBody("direct:getCustomerLoanDetails",
				oGetCustomerLoanDetailsRequestType, String.class);
				
		Assertions.assertTrue(faultResponse.contains("phoneNumber Field is not correct"));
	}
	
	@Test
	public void getCustomerLoanDetails_accountNumber_Validation() throws Exception {
		
		String getCustomerLoanDetailsRequest = Resources.toString(
				Resources.getResource(
						"mock/frontend/GetCustomerLoanDetails/request/GetCustomerLoanDetailsRequestType_accountNumber_Fault.json"),
				Charsets.UTF_8);
		
		String getLoanDetailsResponseBknd = Resources.toString(Resources.getResource(
				"mock/backend/GetCustomerLoanDetails/response/GetCustomerLoanDetailsResponseBknd_Response_Fault.json"),
				Charsets.UTF_8);
		
		String configstoreResponse = Resources.toString(
				Resources.getResource("mock/configStore/ConfigStoreResponse_Errors_ApplicationErrors.json"),
				Charsets.UTF_8);
		
		AdviceWith.adviceWith(camelContext, "getCustomerLoanDetails", routeBuilder -> {
			routeBuilder.replaceFromWith("direct:getCustomerLoanDetails");
		});

		cdmockEndpoint.expectedMessageCount(1);
		cdmockEndpoint.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(getLoanDetailsResponseBknd);
			}
		});

		cdmockEndpoint2.expectedMessageCount(1);
		cdmockEndpoint2.whenAnyExchangeReceived(new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getMessage().setBody(configstoreResponse);
			}
		});

		camelContext.start();
		
		GetCustomerLoanDetailsRequestType oGetCustomerLoanDetailsRequestType = objectMapper
				.readValue(getCustomerLoanDetailsRequest, GetCustomerLoanDetailsRequestType.class);
		
		String faultResponse = producerTemplate.requestBody("direct:getCustomerLoanDetails",
				oGetCustomerLoanDetailsRequestType, String.class);
				
		Assertions.assertTrue(faultResponse.contains("accountNumber Field is not correct"));
	}
}
