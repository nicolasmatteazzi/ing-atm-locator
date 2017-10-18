package com.backbase.assignment.matnic.util;

import org.springframework.stereotype.Service;

@Service
public class RESTCallFactoryImpl implements RESTCallFactory {

	@Override
	public RESTCall newRestCall(String endpoint) {

		return new RESTCall(endpoint);
	}

}
