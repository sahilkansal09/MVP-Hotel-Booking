package com.mvp.hotelbooking.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.hotelbooking.beans.UserBookingRequest;
import com.mvp.hotelbooking.beans.UserBookings;
import com.mvp.hotelbooking.beans.UserUpdateBookingRequest;
import com.mvp.hotelbooking.exception.HotelBookingException;
import com.mvp.hotelbooking.jpa.model.Hotel;
import com.mvp.hotelbooking.service.HotelBooking;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserHotelBookingController {

	private final HotelBooking hotelBooking;

	/**
	 * This Controller handles creation of new hotel booking
	 * @param bookingRequest
	 * @return
	 */
	@PostMapping("/create-hotel-booking")
	public ResponseEntity<String> createHotelBooking(@RequestBody UserBookingRequest bookingRequest){
		try {
			System.out.println("Request : " + bookingRequest);
			doValidationsOnUserData(bookingRequest);
			hotelBooking.createNewUserHotelBooking(bookingRequest);
			return ResponseEntity.ok("Hotel Booking is Successful");
		}catch(HotelBookingException exception){
			return ResponseEntity.badRequest().body(exception.getMessage());
		}catch(Exception exception){
			return ResponseEntity.internalServerError().body("Error Occurred!! Please re-try!!");
		}
	}

	/**
	 * This Controller helps in searching of new hotel booking
	 * @return
	 */
	@GetMapping("/search-hotels")
	public ResponseEntity<List<Hotel>> searchHotelBooking(){
		try {
			return ResponseEntity.ok(hotelBooking.searchAllHotelsForBooking());
		}catch(HotelBookingException exception){
			return ResponseEntity.badRequest().build();
		}catch(Exception exception){
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * This Controller handles cancellation of existing hotel booking
	 * @param userId
	 * @param bookingId
	 * @return
	 */
	@PostMapping("/cancel-hotel-booking")
	public ResponseEntity<String> cancelUserBooking(@RequestParam(required = true) String userId, @RequestParam (required = true) String bookingId ){
		try {
			System.out.println("Request cancelUserBooking: " + userId + " for bookingId: " + bookingId);
			hotelBooking.cancelUserHotelBooking(userId, bookingId);
			return ResponseEntity.ok("Hotel Booking Cancellation is Done Successfully");
		}catch(HotelBookingException exception){
			return ResponseEntity.badRequest().body(exception.getMessage());
		}catch(Exception exception){
			return ResponseEntity.internalServerError().body("Error Occurred!! Please re-try!!");
		}
	}

	/**
	 * This Controller handles to view All the Hotel Bookings of User
	 * @param userId
	 * @return
	 */
	@GetMapping("/view-hotel-booking/{userId}")
	public ResponseEntity<UserBookings> viewUserHotelBookings(@PathVariable(required = true) String userId) {
		try {
			System.out.println("Request viewAllHotelBooking: " + userId);
			UserBookings user = hotelBooking.getUserAllHotelBooking(userId);
			System.out.println("Response viewAllHotelBooking: " + userId + " and booking count : " + user.getBookings());
			return ResponseEntity.ok(user);
		} catch(HotelBookingException exception) {
			return ResponseEntity.badRequest().build();
		} catch(Exception exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * This Controller handles to view the specific Hotel Booking of User
	 * @param userId
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/view-hotel-booking")
	public ResponseEntity<UserBookings> viewUserHotelBooking(@RequestParam (required = true) String userId,@RequestParam (required = true) String bookingId){
		try {
			System.out.println("Request viewAllHotelBooking: " + userId);
			UserBookings user = hotelBooking.getUserHotelBooking(userId, bookingId);
			System.out.println("Response viewAllHotelBooking: " + userId + " and booking count : " + user.getBookings());
			return ResponseEntity.ok(user);
		} catch(HotelBookingException exception) {
			return ResponseEntity.badRequest().build();
		} catch(Exception exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	/**
	 * This Controller handles to update the specific Hotel Booking of User
	 * @param userUpdateBookingRequest
	 * @return
	 */
	@PutMapping("/update-hotel-booking")
	public ResponseEntity<String> updateUserHotelBooking(@RequestBody UserUpdateBookingRequest userUpdateBookingRequest){
		try {
			System.out.println("Request updateUserHotelBooking: " + userUpdateBookingRequest);
			doValidationsOnUserData(userUpdateBookingRequest);
			hotelBooking.updateUserHotelBooking(userUpdateBookingRequest);
			System.out.println("Response updateUserHotelBooking: " + userUpdateBookingRequest);
			return ResponseEntity.ok("Updated SuccessFully");
		}catch(HotelBookingException exception){
			return ResponseEntity.badRequest().body(exception.getMessage());
		}catch(Exception exception){
			return ResponseEntity.internalServerError().body("Error Occurred!! Please re-try!! And Error:  " +exception.getMessage());
		}
	}

	private void doValidationsOnUserData(UserBookingRequest userBookingRequest){
		if(userBookingRequest.getUserId()==null){
			throw new HotelBookingException("Please Provide User Id In Request!!");
		}else if(userBookingRequest.getHotelId()==null){
			throw new HotelBookingException("Please Provide Hotel Id In Request!!");
		}else if(userBookingRequest.getCheckInDate()==null){
			throw new HotelBookingException("Please Provide Check In Date In Request!!");
		}else if(userBookingRequest.getCheckOutDate()==null){
			throw new HotelBookingException("Please Provide Check Out Date In Request!!");
		}else if( StringUtils.isBlank(String.valueOf(userBookingRequest.getNumberOfRooms())) || userBookingRequest.getNumberOfRooms() <= 0){
			throw new HotelBookingException("Please Provide Correct Value to Rooms In Request!!");
		}
	}
}
