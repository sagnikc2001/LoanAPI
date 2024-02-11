
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanAccountNumber",
    "loanInterestRate",
    "loanAmount"
})
public class ApplicantLoanAccountDetails {

    @JsonProperty("loanAccountNumber")
    private Integer loanAccountNumber;
    @JsonProperty("loanInterestRate")
    private Integer loanInterestRate;
    @JsonProperty("loanAmount")
    private Integer loanAmount;

    @JsonProperty("loanAccountNumber")
    public Integer getLoanAccountNumber() {
        return loanAccountNumber;
    }

    @JsonProperty("loanAccountNumber")
    public void setLoanAccountNumber(Integer loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
    }

    @JsonProperty("loanInterestRate")
    public Integer getLoanInterestRate() {
        return loanInterestRate;
    }

    @JsonProperty("loanInterestRate")
    public void setLoanInterestRate(Integer loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    @JsonProperty("loanAmount")
    public Integer getLoanAmount() {
        return loanAmount;
    }

    @JsonProperty("loanAmount")
    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

	public ApplicantLoanAccountDetails() {
		super();
	}

	public ApplicantLoanAccountDetails(Integer loanAccountNumber, Integer loanInterestRate, Integer loanAmount) {
		super();
		this.loanAccountNumber = loanAccountNumber;
		this.loanInterestRate = loanInterestRate;
		this.loanAmount = loanAmount;
	}

	@Override
	public String toString() {
		return "ApplicantLoanAccountDetails [loanAccountNumber=" + loanAccountNumber + ", loanInterestRate="
				+ loanInterestRate + ", loanAmount=" + loanAmount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanAccountNumber, loanAmount, loanInterestRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicantLoanAccountDetails other = (ApplicantLoanAccountDetails) obj;
		return Objects.equals(loanAccountNumber, other.loanAccountNumber)
				&& Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanInterestRate, other.loanInterestRate);
	}

}
