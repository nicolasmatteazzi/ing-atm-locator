package com.backbase.assignment.matnic.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Models an ATM Location
 * 
 * @author nicolas
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	private String street_;
	private String housenumber_;
	private String postalcode_;
	private String city_;
	private GeoLocation geoLocation_;

	public String getStreet() {
		return street_;
	}

	public void setStreet(String street) {
		street_ = street;
	}

	public String getHousenumber() {
		return housenumber_;
	}

	public void setHousenumber(String housenumber) {
		housenumber_ = housenumber;
	}

	public String getPostalcode() {
		return postalcode_;
	}

	public void setPostalcode(String postalcode) {
		postalcode_ = postalcode;
	}

	public String getCity() {
		return city_;
	}

	public void setCity(String city) {
		city_ = city;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation_;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		geoLocation_ = geoLocation;
	}

}
