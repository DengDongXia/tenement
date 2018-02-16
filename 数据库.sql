CREATE DATABASE tenement default character set utf8 collate utf8_general_ci;
use tenement;

CREATE TABLE user(
	id char(8) NOT NULL,
	email varchar(30)  NOT NULL,
	userName varchar(20)  NOT NULL,
	psw varchar(20)  NOT NULL,
	head varchar(100)  NOT NULL Default '1.gif',
	isLandlord boolean Default false,
	phone varchar(11) NOT NULL,
	primary key(id)
)DEFAULT CHARSET=utf8;


CREATE TABLE admin(
	id char(8) NOT NULL,
	userName varchar(20) NOT NULL,
	psw varchar(20) NOT NULL,
	primary key(id)
)DEFAULT CHARSET=utf8;

CREATE TABLE house(
	id int AUTO_INCREMENT NOT NULL,
	title varchar(30) NOT NULL,
	province varchar(10) NOT NULL,
	city varchar(10) NOT NULL,
	region varchar(10) NOT NULL,
	price double NOT NULL,
	address varchar(100) NOT NULL,
	comment varchar(100) ,
	publisher char(8) NOT NULL,
	count int Default 1,
	primary key(id)
)DEFAULT CHARSET=utf8;

CREATE TABLE notice(
	id int AUTO_INCREMENT NOT NULL,
	userTo char(8) NOT NULL,
	comment varchar(100) NOT NULL,
	isRead boolean NOT NULL Default false,
	primary key(id)

)DEFAULT CHARSET=utf8;


CREATE TABLE order(
	id int AUTO_INCREMENT NOT NULL,
	userId char(8) NOT NULL,
	houseId int NOT NULL,
	confirm int Default 0,
	primary key(id)

)DEFAULT CHARSET=utf8;


CREATE TABLE picture(
	houseId int NOT NULL,
	picPath varchar(100) NOT NULL
)DEFAULT CHARSET=utf8;