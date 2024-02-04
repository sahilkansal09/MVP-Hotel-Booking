package com.mvp.hotelbooking.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@Setter
@Getter
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "hotel_id", nullable = false)
	private Long id;
	private String hotelName;
	private String address;
	private String city;
	private String country;
}
