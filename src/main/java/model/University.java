package model;

import java.time.LocalDate;

/**
 * Created on 07.03.2017.
 */
public class University implements Cloneable{

    private int id;
    private int scientistId;
    private String country;
    private String city;
    private String fullName;
    private Degree degree;
    private int graduationYear;
    private boolean deleted;
    private boolean created;
    private boolean updated;

    public University() {
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScientistId() {
        return scientistId;
    }

    public void setScientistId(int scientistId) {
        this.scientistId = scientistId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Builder builder(){
        return this.new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setId(int id) {
            University.this.id = id;
            return this;
        }

        public Builder setScientistId(int scientistId) {
            University.this.scientistId = scientistId;
            return this;
        }

        public Builder setCountry(String country) {
            University.this.country = country;
            return this;
        }

        public Builder setCity(String city) {
            University.this.city = city;
            return this;
        }

        public Builder setFullName(String fullName) {
            University.this.fullName = fullName;
            return this;
        }

        public Builder setDegree(Degree degree) {
            University.this.degree = degree;
            return this;
        }

        public Builder setGraduationYear(int graduationYear) {
            University.this.graduationYear = graduationYear;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            University.this.deleted = deleted;
            return this;
        }

        public Builder setCreated(boolean created) {
            University.this.created = created;
            return this;
        }

        public Builder setUpdated(boolean updated) {
            University.this.updated = updated;
            return this;
        }

        public University build() {
            return University.this;
        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
