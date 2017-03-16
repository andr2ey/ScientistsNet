package security;

import org.apache.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Created on 14.03.2017.
 */
public class UniversityValidator {

    private boolean valid = true;
    //TODO think about name pattern, if it is two spaces
    private final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\u0410-\\u044F\\d\\- ]{2,100}");
    private final Pattern DEGREE_PATTERN = Pattern
            .compile("(?i)^(bachelor|master|specialist|бакалавр|магистр|специалист)$");
    //TODO think about date pattern, check year, day, month
    private final Logger logger = Logger.getRootLogger();

    public UniversityValidator() {
    }

    public UniversityValidator verifyAll(String[] param) {
        country(param[0]);
        city(param[1]);
        fullName(param[2]);
        degree(param[3]);
        return this;
    }

    //TODO delete all system err
    public UniversityValidator country(String country){
        System.err.println("IN VALIDATOR " + country + " " + valid);
        if (!valid) {
            return this;
        }
        if (country == null) {
            logger.warn("Scientist country is null");
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(country).matches()) {
            logger.warn(String.format("Scientist country - (%s) is INVALID", country));
            valid = false;
        }
        return this;
    }

    public UniversityValidator city(String city){
        System.err.println("IN VALIDATOR " + city + " " + valid);
        if (!valid) {
            return this;
        }
        if (city == null) {
            logger.warn("Scientist city is null");
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(city).matches()) {
            logger.warn(String.format("Scientist city - (%s) is INVALID", city));
            valid = false;
        }
        return this;
    }

    public UniversityValidator fullName(String fullName){
        System.err.println("IN VALIDATOR " + fullName + " " + valid);
        if (!valid) {
            return this;
        }
        if (fullName == null) {
            logger.warn("Scientist fullName is null");
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(fullName).matches()) {
            logger.warn(String.format("Scientist fullName - (%s) is INVALID", fullName));
            valid = false;
        }
        return this;
    }

    public UniversityValidator degree(String degree){
        System.err.println("IN VALIDATOR " + degree + " " + degree);
        if (!valid) {
            return this;
        }
        if (degree == null) {
            logger.warn("Scientist degree is null");
            valid = false;
            return this;
        }
        if (!DEGREE_PATTERN.matcher(degree).matches()) {
            logger.warn(String.format("Scientist degree - (%s) is INVALID", degree));
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