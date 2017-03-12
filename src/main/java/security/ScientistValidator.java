package security;

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

    public ScientistValidator password(String password){
        if (!valid)
            return this;
        if (password == null) {
            valid = false;
            return this;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches())
            valid = false;
        return this;
    }

    public ScientistValidator email(String email){
        if (!valid)
            return this;
        if (email == null) {
            valid = false;
            return this;
        }
        if (!EMAIL_PATTERN.matcher(email).matches())
            valid = false;
        return this;
    }

    public ScientistValidator firstName(String firstName){
        if (!valid)
            return this;
        if (firstName == null) {
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(firstName).matches())
            valid = false;
        return this;
    }

    public ScientistValidator secondName(String secondName){
        if (!valid)
            return this;
        if (secondName == null) {
            valid = false;
            return this;
        }
        if (!NAME_PATTERN.matcher(secondName).matches())
            valid = false;
        return this;
    }

    public ScientistValidator middleName(String middleName){
        if (!valid)
            return this;
        if (middleName == null)
            return this;
        if (!NAME_PATTERN.matcher(middleName).matches())
            valid = false;
        return this;
    }

    public ScientistValidator gender(String gender){
        if (!valid)
            return this;
        if (gender == null)
            return this;
        if (!GENDER_PATTERN.matcher(gender).matches())
            valid = false;
        return this;
    }

    public ScientistValidator birthday(String dob){
        if (!valid)
            return this;
        if (dob == null)
            return this;
        if (!DATE_PATTERN.matcher(dob).matches())
            valid = false;
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
