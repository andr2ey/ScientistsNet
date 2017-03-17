package security;

import model.Gender;
import org.apache.log4j.Logger;
import util.Const;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created on 10.03.2017.
 */
public class ScientistValidator {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\dA-Za-z]{3,100}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^.+@.+\\..+[^\\.]$");
    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\u0410-\\u044F\\d\\-\\., ]{2,100}");
    private static final Pattern GENDER_PATTERN = Pattern.compile("(?i)^(male|female|none)$");

    private boolean valid = true;

    private String validPassword;
    private String validEmail;
    private String validFirstName;
    private String validSecondName;
    private String validMiddleName;
    private Gender validGender = Gender.NONE;
    private LocalDate validDate;

    private String validNewPassword;
    private String validNewEmail;

    public ScientistValidator() {
    }

    public String getValidNewPassword() {
        return validNewPassword;
    }

    public String getValidNewEmail() {
        return validNewEmail;
    }

    public String getValidPassword() {
        return validPassword;
    }

    public String getValidEmail() {
        return validEmail;
    }

    public String getValidFirstName() {
        return validFirstName;
    }

    public String getValidSecondName() {
        return validSecondName;
    }

    public String getValidMiddleName() {
        return validMiddleName;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public Gender getValidGender() {
        return validGender;
    }

    public boolean validateRegistrationFields(HttpServletRequest req) {
        if (!dob(req.getParameter("day"),
                req.getParameter("month"),
                req.getParameter("year"), req)
                .email(req.getParameter("emailNew"), req)
                .password(req.getParameter("passwordNew"), req)
                .firstName(req.getParameter("first_name"), req)
                .secondName(req.getParameter("second_name"), req)
                .isValid()) {
            req.setAttribute("first_name", req.getParameter("first_name"));
            req.setAttribute("second_name", req.getParameter("second_name"));
            return false;
        }
        return true;
    }

    public boolean validateBaseInfoFields(HttpServletRequest req) {
        if (!dob(req.getParameter("day"),
                req.getParameter("month"),
                req.getParameter("year"), req)
                .emailNew(req.getParameter("emailNew"), req)
                .passwordNew(req.getParameter("passwordNew"), req)
                .firstName(req.getParameter("first_name"), req)
                .secondName(req.getParameter("second_name"), req)
                .secondName(req.getParameter("middle_name"), req)
                .gender(req.getParameter("gender"), req)
                .password(req.getParameter("password"), req)
                .isValid()) {
            req.setAttribute("first_name", req.getParameter("first_name"));
            req.setAttribute("second_name", req.getParameter("second_name"));
            req.setAttribute("middle_name", req.getParameter("middle_name"));
            req.setAttribute("gender", req.getParameter("gender"));
            req.setAttribute("day", req.getParameter("day"));
            req.setAttribute("month", req.getParameter("month"));
            req.setAttribute("year", req.getParameter("year"));
            req.setAttribute("emailNew", req.getParameter("emailNew"));
            return false;
        }
        return true;
    }

    private ScientistValidator dob(String day, String month, String year, HttpServletRequest req){
        if (!valid || nullable(day, month, year)) {
            validDate = null;
            valid = false;
            return this;
        }
        try {
            int yearInt = Integer.parseInt(year.trim());
            LocalDate dob = LocalDate.of(yearInt, Integer.parseInt(month.trim()), Integer.parseInt(day.trim()));
            LocalDate difference = LocalDate.now().minusYears(yearInt);
            int yearDiffer = difference.getYear();
            if (yearDiffer > Const.TOP_EDGE_OF_AGE || yearDiffer < Const.BOTTOM_EDGE_OF_AGE) {
                validDate = null;
                valid = false;
              req.setAttribute(Const.DATE_INPUT_ERROR, "Date is incorrect!");
            } else {
                validDate = dob;
            }
        } catch (NumberFormatException | DateTimeException e) {
            validDate = null;
            valid = false;
            req.setAttribute(Const.DATE_INPUT_ERROR, "Date is incorrect!");
        }
        return this;
    }

    private boolean nullable(String day, String month, String year) {
        return day == null || month == null || year == null;
    }

    private ScientistValidator email(String email, HttpServletRequest req){
        if (!valid || email == null || email.isEmpty()) {
            validEmail = null;
            valid = false;
            return this;
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            validEmail = null;
            valid = false;
            req.setAttribute(Const.EMAIL_INPUT_ERROR, "Email is incorrect!");
            return this;
        }
        validEmail = email;
        return this;
    }

    private ScientistValidator emailNew(String emailNew, HttpServletRequest req){
        if (!valid || emailNew == null || emailNew.isEmpty()) {
            validEmail = null;
            return this;
        }
        if (!EMAIL_PATTERN.matcher(emailNew.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.EMAIL_INPUT_ERROR, "Email is incorrect!");
        }
        validEmail = emailNew;
        return this;
    }

    private ScientistValidator password(String password, HttpServletRequest req){
        if (!valid || password == null || password.isEmpty()) {
            valid = false;
            validPassword = null;
            return this;
        }
        if (!PASSWORD_PATTERN.matcher(password.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.PASSWORD_INPUT_ERROR, "Password is incorrect!");
            validPassword = null;
            return this;
        }
        validPassword = password;
        return this;
    }

    private ScientistValidator passwordNew(String passwordNew, HttpServletRequest req){
        if (!valid || passwordNew == null || passwordNew.isEmpty()) {
            validNewPassword = null;
            return this;
        }
        if (!PASSWORD_PATTERN.matcher(passwordNew.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.PASSWORD_INPUT_ERROR, "Password is incorrect!");
            validNewPassword = null;
            return this;
        }
        validPassword = passwordNew;
        return this;
    }

    private ScientistValidator firstName(String firstName, HttpServletRequest req){
        if (!valid || firstName == null || firstName.isEmpty()) {
            valid = false;
            validFirstName = null;
            return this;
        }
        if (!NAME_PATTERN.matcher(firstName.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.FIRST_NAME_INPUT_ERROR, "First name is incorrect!");
            validFirstName = null;
            return this;
        }
        validFirstName = firstName;
        return this;
    }

    private ScientistValidator secondName(String secondName, HttpServletRequest req){
        if (!valid || secondName == null || secondName.isEmpty()) {
            valid = false;
            validSecondName = null;
            return this;
        }
        if (!NAME_PATTERN.matcher(secondName.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.SECOND_NAME_INPUT_ERROR, "Second name is incorrect!");
            validSecondName = null;
            return this;
        }
        validSecondName = secondName;
        return this;
    }

    private ScientistValidator middleName(String middleName, HttpServletRequest req){
        if (!valid || middleName == null || middleName.isEmpty()) {
            validMiddleName = null;
            return this;
        }
        if (!NAME_PATTERN.matcher(middleName.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.MIDDLE_NAME_INPUT_ERROR, "Middle name is incorrect!");
            validMiddleName = null;
            return this;
        }
        validMiddleName = middleName;
        return this;
    }

    private ScientistValidator gender(String gender, HttpServletRequest req) {
        if (!valid || gender == null || gender.isEmpty()) {
            validGender = Gender.NONE;
            return this;
        }
        if (!GENDER_PATTERN.matcher(gender.trim()).matches()) {
            valid = false;
            req.setAttribute(Const.GENDER_INPUT_ERROR, "Gender is incorrect!");
            validGender = Gender.NONE;
            return this;
        }
        validGender = Gender.valueOf(gender.trim().toUpperCase());
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
