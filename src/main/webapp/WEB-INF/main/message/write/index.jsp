<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>
<fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
<fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>
<fmt:message bundle="${lang}" key="lang.log.out.button" var="log_out_button"/>
<fmt:message bundle="${lang}" key="lang.button.my.profile" var="button_my_profile"/>
<fmt:message bundle="${lang}" key="lang.button.search" var="button_search"/>
<fmt:message bundle="${lang}" key="lang.button.messages" var="button_messages"/>
<fmt:message bundle="${lang}" key="lang.button.articles" var="button_articles"/>

<fmt:message bundle="${lang}" key="lang.txt.first.name" var="txt_first_name"/>
<fmt:message bundle="${lang}" key="lang.txt.second.name" var="txt_second_name"/>

<fmt:message bundle="${lang}" key="lang.txt.success" var="txt_success"/>
<fmt:message bundle="${lang}" key="lang.txt.fail" var="txt_fail"/>

<fmt:message bundle="${lang}" key="lang.message.to" var="to"/>
<fmt:message bundle="${lang}" key="lang.message.send" var="send_message"/>


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
        textarea {
            width: 80%;
            resize: none; /* Запрещаем изменять размер */
            font: 12pt Arial, sans-serif; /* Рубленый шрифт текста */
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
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_my_profile}"></p>
                </form>
                <form action="${pageContext.request.contextPath}/main/search" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_search}">
                    </p>
                </form>
                <form action="${pageContext.request.contextPath}/main/messages" method="post">
                    <p align="center"><input type="submit" name="button_lang" class="button_of_profile"
                                             value="${button_messages}"></p>
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
                                    <td width="50%"><h3>${send_message}</h3></td>
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
                            <form action="${pageContext.request.contextPath}/main/message/send"
                                  method="post" id="/send_message">
                                <table border="0" width="100%" align="top">
                                    <!--Email-->
                                    <tr>
                                        <td width="10%" align="right">
                                            ${to}:
                                        </td>
                                        <td align="center">
                                            ${requestScope.sciFirstName} ${requestScope.sciSecondName}
                                            ${requestScope.emailTo}
                                        </td>
                                    </tr>
                                    <!--Message-->
                                    <tr>
                                        <td width="10%"></td>
                                        <td>
                                            <p align="center">
                                                <textarea form="/send_message" name="txt_of_message" placeholder=""
                                                          required maxlength="2000" rows="10" cols="35"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="10%"></td>
                                        <td align="center">
                                                <button type="submit" form="/send_message" class="button_update"
                                                        name="button_send_message" value="${requestScope.emailTo}">
                                                    ${send_message}
                                                </button>
                                            <input type="hidden" name="sciFirstName"
                                                   value="${requestScope.sciFirstName}">
                                            <input type="hidden" name="sciSecondName"
                                                   value="${requestScope.sciSecondName}">
                                        </td>
                                    </tr>
                                </table>
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
