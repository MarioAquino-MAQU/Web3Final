<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Cart Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
Cart Overview
</h2>

</header>
<body>

<table>
<tr>
<th>Name</th>
<th>Description</th>
<th>Price</th>
<th>Qty</th>
</tr>
<c:forEach var="entry" items="${cart }">

	<tr>

		<td>${entry.key.name }</td>
		<td>${entry.key.description }</td>
		<td>${entry.key.price}</td>
		<td>${entry.value }</td>
 		
	</tr>
</c:forEach>

<caption>Cart Overview</caption>
</table>
<h3>Totale prijs: ${total }</h3>
<jsp:include page="footer.jsp"/>
</body>
</html>