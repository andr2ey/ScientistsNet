<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>

<fmt:message bundle="${lang}" key="lang.day.birthday" var="day_birthday"/>
<fmt:message bundle="${lang}" key="lang.year.birthday" var="year_birthday"/>

<fmt:message bundle="${lang}" key="lang.month" var="month"/>
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
        .text_day {
            width: 70px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 11pt Arial, sans-serif;
        }
        .text_month {
            width: 160px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 11pt Arial, sans-serif;
        }
        .text_year {
            width: 70px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 11pt Arial, sans-serif;
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

        .button_update { /* Кнопка регистрации */
            background: #0f6100;
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
        .text {
            width: 340px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 11pt Arial, sans-serif;
        }
    </style>
</head>
<body>
<div id="header">
    <table border="0" width="100%" cellpadding="0">
        <tr>
            <td valign="center" align="left" width="20%">
                <form action="/main" method="post">
                    <input type="submit" class="button_lang" name="logout" value="Log out">
                </form>
            </td>
            <td valign="center" align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td valign="center" align="right" width="20%">
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/baseinfo">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/baseinfo">
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
                <form action="/main" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="My profile"></p>
                </form>
                <form action="/friends" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile" value="Friends">
                    </p>
                </form>
                <form action="/messages" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="Messages"></p>
                </form>
                <form action="/articles" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="Articles"></p>
                </form>
            </div>
        </td>
        <%--Base Info--%>
        <td valign="top" align="left" width="70%">
            <table border="0" width="100%" cellpadding="5" align="top">
                <tr>
                    <td>
                        <%--Education--%>
                        <div id="contentEducation">
                            <table border="0" width="100%" align="top">
                                <tr>
                                    <td width="25%"></td>
                                    <td width="50%"><h3>Base info</h3></td>
                                    <td width="25%" align="right">${requestScope.successOfTransaction}</td>
                                </tr>
                            </table>
                            <form action="/info_save" method="get" id="/info_save">
                                <table border="0" width="100%" align="top">
                                    <!--First name-->
                                    <tr>
                                        <td width="30%">
                                            first name<span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="first_name" class="text"
                                                       value="${sessionScope.email.firstName}" size="50"
                                                       required pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}">
                                        </td>
                                    </tr>
                                    <!--Last name-->
                                    <tr>
                                        <td width="20%">
                                            last name<span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="second_name" class="text"
                                                       value="${sessionScope.email.secondName}" size="50"
                                                       required pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}">
                                        </td>
                                    </tr>
                                    <!--Middle name-->
                                    <tr>
                                        <td width="30%">
                                            middle name:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="middle_name" class="text"
                                                       value="${sessionScope.email.middleName}" size="60"
                                                       pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Gender-->
                                        <td width="20%">
                                            gender:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <c:choose>
                                                    <c:when test="${requestScope.email.gender eq 'male'}">
                                                        <c:set var="gender_male" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:when test="${requestScope.email.gender eq 'female'}">
                                                        <c:set var="gender_female" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="gender_none" scope="page" value="checked"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input name="gender" type="radio"
                                                       value="male" placeholder="" ${gender_male}>Male
                                                <input name="gender" type="radio"
                                                       value="female" placeholder="" ${gender_female}>Female
                                                <input name="gender" type="radio"
                                                       value="none" placeholder="" ${gender_none}>None selected
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Date of birthday-->
                                        <td width="30%">
                                            date of birthday<span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input type="number" class="text_day" name="day"
                                                       value="${requestScope.day}" placeholder="${day_birthday}"
                                                       min="1" max="31" size="40" required>
                                                <select class="text_month" name="month" size="1" required>
                                                    <option value="none">${month}</option>
                                                    <option value="1" ${requestScope.m1}>${january}</option>
                                                    <option value="2" ${requestScope.m2}>${february}</option>
                                                    <option value="3" ${requestScope.m3}>${march}</option>
                                                    <option value="4" ${requestScope.m4}>${april}</option>
                                                    <option value="5" ${requestScope.m5}>${may}</option>
                                                    <option value="6" ${requestScope.m6}>${june}</option>
                                                    <option value="7" ${requestScope.m7}>${july}</option>
                                                    <option value="8" ${requestScope.m8}>${august}</option>
                                                    <option value="9" ${requestScope.m9}>${september}</option>
                                                    <option value="10" ${requestScope.m10}>${october}</option>
                                                    <option value="11" ${requestScope.m11}>${november}</option>
                                                    <option value="12" ${requestScope.m12}>${december}</option>
                                                </select>
                                                <%--TODO think about max and min value--%>
                                                <input type="number" class="text_year" name="year"
                                                       value="${requestScope.year}" placeholder="${year_birthday}"
                                                       min="${requestScope.minYear}" max="${requestScope.maxYear}"
                                                       size="60" required>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--New Email-->
                                        <td width="30%">
                                            new email:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input type="email" required maxlength="100" class="text"
                                                       pattern="^.+@.+\..+[^\.]$" form="/info_save"
                                                       name="emailNew" size="60" placeholder=""
                                                       value="${requestScope.emailNew}">
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--New Password-->
                                        <td width="30%">
                                            new password:
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div align="center" class="info"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="^[\dA-Za-z]{3,100}$"
                                                       required maxlength="100" form="/info_save"  class="text"
                                                       name="passwordNew" size="60" placeholder="new password"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--EMPTY line-->
                                        <td width="30%"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <!--Old Password-->
                                        <td width="30%">
                                            confirm password <span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div align="center" class="info"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="^[\dA-Za-z]{3,100}$"
                                                       required maxlength="100" form="/info_save"  class="text"
                                                       name="passwordOld" size="60" placeholder="password"></div>
                                        </td>
                                    </tr>
                                </table>
                                <p align="center">
                                    <button type="submit" form="/info_save" class="button_update"
                                            name="button_update_scientist" value="update">Save
                                    </button>
                                </p>
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>


</body>
</html>
