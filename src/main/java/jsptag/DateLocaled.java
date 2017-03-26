package jsptag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;


//    private String formatDate(Locale locale, LocalDate localDate) {
//        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
//        return df.format(Date.valueOf(localDate));
//    }

public class DateLocaled extends TagSupport {

    private LocalDate dob;

    public DateLocaled() {
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public int doStartTag() throws JspException {
        Locale locale = pageContext.getRequest().getLocale();
//        LocalDate dob = ((Scientist)pageContext.getSession().getAttribute(Const.EMAIL_KEY)).getDob();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        try {
            pageContext.getOut().write(df.format(Date.valueOf(dob)));
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }
}
