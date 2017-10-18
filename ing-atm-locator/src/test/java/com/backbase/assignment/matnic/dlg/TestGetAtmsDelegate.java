package com.backbase.assignment.matnic.dlg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.backbase.assignment.matnic.integration.AtmService;
import com.backbase.assignment.matnic.model.bo.ATMLocation;
import com.backbase.assignment.matnic.model.bo.Address;
import com.backbase.assignment.matnic.model.to.GetAtmsFilter;
import com.backbase.assignment.matnic.model.to.GetAtmsResponse;
import com.backbase.assignment.matnic.model.to.GetAtmsResponseStatus;

/**
 * Unit Test for the {@link GetAtmsDelegate} delegate
 * 
 * @author nicolas
 *
 */
public class TestGetAtmsDelegate {

	@Test
	public void filterAndNoAlterOrder() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("Amsterdam"));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(2, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(1).getAddress().getStreet());
	}

	@Test
	public void filterCaseInsensitive() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("amsterdam"));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(2, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(1).getAddress().getStreet());
	}

	@Test
	public void partialFilterShouldntWork() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("ams"));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	@Test
	public void filterZeroResults() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("Den Haag"));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	@Test
	public void nullFilter() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(null);

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(4, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("John F Kennedyplein, 63-67", atms.getAtms().get(1).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(2).getAddress().getStreet());
		assertEquals("De Ruyterstraat, 1", atms.getAtms().get(3).getAddress().getStreet());
	}

	@Test
	public void noFilter() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(null));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(4, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("John F Kennedyplein, 63-67", atms.getAtms().get(1).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(2).getAddress().getStreet());
		assertEquals("De Ruyterstraat, 1", atms.getAtms().get(3).getAddress().getStreet());
	}

	@Test
	public void emptyFilter() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(""));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(4, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("John F Kennedyplein, 63-67", atms.getAtms().get(1).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(2).getAddress().getStreet());
		assertEquals("De Ruyterstraat, 1", atms.getAtms().get(3).getAddress().getStreet());
	}

	@Test
	public void filterMadeOfSpaces() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("  "));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(4, atms.getAtms().size());
		assertEquals("Burgemeester de Vlugtlaan, 71", atms.getAtms().get(0).getAddress().getStreet());
		assertEquals("John F Kennedyplein, 63-67", atms.getAtms().get(1).getAddress().getStreet());
		assertEquals("Arent Janszoon Ernststraat, 551-559", atms.getAtms().get(2).getAddress().getStreet());
		assertEquals("De Ruyterstraat, 1", atms.getAtms().get(3).getAddress().getStreet());
	}

	@Test
	public void trimming() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(" huizen  "));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(1, atms.getAtms().size());
		assertEquals("De Ruyterstraat, 1", atms.getAtms().get(0).getAddress().getStreet());
	}

	@Test
	public void serviceReturnsNullWithFilter() {

		MockAtmService atmServiceMock = new MockAtmService();

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter("Amsterdam"));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	@Test
	public void serviceReturnsNull() {

		MockAtmService atmServiceMock = new MockAtmService();

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(null));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	@Test
	public void serviceReturnsEmpty() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(new ArrayList<ATMLocation>());

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(null));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.ok));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	@Test
	public void serviceThrowsException() {

		MockAtmService atmServiceMock = new MockAtmService();
		atmServiceMock.setAtms(Arrays.asList(MockAtmService.address("Amsterdam", "Burgemeester de Vlugtlaan, 71"), MockAtmService.address("Purmerend", "John F Kennedyplein, 63-67"), MockAtmService.address("Amsterdam", "Arent Janszoon Ernststraat, 551-559"), MockAtmService.address("Huizen", "De Ruyterstraat, 1")));
		atmServiceMock.setThrowException(true);

		GetAtmsDelegate delegate = new GetAtmsDelegate();
		delegate.setAtmService(atmServiceMock);

		GetAtmsResponse atms = delegate.getAtms(new GetAtmsFilter(null));

		assertNotNull(atms);
		assertTrue(atms.getStatus().equals(GetAtmsResponseStatus.failed));
		assertNotNull(atms.getAtms());
		assertEquals(0, atms.getAtms().size());
	}

	private static class MockAtmService implements AtmService {

		private List<ATMLocation> atms_;
		private boolean throwException_ = false;

		@Override
		public List<ATMLocation> getAtms() {

			if (throwException_) throw new RuntimeException();

			return atms_;
		}

		public void setThrowException(boolean throwException) {
			throwException_ = throwException;
		}

		public void setAtms(List<ATMLocation> atms) {
			atms_ = atms;
		}

		public static ATMLocation address(String string, String string2) {

			Address address = new Address();
			address.setCity(string);
			address.setStreet(string2);

			ATMLocation location = new ATMLocation();
			location.setAddress(address);

			return location;
		}

	}

}
