<%--
  Created by IntelliJ IDEA.
  User: Lhk
  Date: 2021/12/20
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<h2>
    <span>文件上传</span>
</h2>
<form action="${pageContext.request.contextPath}/user/upload" method="post" enctype="multipart/form-data">
    文件1：<input type="file" name="uploadFile"><br>
    文件2：<input type="file" name="uploadFile"><br>
    <input type="submit" value="上传"><br>
</form>

<br>


</body>
</html>
