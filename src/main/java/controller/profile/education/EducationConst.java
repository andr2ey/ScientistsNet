package controller.profile.education;

public interface EducationConst {
    int MAX_UNIVERSITIES = 10;

    String MAX_UNIVERSITIES_KEY = "maxUniversity";
    String SUCCESS_OF_TRANSACTION = "successOfTransaction";
    String UPDATED_STATUS = "updatedStatus";

    String BUTTON_DELETE_EDUCATION = "button_delete_education";
    String BUTTON_UPDATE_EDUCATION = "button_update_education";
    String BUTTON_ADD_EDUCATION = "button_add_education";
    String BUTTON_SAVE_EDUCATION = "button_save_education";

    String MAX_GRADUATION_YEAR_KEY = "maxGraduationYear";
    String MIN_GRADUATION_YEAR_KEY = "minGraduationYear";
    int MIN_GRADUATION_YEAR = 1917;
    int MAX_EDUCATION_TIME = 10; //to calculate MAX_GRADUATION_YEAR
}
