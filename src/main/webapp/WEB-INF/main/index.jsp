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
<table border="0" width="100%" cellpadding="5">
    <tr>
        <td valign="top" align="right" width="30%">
            <div id="sidebar">
                <form action="/main" method="post">
                    <p align="center"><input type="submit" name="button_profile" class="button_of_profile"
                                             value="My profile"></p>
                </form>
                <form action="/friends" method="post">
                    <p align="center"><input type="submit" name="button_friends" class="button_of_profile" value="Friends">
                    </p>
                </form>
                <form action="/messages" method="post">
                    <p align="center"><input type="submit" name="button_message" class="button_of_profile"
                                             value="Messages"></p>
                </form>
                <form action="/articles" method="post">
                    <p align="center"><input type="submit" name="button_articles" class="button_of_profile"
                                             value="Articles"></p>
                </form>
            </div>
        </td>
        <td valign="top" align="left" width="70%">
            <table border="0" width="100%" cellpadding="5" align="top">
                <tr>
                    <td>
                        <div id="contentBaseInfo">
                            <table border="0" width="100%" align="top">
                                <tr>
                                    <td width="25%"></td>
                                    <td width="50%">
                                        <h3>${sessionScope.email.firstName} ${sessionScope.email.secondName}</h3>
                                        <h3>${sessionScope.email.middleName}</h3>
                                    </td>
                                    <td width="25%" align="right">
                                        <form action="/baseinfo" method="get">
                                            <input type="submit" name="button_edit_info" class="button_lang"
                                                   value="edit">
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <table border="0" width="100%" cellpadding="5" align="top">
                                <c:if test="${sessionScope.email.dob != null}">
                                    <tr>
                                        <td width="40%"></td>
                                        <td>date of birthday:</td>
                                        <td>${sessionScope.email.dob}</td>
                                    </tr>
                                </c:if>
                                <c:if test="${sessionScope.email.gender != null}">
                                    <tr>
                                        <td width="40%"></td>
                                        <td>gender:</td>
                                        <td>${sessionScope.email.gender}</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td width="40%"></td>
                                    <td>email:</td>
                                    <td>${sessionScope.email.email}</td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="contentEducation">
                            <table border="0" width="100%" align="top">
                                <tr>
                                    <td width="25%"></td>
                                    <td width="50%"><h3>High Education</h3></td>
                                    <td width="25%" align="right">
                                        <form action="/education" method="get">
                                            <input type="submit" name="button_edit_education" class="button_lang"
                                                   value="edit">
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <c:forEach items="${sessionScope.universities}" var="university" varStatus="number">
                                <br>
                                <table border="0" width="60%" align="center">
                                    <tr>
                                        <td width="30%" align="left">country:</td>
                                        <td align="left">${university.country}</td>
                                    </tr>
                                    <tr>
                                        <td width="30%" align="left">city:</td>
                                        <td align="left">${university.city}</td>
                                    </tr>
                                    <tr>
                                        <td width="30%" align="left">university:</td>
                                        <td align="left">${university.fullName}</td>
                                    </tr>
                                    <tr>
                                        <td width="30%" align="left">degree:</td>
                                        <td align="left">${university.degree}</td>
                                    </tr>
                                </table>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>


</body>
</html>