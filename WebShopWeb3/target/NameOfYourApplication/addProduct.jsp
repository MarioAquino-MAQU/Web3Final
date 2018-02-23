<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Add product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
Add product
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

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <form method="post" action="Controller?action=createProduct" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">Name</label><input type="text" id="name" name="name"
         required value="<c:out value='${name}' />"> </p>
        <p><label for="firstName">Description</label><input type="text" id="description" name="description"
         required value="<c:out value='${description }' />"> </p>
        <p><label for="lastName">Price</label><input type="number" id="price" name="price"
         required value="<c:if test="${fouten != null }"><c:out value='${price }' /></c:if>"> </p>
        <p><input type="submit" id="signUp" value="Add Product"></p>
        
    </form>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>