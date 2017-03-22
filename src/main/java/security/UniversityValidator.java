package security;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created on 14.03.2017.
 */
public class UniversityValidator {

    private boolean valid = true;
    //TODO think about name pattern, if it is two spaces
    private final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\u0410-\\u044F\\d\\- ]{2,100}");
    private final Pattern DEGREE_PATTERN = Pattern.compile("(?i)^(bachelor|master|specialist)$");

    public UniversityValidator() {
    }

    public boolean validateChangedFields(HttpServletRequest req) {

    }

    //TODO delete all system err
    public UniversityValidator country(String country){
        if (!valid) {
            return this;
        }
        if (country == null) {
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(country).matches()) {
            valid = false;
        }
        return this;
    }

    public UniversityValidator city(String city){
        if (!valid) {
            return this;
        }
        if (city == null) {
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(city).matches()) {
            valid = false;
        }
        return this;
    }

    public UniversityValidator fullName(String fullName){
        if (!valid) {
            return this;
        }
        if (fullName == null) {
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(fullName).matches()) {
            valid = false;
        }
        return this;
    }

    public UniversityValidator degree(String degree){
        if (!valid) {
            return this;
        }
        if (degree == null) {
            logger.warn("Scientist degree is null");
            valid = false;
            return this;
        }
        if (!DEGREE_PATTERN.matcher(degree).matches()) {
            valid = false;
        }
        return this;
    }

    public boolean isValid() {
        if (!valid) {
            valid = true;
            return false;
        }
        return true;
    }


}