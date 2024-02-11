
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PersonalDetails",
    "LoanDetails"
})
public class NewLoanApplicationRequest {

    @JsonProperty("PersonalDetails")
    private PersonalDetails personalDetails;
    @JsonProperty("LoanDetails")
    private LoanDetails loanDetails;

    @JsonProperty("PersonalDetails")
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @JsonProperty("PersonalDetails")
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    @JsonProperty("LoanDetails")
    public LoanDetails getLoanDetails() {
        return loanDetails;
    }

    @JsonProperty("LoanDetails")
    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
    }

	public NewLoanApplicationRequest() {
		super();
	}

	public NewLoanApplicationRequest(PersonalDetails personalDetails, LoanDetails loanDetails) {
		super();
		this.personalDetails = personalDetails;
		this.loanDetails = loanDetails;
	}

	@Override
	public String toString() {
		return "NewLoanApplicationRequest [personalDetails=" + personalDetails + ", loanDetails=" + loanDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanDetails, personalDetails);
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
		return Objects.equals(loanDetails, other.loanDetails) && Objects.equals(personalDetails, other.personalDetails);
	}


}
