<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<fmt:message bundle="${lang}" key="lang.fill.all.fields" var="fill_all_fields"/>

<fmt:message bundle="${lang}" key="lang.field.of.science" var="field_of_science"/>
<fmt:message bundle="${lang}" key="lang.architecture" var="architecture"/>
<fmt:message bundle="${lang}" key="lang.business.administration" var="business_administration"/>
<fmt:message bundle="${lang}" key="lang.design" var="design"/>
<fmt:message bundle="${lang}" key="lang.it" var="it"/>
<fmt:message bundle="${lang}" key="lang.history" var="hystory"/>
<fmt:message bundle="${lang}" key="lang.literature" var="literature"/>
<fmt:message bundle="${lang}" key="lang.medicine" var="medicine"/>
<fmt:message bundle="${lang}" key="lang.earth.sciences" var="earth_sciences"/>
<fmt:message bundle="${lang}" key="lang.environmental.sciences" var="environmental_sciences"/>
<fmt:message bundle="${lang}" key="lang.pedagogical.sciences" var="pedagocical_sciences"/>
<fmt:message bundle="${lang}" key="lang.psychology" var="psychology"/>
<fmt:message bundle="${lang}" key="lang.philosophy" var="philosophy"/>
<fmt:message bundle="${lang}" key="lang.jurisprudence" var="jurisprudence"/>
<fmt:message bundle="${lang}" key="lang.astronomy" var="astronomy"/>
<fmt:message bundle="${lang}" key="lang.biology" var="biology"/>
<fmt:message bundle="${lang}" key="lang.engineering" var="engineering"/>
<fmt:message bundle="${lang}" key="lang.arts" var="arts"/>
<fmt:message bundle="${lang}" key="lang.lingustics" var="lingustics"/>
<fmt:message bundle="${lang}" key="lang.mathematics" var="mathematics"/>
<fmt:message bundle="${lang}" key="lang.management" var="management"/>
<fmt:message bundle="${lang}" key="lang.material.science" var="material_science"/>
<fmt:message bundle="${lang}" key="lang.social.sciences" var="social_sciences"/>
<fmt:message bundle="${lang}" key="lang.politics" var="politics"/>
<fmt:message bundle="${lang}" key="lang.physics" var="physics"/>
<fmt:message bundle="${lang}" key="lang.chemistry" var="chemistry"/>
<fmt:message bundle="${lang}" key="lang.electrical.and.electronics" var="electrical_and_electronics"/>
<fmt:message bundle="${lang}" key="lang.economics" var="economics"/>

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
            border-radius: 3px;
        }

        .text {
            width: 440px;
            height: 30px;
            margin: 2px;
            padding: 5px 12px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 14pt Arial, sans-serif;
        }

        .text_day {
            width: 80px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 14pt Arial, sans-serif;
        }

        .text_month {
            width: 170px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 14pt Arial, sans-serif;
        }

        .text_field {
            width: 440px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 14pt Arial, sans-serif;
        }

        .text_year {
            width: 80px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
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
            border-radius: 3px;
        }

        .button_sign_up { /* Кнопка регистрации */
            background: #0f6100;
            border: solid 1px #494949;
            border-radius: 3px;
            color: #f0f0f0;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
        }

        .button_log_in { /* Кнопка регистрации */
            background: #690005;
            border: solid 1px #494949;
            border-radius: 3px;
            color: #f0f0f0;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
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
                    <input type="hidden" name="pathFrom" value="/registration">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/registration">
                    <input type="hidden" name="lang" value="ru">
                    <input type="submit" name="button_lang" class="button_lang" value="${ru_button}">
                </form>
            </td>
        </tr>
    </table>
</div>
<div id="content">
    <p align="center">
    <h3>${signup_title}</h3>
    <h4>${message_about_fields}</h4>

    <form action="/registration" method="post" id="/registration">
        <!--Field of Science-->
        <p align="center">
            <select class="text_field" name="field_of_science" size="1" required>
                <option value="none">${field_of_science}</option>
                <option value="1" ${requestScope.f1}>${architecture}</option>
                <option value="2" ${requestScope.f2}>${business_administration}</option>
                <option value="3" ${requestScope.f3}>${design}</option>
                <option value="4" ${requestScope.f4}>${it}</option>
                <option value="5" ${requestScope.f5}>${hystory}</option>
                <option value="6" ${requestScope.f6}>${literature}</option>
                <option value="7" ${requestScope.f7}>${medicine}</option>
                <option value="8" ${requestScope.f8}>${earth_sciences}</option>
                <option value="9" ${requestScope.f9}>${environmental_sciences}</option>
                <option value="10" ${requestScope.f10}>${pedagocical_sciences}</option>
                <option value="11" ${requestScope.f11}>${psychology}</option>
                <option value="12" ${requestScope.f12}>${philosophy}</option>
                <option value="13" ${requestScope.f13}>${jurisprudence}</option>
                <option value="14" ${requestScope.f14}>${astronomy}</option>
                <option value="15" ${requestScope.f15}>${biology}</option>
                <option value="16" ${requestScope.f16}>${engineering}</option>
                <option value="17" ${requestScope.f17}>${arts}</option>
                <option value="18" ${requestScope.f18}>${lingustics}</option>
                <option value="19" ${requestScope.f19}>${mathematics}</option>
                <option value="20" ${requestScope.f20}>${management}</option>
                <option value="21" ${requestScope.f21}>${material_science}</option>
                <option value="22" ${requestScope.f22}>${social_sciences}</option>
                <option value="23" ${requestScope.f23}>${politics}</option>
                <option value="24" ${requestScope.f24}>${physics}</option>
                <option value="25" ${requestScope.f25}>${chemistry}</option>
                <option value="26" ${requestScope.f26}>${electrical_and_electronics}</option>
                <option value="27" ${requestScope.f27}>${economics}</option>
            </select>
        </p>
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
            <input type="number" class="text_year" name="year" value="${requestScope.year}"
                   placeholder="${year_birthday}"
                   min="${requestScope.minYear}" max="${requestScope.maxYear}" size="60" required>
        </p>
        <!--Email-->
        <p align="center"><input type="email" class="text" required maxlength="100" pattern="^.+@.+\..+[^\.]$"
                                 name="emailNew" size="100" placeholder="${placeholder_email}">
            <!--Password-->
        <p align="center">
        <div class="info" align="center" data-title="${data_title_password}">
            <input type="password" class="text" pattern="^[\dA-Za-z]{3,100}$" required maxlength="100"
                   name="passwordNew" size="100" placeholder="${placeholder_password}"></div>
        <!--Sign up Button-->
        <p align="center"><input type="submit" class="button_sign_up" value="${button_signup}">
    </form>
    <!--Log in Button-->
    <form action="/" method="post">
        <p align="center"><input type="submit" class="button_log_in" value="${button_login}">
    </form>
    <c:if test="${requestScope.email_exist != null}">
    <c:set var="email_exist" scope="request" value="${email_exist}"/>
    </c:if>
    <c:if test="${requestScope.dateInputError != null}">
    <c:set var="dateInputError" scope="request" value="${date_input_error}"/>
    </c:if>
    <p align="center">
        <small>${requestScope.email_exist}</small>
    </p>
    <p align="center">
        <small>${requestScope.dateInputError}</small>
    </p>
    <c:if test="${requestScope.secondNameInputError != null
    || requestScope.firstNameInputError != null
    || requestScope.fieldOfScienceInputError != null}">
        <p align="center">
            <small>${fill_all_fields}</small>
        </p>
    </c:if>
</div>
</body>
</html>