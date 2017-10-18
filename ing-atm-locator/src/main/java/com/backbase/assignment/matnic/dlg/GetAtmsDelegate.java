package com.backbase.assignment.matnic.dlg;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.assignment.matnic.integration.AtmService;
import com.backbase.assignment.matnic.model.bo.ATMLocation;
import com.backbase.assignment.matnic.model.to.GetAtmsFilter;
import com.backbase.assignment.matnic.model.to.GetAtmsResponse;
import com.backbase.assignment.matnic.model.to.GetAtmsResponseStatus;

/**
 * Retrieves the list of ATMS in a given Dutch city
 * 
 * @author nicolas
 *
 */
@Service
public class GetAtmsDelegate {
	
	private static final Logger logger_ = LogManager.getLogger();
	
	@Autowired
	private AtmService atmService_;

	/**
	 * Retrieves the list of ATMs
	 * 
	 * @param filter
	 *            the filter in which the city is specified
	 * @return
	 */
	public GetAtmsResponse getAtms(GetAtmsFilter filter) {
		
		try {
			
			// 1. Invoke external service to retrieve ATMs superset
			List<ATMLocation> atms = atmService_.getAtms();
			
			if (atms == null || atms.isEmpty()) return new GetAtmsResponse();
			
			// 2. Filter the set based on the provided GetAtmsFilter
			if (filter == null || filter.getCity() == null || filter.getCity().trim().isEmpty()) return new GetAtmsResponse(atms);
			
			GetAtmsResponse response = new GetAtmsResponse();
			
			String cityFilter = filter.getCity().trim();
			
			for (ATMLocation atm : atms) {
				
				if (atm.getAddress() == null || atm.getAddress().getCity() == null) continue;
				
				if (atm.getAddress().getCity().equalsIgnoreCase(cityFilter)) response.addAtm(atm); 
			}
			
			return response;
			
		}
		catch (Exception e) {
			
			logger_.error(e);

			GetAtmsResponse response = new GetAtmsResponse();
			response.setStatus(GetAtmsResponseStatus.failed);
			
			return response;
		}
	}

	/**
	 * Used for unit test mocking purposes. 
	 * Package protected. 
	 * 
	 * @param atmService
	 */
	void setAtmService(AtmService atmService) {
		atmService_ = atmService;
	}
}
