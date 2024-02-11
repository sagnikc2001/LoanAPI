
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanAmount",
    "loanTerm",
    "loanType",
    "creditScore"
})
public class LoanDetails {

    @JsonProperty("loanAmount")
    private Integer loanAmount;
    @JsonProperty("loanTerm")
    private Integer loanTerm;
    @JsonProperty("loanType")
    private String loanType;
    @JsonProperty("creditScore")
    private Integer creditScore;

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

	public LoanDetails() {
		super();
	}

	public LoanDetails(Integer loanAmount, Integer loanTerm, String loanType, Integer creditScore) {
		super();
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "LoanDetails [loanAmount=" + loanAmount + ", loanTerm=" + loanTerm + ", loanType=" + loanType
				+ ", creditScore=" + creditScore + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditScore, loanAmount, loanTerm, loanType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanDetails other = (LoanDetails) obj;
		return Objects.equals(creditScore, other.creditScore) && Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanTerm, other.loanTerm) && Objects.equals(loanType, other.loanType);
	}


}
