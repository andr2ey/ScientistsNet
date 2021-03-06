DROP DATABASE scientists;
CREATE DATABASE scientists
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE scientists;

CREATE TABLE degree (
  d_id          INT          NOT NULL AUTO_INCREMENT,
  d_name        VARCHAR(100) NOT NULL,
  d_description VARCHAR(1000),

  PRIMARY KEY (d_id)
);

CREATE TABLE gender (
  g_id   INT          NOT NULL AUTO_INCREMENT,
  g_name VARCHAR(100) NOT NULL,

  PRIMARY KEY (g_id)
);

CREATE TABLE field (
  f_id   INT          NOT NULL AUTO_INCREMENT,
  f_name VARCHAR(100) NOT NULL,

  PRIMARY KEY (f_id)
);

CREATE TABLE scientist (
  s_id          INT          NOT NULL AUTO_INCREMENT,
  s_email       VARCHAR(100) NOT NULL,
  s_password    VARCHAR(100) NOT NULL,
  s_first_name  VARCHAR(100) NOT NULL,
  s_second_name VARCHAR(100) NOT NULL,
  s_middle_name VARCHAR(100),
  s_dob         DATE,
  s_gender_id   INT,
  s_field_id    INT          NOT NULL,

  UNIQUE (s_email),
  PRIMARY KEY (s_id),
  FOREIGN KEY (s_gender_id) REFERENCES gender (g_id),
  FOREIGN KEY (s_field_id) REFERENCES field (f_id)
);

CREATE TABLE university (
  u_id              INT AUTO_INCREMENT,
  u_scientist_id    INT           NOT NULL,
  u_country         VARCHAR(100)  NOT NULL,
  u_city            VARCHAR(100)  NOT NULL,
  u_full_name       VARCHAR(1000) NOT NULL,
  u_graduation_year INT           NOT NULL,
  u_degree_id       INT           NOT NULL,

  PRIMARY KEY (u_id),
  FOREIGN KEY (u_degree_id) REFERENCES degree (d_id)
);

CREATE TABLE messages (
  m_id   INT AUTO_INCREMENT,
  m_from VARCHAR(100)  NOT NULL,
  m_to   VARCHAR(100)  NOT NULL,
  m_text VARCHAR(2000) NOT NULL,
  m_datetime DATETIME(3)   NOT NULL,

  PRIMARY KEY (m_id)
);

CREATE TABLE roles (
  s_email VARCHAR(100) NOT NULL,
  r_name  VARCHAR(25)  NOT NULL,

  PRIMARY KEY (s_email, r_name),
  FOREIGN KEY (s_email) REFERENCES scientist (s_email)
);