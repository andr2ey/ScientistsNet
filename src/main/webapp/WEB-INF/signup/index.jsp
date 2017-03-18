<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--To avoid repeating error message on log in page--%>
<c:set var="error_login" scope="session" value="${null}"/>
<c:set var="signup" scope="session" value="${null}"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>

<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>
<fmt:message bundle="${lang}" key="lang.button.login" var="button_login"/>
<fmt:message bundle="${lang}" key="lang.button.signup" var="button_signup"/>
<fmt:message bundle="${lang}" key="lang.data-title.password" var="data_title_password"/>
<fmt:message bundle="${lang}" key="lang.email.placeholder" var="placeholder_email"/>
<fmt:message bundle="${lang}" key="lang.password.placeholder" var="placeholder_password"/>
<fmt:message bundle="${lang}" key="lang.date.of.birthday" var="date_of_birthday"/>
<fmt:message bundle="${lang}" key="lang.gender.none" var="gender_other"/>
<fmt:message bundle="${lang}" key="lang.gender.male" var="gender_male"/>
<fmt:message bundle="${lang}" key="lang.gender.female" var="gender_female"/>
<fmt:message bundle="${lang}" key="lang.your.first.name" var="your_first_name"/>
<fmt:message bundle="${lang}" key="lang.your.last.name" var="your_last_name"/>
<fmt:message bundle="${lang}" key="lang.signup.title" var="signup_title"/>
<fmt:message bundle="${lang}" key="lang.email.exist" var="email_exist"/>
<%--Все поля необходимо заполнить, day, year, month, ...--%>
<fmt:message bundle="${lang}" key="lang.message.about.fields" var="message_about_fields"/>

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
<fmt:message bundle="${lang}" key="lang.date.input.error" var="date_input_error"/>

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
        h3 {
            font-size: 24px; /* Размер шрифта */
            margin: 5px; /* Убираем отступы */
            color: #690005; /* Цвет текста */
            text-align: center;
        }
        h4 {
            font-size: 16px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #690005; /* Цвет текста */
            text-align: center;
        }
        h5 {
            margin: 0; /* Убираем отступы */
            color: #1d1d1d; /* Цвет текста */
            font: 14pt Arial, sans-serif;
            text-align: center;
        }
        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }
        #header { /* Верхний блок */
            background: #690005; /* Цвет фона */
            padding: 4px; /* Поля вокруг текста */
            border: solid 1px #000000; /* Параметры рамки вокруг */
        }
        #content { /* Центральная колонка */
            background: #b1b1b1; /* Цвет фона */
            margin: 20px auto auto auto; /* Значения отступов */
            padding: 5px; /* Поля вокруг текста */
            width: 500px; /* Ширина колонки */
            border: solid 1px #898989; /* Параметры рамки вокруг */
            border-radius:  3px;
        }
        .text {
            width: 440px;
            height: 30px;
            margin: 2px;
            padding: 5px 12px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 14pt Arial, sans-serif;
        }
        .text_day {
            width: 80px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 14pt Arial, sans-serif;
        }
        .text_month {
             width: 170px;
             height: 30px;
             margin: 0;
             padding: 2px 9px; /*внутренние поля*/
             background: #fcfdff;
             border: solid 1px #898989;
             border-radius:  3px;
             font: 14pt Arial, sans-serif;
         }
        .text_year {
            width: 80px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius:  3px;
            font: 14pt Arial, sans-serif;
        }
        .info:hover::after { /* Всплывающее окно */
            content: attr(data-title); /* Выводим текст */
            position: absolute; /* Абсолютное позиционирование */
            z-index: 1; /* Отображаем подсказку поверх других элементов */
            background: rgba(255, 49, 53, 0.69); /* Полупрозрачный цвет фона */
            font-family: Arial, sans-serif; /* Гарнитура шрифта */
            font-size: 12px; /* Размер текста подсказки */
            padding: 5px 5px; /* Поля */
            border: 1px solid #333; /* Параметры рамки */
            border-radius:  3px;
        }
        .button_sign_up { /* Кнопка регистрации */
            background: #0f6100;
            border: solid 1px #494949;
            border-radius:  3px;
            color: #f0f0f0;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
        }
        .button_log_in{ /* Кнопка регистрации */
            background: #690005;
            border: solid 1px #494949;
            border-radius:  3px;
            color: #f0f0f0;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
        }
        .button_lang{ /* Кнопка регистрации */
            background: #f0f0f0;
            border: solid 1px #494949;
            border-radius:  3px;
            color: #890005;
            padding: 4px 4px;
            font: 8pt Arial, sans-serif;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="header">
    <table border="0" width="100%" cellpadding="0">
        <tr>
            <td align="left" width="20%">

            </td>
            <td align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td align="right" width="20%">
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/signup">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/signup">
                    <input type="hidden" name="lang" value="ru">
                    <input type="submit" name="button_lang" class="button_lang" value="${ru_button}">
                </form>
            </td>
        </tr>
    </table>
</div>
<div id="content">
    <p align="center"><h3>${signup_title}</h3>
    <h4>${message_about_fields}</h4>

    <form action="/signup" method="post" id="/signup">
        <!--First name-->
        <p align="center">
            <input class="text" pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}" required maxlength="100"
                   name="first_name" size="100" placeholder="${your_first_name}" value="${requestScope.first_name}">
        </p>
        <!--Last name-->
        <p align="center">
            <input class="text" pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}" required maxlength="100"
                   name="second_name" size="100" placeholder="${your_last_name}" value="${requestScope.second_name}">
        </p>
        <!--Date of birthday-->
        <h5>${date_of_birthday}</h5>
        <p align="center">
            <input type="number" class="text_day" name="day" value="${requestScope.day}" placeholder="${day_birthday}"
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
            <input type="number" class="text_year" name="year" value="${requestScope.year}" placeholder="${year_birthday}"
                   min="${requestScope.minYear}" max="${requestScope.maxYear}" size="60" required>
        </p>
        <!--Email-->
            <p align="center"><input type="email" class="text" required maxlength="100" pattern="^.+@.+\..+[^\.]$"
                   name="emailNew" size="100" placeholder="${placeholder_email}">
            <!--Password-->
        <p align="center">
        <div class="info" align="center" data-title="${data_title_password}">
            <input type="password"  class="text" pattern="^[\dA-Za-z]{3,100}$" required maxlength="100"
                   name="passwordNew" size="100" placeholder="${placeholder_password}"></div>
        <!--Sign up Button-->
        <p align="center"><input type="submit" class="button_sign_up" value="${button_signup}">
    </form>
    <!--Log in Button-->
    <form action="/" method="post">
        <p align="center"><input type="submit"  class="button_log_in" value="${button_login}">
    </form>
    <c:if test="${requestScope.email_exist != null}">
        <c:set var="email_exist" scope="request" value="${email_exist}"/>
    </c:if>
    <c:if test="${requestScope.dateInputError != null}">
        <c:set var="dateInputError" scope="request" value="${date_input_error}"/>
    </c:if>
    <p align="center"><small>${requestScope.email_exist}</small></p>
    <p align="center"><small>${requestScope.dateInputError}</small></p>
</div>
</body>
</html>