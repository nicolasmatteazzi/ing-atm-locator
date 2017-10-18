package com.backbase.assignment.matnic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides utility methods for performing REST HTTP Calls
 */
public class RESTCall {

	private static final Logger logger_ = LogManager.getLogger();

	private String endpoint_;

	public RESTCall(String endpoint) {
		endpoint_ = endpoint;
	}

	public String call(String payload, String method) {

		HttpURLConnection connection = null;
		try {

			logger_.debug("Sending HTTP " + method + " request to URL " + endpoint_ + " with the following payload: " + payload);

			URL url = new URL(endpoint_);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			if (payload != null) {
				OutputStream os = connection.getOutputStream();
				os.write(payload.getBytes());
				os.flush();
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String response = buildResponse(br);

			logger_.debug("Sent HTTP " + method + " request to URL " + endpoint_ + " and received the following response: " + response);

			return response;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null)
				connection.disconnect();
		}

	}

	/**
	 * Serializes the information provided in the reader and returns it as a
	 * string
	 * 
	 * @param the
	 *            reader
	 * @return the serialized information
	 * @throws IOException
	 */
	private String buildResponse(BufferedReader reader) throws IOException {

		StringBuffer buffer = new StringBuffer();

		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(" ");
			buffer.append(line);
		}

		return buffer.toString();
	}

}
