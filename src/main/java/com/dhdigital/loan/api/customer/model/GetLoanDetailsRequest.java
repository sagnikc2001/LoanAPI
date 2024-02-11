
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CustomerPersonalInfo",
    "CustomerLoanAccountInfo"
})
public class GetLoanDetailsRequest {

    @JsonProperty("CustomerPersonalInfo")
    private CustomerPersonalInfo customerPersonalInfo;
    @JsonProperty("CustomerLoanAccountInfo")
    private CustomerLoanAccountInfo customerLoanAccountInfo;

    @JsonProperty("CustomerPersonalInfo")
    public CustomerPersonalInfo getCustomerPersonalInfo() {
        return customerPersonalInfo;
    }

    @JsonProperty("CustomerPersonalInfo")
    public void setCustomerPersonalInfo(CustomerPersonalInfo customerPersonalInfo) {
        this.customerPersonalInfo = customerPersonalInfo;
    }

    @JsonProperty("CustomerLoanAccountInfo")
    public CustomerLoanAccountInfo getCustomerLoanAccountInfo() {
        return customerLoanAccountInfo;
    }

    @JsonProperty("CustomerLoanAccountInfo")
    public void setCustomerLoanAccountInfo(CustomerLoanAccountInfo customerLoanAccountInfo) {
        this.customerLoanAccountInfo = customerLoanAccountInfo;
    }

	public GetLoanDetailsRequest() {
		super();
	}

	public GetLoanDetailsRequest(CustomerPersonalInfo customerPersonalInfo,
			CustomerLoanAccountInfo customerLoanAccountInfo) {
		super();
		this.customerPersonalInfo = customerPersonalInfo;
		this.customerLoanAccountInfo = customerLoanAccountInfo;
	}

	@Override
	public String toString() {
		return "GetLoanDetailsRequest [customerPersonalInfo=" + customerPersonalInfo + ", customerLoanAccountInfo="
				+ customerLoanAccountInfo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerLoanAccountInfo, customerPersonalInfo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetLoanDetailsRequest other = (GetLoanDetailsRequest) obj;
		return Objects.equals(customerLoanAccountInfo, other.customerLoanAccountInfo)
				&& Objects.equals(customerPersonalInfo, other.customerPersonalInfo);
	}

}
