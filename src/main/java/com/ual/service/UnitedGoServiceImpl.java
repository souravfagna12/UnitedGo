package com.ual.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ual.dto.Flight;
import com.ual.entity.BookingEntity;
import com.ual.entity.FlightEntity;
import com.ual.exception.UnitedGoException;
import com.ual.repository.UnitedGoBookingRepository;
import com.ual.repository.UnitedGoFlightRepository;
import com.ual.dto.*;

@Service("UnitedService")
@Transactional
public class UnitedGoServiceImpl implements UnitedGoService{
	@Autowired
	UnitedGoFlightRepository flightRepo;
	@Autowired 
	UnitedGoBookingRepository bookingRepo;
	ModelMapper mapper=new ModelMapper();
	
	public List<Flight> SearchFlight(String source,String destination,LocalDate date) throws UnitedGoException {
		List<FlightEntity> f=flightRepo.getFlightDetails(source,destination,date);
		if(f.isEmpty()) throw new UnitedGoException("no flight found");
		List<Flight> flight=f.stream().map(r->mapper.map(r, Flight.class)).collect(Collectors.toList());
		return flight;
	}
	public Booking BookFlight(String FlightId,int numberofPassanger) throws UnitedGoException {
		Optional<FlightEntity> op=flightRepo.findById(FlightId);
		FlightEntity f=op.orElseThrow(()->new UnitedGoException("no flight found"));
		if(f.getAvailableSeats()<numberofPassanger)  throw new UnitedGoException("no seat available");
		f.setAvailableSeats(f.getAvailableSeats()-numberofPassanger);
		BookingEntity b=new BookingEntity();
		String pnrNo=""+(char)('a'+(int)(Math.random()*26))+(char)('a'+(int)(Math.random()*26))+(char)('a'+(int)(Math.random()*26))+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10);
		b.setPnr(pnrNo);
		b.setNoOfPassengers(numberofPassanger);
		b.setTotalFare(numberofPassanger*f.getFare());
		b.setFlight(f);
		flightRepo.saveAndFlush(f);
		bookingRepo.save(b);
		Booking booked=mapper.map(b,Booking.class);
		return booked;
	}
	public Booking VeiwBooking(String PNR) throws UnitedGoException {
		Optional<BookingEntity> op=bookingRepo.findById(PNR);
		BookingEntity f=op.orElseThrow(()->new UnitedGoException("no booking found"));
		Booking b=mapper.map(f, Booking.class);
		return b;
	}
	public String DeleteBooking(String PNR) throws UnitedGoException {
		Optional<BookingEntity> op=bookingRepo.findById(PNR);
		BookingEntity b=op.orElseThrow(()->new UnitedGoException("no booking found"));
		FlightEntity f=b.getFlight();
		String deptTime=f.getDepartureTime();
		LocalDate date=f.getAvailableDate();
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("hh:mm a");
		LocalTime time=LocalTime.parse(deptTime,formater);
		LocalDateTime jDate=LocalDateTime.of(date,time);
		LocalDateTime MinTime=jDate.minusHours(6);
		LocalDateTime nowTime=LocalDateTime.now();
		if(nowTime.isAfter(jDate)||nowTime.isEqual(jDate)) {
			throw new UnitedGoException("cancelation is not allowed.");
		}
		
		f.setAvailableSeats(b.getNoOfPassengers()+f.getAvailableSeats());
		flightRepo.saveAndFlush(f);
		bookingRepo.deleteById(PNR);
		if(nowTime.isAfter(MinTime)||nowTime.isEqual(MinTime))
		return "delete Succesfully without refund";
		else
		return "delete Succesfully with refund";
	}

}
