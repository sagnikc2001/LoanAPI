
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetLoanDetailsRequest"
})
public class GetCustomerLoanDetailsRequestType {

    @JsonProperty("GetLoanDetailsRequest")
    private GetLoanDetailsRequest getLoanDetailsRequest;

    @JsonProperty("GetLoanDetailsRequest")
    public GetLoanDetailsRequest getGetLoanDetailsRequest() {
        return getLoanDetailsRequest;
    }

    @JsonProperty("GetLoanDetailsRequest")
    public void setGetLoanDetailsRequest(GetLoanDetailsRequest getLoanDetailsRequest) {
        this.getLoanDetailsRequest = getLoanDetailsRequest;
    }

	public GetCustomerLoanDetailsRequestType() {
		super();
	}

	public GetCustomerLoanDetailsRequestType(GetLoanDetailsRequest getLoanDetailsRequest) {
		super();
		this.getLoanDetailsRequest = getLoanDetailsRequest;
	}

	@Override
	public String toString() {
		return "GetCustomerLoanDetailsRequestType [getLoanDetailsRequest=" + getLoanDetailsRequest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLoanDetailsRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetCustomerLoanDetailsRequestType other = (GetCustomerLoanDetailsRequestType) obj;
		return Objects.equals(getLoanDetailsRequest, other.getLoanDetailsRequest);
	}

}
