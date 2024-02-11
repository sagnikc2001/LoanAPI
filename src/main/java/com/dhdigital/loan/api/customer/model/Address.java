
package com.dhdigital.loan.api.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "locality",
    "city",
    "state",
    "pinCode",
    "region"
})
public class Address {

    @JsonProperty("locality")
    private String locality;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("pinCode")
    private String pinCode;
    @JsonProperty("region")
    private String region;

    @JsonProperty("locality")
    public String getLocality() {
        return locality;
    }

    @JsonProperty("locality")
    public void setLocality(String locality) {
        this.locality = locality;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("pinCode")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("pinCode")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

	public Address() {
		super();
	}

	public Address(String locality, String city, String state, String pinCode, String region) {
		super();
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.region = region;
	}

	@Override
	public String toString() {
		return "Address [locality=" + locality + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode
				+ ", region=" + region + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, locality, pinCode, region, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(locality, other.locality)
				&& Objects.equals(pinCode, other.pinCode) && Objects.equals(region, other.region)
				&& Objects.equals(state, other.state);
	}

}
