
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Applicant"
})
public class PersonalDetails {

    @JsonProperty("Applicant")
    private Applicant applicant;

    @JsonProperty("Applicant")
    public Applicant getApplicant() {
        return applicant;
    }

    @JsonProperty("Applicant")
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

	public PersonalDetails() {
		super();
	}

	public PersonalDetails(Applicant applicant) {
		super();
		this.applicant = applicant;
	}

	@Override
	public String toString() {
		return "PersonalDetails [applicant=" + applicant + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalDetails other = (PersonalDetails) obj;
		return Objects.equals(applicant, other.applicant);
	}


}
