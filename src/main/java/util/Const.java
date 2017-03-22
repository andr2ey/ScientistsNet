package util;

/**
 * Created on 14.03.2017.
 */
public interface Const {

    //session
    String EMAIL_KEY = "email";
    String UNIVERSITIES_KEY = "universities";
    String UNMODIFIED_UNIVERSITIES_KEY = "unmodifiedUniversities";
    String UNIVERSITIES_CHANGED = "universitiesChanged";

    //context
    String SCIENTIST_SERVICE = "ScientistService";
    String UNIVERSITY_SERVICE = "UniversityService";
    String MESSAGE_SERVICE = "MessageService";

    //thread pool
    String THREAD_POOL = "threadPool";
    int NUMBER_OF_THREADS = 10;

    //request validate names
    String DATE_INPUT_ERROR = "dateInputError";
    String EMAIL_INPUT_ERROR = "emailInputError";
    String PASSWORD_INPUT_ERROR = "passwordInputError";
    String FIRST_NAME_INPUT_ERROR = "firstNameInputError";
    String SECOND_NAME_INPUT_ERROR = "secondNameInputError";
    String MIDDLE_NAME_INPUT_ERROR = "middleNameInputError";
    String GENDER_INPUT_ERROR = "middleNameInputError";
    String FIELD_OF_SCIENCE_INPUT_ERROR = "fieldOfScienceInputError";

    String CONFIRM_PASSWORD_ERROR = "confirmPasswordError";

    //education
    String SUCCESS_OF_TRANSACTION = "successOfTransaction";
    String UPDATED_STATUS = "updatedStatus";
    int MAX_UNIVERSITIES = 10;
    String MAX_GRADUATION_YEAR_KEY = "maxGraduationYear";
    String MIN_GRADUATION_YEAR_KEY = "minGraduationYear";
    int MIN_GRADUATION_YEAR = 1900;
    int MAX_EDUCATION_TIME = 10;


    //registration
    String VALID_USER_KEY = "validUser";
    String CREATED_USER_KEY = "createdUser";
    String MAIN_PAGE_FULL_PATH = "WEB-INF/main/index.jsp";
    String REGISTRATION_PAGE_FULL_PATH = "WEB-INF/registration/index.jsp";
    String MAIN_PAGE = "/main";
    int TOP_EDGE_OF_AGE = 110;
    int BOTTOM_EDGE_OF_AGE = 14;
    String MAX_YEAR_KEY = "maxYear";
    String MIN_YEAR_KEY = "minYear";
    int INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES = MAX_UNIVERSITIES;

}
