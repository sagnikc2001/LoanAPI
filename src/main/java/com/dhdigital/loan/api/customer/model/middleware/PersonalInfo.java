
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fullName",
    "AddressInfo",
    "phoneNumber",
    "emailAddress",
    "annualSalary"
})
public class PersonalInfo {

    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("AddressInfo")
    private AddressInfo addressInfo;
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

    @JsonProperty("AddressInfo")
    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    @JsonProperty("AddressInfo")
    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
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

	public PersonalInfo() {
		super();
	}

	public PersonalInfo(String fullName, AddressInfo addressInfo, String phoneNumber, String emailAddress,
			Integer annualSalary) {
		super();
		this.fullName = fullName;
		this.addressInfo = addressInfo;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.annualSalary = annualSalary;
	}

	@Override
	public String toString() {
		return "PersonalInfo [fullName=" + fullName + ", addressInfo=" + addressInfo + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", annualSalary=" + annualSalary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressInfo, annualSalary, emailAddress, fullName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalInfo other = (PersonalInfo) obj;
		return Objects.equals(addressInfo, other.addressInfo) && Objects.equals(annualSalary, other.annualSalary)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

}
