package com.mvp.hotelbooking.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvp.hotelbooking.jpa.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
