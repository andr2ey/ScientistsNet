<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dl" uri="/WEB-INF/tld/datel10n.tld" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>

<fmt:message bundle="${lang}" key="lang.bachelor" var="bachelor"/>
<fmt:message bundle="${lang}" key="lang.master" var="master"/>
<fmt:message bundle="${lang}" key="lang.specialist" var="specialist"/>

<fmt:message bundle="${lang}" key="lang.gender.none" var="gender_none"/>
<fmt:message bundle="${lang}" key="lang.gender.male" var="gender_male"/>
<fmt:message bundle="${lang}" key="lang.gender.female" var="gender_female"/>

<fmt:message bundle="${lang}" key="lang.none" var="none"/>
<fmt:message bundle="${lang}" key="lang.high.education" var="high_education"/>
<fmt:message bundle="${lang}" key="lang.log.out.button" var="log_out_button"/>
<fmt:message bundle="${lang}" key="lang.edit.button" var="edit_button"/>
<fmt:message bundle="${lang}" key="lang.txt.dob" var="txt_dob"/>
<fmt:message bundle="${lang}" key="lang.gender" var="txt_gender"/>
<fmt:message bundle="${lang}" key="lang.email.placeholder" var="txt_email"/>

<fmt:message bundle="${lang}" key="lang.country" var="txt_country"/>
<fmt:message bundle="${lang}" key="lang.city" var="txt_city"/>
<fmt:message bundle="${lang}" key="lang.university" var="txt_university"/>
<fmt:message bundle="${lang}" key="lang.degree" var="txt_degree"/>

<fmt:message bundle="${lang}" key="lang.button.my.profile" var="button_my_profile"/>
<fmt:message bundle="${lang}" key="lang.button.search" var="button_search"/>
<fmt:message bundle="${lang}" key="lang.button.messages" var="button_messages"/>
<fmt:message bundle="${lang}" key="lang.button.articles" var="button_articles"/>

<fmt:message bundle="${lang}" key="lang.button.show.full" var="button_show_full_info"/>
<fmt:message bundle="${lang}" key="lang.txt.graduation.year" var="txt_graduation_year"/>
<fmt:message bundle="${lang}"
             key="lang.${fn:toLowerCase(sessionScope.email.fieldOfScience).replace('_', '.')}"
             var="field_of_science"/>
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
            margin-top: 7px;
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
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <input type="submit" class="button_lang" name="logout" value="${log_out_button}">
                </form>
            </td>
            <td valign="center" align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td valign="center" align="right" width="20%">
                <form action="${pageContext.request.contextPath}/language" method="get">
                    <input type="hidden" name="pathFrom" value="/main">
                    <input type="hidden" name="lang" value="en">
                    <input type="submit" name="button_lang" class="button_lang" value="${en_button}">
                </form>
                <form action="${pageContext.request.contextPath}/language" method="get">
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
                <form action="${pageContext.request.contextPath}/main" method="get">
                    <p align="center"><input type="submit" name="button_profile" class="button_of_profile"
                                             value="${button_my_profile}"></p>
                </form>
                <form action="${pageContext.request.contextPath}/main/search" method="get">
                    <p align="center"><input type="submit" name="button_friends" class="button_of_profile"
                                             value="${button_search}">
                    </p>
                </form>
                <form action="${pageContext.request.contextPath}/main/messages" method="get">
                    <p align="center"><input type="submit" name="button_message" class="button_of_profile"
                                             value="${button_messages}"></p>
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
                                        <h3>
                                            ${field_of_science}
                                        </h3>
                                    </td>
                                    <td width="25%" align="right">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="25%"></td>
                                    <td width="50%">
                                        <h3>${sessionScope.email.firstName} ${sessionScope.email.secondName}</h3>
                                        <h3>${sessionScope.email.middleName}</h3>
                                    </td>
                                    <td width="25%" align="right">
                                        <form action="${pageContext.request.contextPath}/main/baseinfo" method="get">
                                            <input type="submit" name="button_edit_info" class="button_lang"
                                                   value="${edit_button}">
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <table border="0" width="100%" cellpadding="5" align="top">
                                <tr>
                                    <td width="40%"></td>
                                    <td>${txt_dob}:</td>
                                    <td><dl:datel10n dob="${sessionScope.email.dob}"/></td>
                                </tr>
                                <c:if test="${sessionScope.email.gender != 'NONE'}">
                                    <tr>
                                        <td width="40%"></td>
                                        <td>${txt_gender}:</td>
                                        <c:choose>
                                            <c:when test="${sessionScope.email.gender eq 'MALE'}">
                                                <c:set var="gender" scope="page" value="${gender_male}"/>
                                            </c:when>
                                            <c:when test="${sessionScope.email.gender eq 'FEMALE'}">
                                                <c:set var="gender" scope="page" value="${gender_female}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="gender" scope="page" value="${gender_none}"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>${gender}</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td width="40%"></td>
                                    <td>${txt_email}:</td>
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
                                    <td width="50%"><h3>${high_education}</h3></td>
                                    <td width="25%" align="right">
                                        <form action="${pageContext.request.contextPath}/main/education" method="get">
                                            <input type="submit" name="button_edit_education" class="button_lang"
                                                   value="${edit_button}">
                                        </form>
                                    </td>
                                </tr>
                            </table>
                            <c:if test="${sessionScope.universities.size() > 0}">
                                <c:if test="${requestScope.button_show_full == null}">
                                    <c:set var="show_full" scope="page" value="0"/>
                                </c:if>
                                <c:if test="${requestScope.button_show_full != null}">
                                    <c:set var="show_full" scope="page" value="${sessionScope.universities.size()}"/>
                                </c:if>
                                <c:forEach items="${sessionScope.universities}" var="university"
                                           end="${show_full}" varStatus="number">
                                    <br>
                                    <table border="0" width="60%" align="center">
                                        <tr>
                                            <td width="30%" align="left">${txt_country}:</td>
                                            <td align="left">${university.country}</td>
                                        </tr>
                                        <tr>
                                            <td width="30%" align="left">${txt_city}:</td>
                                            <td align="left">${university.city}</td>
                                        </tr>
                                        <tr>
                                            <td width="30%" align="left">${txt_university}:</td>
                                            <td align="left">${university.fullName}</td>
                                        </tr>
                                        <tr>
                                            <td width="30%" align="left">${txt_degree}:</td>
                                            <c:choose>
                                                <c:when test="${university.degree eq 'BACHELOR'}">
                                                    <c:set var="degree" scope="page" value="${bachelor}"/>
                                                </c:when>
                                                <c:when test="${university.degree eq 'MASTER'}">
                                                    <c:set var="degree" scope="page" value="${master}"/>
                                                </c:when>
                                                <c:when test="${university.degree eq 'SPECIALIST'}">
                                                    <c:set var="degree" scope="page" value="${specialist}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="degree" scope="page" value="${none}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <td align="left">${pageScope.degree}</td>
                                        </tr>
                                        <tr>
                                            <td width="30%" align="left">${txt_graduation_year}:</td>
                                            <td align="left">${university.graduationYear}</td>
                                        </tr>
                                    </table>
                                </c:forEach>
                                <c:if test="${requestScope.button_show_full == null}">
                                    <form action="${pageContext.request.contextPath}/main" method="get">
                                        <p align="center"><input type="submit" name="button_show_full"
                                                                 class="button_lang"
                                                                 value="${button_show_full_info}">
                                    </form>
                                </c:if>
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