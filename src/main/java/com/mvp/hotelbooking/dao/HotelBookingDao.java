package com.mvp.hotelbooking.dao;

import java.util.Optional;

import com.mvp.hotelbooking.beans.UserBookingRequest;
import com.mvp.hotelbooking.beans.UserBookings;
import com.mvp.hotelbooking.beans.UserUpdateBookingRequest;
import com.mvp.hotelbooking.jpa.model.User;

public interface HotelBookingDao {
	void saveBookingDetails(UserBookingRequest userBookingRequest);
	User getAllBooking(String userID);
	Optional<User> getUserBooking(String userID, String bookingId);
	Optional<User> findUserByID(String userID);
	void updateUserBooking(User updatedUser);
	void updateUserBooking(UserUpdateBookingRequest userUpdateBookingRequest);
}
