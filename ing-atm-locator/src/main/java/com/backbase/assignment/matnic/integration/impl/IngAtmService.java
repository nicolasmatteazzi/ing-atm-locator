package com.backbase.assignment.matnic.integration.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backbase.assignment.matnic.integration.AtmService;
import com.backbase.assignment.matnic.model.bo.ATMLocation;
import com.backbase.assignment.matnic.util.RESTCallFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IngAtmService implements AtmService {

	private static final Logger logger_ = LogManager.getLogger();

	@Value("${atm.ing.service.url}")
	private String ingAPIEndpoint_;

	@Autowired
	private RESTCallFactory restCallFactory_;

	@Override
	public List<ATMLocation> getAtms() {

		try {
			
			String response = restCallFactory_.newRestCall(ingAPIEndpoint_).call(null, "GET");
	
			if (response == null || response.isEmpty()) return null;
	
			response = response.replace(")]}',", "");

			ObjectMapper mapper = new ObjectMapper();
			List<ATMLocation> atms = mapper.readValue(response, new TypeReference<List<ATMLocation>>() {});
			
			return atms;
		}
		catch (Exception e) {

			logger_.error(e);

			throw new RuntimeException(e);
		}

	}

	/**
	 * This setter is used for unit test purposes
	 * 
	 * @param restCallFactory
	 */
	void setRestCallFactory(RESTCallFactory restCallFactory) {
		restCallFactory_ = restCallFactory;
	}

}
