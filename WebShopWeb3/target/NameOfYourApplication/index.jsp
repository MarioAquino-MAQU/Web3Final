<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>Web shop</span>
			</h1>
			<jsp:include page="header.jsp"/>
			<h2>Home</h2>

		</header>
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt.
		<c:set var="result" value="${result }"/>
		<c:set var="fouten" value="${fouten }"/>
		<c:if test="${result == false }">
		<div class="alert-danger">
		<c:forEach var="fout" items="${fouten }">
			<p>${fout }</p>
		</c:forEach>
		</div>
		</c:if>
		<c:if test="${sessionScope.name != null }">
		<p>		</p>
		<p>Welcome, ${sessionScope.name}.</p>
		<form method="post" action="Controller?action=Logout" novalidate="novalidate">
		<p><input type="submit" id="logOut" value="Log out"></p>
		</c:if>
		<c:if test="${sessionScope.name == null }">
		<form method="post" action="Controller?action=Login" novalidate="novalidate">
		<p><label>Your userid</label><input type="text" id="userid" name="userid" required value="<c:out value='${userid}' />"></p>
		<p><label>Your password</label><input type="password" id="password" name="password" required value="<c:out value='${password}'/>"></p>
 		<p><input type="submit" id="logIn" value="Log in"></p>
 		</form>
 		</c:if>
		
		 </main>
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>