package com.mvp.hotelbooking.beans;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookingRequest {
	private Long userId;
	private Long hotelId;
	private Instant checkInDate;
	private Instant checkOutDate;
	private int numberOfRooms;

	@Override
	public String toString() {
		return "UserBookingRequest{" +
				"userId=" + userId +
				", hotelId=" + hotelId +
				", checkInDate=" + checkInDate +
				", checkOutDate=" + checkOutDate +
				", numberOfRooms=" + numberOfRooms +
				'}';
	}
}
