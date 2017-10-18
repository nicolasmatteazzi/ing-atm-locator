package com.backbase.assignment.matnic.model.to;

import com.backbase.assignment.matnic.dlg.GetAtmsDelegate;

/**
 * Filters used in the {@link GetAtmsDelegate} class to filter the atms list
 * based on the specified city
 * 
 * @author nicolas
 *
 */
public class GetAtmsFilter {

	private String city_;

	public GetAtmsFilter(String city) {
		setCity(city);
	}

	public String getCity() {
		return city_;
	}

	public void setCity(String city) {
		city_ = city;
	}

}
