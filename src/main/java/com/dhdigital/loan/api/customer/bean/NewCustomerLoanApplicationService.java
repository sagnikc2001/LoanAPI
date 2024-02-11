package com.dhdigital.loan.api.customer.bean;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhdigital.loan.api.customer.model.ApplicantLoanAccountDetails;
import com.dhdigital.loan.api.customer.model.NewLoanApplicationResponseType;
import com.dhdigital.loan.api.customer.model.NewLoanApplicationRequest;
import com.dhdigital.loan.api.customer.model.NewLoanApplicationRequestType;
import com.dhdigital.loan.api.customer.model.middleware.Address;
import com.dhdigital.loan.api.customer.model.middleware.Applicant;
import com.dhdigital.loan.api.customer.model.middleware.LoanDetails;
import com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationRequestBackend;
import com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationResponseBackend;
import com.dhdigital.loan.api.customer.model.middleware.PersonalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NewCustomerLoanApplicationService {
	
	@Autowired
	ObjectMapper objectMapper;
	
	private NewLoanApplicationRequest newLoanApplicationRequest;
	
	public void setNewLoanApplicationRequestIn(NewLoanApplicationRequestType newLoanApplicationRequestType, Exception ex) throws Exception{
		
		
		newLoanApplicationRequest = newLoanApplicationRequestType.getNewLoanApplicationRequest();
		
	}
	
	public NewLoanApplicationRequestBackend prepareNewLoanApplicationRequestBackend() throws Exception{
		
		NewLoanApplicationRequestBackend oNewLoanApplicationRequestBackend = new NewLoanApplicationRequestBackend();
		com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationRequest oNewLoanApplicationRequest = new com.dhdigital.loan.api.customer.model.middleware.NewLoanApplicationRequest();
		PersonalDetails oPersonalDetails = new PersonalDetails();
		Applicant oApplicant = new Applicant();
		Address oAddress = new Address();
		LoanDetails oLoanDetails = new LoanDetails();
		
		oNewLoanApplicationRequestBackend.setNewLoanApplicationRequest(oNewLoanApplicationRequest);
		oNewLoanApplicationRequest.setPersonalDetails(oPersonalDetails);
		oPersonalDetails.setApplicant(oApplicant);
		oApplicant.setAddress(oAddress);
		oNewLoanApplicationRequest.setLoanDetails(oLoanDetails);
		
		oApplicant.setFullName(newLoanApplicationRequest.getApplicantDetails().getName());
		oApplicant.setPhoneNumber(newLoanApplicationRequest.getApplicantDetails().getPhoneNumber());
		oApplicant.setEmailAddress(newLoanApplicationRequest.getApplicantDetails().getEmailAddress());
		oApplicant.setAnnualSalary(newLoanApplicationRequest.getApplicantDetails().getAnnualIncome());
		
		oAddress.setLocation(newLoanApplicationRequest.getApplicantDetails().getAddress().getLocality());
		oAddress.setCity(newLoanApplicationRequest.getApplicantDetails().getAddress().getCity());
		oAddress.setState(newLoanApplicationRequest.getApplicantDetails().getAddress().getState());
		oAddress.setPinCode(newLoanApplicationRequest.getApplicantDetails().getAddress().getPinCode());
		oAddress.setRegion(newLoanApplicationRequest.getApplicantDetails().getAddress().getRegion());
		
		oLoanDetails.setLoanAmount(newLoanApplicationRequest.getApplicantLoanDetails().getLoanAmount());
		oLoanDetails.setLoanTerm(newLoanApplicationRequest.getApplicantLoanDetails().getLoanTermMonths());
		oLoanDetails.setLoanType(newLoanApplicationRequest.getApplicantLoanDetails().getLoanType());
		oLoanDetails.setCreditScore(newLoanApplicationRequest.getApplicantLoanDetails().getCreditScore());
		
		
		return oNewLoanApplicationRequestBackend;
	}
	
	public NewLoanApplicationResponseType prepareNewCustomerLoanApplicationResponse(NewLoanApplicationResponseBackend newLoanApplicationResponseBackend, Exchange ex) throws Exception{
		
		NewLoanApplicationResponseType oNewCustomerLoanApplicationResponseType = new NewLoanApplicationResponseType();
		com.dhdigital.loan.api.customer.model.NewLoanApplicationResponse oNewLoanApplicationResponse = new com.dhdigital.loan.api.customer.model.NewLoanApplicationResponse();
		ApplicantLoanAccountDetails oApplicantLoanAccountDetails = new ApplicantLoanAccountDetails();
		
		oNewCustomerLoanApplicationResponseType.setNewLoanApplicationResponse(oNewLoanApplicationResponse);
		oNewLoanApplicationResponse.setApplicantLoanAccountDetails(oApplicantLoanAccountDetails);
		
		oNewLoanApplicationResponse.setLoanStatus(newLoanApplicationResponseBackend.getNewLoanApplicationResponse().getStatus());
		oApplicantLoanAccountDetails.setLoanAccountNumber(newLoanApplicationResponseBackend.getNewLoanApplicationResponse().getLoanAccountDetails().getAccountNumber());
		oApplicantLoanAccountDetails.setLoanInterestRate(newLoanApplicationResponseBackend.getNewLoanApplicationResponse().getLoanAccountDetails().getInterestRate());
		oApplicantLoanAccountDetails.setLoanAmount(newLoanApplicationResponseBackend.getNewLoanApplicationResponse().getLoanAccountDetails().getLoanAmount());
		
		return oNewCustomerLoanApplicationResponseType;
	}

}
