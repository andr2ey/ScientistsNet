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
    private int u_id;
    private String u_country;
    private String u_city;
    private String u_full_name;
    private Degree u_degree;

    public University(int u_id, String u_country, String u_city, String u_full_name, Degree u_degree) {
        this.u_id = u_id;
        this.u_country = u_country;
        this.u_city = u_city;
        this.u_full_name = u_full_name;
        this.u_degree = u_degree;
    }

    public University() {
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_country() {
        return u_country;
    }

    public void setU_country(String u_country) {
        this.u_country = u_country;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_full_name() {
        return u_full_name;
    }

    public void setU_full_name(String u_full_name) {
        this.u_full_name = u_full_name;
    }

    public Degree getU_degree() {
        return u_degree;
    }

    public void setU_degree(Degree u_degree) {
        this.u_degree = u_degree;
    }
}
