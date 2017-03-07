CREATE TABLE University (
  id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  country         VARCHAR(100) NOT NULL,
  city            VARCHAR(100) NOT NULL,
  full_name       VARCHAR(255) NOT NULL,
  degree          VARCHAR(30) NOT NULL
);

CREATE TABLE Scientist (
  id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password        VARCHAR(255) NOT NULL,
  email           VARCHAR(100) NOT NULL UNIQUE,
  first_name      VARCHAR(100) NOT NULL,
  second_name     VARCHAR(100) NOT NULL,
  middle_name     VARCHAR(100),
  birthday        DATE,
  university_id   INT,
  degree_id       VARCHAR(100) NOT NULL,

  FOREIGN KEY (university_id) REFERENCES University(id),
  FOREIGN KEY (degree_id) REFERENCES Degree(id)
);

CREATE TABLE Degree (
  id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name            VARCHAR(100) NOT NULL,
  description     VARCHAR(1000)
);