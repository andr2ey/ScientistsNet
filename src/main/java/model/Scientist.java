package model;

import java.time.LocalDate;

/**
 * Created on 07.03.2017.
 */
public class Scientist {
    /*
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password        VARCHAR(255) NOT NULL,
  email           VARCHAR(100) NOT NULL UNIQUE,
  first_name      VARCHAR(100) NOT NULL,
  second_name     VARCHAR(100) NOT NULL,
  middle_name     VARCHAR(100),
  birthday        DATE,
  university_id   INT,
  degree_id       VARCHAR(100) NOT NULL,

  FOREIGN KEY (university_id) REFERENCES University(id),
  FOREIGN KEY (degree_id) REFERENCES Degree(id)*/

    public static String FIRST_NAME_KEY = "firstName";
    //id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    private int id;
    //password        VARCHAR(255) NOT NULL,
    private String password;
    //email           VARCHAR(100) NOT NULL UNIQUE,
    private String email;
    //first_name      VARCHAR(100) NOT NULL,
    private String first_name;
    //second_name     VARCHAR(100) NOT NULL,
    private String second_name;
    //middle_name     VARCHAR(100),
    private String middle_name;
    //birthday        DATE,
    private LocalDate birthday;
    //university_id   INT,
    private University university;

    public Scientist(int id, String password, String email,
                     String first_name, String second_name, String middle_name,
                     LocalDate birthday, University university) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.birthday = birthday;
        this.university = university;
    }

    public Scientist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public static Builder newBuilder() {
        return new Scientist().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId(int id) {
            Scientist.this.id = id;
            return this;
        }

        public Builder setPassword(String password) {
            Scientist.this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            Scientist.this.email = email;
            return this;
        }

        public Builder setFirst_name(String first_name) {
            Scientist.this.first_name = first_name;
            return this;
        }

        public Builder setSecond_name(String second_name) {
            Scientist.this.second_name = second_name;
            return this;
        }

        public Builder setMiddle_name(String middle_name) {
            Scientist.this.middle_name = middle_name;
            return this;
        }
        public Builder setBirthday(LocalDate birthday) {
            Scientist.this.birthday = birthday;
            return this;
        }

        public Builder setUniversity(University university) {
            Scientist.this.university = university;
            return this;
        }

        public Scientist build() {
            return Scientist.this;
        }

    }
}
