package com.backbase.assignment.matnic.model.bo;

import com.backbase.assignment.matnic.model.bo.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Maps the Atm object returned by the ING API
 * 
 * @author nicolas
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ATMLocation {

	private Address address_;

	public Address getAddress() {
		return address_;
	}

	public void setAddress(Address address) {
		address_ = address;
	}
}
