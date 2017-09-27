create database JobApply;

use JobApply;

CREATE TABLE Positions (
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	category nvarchar(10) NULL,
	jobtype char(3) NULL,
	experiencelevel char(3) NULL,
	title nvarchar(100) NOT NULL,
	company nvarchar(50) NULL,
	location nvarchar(50) NULL,
	zipcode nvarchar(10) NULL,
	salary nvarchar(40) NULL,
	applyemail nvarchar(80) NULL,
	description nvarchar(300) NULL,
	reviews nvarchar(15) NULL,
	stars char(1) NULL,
	sponsored char(1) NULL,
	likeit char(1) NULL,
	jobcreated nvarchar(15) NULL,
	joblastchecked nvarchar(15) NULL,
    jobinsertdate nvarchar(25) NULL,
	jobapplydate nvarchar(25) NULL,
	responsedate nvarchar(25) NULL,
	titlehref nvarchar(700) NOT NULL,
	companyhref nvarchar(160) NULL,
	comments nvarchar(100) NULL
    );
    

CREATE TABLE Users (
	id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username nvarchar(50) NOT NULL,
	pwd nvarchar(50) NOT NULL,
	category nvarchar(10) NULL,
	jobtype char(3) NULL,
	experiencelevel char(3) NULL,
	title nvarchar(100) NULL,
	state nvarchar(50) NULL,
	city nvarchar(50) NULL,
	street nvarchar(50) NULL,
	zipcode nvarchar(10) NULL,
	email nvarchar(100) NULL,
	valid char(1) NULL
);
