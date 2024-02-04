package com.mvp.hotelbooking.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mvp.hotelbooking.jpa.model.User;

@Repository
public interface UserHotelBookingRepository extends JpaRepository<User,Long> {

	@Query(value = "Select user from User user "+
	"left join fetch user.bookings bookings where user.user_id in (?1) and bookings.id in (?2)")
	Optional<User> findUserHotelBookingWithBookingId(long userId, long bookingId);
}
