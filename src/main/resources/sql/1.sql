DROP DATABASE IF EXISTS scientists;
CREATE DATABASE scientists;
USE scientists;

CREATE TABLE degree (
  d_id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  d_name        VARCHAR(100) NOT NULL,
  d_description VARCHAR(1000)
);

CREATE TABLE university (
  u_id INT NOT NULL AUTO_INCREMENT,
  u_country VARCHAR(100) NOT NULL,
  u_city VARCHAR(100) NOT NULL,
  u_full_name VARCHAR(255) NOT NULL,
  u_degree_id INT,

  PRIMARY KEY (u_id),
  FOREIGN KEY (u_degree_id) REFERENCES degree (d_id)
);

CREATE TABLE gender (
  g_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  g_name VARCHAR(100) NOT NULL
);

CREATE TABLE scientist (
  s_id            INT          NOT NULL AUTO_INCREMENT,
  s_email         VARCHAR(100) NOT NULL,
  s_password      VARCHAR(255) NOT NULL,
  s_first_name    VARCHAR(100) NOT NULL,
  s_second_name   VARCHAR(100) NOT NULL,
  s_middle_name   VARCHAR(100),
  s_birthday      DATE,
  s_gender_id     INT,
  s_university_id INT,

  UNIQUE (s_email),
  PRIMARY KEY (s_id, s_email),
  FOREIGN KEY (s_gender_id) REFERENCES gender (g_id),
  FOREIGN KEY (s_university_id) REFERENCES university (u_id)
);

CREATE TABLE roles (
  s_email VARCHAR(100) NOT NULL,
  r_name  VARCHAR(25)  NOT NULL,

  PRIMARY KEY (s_email, r_name),
  FOREIGN KEY (s_email) REFERENCES Scientist (s_email)
);