package com.dhdigital.loan.api.customer.bean;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhdigital.loan.api.customer.model.CustomerAddress;
import com.dhdigital.loan.api.customer.model.CustomerLoanInfo;
import com.dhdigital.loan.api.customer.model.GetCustomerLoanDetailsRequestType;
import com.dhdigital.loan.api.customer.model.GetCustomerLoanDetailsResponseType;
import com.dhdigital.loan.api.customer.model.GetLoanDetailsRequest;
import com.dhdigital.loan.api.customer.model.middleware.CustomerInfo;
import com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsRequestBackend;
import com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsResponseBackend;
import com.dhdigital.loan.api.customer.model.middleware.LoanAccountInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetCustomerLoanDetailsService {
	
	@Autowired
	ObjectMapper objectMapper;
	
	private GetLoanDetailsRequest getLoanDetailsRequest;
	
	public void setGetLoanDetailsRequestIn(GetCustomerLoanDetailsRequestType getCustomerLoanDetailsRequestType, Exchange ex) throws Exception{
		
		getLoanDetailsRequest = getCustomerLoanDetailsRequestType.getGetLoanDetailsRequest();
	}
	
	public GetLoanDetailsRequestBackend prepareGetLoanDetailsRequestBackend() throws Exception{
		
		GetLoanDetailsRequestBackend oGetLoanDetailsRequestBackend = new GetLoanDetailsRequestBackend();
		com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsRequest oGetLoanDetailsRequest = new com.dhdigital.loan.api.customer.model.middleware.GetLoanDetailsRequest();
		CustomerInfo oCustomerInfo = new CustomerInfo();
		LoanAccountInfo oLoanAccountInfo = new LoanAccountInfo();
		
		oGetLoanDetailsRequestBackend.setGetLoanDetailsRequest(oGetLoanDetailsRequest);
		oGetLoanDetailsRequest.setCustomerInfo(oCustomerInfo);
		oGetLoanDetailsRequest.setLoanAccountInfo(oLoanAccountInfo);
		
		oCustomerInfo.setFullname(getLoanDetailsRequest.getCustomerPersonalInfo().getName());
		oCustomerInfo.setPhoneNumber(getLoanDetailsRequest.getCustomerPersonalInfo().getPhoneNumber());
		oCustomerInfo.setEmailAddress(getLoanDetailsRequest.getCustomerPersonalInfo().getEmailAddress());
		
		oLoanAccountInfo.setAccountNumber(getLoanDetailsRequest.getCustomerLoanAccountInfo().getLoanAccountNumber());
		
		return oGetLoanDetailsRequestBackend;
	}
	
	public GetCustomerLoanDetailsResponseType prepareGetCustomerLoanDetailsResponse(GetLoanDetailsResponseBackend getLoanDetailsResponseBackend, Exchange ex) throws Exception{
		
		GetCustomerLoanDetailsResponseType oGetCustomerLoanDetailsResponseType = new GetCustomerLoanDetailsResponseType();
		com.dhdigital.loan.api.customer.model.GetLoanDetailsResponse oGetLoanDetailsResponse = new com.dhdigital.loan.api.customer.model.GetLoanDetailsResponse();
		com.dhdigital.loan.api.customer.model.CustomerInfo oCustomerInfo = new com.dhdigital.loan.api.customer.model.CustomerInfo();
		CustomerAddress oCustomerAddress = new CustomerAddress();
		CustomerLoanInfo oCustomerLoanInfo = new CustomerLoanInfo();
		
		
		oGetCustomerLoanDetailsResponseType.setGetLoanDetailsResponse(oGetLoanDetailsResponse);
		oGetLoanDetailsResponse.setCustomerInfo(oCustomerInfo);
		oCustomerInfo.setCustomerAddress(oCustomerAddress);
		oGetLoanDetailsResponse.setCustomerLoanInfo(oCustomerLoanInfo);
		
		oCustomerInfo.setName(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getFullName());
		oCustomerInfo.setPhoneNumber(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getPhoneNumber());
		oCustomerInfo.setEmailAddress(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getEmailAddress());
		oCustomerInfo.setAnnualIncome(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAnnualSalary());
		
		oCustomerAddress.setLocation(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAddressInfo().getLocation());
		oCustomerAddress.setCity(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAddressInfo().getCity());
		oCustomerAddress.setState(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAddressInfo().getState());
		oCustomerAddress.setPinCode(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAddressInfo().getPinCode());
		oCustomerAddress.setRegion(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getPersonalInfo().getAddressInfo().getRegion());
		
		oCustomerLoanInfo.setLoanAccountNumber(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getAccountNumber());
		oCustomerLoanInfo.setLoanStatus(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getStatus());
		oCustomerLoanInfo.setLoanInterestRate(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getInterestRate());
		oCustomerLoanInfo.setLoanAmount(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getLoanAmount());
		oCustomerLoanInfo.setLoanTerm(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getLoanTerm());
		oCustomerLoanInfo.setLoanType(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getLoanType());
		oCustomerLoanInfo.setCreditScore(getLoanDetailsResponseBackend.getGetLoanDetailsResponse().getLoanInfo().getCreditScore());
		
		return oGetCustomerLoanDetailsResponseType;
	}

}
