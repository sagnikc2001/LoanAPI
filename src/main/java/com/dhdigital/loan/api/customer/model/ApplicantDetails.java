
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "Address",
    "phoneNumber",
    "emailAddress",
    "annualIncome"
})
public class ApplicantDetails {

    @JsonProperty("name")
    private String name;
    @JsonProperty("Address")
    private Address address;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("annualIncome")
    private Integer annualIncome;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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

    @JsonProperty("annualIncome")
    public Integer getAnnualIncome() {
        return annualIncome;
    }

    @JsonProperty("annualIncome")
    public void setAnnualIncome(Integer annualIncome) {
        this.annualIncome = annualIncome;
    }

	public ApplicantDetails() {
		super();
	}

	public ApplicantDetails(String name, Address address, String phoneNumber, String emailAddress,
			Integer annualIncome) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.annualIncome = annualIncome;
	}

	@Override
	public String toString() {
		return "ApplicantDetails [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", annualIncome=" + annualIncome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, annualIncome, emailAddress, name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicantDetails other = (ApplicantDetails) obj;
		return Objects.equals(address, other.address) && Objects.equals(annualIncome, other.annualIncome)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

}
