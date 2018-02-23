<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
Sign Up
</h2>

</header><main>
	<c:set var="fouten" value="${fouten }"/>
	<c:if test="${fouten != null }">
	<div class="alert-danger">
		
		<ul>
		<c:forEach var="fout" items="${fouten}">
			<li>${fout}</li>
		</c:forEach>
		</ul>
	</div>
	</c:if>


    <form method="post" action="Controller?action=create" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid"
         required value="<c:out value='${userid }' />"> </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
         required value="<c:out value='${firstName }' />"> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
         required value="<c:out value='${lastName }' />"> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" required value="${email }"></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password"
         required value="<c:out value='${password }' />"> </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>
