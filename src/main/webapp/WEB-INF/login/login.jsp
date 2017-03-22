<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ScientistsNet</title>
    <c:if test="${sessionScope.language eq null}">
        <c:set var="language" scope="session" value="en"/>
    </c:if>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language.lang" var="lang"/>

    <fmt:message bundle="${lang}" key="lang.button.rus" var="ru_button"/>
    <fmt:message bundle="${lang}" key="lang.button.en" var="en_button"/>
    <fmt:message bundle="${lang}" key="lang.login.title" var="login_title"/>
    <fmt:message bundle="${lang}" key="lang.error.login" var="error_login"/>
    <fmt:message bundle="${lang}" key="lang.email.placeholder" var="placeholder_email"/>
    <fmt:message bundle="${lang}" key="lang.password.placeholder" var="placeholder_password"/>
    <fmt:message bundle="${lang}" key="lang.button.login" var="button_login"/>
    <fmt:message bundle="${lang}" key="lang.button.signup" var="button_signup"/>
    <fmt:message bundle="${lang}" key="lang.data-title.password" var="data_title_password"/>
    <fmt:message bundle="${lang}" key="lang.signed.up.success" var="signed_up_success"/>
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
            margin: 0; /* Убираем отступы */
            color: #690005; /* Цвет текста */
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
        #footer { /* Нижний блок */
            background: #636363; /* Цвет фона */
            padding: 5px; /* Поля вокруг текста */
            color: #fff; /* Цвет текста */
            clear: left; /* Отменяем действие float */
            position: absolute;
            bottom: 0;
            width: 100%;
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
            margin: 0;
        }
    </style>
</head>
<body>
<div id="header">
    <table border="0" width="100%" cellpadding="0">
        <tr>
        <tr>
            <td align="left" width="20%">

            </td>
            <td align="center" width="60%">
                <h1>ScientistsNet</h1>
            </td>
            <td align="right" width="20%">
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
        </tr>
    </table>
</div>
<div id="content">
    <p align="center"><h3>${login_title}</h3>
    <form action="j_security_check" method="post" id="j_security_check">
        <!--Email-->
        <!--TODO create pattern for email-->
        <p align="center">
            <input type="email" class="text" required maxlength="100" pattern=".+@.+\..+"
                   name="j_username" size="100" placeholder="${placeholder_email}">
        <!--Password-->
        <p align="center"><div class="info" align="center" data-title="${data_title_password}">
        <input type="password"  class="text" pattern="[A-Za-z0-9]{3,255}" required maxlength="255"
               name="j_password" size="255" placeholder="${placeholder_password}"
               value=""></div>
        <!--Log in Button-->
        <p align="center">
            <button type="submit" form="j_security_check" class="button_log_in">${button_login}</button>

    </form>
    <!--Sign up Button-->
    <form method="post" action="/registration">
        <p align="center"><input type="submit"  class="button_sign_up" value="${button_signup}">
    </form>
    <c:if test="${sessionScope.error_login != null}">
        <c:set var="error_login" scope="session" value="${error_login}"/>
    </c:if>
    <p align="center"><small>${sessionScope.error_login}</small></p>
    <c:if test="${sessionScope.signup != null}">
        <c:set var="signup" scope="session" value="${signed_up_success}"/>
    </c:if>
    <p align="center"><small>${sessionScope.signup}</small></p>
</div>
</body>
</html>