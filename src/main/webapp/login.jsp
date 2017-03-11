<%@ page import="java.io.PrintWriter" %>
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
        }
    </style>
</head>
<body>
<div id="header">
    <div>
        <p align="center"> <h1>ScientistsNet</h1>
    </div>
    <div>
        <form action="/language" method="post">
            <p align="center"><input type="submit"  class="button_lang" value="ENG">
                <input type="submit"  class="button_lang"  value="RUS"></p>
        </form>
    </div>
</div>
<div id="content">
    <p align="center"><h3>Authorization</h3>
    <form action="j_security_check" method="post" id="j_security_check">
        <!--Email-->
        <!--TODO create pattern for email-->
        <p align="center">
            <input type="email" class="text" required maxlength="100" pattern=".+@.+\..+"
                   name="j_username" size="100" placeholder="email">
        <!--Password-->
        <p align="center"><div class="info" align="center" data-title="Only latin letters and numbers, length 3-255">
        <input type="password"  class="text" pattern="[A-Za-z0-9]{3,255}" required maxlength="255"
               name="j_password" size="255" placeholder="password"
               value=""></div>
        <!--Log in Button-->
        <p align="center">
            <button type="submit" form="j_security_check" class="button_log_in">Log in</button>
        <p align="center">
        ${requestScope.unregistered}
    </form>
    <!--Sign up Button-->
    <form method="post" action="/signup">
        <p align="center"><input type="submit"  class="button_sign_up" value="Sign up">
    </form>

</div>
</body>
</html>