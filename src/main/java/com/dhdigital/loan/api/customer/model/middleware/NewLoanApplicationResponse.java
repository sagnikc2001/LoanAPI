
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "LoanAccountDetails"
})
public class NewLoanApplicationResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("LoanAccountDetails")
    private LoanAccountDetails loanAccountDetails;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("LoanAccountDetails")
    public LoanAccountDetails getLoanAccountDetails() {
        return loanAccountDetails;
    }

    @JsonProperty("LoanAccountDetails")
    public void setLoanAccountDetails(LoanAccountDetails loanAccountDetails) {
        this.loanAccountDetails = loanAccountDetails;
    }

	public NewLoanApplicationResponse() {
		super();
	}

	public NewLoanApplicationResponse(String status, LoanAccountDetails loanAccountDetails) {
		super();
		this.status = status;
		this.loanAccountDetails = loanAccountDetails;
	}

	@Override
	public String toString() {
		return "NewLoanApplicationResponse [status=" + status + ", loanAccountDetails=" + loanAccountDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loanAccountDetails, status);
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
		return Objects.equals(loanAccountDetails, other.loanAccountDetails) && Objects.equals(status, other.status);
	}


}
