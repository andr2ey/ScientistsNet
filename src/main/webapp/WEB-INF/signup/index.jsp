<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 07.03.2017
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
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
            color: #1d1d1d; /* Цвет текста */
            text-align: center;
        }
        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }
        #header { /* Верхний блок */
            background: #158fec; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }

        #content { /* Центральная колонка */
            background: #dfdfdf; /* Цвет фона */
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
            height: 25px;
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
            background: #5bc657;
            border: solid 1px #494949;
            border-radius:  3px;
            color: #1d1d1d;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
        }
        .button_log_in{ /* Кнопка регистрации */
            background: #158fec;
            border: solid 1px #494949;
            border-radius:  3px;
            color: #1d1d1d;
            padding: 12px 25px;
            text-align: center;
            text-decoration: none;
            font: 14pt Arial, sans-serif;
            margin: 2px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="header">
    <h1>ScientistsNet</h1>
    <form action="/russian" method="post" id="RUS">
        <button type="submit" value="RUS">RUS</button>
    </form>
    <form action="/english" method="post" id="ENG">
        <button type="submit" value="ENG">ENG</button>
    </form>
</div>
<div id="content">
    <form action="/SignUp" method="post" id="SignUp">
        <!--First name-->
        <p align="center"><input class="text" pattern="[A-Za-zА-Яа-я0-9]]{2,40}" required maxlength="100"
                                 name="s_first_name" size="25" placeholder="Your first name"></p>
        <!--Last name-->
        <p align="center"><input class="text" pattern="[A-Za-zА-Яа-я0-9]{2,40}" required maxlength="100"
                                 name="s_second_name" size="25" placeholder="Your last name"></p>
        <!--Middle name-->
        <p align="center"><input class="text" pattern="[A-Za-zА-Яа-я0-9]{2,40}" maxlength="100"
                                 name="s_second_name" size="25" placeholder="Your middle name"></p>
        <!--Gender-->
        <p align="center"><input name="gender" type="radio" value="male" placeholder="" required>Male
            <input name="gender" type="radio" value="female" placeholder="" required>Female</p>
        <!--Email-->
        <p align="center"><div class="info" align="center" data-title="Only latin letters and numbers, length 2-100">
        <input type="text" class="text"  pattern="[A-Za-z0-9]{3,20}" required maxlength="100"
               name="emailNew" size="20" placeholder="emailNew"></div>
        <!--Password-->
        <p align="center"><div class="info" align="center" data-title="Only latin letters and numbers, length 4-255">
        <input type="password"  class="text" pattern="[A-Za-z0-9]{6,20}" required maxlength="255"
               name="password" size="20" placeholder="password"></div>
        <!--Sign up Button-->
        <p align="center"><button type="submit" form="SignUp" class="button_sign_up">Sign up</button>
            <!--Sign up Button-->
            <button type="submit" form="SignUp" class="button_log_in">Log in</button></p>
    </form>
</div>
<div id="footer">&copy;</div>
</body>
</html>
