
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fullName",
    "Address",
    "phoneNumber",
    "emailAddress",
    "annualSalary"
})
public class Applicant {

    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("Address")
    private Address address;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("annualSalary")
    private Integer annualSalary;

    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("fullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("Address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("emailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("emailAddress")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonProperty("annualSalary")
    public Integer getAnnualSalary() {
        return annualSalary;
    }

    @JsonProperty("annualSalary")
    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

	public Applicant() {
		super();
	}

	public Applicant(String fullName, Address address, String phoneNumber, String emailAddress, Integer annualSalary) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.annualSalary = annualSalary;
	}

	@Override
	public String toString() {
		return "Applicant [fullName=" + fullName + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", annualSalary=" + annualSalary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, annualSalary, emailAddress, fullName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant other = (Applicant) obj;
		return Objects.equals(address, other.address) && Objects.equals(annualSalary, other.annualSalary)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}


}
