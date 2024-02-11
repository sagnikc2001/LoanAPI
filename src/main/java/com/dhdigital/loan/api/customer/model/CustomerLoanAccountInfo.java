
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanAccountNumber"
})
public class CustomerLoanAccountInfo {

    @JsonProperty("loanAccountNumber")
    private Integer loanAccountNumber;

    @JsonProperty("loanAccountNumber")
    public Integer getLoanAccountNumber() {
        return loanAccountNumber;
    }

    @JsonProperty("loanAccountNumber")
    public void setLoanAccountNumber(Integer loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
    }

	public CustomerLoanAccountInfo() {
		super();
	}

	public CustomerLoanAccountInfo(Integer loanAccountNumber) {
		super();
		this.loanAccountNumber = loanAccountNumber;
	}

	@Override
	public String toString() {
		return "CustomerLoanAccountInfo [loanAccountNumber=" + loanAccountNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanAccountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerLoanAccountInfo other = (CustomerLoanAccountInfo) obj;
		return Objects.equals(loanAccountNumber, other.loanAccountNumber);
	}

}
