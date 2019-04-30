<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/uploadfile.do" method="post"
     enctype="multipart/form-data">
  文件说明：<input type="text" name="demo"><br>
     文件1：<input type="file" name="file1"><br>
     文件2：<input type="file" name="file2"><br>
     文件3：<input type="file" name="file3"><br>
     文件4：<input type="file" name="file4"><br>
     <input type="submit" value="上传">
</form>
</body>
</html>