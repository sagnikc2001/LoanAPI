
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CustomerInfo",
    "LoanAccountInfo"
})
public class GetLoanDetailsRequest {

    @JsonProperty("CustomerInfo")
    private CustomerInfo customerInfo;
    @JsonProperty("LoanAccountInfo")
    private LoanAccountInfo loanAccountInfo;

    @JsonProperty("CustomerInfo")
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    @JsonProperty("CustomerInfo")
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @JsonProperty("LoanAccountInfo")
    public LoanAccountInfo getLoanAccountInfo() {
        return loanAccountInfo;
    }

    @JsonProperty("LoanAccountInfo")
    public void setLoanAccountInfo(LoanAccountInfo loanAccountInfo) {
        this.loanAccountInfo = loanAccountInfo;
    }

	public GetLoanDetailsRequest() {
		super();
	}

	public GetLoanDetailsRequest(CustomerInfo customerInfo, LoanAccountInfo loanAccountInfo) {
		super();
		this.customerInfo = customerInfo;
		this.loanAccountInfo = loanAccountInfo;
	}

	@Override
	public String toString() {
		return "GetLoanDetailsRequest [customerInfo=" + customerInfo + ", loanAccountInfo=" + loanAccountInfo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerInfo, loanAccountInfo);
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
		return Objects.equals(customerInfo, other.customerInfo)
				&& Objects.equals(loanAccountInfo, other.loanAccountInfo);
	}
    
}
