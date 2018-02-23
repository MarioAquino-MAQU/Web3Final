<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview Products</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
Product Overview
</h2>

</header><main>
<table>
<tr>
<th>Name</th>
<th>Description</th>
<th>Price</th>
</tr>
<c:forEach var="product" items="${service }">

	<tr>

		<td><a href="Controller?action=update&name=${product.name }&disc=${product.description}&price=${product.price}&productId=${product.productId}">${product.name }</a></td>
		<td>${product.description }</td>
		<td>${product.price }</td>
		<td><a href="Controller?action=delete&name=${product.name }&disc=${product.description}&price=${product.price}&productId=${product.productId}">delete</a></td>
		<td><form method="post" action="Controller?action=addToChart&productId=${product.productId}" novalidate="novalidate">
		<p><label></label><input id="count" name="count" type="number" step="1" min="1" max="10" value="1"></p>
        <p><input type="submit" id="signUp" value="Add To Cart"></p>
		</form></td>
 		
	</tr>
</c:forEach>

<caption>Products Overview</caption>
</table>
<c:if test="${sessionScope.cartsize != null }">
<h3><a href="Controller?action=bill">Shopping cart</a>: ${sessionScope.cartsize} items</h3>
</c:if>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>