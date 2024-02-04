package com.mvp.hotelbooking.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mvp.hotelbooking.beans.UserBookingRequest;
import com.mvp.hotelbooking.beans.UserBookings;
import com.mvp.hotelbooking.beans.UserUpdateBookingRequest;
import com.mvp.hotelbooking.dao.HotelBookingDao;
import com.mvp.hotelbooking.exception.HotelBookingException;
import com.mvp.hotelbooking.jpa.model.Booking;
import com.mvp.hotelbooking.jpa.model.User;
import com.mvp.hotelbooking.service.HotelBooking;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class HotelBookingImpl implements HotelBooking {

	private final HotelBookingDao hotelBookingDao;

	@Override
	public void createNewUserHotelBooking(@NonNull UserBookingRequest bookingRequest) {
		hotelBookingDao.saveBookingDetails(bookingRequest);
	}

	@Override
	public UserBookings getUserAllHotelBooking(String userId) {
		User user = hotelBookingDao.getAllBooking(userId);
		UserBookings userBookings = new UserBookings();
		userBookings.setUserId(user.getUser_id());
		userBookings.setUserName(user.getName());
		userBookings.setBookings(user.getBookings());
		return userBookings;
	}

	@Override
	public UserBookings getUserHotelBooking(@NonNull String userId, @NonNull String bookingId) {
		User user = hotelBookingDao.getUserBooking(userId, bookingId).orElseThrow(() -> new HotelBookingException("User or Booking Id is Not Present In DB"));
		UserBookings userBookings = new UserBookings();
		userBookings.setUserId(user.getUser_id());
		userBookings.setUserName(user.getName());
		userBookings.setBookings(user.getBookings());
		return userBookings;
	}

	@Override
	public void cancelUserHotelBooking(@NonNull String userId,@NonNull String bookingId) {
		User user = hotelBookingDao.findUserByID(userId).orElseThrow(() -> new HotelBookingException("User or Booking Id is Not Correct"));
		List<Booking> bookingToBeCancel = new ArrayList<>();
		user.getBookings().stream()
				.filter(booking -> bookingId.equals(String.valueOf(booking.getId())))
				.forEach(bookingToBeCancel::add);
		user.getBookings().removeAll(bookingToBeCancel);
		hotelBookingDao.updateUserBooking(user);
	}

	@Override
	public void updateUserHotelBooking(UserUpdateBookingRequest userUpdateBookingRequest) {
		hotelBookingDao.updateUserBooking(userUpdateBookingRequest);
	}
}
