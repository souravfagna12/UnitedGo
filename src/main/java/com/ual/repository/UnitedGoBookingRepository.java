package com.ual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ual.entity.BookingEntity;

public interface UnitedGoBookingRepository extends JpaRepository<BookingEntity,String>{

}
