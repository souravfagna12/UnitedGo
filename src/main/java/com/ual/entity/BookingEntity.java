package com.ual.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingEntity {
	@Id
	private String pnr;
	private int noOfPassengers;
	private double totalFare;
	@ManyToOne
	@JoinColumn(name="flightId")
	private FlightEntity flight;
}
