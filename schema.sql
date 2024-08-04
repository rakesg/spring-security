CREATE TABLE users(
	user_id SERIAL NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	enabled boolean NOT NULL DEFAULT FALSE,
	PRIMARY KEY(username)
)

CREATE TABLE authorities(
	user_role_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	FOREIGN KEY (username) REFERENCES users(username)
)

INSERT INTO users(username, password, email, enabled) VALUES('testuser@test.com', '$2a$10$/.m1uyAb4WajO7i6RxQrFuWgASFaGF43S5mskSLoSphrl5ISfErSC', 'testuser@test.com', true);
INSERT INTO users(username, password, email, enabled) VALUES('user@test.com', '$2a$10$x6BMwRwu32SaHdCXd72e7eoY/bEE2ojy0ujrlWV5EkXSAm4Zw5GB.', 'user@test.com', true);
INSERT INTO users(username, password, email, enabled) VALUES('rakesh@test.com', '$2a$10$SmzIYYJIJCF8Vth/F.AmGu1aaYPtrMiE0ggCu.wfSHPVViICActJ2', 'rakesh@test.com', true);

INSERT INTO authorities(username, authority) VALUES('user@test.com', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('testuser@test.com', 'ROLE_ADMIN');
INSERT INTO authorities(username, authority) VALUES('rakesh@test.com', 'ROLE_ADMIN');
