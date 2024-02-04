## MVP-Hotel Booking

With this code base, User will need to be able to Search,Create,Update,View And Cancel their hotel bookings.

- While starting this application, User and hotel data will be loaded from resources/schema.sql file.
- For each User operation, different URI is exposed.
  
URI's Exposed:

1. For Creation of New hotel Booking:

   URI: http://localhost:8081/user/create-hotel-booking
   HTTP Method: Post
   Request Sample:
   {
	"userId" : "1",
	"hotelId" : "2",
	"checkInDate" : "2023-01-20T05:47:26.853Z",
	"checkOutDate" : "2023-01-21T05:47:26.853Z",
	"numberOfRooms" : "9"
  }
  
  Success Response: 
  Http Status Code: 200, Msg: Hotel Booking is Successful

2. For Cancellation of New hotel Booking:
   URI: http://localhost:8081/user/cancel-hotel-booking?userId=1&bookingId=1
   HTTP Method: Post
   Success Response: 
    Http Status Code: 200, Msg: Hotel Booking Cancellation is Done Successfully

3. For Cancellation of New hotel Booking:
    URI: http://localhost:8081/user/search-hotels
   HTTP Method: Get
   Success Response: 
    Http Status Code: 200, 
    [
    {
        "id": 1,
        "hotelName": "India",
        "address": "JW Marriott",
        "city": "Sector-26",
        "country": "Chandigarh"
    },
    {
        "id": 2,
        "hotelName": "India",
        "address": "Orange",
        "city": "Sector-68",
        "country": "Mohali"
    }
]
4. View hotel Booking:
   URI: http://localhost:8081/user/cancel-hotel-booking?userId=1&bookingId=2
   HTTP Method: Get
   Success Response: 
    Http Status Code: 200, Msg: Hotel Booking Cancellation is Done Successfully
   {
    "userId": 1,
    "userName": "sahil",
    "bookings": [
        {
            "id": 2,
            "checkInDate": "2023-01-20T05:47:26.853Z",
            "checkOutDate": "2023-01-21T05:47:26.853Z",
            "numberOfRooms": 4,
            "hotel": {
                "id": 2,
                "hotelName": "India",
                "address": "Orange",
                "city": "Sector-68",
                "country": "Mohali"
            }
        }
    ]
}

5. Update Existing hotel Booking:
   URI: http://localhost:8081/user/update-hotel-booking
   HTTP Method: Post
   Request Body:
   {
	"userId" : "1",
	"hotelId" : "2",
    "bookingId" : "2",
	"checkInDate" : "2023-01-20T05:47:26.853Z",
	"checkOutDate" : "2023-01-21T05:47:26.853Z",
	"numberOfRooms" : "4"
  }
   Success Response: 
    Http Status Code: 200, Msg: Updated SuccessFully
