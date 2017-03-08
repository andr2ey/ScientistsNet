<%@ page language="java" contentType="text/html; utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>

<form method=post action="j_security_check">
    <p>
        <span>Username:</span> <br /> <input type="text" name="j_username">
    </p>
    <p>
        <span>Password:</span> <br /> <input type="s_password" name="j_password">
    </p>
    <p>
        <input type="submit" value="Login">
    </p>
</form>


</body>
</html>