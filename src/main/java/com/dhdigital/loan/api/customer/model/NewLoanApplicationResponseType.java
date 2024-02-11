
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "NewLoanApplicationResponse"
})
public class NewLoanApplicationResponseType {

    @JsonProperty("NewLoanApplicationResponse")
    private NewLoanApplicationResponse newLoanApplicationResponse;

    @JsonProperty("NewLoanApplicationResponse")
    public NewLoanApplicationResponse getNewLoanApplicationResponse() {
        return newLoanApplicationResponse;
    }

    @JsonProperty("NewLoanApplicationResponse")
    public void setNewLoanApplicationResponse(NewLoanApplicationResponse newLoanApplicationResponse) {
        this.newLoanApplicationResponse = newLoanApplicationResponse;
    }

	public NewLoanApplicationResponseType() {
		super();
	}

	public NewLoanApplicationResponseType(NewLoanApplicationResponse newLoanApplicationResponse) {
		super();
		this.newLoanApplicationResponse = newLoanApplicationResponse;
	}

	@Override
	public String toString() {
		return "NewCustomerLoanApplicationResponseType [newLoanApplicationResponse=" + newLoanApplicationResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(newLoanApplicationResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewLoanApplicationResponseType other = (NewLoanApplicationResponseType) obj;
		return Objects.equals(newLoanApplicationResponse, other.newLoanApplicationResponse);
	}

}
