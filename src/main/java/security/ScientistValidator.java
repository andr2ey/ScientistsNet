package security;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created on 10.03.2017.
 */
public class ScientistValidator {

    private boolean valid = true;
    private static final String PASSWORD_PATTERN = "^[\\dA-Za-z]{3,100}$";
    private static final String EMAIL_PATTERN = "^.+@.+\\..+[^\\.]$";
    //TODO think about name pattern
    private static final String NAME_PATTERN = ".+";
    private static final String GENDER_PATTERN = "(?i)^(male|female)$";

    public ScientistValidator password(String password){
        if (!valid)
            return this;
        if (password == null) {
            valid = false;
            return this;
        }
        if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches())
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
        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches())
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
        if (!Pattern.compile(NAME_PATTERN).matcher(firstName).matches())
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
        if (!Pattern.compile(NAME_PATTERN).matcher(secondName).matches())
            valid = false;
        return this;
    }

    public ScientistValidator middleName(String middleName){
        if (!valid)
            return this;
        if (middleName == null)
            return this;
        if (!Pattern.compile(NAME_PATTERN).matcher(middleName).matches())
            valid = false;
        return this;
    }

    public ScientistValidator gender(String gender){
        if (!valid)
            return this;
        if (gender == null)
            return this;
        if (!Pattern.compile(GENDER_PATTERN).matcher(gender).matches())
            valid = false;
        return this;
    }

    //TODO verify date
    public ScientistValidator birthday(LocalDate date){
        if (!valid)
            return this;
        if (date == null)
            return this;
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
