
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanAmount",
    "loanTermMonths",
    "loanType",
    "creditScore"
})
public class ApplicantLoanDetails {

    @JsonProperty("loanAmount")
    private Integer loanAmount;
    @JsonProperty("loanTermMonths")
    private Integer loanTermMonths;
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

    @JsonProperty("loanTermMonths")
    public Integer getLoanTermMonths() {
        return loanTermMonths;
    }

    @JsonProperty("loanTermMonths")
    public void setLoanTermMonths(Integer loanTermMonths) {
        this.loanTermMonths = loanTermMonths;
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

	public ApplicantLoanDetails() {
		super();
	}

	public ApplicantLoanDetails(Integer loanAmount, Integer loanTermMonths, String loanType, Integer creditScore) {
		super();
		this.loanAmount = loanAmount;
		this.loanTermMonths = loanTermMonths;
		this.loanType = loanType;
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "ApplicantLoanDetails [loanAmount=" + loanAmount + ", loanTermMonths=" + loanTermMonths + ", loanType="
				+ loanType + ", creditScore=" + creditScore + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditScore, loanAmount, loanTermMonths, loanType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicantLoanDetails other = (ApplicantLoanDetails) obj;
		return Objects.equals(creditScore, other.creditScore) && Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanTermMonths, other.loanTermMonths) && Objects.equals(loanType, other.loanType);
	}

}
