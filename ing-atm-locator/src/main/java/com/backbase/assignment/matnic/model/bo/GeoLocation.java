package com.backbase.assignment.matnic.model.bo;

/**
 * Models a generic geo location as lat, lng coordinates
 * 
 * @author nicolas
 *
 */
public class GeoLocation {

	private String lat_;
	private String lng_;

	public String getLat() {
		return lat_;
	}

	public void setLat(String lat) {
		lat_ = lat;
	}

	public String getLng() {
		return lng_;
	}

	public void setLng(String lng) {
		lng_ = lng;
	}

}
