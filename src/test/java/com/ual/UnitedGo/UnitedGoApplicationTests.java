package com.ual.UnitedGo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ual.dto.Booking;
import com.ual.dto.Flight;
import com.ual.exception.UnitedGoException;
import com.ual.service.UnitedGoServiceImpl;

@SpringBootTest
class UnitedGoApplicationTests {
	
	@Autowired
	UnitedGoServiceImpl service;
	@Test
	void testValidSearchFlight() throws UnitedGoException {
		String source="New York";
		String destination="Los Angeles";
		LocalDate date=LocalDate.of(2025,8, 15);
		List<Flight> f=service.SearchFlight(source, destination, date);
		Flight ff=f.get(0);
		String expectedFlightId=ff.getFlightId();
		String actualFlightId="UA-105";
		assertEquals(actualFlightId,expectedFlightId);
	}
	@Test
	void testInvalidSearchFlight() throws UnitedGoException {
		String source="New York";
		String destination="rjfgnrg23";
		LocalDate date=LocalDate.of(2025,8, 15);
		UnitedGoException exception=assertThrows(UnitedGoException.class,()->service.SearchFlight(source, destination, date));
		String expected="no flight found";
		assertEquals(exception.getMessage(),expected);
	}
	@Test
	void testValidBookFlight() throws UnitedGoException {
		String FlightId="DL-207";
		int noOfPassanger=2;
		Booking f=service.BookFlight(FlightId, noOfPassanger);
		String expected="DL-207";
		String actual=f.getFlight().getFlightId();
		assertEquals(actual,expected);
	}
	@Test
	void testInvalidBookFlight() throws UnitedGoException {
		String FlightId="DL-207";
		int noOfPassanger=2000;
		UnitedGoException exception=assertThrows(UnitedGoException.class,()->service.BookFlight(FlightId, noOfPassanger));
		String expected="no seat available";
		assertEquals(exception.getMessage(),expected);
	}
	@Test
	void testInvalidBookFlightId() throws UnitedGoException {
		String FlightId="DL-2073";
		int noOfPassanger=2;
		UnitedGoException exception=assertThrows(UnitedGoException.class,()->service.BookFlight(FlightId, noOfPassanger));
		String expected="no flight found";
		assertEquals(exception.getMessage(),expected);
	}
	@Test
	void testValidVeiwBooking() throws UnitedGoException {
		String Pnr="zwx980";
		Booking f=service.VeiwBooking(Pnr);
		String expected="zwx980";
		String actual=f.getPnr();
		assertEquals(actual,expected);
	}
	@Test
	void testInValidVeiwBooking() throws UnitedGoException {
		String Pnr="zwx98";
		UnitedGoException e= assertThrows(UnitedGoException.class,()-> service.VeiwBooking(Pnr));
		String expected="no booking found";
		String actual=e.getMessage();
		assertEquals(actual,expected);
	}
	@Test 
	void testValidDeleteBooking() throws UnitedGoException {
		String Pnr="cwp554";
		String f=service.DeleteBooking(Pnr);
		String expected="zwx980";
		String actual="delete Succesfully with refund";
		assertEquals(actual,f);
	}
	@Test
	void testInValidDeleteBooking() throws UnitedGoException {
		String Pnr="zwx98";
		UnitedGoException e= assertThrows(UnitedGoException.class,()-> service.DeleteBooking(Pnr));
		String expected="no booking found";
		String actual=e.getMessage();
		assertEquals(actual,expected);
	}
}
