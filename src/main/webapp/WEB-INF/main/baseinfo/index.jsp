<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>

<fmt:message bundle="${lang}" key="lang.gender.none" var="gender_none"/>
<fmt:message bundle="${lang}" key="lang.gender.male" var="gender_male"/>
<fmt:message bundle="${lang}" key="lang.gender.female" var="gender_female"/>

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

<fmt:message bundle="${lang}" key="lang.base.info" var="base_info"/>
<fmt:message bundle="${lang}" key="lang.log.out.button" var="log_out_button"/>
<fmt:message bundle="${lang}" key="lang.txt.dob" var="txt_dob"/>
<fmt:message bundle="${lang}" key="lang.gender" var="txt_gender"/>
<fmt:message bundle="${lang}" key="lang.txt.first.name" var="txt_first_name"/>
<fmt:message bundle="${lang}" key="lang.txt.second.name" var="txt_second_name"/>
<fmt:message bundle="${lang}" key="lang.txt.middle.name" var="txt_middle_name"/>
<fmt:message bundle="${lang}" key="lang.txt.new.password" var="txt_new_password"/>
<fmt:message bundle="${lang}" key="lang.txt.old.password" var="txt_old_password"/>
<fmt:message bundle="${lang}" key="lang.button.save" var="button_save"/>

<fmt:message bundle="${lang}" key="lang.txt.success" var="txt_success"/>
<fmt:message bundle="${lang}" key="lang.txt.fail" var="txt_fail"/>
<fmt:message bundle="${lang}" key="lang.txt.conformation.password" var="txt_conformation_password"/>
<fmt:message bundle="${lang}" key="lang.email.exist" var="email_exist"/>
<fmt:message bundle="${lang}" key="lang.date.input.error" var="date_input_error"/>

<fmt:message bundle="${lang}" key="lang.button.my.profile" var="button_my_profile"/>
<fmt:message bundle="${lang}" key="lang.button.search" var="button_search"/>
<fmt:message bundle="${lang}" key="lang.button.messages" var="button_messages"/>
<fmt:message bundle="${lang}" key="lang.button.articles" var="button_articles"/>

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
            border-radius: 3px;
            font: 11pt Arial, sans-serif;
        }
        .text_month {
            width: 160px;
            height: 30px;
            margin: 0;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
            font: 11pt Arial, sans-serif;
        }
        .text_year {
            width: 70px;
            height: 30px;
            margin: 2px;
            padding: 2px 9px; /*внутренние поля*/
            background: #fcfdff;
            border: solid 1px #898989;
            border-radius: 3px;
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
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <input type="submit" class="button_lang" name="logout" value="${log_out_button}">
                </form>
            </td>
            <td valign="center" align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td valign="center" align="right" width="20%">
                <form action="${pageContext.request.contextPath}/language" method="get">
                    <input type="hidden" name="pathFrom" value="/main/baseinfo">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="${pageContext.request.contextPath}/language" method="get">
                    <input type="hidden" name="pathFrom" value="/main/baseinfo">
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
                <form action="${pageContext.request.contextPath}/main" method="get">
                    <p align="center">
                        <input type="submit" name="button_lang" class="button_of_profile" value="${button_my_profile}">
                    </p>
                </form>
                <form action="${pageContext.request.contextPath}/main/search" method="get">
                    <p align="center">
                        <input type="submit" name="button_lang" class="button_of_profile" value="${button_search}">
                    </p>
                </form>
                <form action="${pageContext.request.contextPath}/main/messages" method="get">
                    <p align="center">
                        <input type="submit" name="button_lang" class="button_of_profile" value="${button_messages}">
                    </p>
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
                                    <td width="50%"><h3>${base_info}</h3></td>
                                    <td width="25%" align="right">
                                        <small>
                                            <c:if test="${requestScope.fail != null}">
                                                ${txt_fail}
                                            </c:if>
                                        </small>
                                        <small>
                                            <c:if test="${requestScope.success != null}">
                                                ${txt_success}
                                            </c:if>
                                        </small>
                                    </td>
                                </tr>
                            </table>
                            <form action="${pageContext.request.contextPath}/main/baseinfo" method="post" id="/info_save">
                                <table border="0" width="100%" align="top">
                                    <!--Field of Science-->
                                    <tr>
                                        <td width="30%">
                                            ${field_of_science}<span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <select class="text" name="field_of_science" size="1" required>
                                                    <option value="1" ${requestScope.f1}>${architecture}</option>
                                                    <option value="2" ${requestScope.f2}>
                                                        ${business_administration}</option>
                                                    <option value="3" ${requestScope.f3}>${design}</option>
                                                    <option value="4" ${requestScope.f4}>${it}</option>
                                                    <option value="5" ${requestScope.f5}>${hystory}</option>
                                                    <option value="6" ${requestScope.f6}>${literature}</option>
                                                    <option value="7" ${requestScope.f7}>${medicine}</option>
                                                    <option value="8" ${requestScope.f8}>${earth_sciences}</option>
                                                    <option value="9" ${requestScope.f9}>
                                                        ${environmental_sciences}
                                                    </option>
                                                    <option value="10" ${requestScope.f10}>
                                                        ${pedagocical_sciences}
                                                    </option>
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
                                                    <option value="26" ${requestScope.f26}>
                                                        ${electrical_and_electronics}
                                                    </option>
                                                    <option value="27" ${requestScope.f27}>${economics}</option>
                                                </select>
                                        </td>
                                    </tr>
                                    <!--First name-->
                                    <tr>
                                        <td width="30%">
                                            ${txt_first_name}<span>*</span>:
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
                                            ${txt_second_name}<span>*</span>:
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
                                            ${txt_middle_name}:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="middle_name" class="text"
                                                       value="${sessionScope.email.middleName}" size="60"
                                                       placeholder="${txt_middle_name}"
                                                       pattern="[A-Za-z\u0410-\u044F\d\-\., ]{2,100}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Gender-->
                                        <td width="20%">
                                            ${txt_gender}:
                                        </td>
                                        <td>
                                            <p align="center">
                                                <c:choose>
                                                    <c:when test="${sessionScope.email.gender eq 'MALE'}">
                                                        <c:set var="gender_male_var" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.email.gender eq 'FEMALE'}">
                                                        <c:set var="gender_female_var" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="gender_none_var" scope="page" value="checked"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input name="gender" type="radio"
                                                       value="male" placeholder="" ${gender_male_var}>${gender_male}
                                                <input name="gender" type="radio"
                                                       value="female"
                                                       placeholder="" ${gender_female_var}>${gender_female}
                                                <input name="gender" type="radio"
                                                       value="none" placeholder="" ${gender_none_var}>${gender_none}
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Date of birthday-->
                                        <td width="30%">
                                            ${txt_dob}<span>*</span>:
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
                                        <!--New Password-->
                                        <td width="30%">
                                            ${txt_new_password}:
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div align="center" class="info"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="^[\dA-Za-z]{3,100}$"
                                                       maxlength="100" form="/info_save" class="text"
                                                       name="passwordNew" size="60"
                                                       placeholder="${txt_new_password}"></div>
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
                                            ${txt_old_password}<span>*</span>:
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div align="center" class="info"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="^[\dA-Za-z]{3,100}$"
                                                       required maxlength="100" form="/info_save" class="text"
                                                       name="passwordOld" size="60"
                                                       placeholder="${txt_old_password}"></div>
                                        </td>
                                    </tr>
                                </table>
                                <p align="center">
                                    <button type="submit" form="/info_save" class="button_update"
                                            name="button_update_scientist" value="update">${button_save}
                                    </button>
                                </p>
                            </form>
                            <c:if test="${requestScope.confirmPasswordError != null}">
                                <p align="center">${txt_conformation_password}</p>
                            </c:if>
                            <c:if test="${requestScope.exist_email != null}">
                                <p align="center">${email_exist}</p>
                            </c:if>
                            <c:if test="${requestScope.dateInputError != null}">
                                <p align="center">${date_input_error}</p>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>


</body>
</html>
