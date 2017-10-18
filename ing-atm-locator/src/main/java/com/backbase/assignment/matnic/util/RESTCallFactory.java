package com.backbase.assignment.matnic.util;

/**
 * Factory to create REST call wrappers
 * 
 * @author nicolas
 *
 */
public interface RESTCallFactory {

	/**
	 * Creates a new REST Call skeleton
	 * 
	 * @param endpoint
	 *            the endpoint for the REST Call (e.g.
	 *            https://domain.eu/api/...)
	 * @return
	 */
	public RESTCall newRestCall(String endpoint);

}
