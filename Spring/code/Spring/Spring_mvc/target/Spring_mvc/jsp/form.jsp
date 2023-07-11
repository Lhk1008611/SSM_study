<%--
  Created by IntelliJ IDEA.
  User: Lhk
  Date: 2021/12/20
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<%--${pageContext.request.contextPath}--%>
<form action="/Spring_mvc/user/quick13" method="post">
    <input type="text" name="userList[0].name"><br>
    <input type="text" name="userList[0].age"><br>
    <input type="text" name="userList[1].name"><br>
    <input type="text" name="userList[1].age"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
