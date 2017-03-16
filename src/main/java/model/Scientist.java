package model;

import java.time.LocalDate;

/**
 * Created on 07.03.2017.
 */
public class Scientist {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String middleName;
    private LocalDate dob;
    private String formattedDob;
    private Gender gender;

    public Scientist(int id, String email, String password, String firstName, String secondName, String middleName,
                     LocalDate dob, Gender gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.dob = dob;
        this.gender = gender;
    }

    public Scientist() {
    }

    public String getFormattedDob() {
        return formattedDob;
    }

    public void setFormattedDob(String formattedDob) {
        this.formattedDob = formattedDob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Builder builder(){
        return this.new Builder();
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

        public Builder setFirstName(String firstName) {
            Scientist.this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(String secondName) {
            Scientist.this.secondName = secondName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            Scientist.this.middleName = middleName;
            return this;
        }
        public Builder setDob(LocalDate dob) {
            Scientist.this.dob = dob;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                '}';
    }
}
