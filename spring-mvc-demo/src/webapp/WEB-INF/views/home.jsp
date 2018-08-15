<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DuanJiaNing
  Date: 2018/8/15
  Time: 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<h1>Home, Hello World</h1>

<a href="<c:url value="/spittles" />">Spittles</a> |
<a href="<c:url value="/spittles/register" />">Register</a>

</body>
</html>
