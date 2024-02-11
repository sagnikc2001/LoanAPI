
package com.dhdigital.loan.api.customer.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "CustomerAddress",
    "phoneNumber",
    "emailAddress",
    "annualIncome"
})
public class CustomerInfo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("CustomerAddress")
    private CustomerAddress customerAddress;
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

    @JsonProperty("CustomerAddress")
    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    @JsonProperty("CustomerAddress")
    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
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

	public CustomerInfo() {
		super();
	}

	public CustomerInfo(String name, CustomerAddress customerAddress, String phoneNumber, String emailAddress,
			Integer annualIncome) {
		super();
		this.name = name;
		this.customerAddress = customerAddress;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.annualIncome = annualIncome;
	}

	@Override
	public String toString() {
		return "CustomerInfo [name=" + name + ", customerAddress=" + customerAddress + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", annualIncome=" + annualIncome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(annualIncome, customerAddress, emailAddress, name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInfo other = (CustomerInfo) obj;
		return Objects.equals(annualIncome, other.annualIncome)
				&& Objects.equals(customerAddress, other.customerAddress)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

}
