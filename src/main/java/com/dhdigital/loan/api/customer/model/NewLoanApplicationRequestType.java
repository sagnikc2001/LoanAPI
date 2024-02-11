
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "NewLoanApplicationRequest"
})
public class NewLoanApplicationRequestType {

    @JsonProperty("NewLoanApplicationRequest")
    private NewLoanApplicationRequest newLoanApplicationRequest;

    @JsonProperty("NewLoanApplicationRequest")
    public NewLoanApplicationRequest getNewLoanApplicationRequest() {
        return newLoanApplicationRequest;
    }

    @JsonProperty("NewLoanApplicationRequest")
    public void setNewLoanApplicationRequest(NewLoanApplicationRequest newLoanApplicationRequest) {
        this.newLoanApplicationRequest = newLoanApplicationRequest;
    }

	public NewLoanApplicationRequestType() {
		super();
	}

	public NewLoanApplicationRequestType(NewLoanApplicationRequest newLoanApplicationRequest) {
		super();
		this.newLoanApplicationRequest = newLoanApplicationRequest;
	}

	@Override
	public String toString() {
		return "NewLoanApplicationRequestType [newLoanApplicationRequest=" + newLoanApplicationRequest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(newLoanApplicationRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewLoanApplicationRequestType other = (NewLoanApplicationRequestType) obj;
		return Objects.equals(newLoanApplicationRequest, other.newLoanApplicationRequest);
	}

}
