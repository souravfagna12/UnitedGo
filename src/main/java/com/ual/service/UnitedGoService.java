package com.ual.service;

import java.time.LocalDate;
import java.util.List;

import com.ual.dto.Booking;
import com.ual.dto.Flight;
import com.ual.exception.UnitedGoException;

public interface UnitedGoService {
	public List<Flight> SearchFlight(String source,String destination,LocalDate date) throws UnitedGoException;
	
	
	public Booking BookFlight(String FlightId,int numberofPassanger) throws UnitedGoException ;
	
	public Booking VeiwBooking(String PNR) throws UnitedGoException ;
	
	public String DeleteBooking(String PNR) throws UnitedGoException ;
}
