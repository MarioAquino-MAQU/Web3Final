<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Delete Confirmation</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
<jsp:include page="header.jsp"/>
</header>
<h2>
Delete Confirmation
</h2>
<p>Are u sure you want to delete this product?</p>
<ul>
<li>naam: ${param.name }</li>
<li>description: ${param.disc }</li>
<li>price: ${param.price }</li>
</ul>
 <form method="post" > 
    <p><input type="hidden" name="id" value="${param.productId }"></p>
    <p>
        <input type="submit" name="submit" value="Ja" formaction="Controller?action=deleteConfirmed">
        <input type="submit" name="submit" value="Neen" formaction="Controller?action=products">
    </p>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>