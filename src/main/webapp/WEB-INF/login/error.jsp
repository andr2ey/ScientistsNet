
<%@ page contentType="text/html; utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language.lang" var="lang"/>

<fmt:message bundle="${lang}" key="lang.error.login" var="error_login"/>
<c:set var="error_login" scope="session" value="${error_login}"/>
<c:redirect url="/main"/>

