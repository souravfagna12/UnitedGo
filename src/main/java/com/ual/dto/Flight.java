package com.ual.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	private String flightId;
	private String airline;
	private String source;
	private String destination;
	private String arrivalTime;
	private String departureTime;
	private int availableSeats;
	private LocalDate availableDate;
	private Double fare;
}
