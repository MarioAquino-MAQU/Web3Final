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

</tr>
</c:forEach>

<caption>Products Overview</caption>
</table>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>