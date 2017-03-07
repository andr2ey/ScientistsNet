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

CREATE TABLE Gender (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL
);

CREATE TABLE Scientist (
  id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email         VARCHAR(100) NOT NULL PRIMARY KEY,
  password      VARCHAR(255) NOT NULL,
  first_name    VARCHAR(100) NOT NULL,
  second_name   VARCHAR(100) NOT NULL,
  middle_name   VARCHAR(100),
  birthday      DATE,
  gender_id     INT,
  university_id INT,

  FOREIGN KEY (gender_id) REFERENCES University (id),
  FOREIGN KEY (university_id) REFERENCES University (id),
  UNIQUE (email),
  PRIMARY KEY (id, email)
);

CREATE TABLE Roles (
  email VARCHAR(100) NOT NULL,
  role  VARCHAR(25) NOT NULL,
  PRIMARY KEY (email, role),
  FOREIGN KEY (email) REFERENCES Scientist (email)
)