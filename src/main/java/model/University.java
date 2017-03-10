package model;

/**
 * Created on 07.03.2017.
 */
public class University {
    /* u_id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  u_country         VARCHAR(100) NOT NULL,
  u_city            VARCHAR(100) NOT NULL,
  u_full_name       VARCHAR(255) NOT NULL,
  u_degree          VARCHAR(30) NOT NULL*/
    private int id;
    private String country;
    private String city;
    private String full_name;
    private Degree degree;

    public University(int id, String country, String city, String full_name, Degree degree) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.full_name = full_name;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
