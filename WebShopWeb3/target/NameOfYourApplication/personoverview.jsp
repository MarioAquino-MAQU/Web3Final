<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>User Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
<h2>
User Overview
</h2>

</header><main>
<table>
<tr>
<th>E-mail</th>
<th>First Name</th>
<th>Last Name</th>
</tr>
<c:forEach var="person" items="${service }">
<tr>

<td>${person.email }</td>
<td>${person.firstName }</td>
<td>${person.lastName }</td>
<td><a href="Controller?action=deletePerson&userid=${person.userid}">Delete</a></td>
<td><a href="Controller?action=pw&userid=${person.userid }&salt=${person.salt}">Check</a></td>
<td><a href="Controller?action=updatePerson&userid=${person.userid}&email=${person.email}&firstname=${person.firstName}&lastname=${person.lastName}&role=${person.role}">Update</a></td>

</tr>
</c:forEach>

<caption>Users Overview</caption>
</table>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>