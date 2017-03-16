<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>

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
            padding: 5px; /* Поля вокруг текста */
            border-radius: 3px;
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
                    <input type="hidden" name="pathFrom" value="/main">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/main">
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
                                        <td width="20%">
                                            <small>first name:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="first_name"
                                                       value="${sessionScope.email.firstName}" size="60"
                                                       required pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                        </td>
                                    </tr>
                                    <!--Last name-->
                                    <tr>
                                        <td width="20%">
                                            <small>last name:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="second_name"
                                                       value="${sessionScope.email.secondName}" size="60"
                                                       required pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                        </td>
                                    </tr>
                                    <!--Middle name-->
                                    <tr>
                                        <td width="20%">
                                            <small>middle name:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input form="/info_save" name="middle_name"
                                                       value="${sessionScope.email.middleName}" size="60"
                                                       pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Gender-->
                                        <td width="20%">
                                            <small>gender:</small>
                                        </td>
                                        <td>
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
                                            <p align="center">
                                                <c:choose>
                                                    <c:when test="${requestScope.email.gender eq 'male'}">
                                                        <c:set var="gender_male" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:when test="${requestScope.email.gender eq 'female'}">
                                                        <c:set var="gender_female" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:when test="${requestScope.email.gender eq 'other'}">
                                                        <c:set var="gender_other" scope="page" value="checked"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="gender_none" scope="page" value="checked"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input name="gender" type="radio" value="male" placeholder="" ${gender_male} >${gender_male}
                                                <input name="gender" type="radio" value="female" placeholder="" ${gender_female} >${gender_female}
                                                <input name="gender" type="radio" value="other" placeholder="" ${gender_other} >${gender_other}
                                                <input name="gender" type="radio" value="none" placeholder="" ${gender_none} >None selected
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Date of birthday-->
                                        <td width="20%">
                                            <small>date of birthday:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input type="date" name="dob" form="/info_save"
                                                       value="${sessionScope.email.dob}" placeholder=""
                                                       max="2001-01-01" min="1900-01-01">
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Email-->
                                        <td width="20%">
                                            <small>email:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                                <input type="email" required maxlength="100"
                                                       pattern=".+@.+\..+" form="/info_save"
                                                       name="emailNew" size="60" placeholder="email"
                                                       value="${sessionScope.email.email}">
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--Old Password-->
                                        <td width="20%">
                                            <small>old password:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div class="info" align="center"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="[A-Za-z0-9]{3,100}"
                                                       required maxlength="100" form="/info_save"
                                                       name="passwordOld" size="60" placeholder="old password"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <!--New Password-->
                                        <td width="20%">
                                            <small>new password:</small>
                                        </td>
                                        <td>
                                            <p align="center">
                                            <div class="info" align="center"
                                                 data-title="Only latin letters and numbers, length 3-100">
                                                <input type="password" pattern="[A-Za-z0-9]{3,100}"
                                                       required maxlength="100" form="/info_save"
                                                       name="passwordNew" size="60" placeholder="new password"></div>
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
