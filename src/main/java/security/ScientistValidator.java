package security;

import org.apache.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Created on 10.03.2017.
 */
public class ScientistValidator {

    private boolean valid = true;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\dA-Za-z]{3,100}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^.+@.+\\..+[^\\.]$");
    //TODO think about name pattern, if it is two spaces
    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\u0410-\\u044F\\d ]{2,100}");
    private static final Pattern GENDER_PATTERN = Pattern.compile("(?i)^(male|female|other)$");
    //TODO think about date pattern, check year, day, month
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private final Logger logger;

    public ScientistValidator(Logger logger) {
        this.logger = logger;
    }

    public ScientistValidator password(String password){
        System.err.println("IN VALIDATOR "+password+valid);
        if (!valid) {
            return this;
        }
        if (password == null) {
            logger.warn("User password is null");
            valid = false;
            return this;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            logger.warn("User password is INVALID");
            valid = false;
        }
        return this;
    }

    public ScientistValidator email(String email){
        System.err.println("IN VALIDATOR "+email+valid);
        if (!valid) {
            return this;
        }
        if (email == null) {
            logger.warn("User email is null");
            valid = false;
            return this;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            logger.warn(String.format("User email - (%s) is INVALID", email));
            valid = false;
        }
        return this;
    }

    public ScientistValidator firstName(String firstName){
        System.err.println("IN VALIDATOR "+firstName+valid);
        if (!valid) {
            return this;
        }
        if (firstName == null) {
            logger.warn("User firstName is null");
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(firstName).matches()) {
            logger.warn(String.format("User firstName - (%s) is INVALID", firstName));
            valid = false;
        }
        return this;
    }

    public ScientistValidator secondName(String secondName){
        System.err.println("IN VALIDATOR "+secondName+valid);
        if (!valid) {
            return this;
        }
        if (secondName == null) {
            logger.warn("User firstName is null");
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(secondName).matches()) {
            logger.warn(String.format("User secondName - (%s) is INVALID", secondName));
            valid = false;
        }
        return this;
    }

    public ScientistValidator middleName(String middleName){
        System.err.println("IN VALIDATOR "+middleName+valid);
        if (!valid) {
            return this;
        }
        if (middleName == null || middleName.isEmpty())
            return this;
        if (!NAME_PATTERN.matcher(middleName).matches()) {
            logger.warn(String.format("User middleName - (%s) is INVALID", middleName));
            valid = false;
        }
        return this;
    }

    public ScientistValidator gender(String gender){
        System.err.println("IN VALIDATOR "+gender+valid);
        if (!valid) {
            return this;
        }
        if (gender == null)
            return this;
        if (!GENDER_PATTERN.matcher(gender).matches()) {
            logger.warn(String.format("User gender - (%s) is INVALID", gender));
            valid = false;
        }
        return this;
    }

    public ScientistValidator birthday(String dob){
        System.err.println("IN VALIDATOR "+dob+valid);
        if (!valid) {
            return this;
        }
        if (dob == null)
            return this;
        if (!DATE_PATTERN.matcher(dob).matches()) {
            logger.warn(String.format("User dob - (%s) is INVALID", dob));
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
