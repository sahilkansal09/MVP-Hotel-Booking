package com.mvp.hotelbooking.service;

import java.util.List;

import com.mvp.hotelbooking.beans.UserBookingRequest;
import com.mvp.hotelbooking.beans.UserBookings;
import com.mvp.hotelbooking.beans.UserUpdateBookingRequest;
import com.mvp.hotelbooking.jpa.model.Hotel;

import lombok.NonNull;

public interface HotelBooking {
	void createNewUserHotelBooking(@NonNull UserBookingRequest bookingRequest);
	UserBookings getUserAllHotelBooking(@NonNull String userId);
	UserBookings getUserHotelBooking(@NonNull String userId,@NonNull String bookingId);
	void cancelUserHotelBooking(@NonNull String userId,@NonNull String bookingId);
	void updateUserHotelBooking(UserUpdateBookingRequest userUpdateBookingRequest);
	List<Hotel> searchAllHotelsForBooking();
}
