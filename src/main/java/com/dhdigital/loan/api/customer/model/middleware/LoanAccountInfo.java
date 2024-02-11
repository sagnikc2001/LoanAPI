
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountNumber"
})
public class LoanAccountInfo {

    @JsonProperty("accountNumber")
    private int accountNumber;

    @JsonProperty("accountNumber")
    public int getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

	public LoanAccountInfo() {
		super();
	}

	public LoanAccountInfo(int accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "LoanAccountInfo [accountNumber=" + accountNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanAccountInfo other = (LoanAccountInfo) obj;
		return Objects.equals(accountNumber, other.accountNumber);
	}
    
}
