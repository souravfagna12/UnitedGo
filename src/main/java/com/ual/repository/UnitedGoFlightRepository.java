package com.ual.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ual.entity.FlightEntity;

public interface UnitedGoFlightRepository extends JpaRepository<FlightEntity, String>{
	@Query("select t from FlightEntity t where t.source=?1 and t.destination=?2 and t.availableDate=?3")
	public List<FlightEntity> getFlightDetails(String source, String destination, LocalDate availableDate);

}
