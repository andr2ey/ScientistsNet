CREATE TABLE University (
  id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  country   VARCHAR(100) NOT NULL,
  city      VARCHAR(100) NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  degree_id     INT,

  FOREIGN KEY (degree_id) REFERENCES Degree (id)
);

CREATE TABLE Degree (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(1000)
);

CREATE TABLE Scientist (
  id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password      VARCHAR(255) NOT NULL,
  email         VARCHAR(100) NOT NULL PRIMARY KEY,
  first_name    VARCHAR(100) NOT NULL,
  second_name   VARCHAR(100) NOT NULL,
  middle_name   VARCHAR(100),
  birthday      DATE,
  university_id INT,

  FOREIGN KEY (university_id) REFERENCES University (id),
  UNIQUE (email),
  PRIMARY KEY (id, email)
);

CREATE TABLE user_roles (
  email VARCHAR(100) NOT NULL,
  role  VARCHAR(25) NOT NULL,
  PRIMARY KEY (email, role)
)