
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanAccountNumber",
    "loanStatus",
    "loanInterestRate",
    "loanAmount",
    "loanTerm",
    "loanType",
    "creditScore"
})
public class CustomerLoanInfo {

    @JsonProperty("loanAccountNumber")
    private Integer loanAccountNumber;
    @JsonProperty("loanStatus")
    private String loanStatus;
    @JsonProperty("loanInterestRate")
    private Integer loanInterestRate;
    @JsonProperty("loanAmount")
    private Integer loanAmount;
    @JsonProperty("loanTerm")
    private Integer loanTerm;
    @JsonProperty("loanType")
    private String loanType;
    @JsonProperty("creditScore")
    private Integer creditScore;

    @JsonProperty("loanAccountNumber")
    public Integer getLoanAccountNumber() {
        return loanAccountNumber;
    }

    @JsonProperty("loanAccountNumber")
    public void setLoanAccountNumber(Integer loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
    }

    @JsonProperty("loanStatus")
    public String getLoanStatus() {
        return loanStatus;
    }

    @JsonProperty("loanStatus")
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
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

	public CustomerLoanInfo() {
		super();
	}

	public CustomerLoanInfo(Integer loanAccountNumber, String loanStatus, Integer loanInterestRate, Integer loanAmount,
			Integer loanTerm, String loanType, Integer creditScore) {
		super();
		this.loanAccountNumber = loanAccountNumber;
		this.loanStatus = loanStatus;
		this.loanInterestRate = loanInterestRate;
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "CustomerLoanInfo [loanAccountNumber=" + loanAccountNumber + ", loanStatus=" + loanStatus
				+ ", loanInterestRate=" + loanInterestRate + ", loanAmount=" + loanAmount + ", loanTerm=" + loanTerm
				+ ", loanType=" + loanType + ", creditScore=" + creditScore + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditScore, loanAccountNumber, loanAmount, loanInterestRate, loanStatus, loanTerm,
				loanType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerLoanInfo other = (CustomerLoanInfo) obj;
		return Objects.equals(creditScore, other.creditScore)
				&& Objects.equals(loanAccountNumber, other.loanAccountNumber)
				&& Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanInterestRate, other.loanInterestRate)
				&& Objects.equals(loanStatus, other.loanStatus) && Objects.equals(loanTerm, other.loanTerm)
				&& Objects.equals(loanType, other.loanType);
	}

}
