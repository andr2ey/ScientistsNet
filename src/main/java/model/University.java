package model;

/**
 * Created on 07.03.2017.
 */
public class University {

    private int id;
    private int scientistId;
    private String country;
    private String city;
    private String fullName;
    private Degree degree;

    public University(int id, int scientistId, String country, String city, String fullName, Degree degree) {
        this.id = id;
        this.scientistId = scientistId;
        this.country = country;
        this.city = city;
        this.fullName = fullName;
        this.degree = degree;
    }

    public University() {
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

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", scientistId=" + scientistId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", fullName='" + fullName + '\'' +
                ", degree=" + degree +
                '}';
    }
}
