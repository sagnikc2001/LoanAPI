
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PersonalInfo",
    "LoanInfo"
})
public class GetLoanDetailsResponse {

    @JsonProperty("PersonalInfo")
    private PersonalInfo personalInfo;
    @JsonProperty("LoanInfo")
    private LoanInfo loanInfo;

    @JsonProperty("PersonalInfo")
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    @JsonProperty("PersonalInfo")
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    @JsonProperty("LoanInfo")
    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    @JsonProperty("LoanInfo")
    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

	public GetLoanDetailsResponse() {
		super();
	}

	public GetLoanDetailsResponse(PersonalInfo personalInfo, LoanInfo loanInfo) {
		super();
		this.personalInfo = personalInfo;
		this.loanInfo = loanInfo;
	}

	@Override
	public String toString() {
		return "GetLoanDetailsResponse [personalInfo=" + personalInfo + ", loanInfo=" + loanInfo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanInfo, personalInfo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetLoanDetailsResponse other = (GetLoanDetailsResponse) obj;
		return Objects.equals(loanInfo, other.loanInfo) && Objects.equals(personalInfo, other.personalInfo);
	}

}
