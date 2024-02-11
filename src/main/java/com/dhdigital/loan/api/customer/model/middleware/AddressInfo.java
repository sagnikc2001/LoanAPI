
package com.dhdigital.loan.api.customer.model.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location",
    "city",
    "state",
    "pinCode",
    "region"
})
public class AddressInfo {

    @JsonProperty("location")
    private String location;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("pinCode")
    private String pinCode;
    @JsonProperty("region")
    private String region;

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
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

	public AddressInfo() {
		super();
	}

	public AddressInfo(String location, String city, String state, String pinCode, String region) {
		super();
		this.location = location;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.region = region;
	}

	@Override
	public String toString() {
		return "AddressInfo [location=" + location + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode
				+ ", region=" + region + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, location, pinCode, region, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressInfo other = (AddressInfo) obj;
		return Objects.equals(city, other.city) && Objects.equals(location, other.location)
				&& Objects.equals(pinCode, other.pinCode) && Objects.equals(region, other.region)
				&& Objects.equals(state, other.state);
	}

}
