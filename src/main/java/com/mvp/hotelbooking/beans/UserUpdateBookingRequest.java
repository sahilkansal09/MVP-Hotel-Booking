package com.mvp.hotelbooking.beans;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateBookingRequest extends UserBookingRequest{
	@NonNull
	private Long bookingId;
}
