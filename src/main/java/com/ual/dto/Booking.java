package com.ual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	private String pnr;
	private int noOfPassengers;
	private double totalFare;
	private Flight flight;
	
}
