CREATE TABLE USER(
	ID INT(255)  AUTO_INCREMENT,
	USERNAME  CHAR(255) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	EMAIL CHAR(255) ,
	PHONE BIGINT(20),
	ADDRESS CHAR(255),
	ISSUPER INT(1) DEFAULT 0,
	PRIMARY KEY (ID)
	);

CREATE TABLE BOOK(
	BOOKKEY BIGINT(13) NOT NULL,
	BOOKNAME CHAR(255) NOT NULL,
	COST FLOAT(10,2) NOT NULL,
	AUTHOR CHAR(255) NOT NULL,
	PRESS CHAR(255) NOT NULL,
	INTRODUCE TEXT(100),
	DATE DATE NOT NULL,
	AMOUNT INT(11) NOT NULL DEFAULT 0,
	PIC VARCHAR(255) NOT NULL,
	TYPE CHAR(255) NOT NULL,
	PRIMARY KEY(BOOKKEY)
);

CREATE TABLE TROLLEY(
	ID INT AUTO_INCREMENT,
	AMOUNT INT(11) NOT NULL,
	COST FLOAT(10,1) NOT NULL,
	BOOK_BOOKKEY BIGINT(13) NOT NULL,
	USER_ID INT(255),
	PRIMARY KEY(ID),
	FOREIGN KEY(BOOK_BOOKKEY)
		REFERENCES BOOK(BOOKKEY),
	FOREIGN KEY(USER_ID)
		REFERENCES USER(ID)
		ON DELETE RESTRICT
	);

CREATE TABLE BILL(
	ID INT AUTO_INCREMENT,
	AMOUNT INT(11) NOT NULL,
	COST FLOAT(10,1) NOT NULL,
	BOOK_BOOKKEY BIGINT(13) NOT NULL,
	USER_ID INT(255),
	PRIMARY KEY(ID),
	FOREIGN KEY(BOOK_BOOKKEY)
		REFERENCES BOOK(BOOKKEY),
	FOREIGN KEY(USER_ID)
		REFERENCES USER(ID)
		ON DELETE RESTRICT
	);

CREATE TABLE LOGIN(
	ID INT AUTO_INCREMENT,
	USER_ID INT(255) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (USER_ID)
		REFERENCES USER(ID)
	);