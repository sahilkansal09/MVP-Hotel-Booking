package com.mvp.hotelbooking.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mvp.hotelbooking.jpa.model.Booking;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBookings {
	private long userId;
	private String userName;
	List<Booking> bookings;
}
