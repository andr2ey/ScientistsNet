<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.03.2017
  Time: 19:28
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
EMPTY HOME PAGE
</body>
</html>
