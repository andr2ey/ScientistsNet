<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>
<fmt:message bundle="${lang}" key="lang.log.out.button" var="log_out_button"/>

<fmt:message bundle="${lang}" key="lang.txt.success" var="txt_success"/>
<fmt:message bundle="${lang}" key="lang.message.success" var="message_success"/>
<fmt:message bundle="${lang}" key="lang.txt.fail" var="txt_fail"/>
<fmt:message bundle="${lang}" key="lang.message.deleted" var="message_deleted"/>
<fmt:message bundle="${lang}" key="lang.message.max.universities" var="message_max_universities"/>

<fmt:message bundle="${lang}" key="lang.high.education" var="high_education"/>
<fmt:message bundle="${lang}" key="lang.message.about.fields" var="message_about_fields"/>
<fmt:message bundle="${lang}" key="lang.button.delete" var="delete_button"/>
<fmt:message bundle="${lang}" key="lang.button.update" var="update_button"/>
<fmt:message bundle="${lang}" key="lang.button.add" var="add_button"/>
<fmt:message bundle="${lang}" key="lang.button.save" var="save_button"/>

<fmt:message bundle="${lang}" key="lang.country" var="txt_country"/>
<fmt:message bundle="${lang}" key="lang.city" var="txt_city"/>
<fmt:message bundle="${lang}" key="lang.university" var="txt_university"/>
<fmt:message bundle="${lang}" key="lang.degree" var="txt_degree"/>

<fmt:message bundle="${lang}" key="lang.button.my.profile" var="button_my_profile"/>
<fmt:message bundle="${lang}" key="lang.button.search" var="button_search"/>
<fmt:message bundle="${lang}" key="lang.button.messages" var="button_messages"/>
<fmt:message bundle="${lang}" key="lang.button.articles" var="button_articles"/>

<fmt:message bundle="${lang}" key="lang.bachelor" var="bachelor"/>
<fmt:message bundle="${lang}" key="lang.master" var="master"/>
<fmt:message bundle="${lang}" key="lang.specialist" var="specialist"/>
<fmt:message bundle="${lang}" key="lang.txt.graduation.year" var="txt_graduation_date"/>

<fmt:message bundle="${lang}" key="lang.january" var="january"/>
<fmt:message bundle="${lang}" key="lang.february" var="february"/>
<fmt:message bundle="${lang}" key="lang.march" var="march"/>
<fmt:message bundle="${lang}" key="lang.april" var="april"/>
<fmt:message bundle="${lang}" key="lang.may" var="may"/>
<fmt:message bundle="${lang}" key="lang.june" var="june"/>
<fmt:message bundle="${lang}" key="lang.july" var="july"/>
<fmt:message bundle="${lang}" key="lang.august" var="august"/>
<fmt:message bundle="${lang}" key="lang.september" var="september"/>
<fmt:message bundle="${lang}" key="lang.october" var="october"/>
<fmt:message bundle="${lang}" key="lang.november" var="november"/>
<fmt:message bundle="${lang}" key="lang.december" var="december"/>

<fmt:message bundle="${lang}" key="lang.day.birthday" var="day_birthday"/>
<fmt:message bundle="${lang}" key="lang.year.birthday" var="year_birthday"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ScientistsNet</title>
    <style>
        body {
            font: 14pt Arial, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
            background: #f0f0f0;
        }

        h1 {
            font-size: 24px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #f0f0f0; /* Цвет текста */
            text-align: center;
        }

        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }

        h3 {
            font-size: 24px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #690005; /* Цвет текста */
            text-align: center;
        }
        h4 {
            font-size: 16px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #690005; /* Цвет текста */
            text-align: center;
        }
        #header { /* Верхний блок */
            background: #690005; /* Цвет фона */
            padding: 4px; /* Поля вокруг текста */
            border: solid 1px #000000; /* Параметры рамки вокруг */
        }
        #sidebar { /* Левая колонка */
            background: #e0e0e0; /* Цвет фона */
            border: solid 1px #898989; /* Параметры рамки вокруг */
            width: 200px; /* Ширина колонки */
            margin-top: 7px;
            padding: 5px; /* Поля вокруг текста */
            border-radius: 3px;
        }
        .text_year {
            width: 80px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 11pt Arial, sans-serif;
        }
        .text {
            width: 320px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 11pt Arial, sans-serif;
        }
        #contentBaseInfo { /* Правая колонка */
            background: #e0e0e0; /* Цвет фона */
            border: solid 1px #898989; /* Параметры рамки вокруг */
            width: 600px; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
            border-radius: 3px;
        }
        #contentEducation { /* Правая колонка */
            background: #e0e0e0; /* Цвет фона */
            border: solid 1px #898989; /* Параметры рамки вокруг */
            width: 600px; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
            border-radius: 3px;
        }
        .button_lang { /* Кнопка регистрации */
            background: #f0f0f0;
            border: solid 1px #494949;
            border-radius: 3px;
            color: #890005;
            padding: 4px 4px;
            font: 8pt Arial, sans-serif;
            cursor: pointer;
        }
        .button_of_profile { /* Кнопка регистрации */
            background: #494949;
            border: solid 1px #494949;
            border-radius: 3px;
            color: #f0f0f0;
            padding: 11px 5px;
            width: 70%;
            text-align: center;
            text-decoration: none;
            font: 12pt Arial, sans-serif;
            margin: 0;
            cursor: pointer;
        }
        .button_add { /* Кнопка регистрации */
            background: #690005;
            border: solid 1px #494949;
            border-radius: 3px;
            color: #f0f0f0;
            padding: 11px 5px;
            width: 30%;
            text-align: center;
            text-decoration: none;
            font: 12pt Arial, sans-serif;
            margin: 0;
            cursor: pointer;
        }
        .button_save { /* Кнопка регистрации */
            background: #0f6100;
            border: solid 1px #636363;
            border-radius: 3px;
            color: #f0f0f0;
            padding: 11px 5px;
            width: 30%;
            text-align: center;
            text-decoration: none;
            font: 12pt Arial, sans-serif;
            margin: 0;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="header">
    <table border="0" width="100%" cellpadding="0">
        <tr>
            <td valign="center" align="left" width="20%">
                <form action="/main" method="post">
                    <input type="submit" class="button_lang" name="logout" value="${log_out_button}">
                </form>
            </td>
            <td valign="center" align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td valign="center" align="right" width="20%">
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/main/education">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/main/education">
                    <input type="hidden" name="lang" value="ru">
                    <input type="submit" name="button_lang" class="button_lang" value="${ru_button}">
                </form>
            </td>
        </tr>
    </table>
</div>
<%--Control Buttons, Base info and Education--%>
<table border="0" width="100%" cellpadding="5">
    <tr>
        <%--Control Buttons--%>
        <td valign="top" align="right" width="30%">
            <div id="sidebar">
                <form action="/main" method="get">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_my_profile}"></p>
                </form>
                <form action="/main/search" method="get">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_search}">
                    </p>
                </form>
                <form action="/main/messages" method="get">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_messages}"></p>
                </form>
            </div>
        </td>
        <%--Education--%>
        <td valign="top" align="left" width="70%">
            <table border="0" width="100%" cellpadding="5" align="top">
                <tr>
                    <td>
                        <%--Education--%>
                        <div id="contentEducation">
                            <table border="0" width="100%" align="top">
                                <tr>
                                    <td width="25%"></td>
                                    <td width="50%">
                                        <h3>${high_education}</h3>
                                        <h4>${message_about_fields}</h4>
                                    </td>
                                    <td width="25%" align="right">
                                        <small>
                                            <c:if test="${requestScope.successOfTransaction != null}">
                                                <c:choose>
                                                    <c:when test="${requestScope.successOfTransaction == 'success'}">
                                                        ${txt_success}
                                                        ${requestScope.successOfTransaction = null}
                                                    </c:when>
                                                    <c:when test="${requestScope.successOfTransaction == 'fail'}">
                                                        ${txt_fail}
                                                        ${requestScope.successOfTransaction = null}
                                                    </c:when>
                                                </c:choose>
                                            </c:if>
                                        </small>
                                    </td>
                                </tr>
                            </table>
                            <c:forEach items="${sessionScope.universities}" var="university" varStatus="number">
                                <c:if test="${university.deleted == false}">
                                    <table border="0" width="100%" align="top">
                                        <tr>
                                            <form action="/main/education" method="post" id="/education_delete">
                                                <td width="3%" align="left" valign="bottom">
                                                    <button type="submit" form="/education_delete" class="button_lang"
                                                            name="button_delete_education"
                                                            value="${number.index}">${delete_button}
                                                    </button>
                                                </td>
                                            </form>
                                            <td width="3%" align="left" valign="bottom">
                                                <button type="submit" form="/education_update${number.index}" class="button_lang"
                                                        name="button_update_education"
                                                        value="${number.index}">${update_button}
                                                </button>
                                            </td>
                                            <td>
                                                <c:if test="${university.updated == true
                                                && requestScope.updatedStatus != null}">
                                                    <c:choose>
                                                        <c:when test="${requestScope.updatedStatus == 'success'}">
                                                            <p align="right">
                                                                <small>${message_success}</small>
                                                            </p>
                                                        </c:when>
                                                        <c:when test="${requestScope.updatedStatus == 'fail'}">
                                                            <p align="right">
                                                                <small>${message_success}</small>
                                                            </p>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </table>
                                    <form action="/main/education" method="post" id="/education_update${number.index}">
                                        <table border="0" width="100%" align="top">
                                            <tr>
                                                <td width="25%">
                                                        ${txt_country}:
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update${number.index}" class="text"
                                                               name="education_country${number.index}"
                                                               value="${university.country}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                                <td width="25%"></td>
                                            </tr>
                                            <tr>
                                                <td width="25%">
                                                        ${txt_city}:
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update${number.index}" class="text"
                                                               name="education_city${number.index}"
                                                               value="${university.city}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                                <td width="25%"></td>
                                            </tr>
                                            <tr>
                                                <td width="25%">
                                                        ${txt_university}:
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update${number.index}" class="text"
                                                               name="education_fullName${number.index}"
                                                               value="${university.fullName}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                                <td width="25%"></td>
                                            </tr>
                                            <tr>
                                                <td width="25%">
                                                        ${txt_degree}:
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <select form="/education_update${number.index}" class="text"
                                                                name="education_degree${number.index}" size="1" required>
                                                            <c:choose>
                                                                <c:when test="${university.degree eq 'BACHELOR'}">
                                                                    <c:set var="bachelor_var" scope="page"
                                                                           value="selected"/>
                                                                    <c:set var="master_var" scope="page" value=""/>
                                                                    <c:set var="specialist_var" scope="page" value=""/>
                                                                </c:when>
                                                                <c:when test="${university.degree eq 'MASTER'}">
                                                                    <c:set var="master_var" scope="page"
                                                                           value="selected"/>
                                                                    <c:set var="bachelor_var" scope="page" value=""/>
                                                                    <c:set var="specialist_var" scope="page" value=""/>
                                                                </c:when>
                                                                <c:when test="${university.degree eq 'SPECIALIST'}">
                                                                    <c:set var="specialist_var" scope="page"
                                                                           value="selected"/>
                                                                    <c:set var="master_var" scope="page" value=""/>
                                                                    <c:set var="bachelor_var" scope="page" value=""/>
                                                                </c:when>
                                                            </c:choose>
                                                            <option value="none">${txt_degree}</option>
                                                            <option ${bachelor_var}
                                                                    value="bachelor">${bachelor}</option>
                                                            <option ${master_var} value="master">${master}</option>
                                                            <option ${specialist_var}
                                                                    value="specialist">${specialist}</option>
                                                        </select>
                                                </td>
                                                <td width="25%"></td>
                                            </tr>
                                            <tr>
                                                <td width="25%">
                                                        ${txt_graduation_date}:
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input name="education_year${number.index}" type="number"
                                                               form="/education_update${number.index}"
                                                               class="text_year"
                                                               min="${requestScope.minGraduationYear}"
                                                               max="${requestScope.maxGraduationYear}"
                                                               value="${university.graduationYear}"
                                                               size="60" required>
                                                </td>
                                                <td width="25%"></td>
                                            </tr>
                                        </table>
                                    </form>
                                </c:if>
                                <c:if test="${university.deleted == true}">
                                    <small>${message_deleted}</small>
                                    <br/>
                                </c:if>
                            </c:forEach>
                            <form action="/main/education" method="post" id="/education_add">
                                <p align="center">
                                    <input align="center" name="education_country" placeholder="${txt_country}"
                                           required class="text"
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <input name="education_city" placeholder="${txt_city}" required class="text"
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <input name="education_fullName" placeholder="${txt_university}" required
                                           class="text"
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <select name="education_degree" size="1" required class="text">
                                        <option disabled>${txt_degree}</option>
                                        <option value="bachelor">${bachelor}</option>
                                        <option value="master">${master}</option>
                                        <option value="specialist">${specialist}</option>
                                    </select>
                                </p>
                                <p align="center">
                                    <input name="education_year" placeholder="${year_birthday}" required
                                           type="number" class="text_year"
                                           min="${requestScope.minGraduationYear}"
                                           max="${requestScope.maxGraduationYear}"
                                           size="60">
                                </p>
                                <p align="center">
                                    <button type="submit" form="/education_add" class="button_add"
                                            name="button_add_education" value="add">${add_button}
                                    </button>
                                </p>
                            </form>
                            <form action="/main/education" method="get" id="/education_save">
                                <p align="center">
                                    <button type="submit" form="/education_save" class="button_save"
                                            name="button_save_education" value="save">${save_button}
                                    </button>
                                </p>
                            </form>
                            <%--Restriction of universities count--%>
                            <p align="center">
                                <c:if test="${requestScope.maxUniversity != null}">
                                    ${message_max_universities}
                                </c:if>
                            </p>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>


</body>
</html>