package com.backbase.assignment.matnic.integration.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.backbase.assignment.matnic.model.bo.ATMLocation;
import com.backbase.assignment.matnic.util.RESTCall;
import com.backbase.assignment.matnic.util.RESTCallFactory;

/**
 * Test class for the {@link IngAtmService}
 * 
 * @author nicolas
 *
 */
public class TestIngAtmService {

	@Test
	public void standardPath() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("[{\"address\":{\"street\":\"Johannes Vermeerstraat\",\"housenumber\":\"23\",\"postalcode\":\"3601 EL\",\"city\":\"Maarssen\",\"geoLocation\":{\"lat\":\"52.142646\",\"lng\":\"5.04114\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kinheim\",\"housenumber\":\"305\",\"postalcode\":\"3191 EG\",\"city\":\"Hoogvliet Rotterdam\",\"geoLocation\":{\"lat\":\"51.864567\",\"lng\":\"4.354779\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kleiweg\",\"housenumber\":\"35\",\"postalcode\":\"3051 GJ\",\"city\":\"Rotterdam\",\"geoLocation\":{\"lat\":\"51.945091\",\"lng\":\"4.481572\"}},\"distance\":0,\"type\":\"ING\"}]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertFalse(atms.isEmpty());
		assertEquals(3, atms.size());
		
		assertEquals("Johannes Vermeerstraat", atms.get(0).getAddress().getStreet());
		assertEquals("23", atms.get(0).getAddress().getHousenumber());
		assertEquals("3601 EL", atms.get(0).getAddress().getPostalcode());
		assertEquals("Maarssen", atms.get(0).getAddress().getCity());
		assertNotNull(atms.get(0).getAddress().getGeoLocation());
		assertEquals("52.142646", atms.get(0).getAddress().getGeoLocation().getLat());
		assertEquals("5.04114", atms.get(0).getAddress().getGeoLocation().getLng());
		
		assertEquals("Kinheim", atms.get(1).getAddress().getStreet());
		assertEquals("305", atms.get(1).getAddress().getHousenumber());
		assertEquals("3191 EG", atms.get(1).getAddress().getPostalcode());
		assertEquals("Hoogvliet Rotterdam", atms.get(1).getAddress().getCity());
		assertNotNull(atms.get(1).getAddress().getGeoLocation());
		assertEquals("51.864567", atms.get(1).getAddress().getGeoLocation().getLat());
		assertEquals("4.354779", atms.get(1).getAddress().getGeoLocation().getLng());
		
		assertEquals("Kleiweg", atms.get(2).getAddress().getStreet());
		assertEquals("35", atms.get(2).getAddress().getHousenumber());
		assertEquals("3051 GJ", atms.get(2).getAddress().getPostalcode());
		assertEquals("Rotterdam", atms.get(2).getAddress().getCity());
		assertNotNull(atms.get(2).getAddress().getGeoLocation());
		assertEquals("51.945091", atms.get(2).getAddress().getGeoLocation().getLat());
		assertEquals("4.481572", atms.get(2).getAddress().getGeoLocation().getLng());
	}
	
	@Test
	public void jsonProtection() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory(")]}',[{\"address\":{\"street\":\"Johannes Vermeerstraat\",\"housenumber\":\"23\",\"postalcode\":\"3601 EL\",\"city\":\"Maarssen\",\"geoLocation\":{\"lat\":\"52.142646\",\"lng\":\"5.04114\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kinheim\",\"housenumber\":\"305\",\"postalcode\":\"3191 EG\",\"city\":\"Hoogvliet Rotterdam\",\"geoLocation\":{\"lat\":\"51.864567\",\"lng\":\"4.354779\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kleiweg\",\"housenumber\":\"35\",\"postalcode\":\"3051 GJ\",\"city\":\"Rotterdam\",\"geoLocation\":{\"lat\":\"51.945091\",\"lng\":\"4.481572\"}},\"distance\":0,\"type\":\"ING\"}]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertFalse(atms.isEmpty());
		assertEquals(3, atms.size());
		assertEquals("Johannes Vermeerstraat", atms.get(0).getAddress().getStreet());
		assertEquals("Kinheim", atms.get(1).getAddress().getStreet());
		assertEquals("Kleiweg", atms.get(2).getAddress().getStreet());
	}

	@Test
	public void noAddress() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("[{\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kinheim\",\"housenumber\":\"305\",\"postalcode\":\"3191 EG\",\"city\":\"Hoogvliet Rotterdam\",\"geoLocation\":{\"lat\":\"51.864567\",\"lng\":\"4.354779\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kleiweg\",\"housenumber\":\"35\",\"postalcode\":\"3051 GJ\",\"city\":\"Rotterdam\",\"geoLocation\":{\"lat\":\"51.945091\",\"lng\":\"4.481572\"}},\"distance\":0,\"type\":\"ING\"}]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertFalse(atms.isEmpty());
		assertEquals(3, atms.size());
		
		assertNull(atms.get(0).getAddress());
		
		assertEquals("Kinheim", atms.get(1).getAddress().getStreet());
		assertEquals("305", atms.get(1).getAddress().getHousenumber());
		assertEquals("3191 EG", atms.get(1).getAddress().getPostalcode());
		assertEquals("Hoogvliet Rotterdam", atms.get(1).getAddress().getCity());
		assertNotNull(atms.get(1).getAddress().getGeoLocation());
		assertEquals("51.864567", atms.get(1).getAddress().getGeoLocation().getLat());
		assertEquals("4.354779", atms.get(1).getAddress().getGeoLocation().getLng());
		
		assertEquals("Kleiweg", atms.get(2).getAddress().getStreet());
		assertEquals("35", atms.get(2).getAddress().getHousenumber());
		assertEquals("3051 GJ", atms.get(2).getAddress().getPostalcode());
		assertEquals("Rotterdam", atms.get(2).getAddress().getCity());
		assertNotNull(atms.get(2).getAddress().getGeoLocation());
		assertEquals("51.945091", atms.get(2).getAddress().getGeoLocation().getLat());
		assertEquals("4.481572", atms.get(2).getAddress().getGeoLocation().getLng());
	}

	@Test
	public void noGeo() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("[{\"address\":{\"street\":\"Johannes Vermeerstraat\",\"housenumber\":\"23\",\"postalcode\":\"3601 EL\",\"city\":\"Maarssen\"},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kinheim\",\"housenumber\":\"305\",\"postalcode\":\"3191 EG\",\"city\":\"Hoogvliet Rotterdam\",\"geoLocation\":{\"lat\":\"51.864567\",\"lng\":\"4.354779\"}},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kleiweg\",\"housenumber\":\"35\",\"postalcode\":\"3051 GJ\",\"city\":\"Rotterdam\",\"geoLocation\":{\"lat\":\"51.945091\",\"lng\":\"4.481572\"}},\"distance\":0,\"type\":\"ING\"}]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertFalse(atms.isEmpty());
		assertEquals(3, atms.size());
		
		assertEquals("Johannes Vermeerstraat", atms.get(0).getAddress().getStreet());
		assertEquals("23", atms.get(0).getAddress().getHousenumber());
		assertEquals("3601 EL", atms.get(0).getAddress().getPostalcode());
		assertEquals("Maarssen", atms.get(0).getAddress().getCity());
		assertNull(atms.get(0).getAddress().getGeoLocation());
	}

	@Test
	public void nullGeo() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("[{\"address\":{\"street\":\"Johannes Vermeerstraat\",\"housenumber\":\"23\",\"postalcode\":\"3601 EL\",\"city\":\"Maarssen\"},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kinheim\",\"housenumber\":\"305\",\"postalcode\":\"3191 EG\",\"city\":\"Hoogvliet Rotterdam\",\"geoLocation\":null},\"distance\":0,\"type\":\"ING\"},{\"address\":{\"street\":\"Kleiweg\",\"housenumber\":\"35\",\"postalcode\":\"3051 GJ\",\"city\":\"Rotterdam\",\"geoLocation\":{\"lat\":\"51.945091\",\"lng\":\"4.481572\"}},\"distance\":0,\"type\":\"ING\"}]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertFalse(atms.isEmpty());
		assertEquals(3, atms.size());
		
		assertNull(atms.get(1).getAddress().getGeoLocation());
	}

	@Test
	public void emptyArrayResponse() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("[]");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNotNull(atms);
		assertTrue(atms.isEmpty());
	}

	@Test
	public void emptyResponse() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory("");
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNull(atms);
	}

	@Test
	public void nullResponse() {
		
		RESTCallFactory restCallFactory = new MockRESTCallFactory(null);
		
		IngAtmService service = new IngAtmService();
		service.setRestCallFactory(restCallFactory);
		
		List<ATMLocation> atms = service.getAtms();
		
		assertNull(atms);
	}

	@Test
	public void exception() {
		
		try {
			
			MockRESTCallFactory restCallFactory = new MockRESTCallFactory("");
			restCallFactory.setException(true);
			
			IngAtmService service = new IngAtmService();
			service.setRestCallFactory(restCallFactory);
			
			service.getAtms();
			
			fail("Should have thrown an exception");
		}
		catch (Exception e) {
		}
	}
	
	/**
	 * Used to mock the {@link RESTCall}
	 * @author nicolas
	 *
	 */
	private static final class MockRESTCallFactory implements RESTCallFactory {

		private String mockedRESTResponse_;
		private Boolean exception_ = false;

		public MockRESTCallFactory(String mockedRESTResponse) {
			mockedRESTResponse_ = mockedRESTResponse;
		}

		public void setException(Boolean exception) {
			exception_ = exception;			
		}

		@Override
		public RESTCall newRestCall(String endpoint) {
			return new MockRESTCall(endpoint, mockedRESTResponse_, exception_);
		}
		
	}
	
	private static final class MockRESTCall extends RESTCall {

		private String mockedResponse_;
		private Boolean exception_;

		public MockRESTCall(String endpoint) {
			super(endpoint);
		}
		
		public MockRESTCall(String endpoint, String mockedResponse, Boolean exception) {
			super(endpoint);
			mockedResponse_ = mockedResponse;
			exception_ = exception;
		}
		
		@Override
		public String call(String payload, String method) {
			
			if (exception_) throw new RuntimeException();
			
			return mockedResponse_;
		}
		
	}
	
}
