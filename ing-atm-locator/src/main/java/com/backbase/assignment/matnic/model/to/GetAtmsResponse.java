package com.backbase.assignment.matnic.model.to;

import java.util.ArrayList;
import java.util.List;

import com.backbase.assignment.matnic.dlg.GetAtmsDelegate;
import com.backbase.assignment.matnic.model.bo.ATMLocation;

/**
 * Response of the {@link GetAtmsDelegate}. It's the response that will be
 * forwarded to the API caller and contains an array of ATM locations.
 * 
 * @author nicolas
 *
 */
public class GetAtmsResponse {

	private GetAtmsResponseStatus status_ = GetAtmsResponseStatus.ok;
	private List<ATMLocation> atms_ = new ArrayList<ATMLocation>();

	public GetAtmsResponse() {}

	public GetAtmsResponse(List<ATMLocation> atms) {
		setAtms(atms);
	}

	public GetAtmsResponseStatus getStatus() {
		return status_;
	}

	public void setStatus(GetAtmsResponseStatus status) {
		status_ = status;
	}

	public void addAtm(ATMLocation atm) {
		atms_.add(atm);
	}

	public List<ATMLocation> getAtms() {
		return atms_;
	}

	public void setAtms(List<ATMLocation> atms) {
		atms_ = atms;
	}

}
