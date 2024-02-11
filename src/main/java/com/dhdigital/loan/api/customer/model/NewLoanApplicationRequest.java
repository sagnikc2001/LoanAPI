
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ApplicantDetails",
    "ApplicantLoanDetails"
})
public class NewLoanApplicationRequest {

    @JsonProperty("ApplicantDetails")
    private ApplicantDetails applicantDetails;
    @JsonProperty("ApplicantLoanDetails")
    private ApplicantLoanDetails applicantLoanDetails;

    @JsonProperty("ApplicantDetails")
    public ApplicantDetails getApplicantDetails() {
        return applicantDetails;
    }

    @JsonProperty("ApplicantDetails")
    public void setApplicantDetails(ApplicantDetails applicantDetails) {
        this.applicantDetails = applicantDetails;
    }

    @JsonProperty("ApplicantLoanDetails")
    public ApplicantLoanDetails getApplicantLoanDetails() {
        return applicantLoanDetails;
    }

    @JsonProperty("ApplicantLoanDetails")
    public void setApplicantLoanDetails(ApplicantLoanDetails applicantLoanDetails) {
        this.applicantLoanDetails = applicantLoanDetails;
    }

	public NewLoanApplicationRequest() {
		super();
	}

	public NewLoanApplicationRequest(ApplicantDetails applicantDetails, ApplicantLoanDetails applicantLoanDetails) {
		super();
		this.applicantDetails = applicantDetails;
		this.applicantLoanDetails = applicantLoanDetails;
	}

	@Override
	public String toString() {
		return "NewLoanApplicationRequest [applicantDetails=" + applicantDetails + ", applicantLoanDetails="
				+ applicantLoanDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicantDetails, applicantLoanDetails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewLoanApplicationRequest other = (NewLoanApplicationRequest) obj;
		return Objects.equals(applicantDetails, other.applicantDetails)
				&& Objects.equals(applicantLoanDetails, other.applicantLoanDetails);

	}
}	
