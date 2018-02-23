<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Update Person</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${kleur }.css">
<jsp:include page="header.jsp"/>
<h2>
Update Person
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


    <form method="post" action="Controller?action=updatePersonConfirmation&userid=${param.userid}" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="email">email</label><input type="text" id="email" name="email"
         required value="${param.email }"> </p>
        <p><label for="firstName">firstname</label><input type="text" id="firstName" name="firstName"
         required value="${param.firstname }"> </p>
        <p><label for="lastName">lastname</label><input type="text" id="lastName" name="lastName"
         required value="${param.lastname }"> </p>
         <p>
         <label></label><input id="userid" name="userid" type="hidden" value="${param.userid }">
        </p>
        <p><label for="role">role</label>
        <select class="role-select" name="role" id="role">
        	<c:forEach var="role" items="${enumlijst}">
        		<option value="${role}">${role}</option>
        	</c:forEach>
        </select>
        </p>
        <p><input type="submit" id="update" value="Save"></p>
        
    </form>
</main>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>