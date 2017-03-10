package model;

import java.time.LocalDate;

/**
 * Created on 07.03.2017.
 */
public class Scientist {

    public static String FIRST_NAME_KEY = "firstName";
    //s_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    private int id;
    //s_password        VARCHAR(255) NOT NULL,
    private String password;
    //s_email           VARCHAR(100) NOT NULL UNIQUE,
    private String email;
    //s_first_name      VARCHAR(100) NOT NULL,
    private String firstName;
    //s_second_name     VARCHAR(100) NOT NULL,
    private String secondName;
    //s_middle_name     VARCHAR(100),
    private String middleName;
    //s_birthday        DATE,
    private LocalDate birthday;
    //university_id   INT,
    private University university;
    //s_gender_id     INT,
    private Gender gender;

    public Scientist(int id, String password, String email,
                     String firstName, String secondName, String middleName,
                     LocalDate birthday, University university, Gender gender) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.university = university;
        this.gender = gender;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

        public Builder setFirst_name(String firstName) {
            Scientist.this.firstName = firstName;
            return this;
        }

        public Builder setSecond_name(String secondName) {
            Scientist.this.secondName = secondName;
            return this;
        }

        public Builder setMiddle_name(String middleName) {
            Scientist.this.middleName = middleName;
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

        public Builder setGender(Gender gender) {
            Scientist.this.gender = gender;
            return this;
        }

        public Scientist build() {
            return Scientist.this;
        }

    }

    @Override
    public String toString() {
        return "Scientist{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", university=" + university +
                ", gender=" + gender +
                '}';
    }
}
