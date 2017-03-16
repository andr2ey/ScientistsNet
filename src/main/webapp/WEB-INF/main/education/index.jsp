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
                    <input type="submit" class="button_lang" name="logout" value="Log out">
                </form>
            </td>
            <td valign="center" align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td valign="center" align="right" width="20%">
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/education">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="/language" method="post">
                    <input type="hidden" name="pathFrom" value="/education">
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
                                    <td width="50%"><h3>High Education</h3></td>
                                    <td width="25%" align="right">
                                        <small>${requestScope.successOfTransaction}</small>
                                    </td>
                                </tr>
                            </table>
                            <c:forEach items="${sessionScope.universities}" var="university" varStatus="number">
                                <c:if test="${university.deleted == false}">
                                    <%--Don't delete next block--%>

                                    <table border="0" width="100%" align="top">
                                        <tr>
                                            <form action="/education_delete" method="get" id="/education_delete">
                                                <td width="3%" align="left" valign="bottom">
                                                    <button type="submit"
                                                            form="/education_delete" class="button_lang"
                                                            name="button_delete_education" value="${number.index}">del
                                                    </button>
                                                </td>
                                            </form>
                                            <td width="3%" align="left" valign="bottom">
                                                <button type="submit" form="/education_update" class="button_lang"
                                                        name="button_update_education" value="${number.index}">upd
                                                </button>
                                            </td>
                                            <td>
                                                <c:if test="${university.updated == true}">
                                                    <p align="right">
                                                        <small>${requestScope.updatedStatus}</small>
                                                    </p>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </table>
                                    <form action="/education_update" method="get" id="/education_update">
                                        <table border="0" width="100%" align="top">
                                            <tr>
                                                <td>
                                                    <small>country:</small>
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update"
                                                               name="univ_country${number.index}"
                                                               value="${university.country}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <small>city:</small>
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update" name="univ_city${number.index}"
                                                               value="${university.city}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <small>university:</small>
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <input form="/education_update"
                                                               name="univ_full_name${number.index}"
                                                               value="${university.fullName}" required size="60"
                                                               pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <small>degree:</small>
                                                </td>
                                                <td>
                                                    <p align="center">
                                                        <select form="/education_update"
                                                                name="univ_degree${number.index}" size="1" required>
                                                            <c:choose>
                                                                <c:when test="${university.degree eq 'BACHELOR'}">
                                                                    <c:set var="bachelor" scope="page"
                                                                           value="selected"/>
                                                                    <c:set var="master" scope="page" value=""/>
                                                                    <c:set var="specialist" scope="page" value=""/>
                                                                </c:when>
                                                                <c:when test="${university.degree eq 'MASTER'}">
                                                                    <c:set var="master" scope="page" value="selected"/>
                                                                    <c:set var="bachelor" scope="page" value=""/>
                                                                    <c:set var="specialist" scope="page" value=""/>
                                                                </c:when>
                                                                <c:when test="${university.degree eq 'SPECIALIST'}">
                                                                    <c:set var="specialist" scope="page"
                                                                           value="selected"/>
                                                                    <c:set var="master" scope="page" value=""/>
                                                                    <c:set var="bachelor" scope="page" value=""/>
                                                                </c:when>
                                                            </c:choose>
                                                            <option disabled>Выберите ученую степень</option>
                                                            <option ${bachelor} value="bachelor">бакалавр</option>
                                                            <option ${master} value="master">магистр</option>
                                                            <option ${specialist} value="specialist">специалист</option>
                                                        </select>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </c:if>
                                <c:if test="${university.deleted == true}">
                                    <small>This entry will be deleted once you've saved your changes.</small>
                                    <br/>
                                </c:if>
                            </c:forEach>
                            <form action="/education_add" method="post" id="/education_add">
                                <p align="center">
                                    <input name="education_country" placeholder="country" required
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <input name="education_city" placeholder="city" required
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <input name="education_fullName" placeholder="university" required
                                           pattern="[A-Za-z\u0410-\u044F\d\- ]{2,100}" size="60"></p>
                                <p align="center">
                                    <select name="education_degree" size="1" required>
                                        <option disabled>Выберите ученую степень</option>
                                        <option value="bachelor">бакалавр</option>
                                        <option selected value="master">магистр</option>
                                        <option value="specialist">специалист</option>
                                    </select>
                                </p>
                                <p align="center">
                                    <button type="submit" form="/education_add" class="button_add"
                                            name="button_add_education" value="add">Add
                                    </button>
                                </p>
                            </form>
                            <form action="/education_save" method="get" id="/education_save">
                                <p align="center">
                                    <button type="submit" form="/education_save" class="button_save"
                                            name="button_save_education" value="save">Save
                                    </button>
                                </p>
                            </form>
                            <c:if test="${requestScope.maxUniversity != null}">
                                ${requestScope.maxUniversity}
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