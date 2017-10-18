package com.backbase.assignment.matnic.integration;

import java.util.List;

import com.backbase.assignment.matnic.model.bo.ATMLocation;

/**
 * Interface to integrate external services to retrieve the list of ATMs
 * 
 * @author nicolas
 *
 */
public interface AtmService {

	public List<ATMLocation> getAtms();

}
