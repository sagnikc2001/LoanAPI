
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountNumber",
    "status",
    "interestRate",
    "loanAmount",
    "loanTerm",
    "loanType",
    "creditScore"
})
public class LoanInfo {

    @JsonProperty("accountNumber")
    private Integer accountNumber;
    @JsonProperty("status")
    private String status;
    @JsonProperty("interestRate")
    private Integer interestRate;
    @JsonProperty("loanAmount")
    private Integer loanAmount;
    @JsonProperty("loanTerm")
    private Integer loanTerm;
    @JsonProperty("loanType")
    private String loanType;
    @JsonProperty("creditScore")
    private Integer creditScore;

    @JsonProperty("accountNumber")
    public Integer getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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

    @JsonProperty("loanTerm")
    public Integer getLoanTerm() {
        return loanTerm;
    }

    @JsonProperty("loanTerm")
    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    @JsonProperty("loanType")
    public String getLoanType() {
        return loanType;
    }

    @JsonProperty("loanType")
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @JsonProperty("creditScore")
    public Integer getCreditScore() {
        return creditScore;
    }

    @JsonProperty("creditScore")
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

	public LoanInfo() {
		super();
	}

	public LoanInfo(Integer accountNumber, String status, Integer interestRate, Integer loanAmount, Integer loanTerm,
			String loanType, Integer creditScore) {
		super();
		this.accountNumber = accountNumber;
		this.status = status;
		this.interestRate = interestRate;
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "LoanInfo [accountNumber=" + accountNumber + ", status=" + status + ", interestRate=" + interestRate
				+ ", loanAmount=" + loanAmount + ", loanTerm=" + loanTerm + ", loanType=" + loanType + ", creditScore="
				+ creditScore + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, creditScore, interestRate, loanAmount, loanTerm, loanType, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanInfo other = (LoanInfo) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(creditScore, other.creditScore)
				&& Objects.equals(interestRate, other.interestRate) && Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanTerm, other.loanTerm) && Objects.equals(loanType, other.loanType)
				&& Objects.equals(status, other.status);
	}

}
