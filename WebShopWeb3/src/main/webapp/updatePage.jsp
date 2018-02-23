<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Update Product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
<jsp:include page="header.jsp"/>
<h2>
Update product
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


    <form method="post" action="Controller?action=updateP&productId=${param.productId}" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">Name</label><input type="text" id="name" name="name"
         required value='<c:out value="${param.name }"></c:out>'> </p>
        <p><label for="firstName">Description</label><input type="text" id="description" name="description"
         required value='<c:out value="${param.disc }"></c:out>'> </p>
        <p><label for="lastName">Price</label><input type="number" id="price" name="price"
         required value='<c:out value="${param.price }"></c:out>'> </p>
         <p>
         <label></label><input id="productId" name="productId" type="hidden" value="<c:out value="${param.productId }"></c:out>">
        </p>
        <p><input type="submit" id="signUp" value="Save"></p>
        
    </form>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>