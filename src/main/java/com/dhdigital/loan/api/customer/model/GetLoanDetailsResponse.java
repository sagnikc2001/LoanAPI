
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CustomerInfo",
    "CustomerLoanInfo"
})
public class GetLoanDetailsResponse {

    @JsonProperty("CustomerInfo")
    private CustomerInfo customerInfo;
    @JsonProperty("CustomerLoanInfo")
    private CustomerLoanInfo customerLoanInfo;

    @JsonProperty("CustomerInfo")
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    @JsonProperty("CustomerInfo")
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @JsonProperty("CustomerLoanInfo")
    public CustomerLoanInfo getCustomerLoanInfo() {
        return customerLoanInfo;
    }

    @JsonProperty("CustomerLoanInfo")
    public void setCustomerLoanInfo(CustomerLoanInfo customerLoanInfo) {
        this.customerLoanInfo = customerLoanInfo;
    }

	public GetLoanDetailsResponse() {
		super();
	}

	public GetLoanDetailsResponse(CustomerInfo customerInfo, CustomerLoanInfo customerLoanInfo) {
		super();
		this.customerInfo = customerInfo;
		this.customerLoanInfo = customerLoanInfo;
	}

	@Override
	public String toString() {
		return "GetLoanDetailsResponse [customerInfo=" + customerInfo + ", customerLoanInfo=" + customerLoanInfo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerInfo, customerLoanInfo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetLoanDetailsResponse other = (GetLoanDetailsResponse) obj;
		return Objects.equals(customerInfo, other.customerInfo)
				&& Objects.equals(customerLoanInfo, other.customerLoanInfo);
	}

}
