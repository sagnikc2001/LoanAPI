
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fullname",
    "phoneNumber",
    "emailAddress"
})
public class CustomerInfo {

    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("fullname")
    public String getFullname() {
        return fullname;
    }

    @JsonProperty("fullname")
    public void setFullname(String fullname) {
        this.fullname = fullname;
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

	public CustomerInfo() {
		super();
	}

	public CustomerInfo(String fullname, String phoneNumber, String emailAddress) {
		super();
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "CustomerInfo [fullname=" + fullname + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailAddress, fullname, phoneNumber);
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
		return Objects.equals(emailAddress, other.emailAddress) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
    
}
