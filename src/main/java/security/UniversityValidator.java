package security;

import model.Degree;
import org.apache.log4j.Logger;
import util.Const;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created on 14.03.2017.
 */
public class UniversityValidator {

    private boolean valid = true;
    //TODO think about name pattern, if it is two spaces
    private final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\u0410-\\u044F\\\\d\\-\\\" ]{2,100}");
    private final Pattern DEGREE_PATTERN = Pattern.compile("(?i)^(bachelor|master|specialist)$");

    private String validCountry;
    private String validCity;
    private String validFullName;
    private Degree validDegree = Degree.BACHELOR;
    private int validYear = Const.MIN_GRADUATION_YEAR;

    public UniversityValidator() {
    }

    public String getValidCountry() {
        return validCountry;
    }

    public String getValidCity() {
        return validCity;
    }

    public String getValidFullName() {
        return validFullName;
    }

    public Degree getValidDegree() {
        return validDegree;
    }

    public int getValidYear() {
        return validYear;
    }

    public boolean validateChangedFields(HttpServletRequest req, int indexUpdated) {
        return  country(req.getParameter("education_country" + indexUpdated))
                .city(req.getParameter("education_city" + indexUpdated))
                .fullName(req.getParameter("education_fullName" + indexUpdated))
                .degree(req.getParameter("education_degree" + indexUpdated))
                .year(req.getParameter("education_year" + indexUpdated))
                .isValid();
    }

    public boolean validateAddedFields(HttpServletRequest req) {
        return  country(req.getParameter("education_country"))
                .city(req.getParameter("education_city"))
                .fullName(req.getParameter("education_fullName"))
                .degree(req.getParameter("education_degree"))
                .year(req.getParameter("education_year")).isValid();
    }

    private UniversityValidator country(String country){
        if (!valid || country == null || country.isEmpty() ||
                !NAME_PATTERN.matcher(country.trim()).matches()) {
            valid = false;
            validCountry = null;
            return this;
        }
        validCountry = country.trim();
        return this;
    }

    private UniversityValidator city(String city){
        if (!valid || city == null || city.isEmpty() ||
                !NAME_PATTERN.matcher(city.trim()).matches()) {
            valid = false;
            validCity = null;
            return this;
        }
        validCity = city.trim();
        return this;
    }

    private UniversityValidator fullName(String fullName){
        if (!valid || fullName == null || fullName.isEmpty() ||
                !NAME_PATTERN.matcher(fullName.trim()).matches()) {
            valid = false;
            validFullName = null;
            return this;
        }
        validFullName = fullName.trim();
        return this;
    }

    private UniversityValidator degree(String degree){
        if (!valid || degree == null || degree.isEmpty() ||
                !DEGREE_PATTERN.matcher(degree.trim()).matches()) {
            validDegree = Degree.BACHELOR;
            valid = false;
            return this;
        }
        validDegree = Degree.valueOf(degree.trim().toUpperCase());
        return this;
    }

    private UniversityValidator year(String year){
        if (!valid || year == null || year.isEmpty()) {
            validYear = Const.MIN_GRADUATION_YEAR;
            valid = false;
            return this;
        }
        int yearInt = Const.MIN_GRADUATION_YEAR;
        try {
            yearInt = Integer.parseInt(year.trim());
            if (yearInt < Const.MIN_GRADUATION_YEAR ||
                    yearInt > (LocalDate.now().getYear() + Const.MAX_EDUCATION_TIME)) {
                validYear = Const.MIN_GRADUATION_YEAR;
                valid = false;
                return this;
            }
        } catch (NumberFormatException e) {
            validYear = yearInt;
            valid = false;
            return this;
        }
        validYear = yearInt;
        return this;
    }

    private boolean isValid() {
        if (!valid) {
            valid = true;
            return false;
        }
        return true;
    }

}