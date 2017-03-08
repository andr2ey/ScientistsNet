package model;

import java.time.LocalDate;

/**
 * Created on 07.03.2017.
 */
public class Scientist {
    /*
  s_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  s_password        VARCHAR(255) NOT NULL,
  s_email           VARCHAR(100) NOT NULL UNIQUE,
  s_first_name      VARCHAR(100) NOT NULL,
  s_second_name     VARCHAR(100) NOT NULL,
  s_middle_name     VARCHAR(100),
  s_birthday        DATE,
  university_id   INT,
  degree_id       VARCHAR(100) NOT NULL,

  FOREIGN KEY (university_id) REFERENCES University(s_id),
  FOREIGN KEY (degree_id) REFERENCES Degree(s_id)*/

    public static String FIRST_NAME_KEY = "firstName";
    //s_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    private int s_id;
    //s_password        VARCHAR(255) NOT NULL,
    private String s_password;
    //s_email           VARCHAR(100) NOT NULL UNIQUE,
    private String s_email;
    //s_first_name      VARCHAR(100) NOT NULL,
    private String s_first_name;
    //s_second_name     VARCHAR(100) NOT NULL,
    private String s_second_name;
    //s_middle_name     VARCHAR(100),
    private String s_middle_name;
    //s_birthday        DATE,
    private LocalDate s_birthday;
    //university_id   INT,
    private University s_university;
    //s_gender_id     INT,
    private Gender s_gender;

    public Scientist(int s_id, String s_password, String s_email,
                     String s_first_name, String s_second_name, String s_middle_name,
                     LocalDate s_birthday, University s_university, Gender s_gender) {
        this.s_id = s_id;
        this.s_password = s_password;
        this.s_email = s_email;
        this.s_first_name = s_first_name;
        this.s_second_name = s_second_name;
        this.s_middle_name = s_middle_name;
        this.s_birthday = s_birthday;
        this.s_university = s_university;
        this.s_gender = s_gender;
    }

    public Scientist() {
    }

    public Gender getS_gender() {
        return s_gender;
    }

    public void setS_gender(Gender s_gender) {
        this.s_gender = s_gender;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_first_name() {
        return s_first_name;
    }

    public void setS_first_name(String s_first_name) {
        this.s_first_name = s_first_name;
    }

    public String getS_second_name() {
        return s_second_name;
    }

    public void setS_second_name(String s_second_name) {
        this.s_second_name = s_second_name;
    }

    public String getS_middle_name() {
        return s_middle_name;
    }

    public void setS_middle_name(String s_middle_name) {
        this.s_middle_name = s_middle_name;
    }

    public LocalDate getS_birthday() {
        return s_birthday;
    }

    public void setS_birthday(LocalDate s_birthday) {
        this.s_birthday = s_birthday;
    }

    public University getS_university() {
        return s_university;
    }

    public void setS_university(University s_university) {
        this.s_university = s_university;
    }

    public static Builder newBuilder() {
        return new Scientist().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId(int id) {
            Scientist.this.s_id = id;
            return this;
        }

        public Builder setPassword(String password) {
            Scientist.this.s_password = password;
            return this;
        }

        public Builder setEmail(String email) {
            Scientist.this.s_email = email;
            return this;
        }

        public Builder setFirst_name(String first_name) {
            Scientist.this.s_first_name = first_name;
            return this;
        }

        public Builder setSecond_name(String second_name) {
            Scientist.this.s_second_name = second_name;
            return this;
        }

        public Builder setMiddle_name(String middle_name) {
            Scientist.this.s_middle_name = middle_name;
            return this;
        }
        public Builder setBirthday(LocalDate birthday) {
            Scientist.this.s_birthday = birthday;
            return this;
        }

        public Builder setUniversity(University university) {
            Scientist.this.s_university = university;
            return this;
        }

        public Builder setGender(Gender gender) {
            Scientist.this.s_gender = gender;
            return this;
        }

        public Scientist build() {
            return Scientist.this;
        }

    }
}
