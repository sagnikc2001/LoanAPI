
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loanStatus",
    "ApplicantLoanAccountDetails"
})
public class NewLoanApplicationResponse {

    @JsonProperty("loanStatus")
    private String loanStatus;
    @JsonProperty("ApplicantLoanAccountDetails")
    private ApplicantLoanAccountDetails applicantLoanAccountDetails;

    @JsonProperty("loanStatus")
    public String getLoanStatus() {
        return loanStatus;
    }

    @JsonProperty("loanStatus")
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @JsonProperty("ApplicantLoanAccountDetails")
    public ApplicantLoanAccountDetails getApplicantLoanAccountDetails() {
        return applicantLoanAccountDetails;
    }

    @JsonProperty("ApplicantLoanAccountDetails")
    public void setApplicantLoanAccountDetails(ApplicantLoanAccountDetails applicantLoanAccountDetails) {
        this.applicantLoanAccountDetails = applicantLoanAccountDetails;
    }

	public NewLoanApplicationResponse() {
		super();
	}

	public NewLoanApplicationResponse(String loanStatus, ApplicantLoanAccountDetails applicantLoanAccountDetails) {
		super();
		this.loanStatus = loanStatus;
		this.applicantLoanAccountDetails = applicantLoanAccountDetails;
	}

	@Override
	public String toString() {
		return "NewLoanApplicationResponse [loanStatus=" + loanStatus + ", applicantLoanAccountDetails="
				+ applicantLoanAccountDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicantLoanAccountDetails, loanStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewLoanApplicationResponse other = (NewLoanApplicationResponse) obj;
		return Objects.equals(applicantLoanAccountDetails, other.applicantLoanAccountDetails)
				&& Objects.equals(loanStatus, other.loanStatus);
	}

}
