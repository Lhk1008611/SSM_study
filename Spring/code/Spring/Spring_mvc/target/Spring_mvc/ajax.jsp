<%--
  Created by IntelliJ IDEA.
  User: Lhk
  Date: 2021/12/20
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script>
        var userList = new Array();
        userList.push({username:"lhk",age:21});
        userList.push({username:"LHK",age:22});

        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/quick14",
            data:JSON.stringify(userList),
            contentType:"application/json;charset=utf-8"
        });
    </script>

</head>
<body>

</body>
</html>
