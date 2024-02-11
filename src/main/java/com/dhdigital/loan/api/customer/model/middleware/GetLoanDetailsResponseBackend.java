
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GetLoanDetailsResponse"
})
public class GetLoanDetailsResponseBackend {

    @JsonProperty("GetLoanDetailsResponse")
    private GetLoanDetailsResponse getLoanDetailsResponse;

    @JsonProperty("GetLoanDetailsResponse")
    public GetLoanDetailsResponse getGetLoanDetailsResponse() {
        return getLoanDetailsResponse;
    }

    @JsonProperty("GetLoanDetailsResponse")
    public void setGetLoanDetailsResponse(GetLoanDetailsResponse getLoanDetailsResponse) {
        this.getLoanDetailsResponse = getLoanDetailsResponse;
    }

	public GetLoanDetailsResponseBackend() {
		super();
	}

	public GetLoanDetailsResponseBackend(GetLoanDetailsResponse getLoanDetailsResponse) {
		super();
		this.getLoanDetailsResponse = getLoanDetailsResponse;
	}

	@Override
	public String toString() {
		return "GetLoanDetailsResponseBackend [getLoanDetailsResponse=" + getLoanDetailsResponse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLoanDetailsResponse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetLoanDetailsResponseBackend other = (GetLoanDetailsResponseBackend) obj;
		return Objects.equals(getLoanDetailsResponse, other.getLoanDetailsResponse);
	}

}
