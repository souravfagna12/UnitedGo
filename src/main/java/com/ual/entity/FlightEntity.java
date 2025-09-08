package com.ual.entity;

import java.time.LocalDate;

import com.ual.dto.Flight;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FlightEntity {
	@Id
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

