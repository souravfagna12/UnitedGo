package com.ual.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ual.dto.Booking;
import com.ual.dto.Flight;
import com.ual.exception.UnitedGoException;
import com.ual.service.UnitedGoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/login")
@Validated
public class UnitedGoController {
	
	@Autowired
	UnitedGoService UnitedService;
	@GetMapping("/Flight/{source}/{destination}/{date}")
	public ResponseEntity<List<Flight>> getFlight(@Valid @PathVariable @Pattern(regexp="^[ A-Za-z]+$",message="{Validator.INVALID_SOURCE}") @NotNull(message = "value should not null") String source,@PathVariable @Pattern(regexp="^[ A-Za-z]+$",message="{Validator.INVALID_DESTINATION}")  @NotNull(message = "value should not null") String destination,@PathVariable  @NotNull(message = "value should not null") @FutureOrPresent(message="{Validator.INVALID_DATE}") LocalDate date) throws UnitedGoException{
		List<Flight> f=UnitedService.SearchFlight(source, destination, date);
		return new ResponseEntity<>(f,HttpStatus.OK);
	}
	@PostMapping("/Flight/{flightId}/{noOfPassanger}")
	public ResponseEntity<Booking> BookFlights(@Valid @PathVariable  @NotNull(message = "value should not null") @Pattern(regexp="[A-Z]{2}-[0-9]{3}",message="{Validator.INVALID_FLIGHTID}")String flightId,@PathVariable  @NotNull(message = "value should not null") @Min(value=1,message="{Validator.INVALID_PAX}") @Max(value=4,message="{Validator.INVALID_PAX}")int noOfPassanger) throws UnitedGoException{
		Booking s=UnitedService.BookFlight(flightId, noOfPassanger);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	@GetMapping("/Flight/{PNR}")
	public ResponseEntity<Booking> VeiwBookings(@Valid @PathVariable  @NotNull(message = "value should not null") @Pattern(regexp="^[A-Za-z]{3}[0-9]{3}$",message="{Validator.INVALID_PNR}") String PNR) throws UnitedGoException{
		Booking b=UnitedService.VeiwBooking(PNR);
		return new ResponseEntity<>(b,HttpStatus.OK);
	}
	@DeleteMapping("/Flight/{pnr}")
	public ResponseEntity<String> DeleteBoookings(@Valid @PathVariable  @NotNull(message = "value should not null") @Pattern(regexp="^[A-Za-z]{3}[0-9]{3}$",message="Validator.INVALID_PNR") String pnr) throws UnitedGoException{
		String s=UnitedService.DeleteBooking(pnr);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
}
