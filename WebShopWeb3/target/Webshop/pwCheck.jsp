
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Password result</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
Check password
</h2>
</header><main>
<p>Fill out your password:</p>
<form method="post" action="Controller?action=CheckPw" novalidate="novalidate">
<p><label for="password">Password</label><input type="text" id="password" name="password"
         required value=""> </p>
<p><label></label><input id="userid" name="userid" type="hidden" value="${param.userid }"></p>
<p><input type="submit" id="Check" value="Check"></p>
</form>

</main>
<jsp:include page="footer.jsp"/>

</div>
</body>
</html>