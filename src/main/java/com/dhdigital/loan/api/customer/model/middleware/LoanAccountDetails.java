
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountNumber",
    "interestRate",
    "loanAmount"
})
public class LoanAccountDetails {

    @JsonProperty("accountNumber")
    private int accountNumber;
    @JsonProperty("interestRate")
    private Integer interestRate;
    @JsonProperty("loanAmount")
    private Integer loanAmount;

    @JsonProperty("accountNumber")
    public int getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("interestRate")
    public Integer getInterestRate() {
        return interestRate;
    }

    @JsonProperty("interestRate")
    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    @JsonProperty("loanAmount")
    public Integer getLoanAmount() {
        return loanAmount;
    }

    @JsonProperty("loanAmount")
    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

	public LoanAccountDetails() {
		super();
	}


	public LoanAccountDetails(int accountNumber, Integer interestRate, Integer loanAmount) {
		super();
		this.accountNumber = accountNumber;
		this.interestRate = interestRate;
		this.loanAmount = loanAmount;
	}

	@Override
	public String toString() {
		return "LoanAccountDetails [accountNumber=" + accountNumber + ", interestRate=" + interestRate + ", loanAmount="
				+ loanAmount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, interestRate, loanAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanAccountDetails other = (LoanAccountDetails) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(interestRate, other.interestRate)
				&& Objects.equals(loanAmount, other.loanAmount);
	}


}
