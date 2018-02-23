<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
Check password result
</h2>
</header><main>
<p>${result }</p>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>