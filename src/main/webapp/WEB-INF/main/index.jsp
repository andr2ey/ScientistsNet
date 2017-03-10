<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.03.2017
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
${sessionScope.email}
<form action="/main" method="post">
    <p align="center">
        <input type="submit"  class="button_lang" name="logout" value="true">
    </p>
</form>
</body>
</html>
