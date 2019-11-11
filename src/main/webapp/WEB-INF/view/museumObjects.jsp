<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width = device-width, initial- scale = 1">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


<style>
.page-header{
	color:#E12323
}

</style>

</head>
<body>

<nav class="navbar navbar-light" style="background-color: #e3f2fd">
	<a class="navbar-brand">Najbolji Muzej</a>
	<a href="logInForm" class="btn btn-outline-primary" type="button">LogIn</a>	
</nav>




<div class="container">

    <c:choose>
		<c:when test="${museumObjects.size()==0}">
      		<p>Nema objekata!</p>
    	</c:when>
		<c:otherwise>
			<ul>
				<table style="width:100%">
					<c:forEach var="museumObjects" items="${museumObjectsInRows}">
						<tr>
							<c:forEach var="museumObject" items="${museumObjects}">
							
								<thead>
									<tr>
										<th><a href="museumObject/${museumObject.id}"><img width="220" height="220" src="images/${museumObject.imageName}" alt=".." class="img-thumbnail"></a></th>
									</tr>
								</thead>
																
								<tbody>
									<tr>
										<td>${museumObject.name}</td>
									</tr>
								</tbody>
								
								
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</ul>
		</c:otherwise>
	</c:choose>
	
</div>	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
</body>
</html>
