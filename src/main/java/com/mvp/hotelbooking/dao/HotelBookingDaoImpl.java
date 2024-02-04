package com.mvp.hotelbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mvp.hotelbooking.beans.UserBookingRequest;
import com.mvp.hotelbooking.beans.UserUpdateBookingRequest;
import com.mvp.hotelbooking.exception.HotelBookingException;
import com.mvp.hotelbooking.jpa.model.Booking;
import com.mvp.hotelbooking.jpa.model.Hotel;
import com.mvp.hotelbooking.jpa.model.User;
import com.mvp.hotelbooking.jpa.repository.HotelRepository;
import com.mvp.hotelbooking.jpa.repository.UserHotelBookingRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HotelBookingDaoImpl implements HotelBookingDao{

	private final UserHotelBookingRepository userHotelBookingRepository;
	private final HotelRepository hotelRepository;

	@Override
	public void saveBookingDetails(UserBookingRequest userBookingRequest) {
		User user = userHotelBookingRepository.findById(userBookingRequest.getUserId()).orElseThrow(() -> new HotelBookingException("User or Booking Id is Not Correct"));
		Hotel hotel = hotelRepository.findById(userBookingRequest.getHotelId()).orElseThrow(() -> new HotelBookingException("Hotel Id is not Correct"));
		Booking newHotelBooking = new Booking();
		newHotelBooking.setCheckInDate(userBookingRequest.getCheckInDate());
		newHotelBooking.setCheckOutDate(userBookingRequest.getCheckOutDate());
		newHotelBooking.setNumberOfRooms(userBookingRequest.getNumberOfRooms());
		newHotelBooking.setUser(user);
		newHotelBooking.setHotel(hotel);
		user.getBookings().add(newHotelBooking);
		userHotelBookingRepository.save(user);
	}

	@Override
	public User getAllBooking(String userID) {
		return userHotelBookingRepository.findById(Long.parseLong(userID)).orElseThrow(() -> new HotelBookingException("User or Booking Id is Not Correct"));
	}

	@Override
	public Optional<User> getUserBooking(String userID, String bookingId) {
		return userHotelBookingRepository.findUserHotelBookingWithBookingId(Long.parseLong(userID),Long.parseLong(bookingId));
	}

	@Override
	public Optional<User> findUserByID(String userID) {
		return userHotelBookingRepository.findById(Long.valueOf(userID));
	}

	@Override
	public void updateUserBooking(User updatedUser) {
		userHotelBookingRepository.save(updatedUser);
	}

	@Override
	public void updateUserBooking(UserUpdateBookingRequest userUpdateBookingRequest) {
		User user = userHotelBookingRepository.findUserHotelBookingWithBookingId(userUpdateBookingRequest.getUserId(),userUpdateBookingRequest.getBookingId())
						.orElseThrow(() -> new HotelBookingException("User or Booking Id is Not Correct"));
		Hotel hotel = hotelRepository.findById(userUpdateBookingRequest.getHotelId()).orElseThrow(() -> new HotelBookingException("Hotel Id is not Correct"));
		Booking updateHotelBooking = user.getBookings().get(0);
		updateHotelBooking.setCheckInDate(userUpdateBookingRequest.getCheckInDate());
		updateHotelBooking.setCheckOutDate(userUpdateBookingRequest.getCheckOutDate());
		updateHotelBooking.setNumberOfRooms(userUpdateBookingRequest.getNumberOfRooms());
		updateHotelBooking.setUser(user);
		updateHotelBooking.setHotel(hotel);
		user.getBookings().add(updateHotelBooking);
		userHotelBookingRepository.save(user);
	}

	@Override
	public List<Hotel> findAllHotels() {
		return hotelRepository.findAll();
	}
}
