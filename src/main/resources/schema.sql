Create Table if not exists user (
	user_id long primary key,
	name varchar(250),
	phoneNo varchar(250)
);

Create Table if not exists hotel (
	hotel_id long primary key,
	hotelName varchar(250),
	address varchar(250),
	city varchar(250),
	country varchar(250)
);

Insert into User Values(1,'sahil','9803273436');
Insert into User Values(2,'Ajay','9803273437');
Insert into hotel Values(1,'JW Marriott','Sector-26','Chandigarh','India');
Insert into hotel Values(2,'Orange','Sector-68','Mohali','India');