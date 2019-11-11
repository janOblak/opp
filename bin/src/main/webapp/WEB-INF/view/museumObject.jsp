<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<meta charset="UTF-8">
</head>
<body>
	<nav>
		<div class="p-2">
				<p>
					<a href="/museumObjects" class="btn btn-info btn-md" role="button">Pocetna</a>
				</p>
			</div>
	</nav>
	<div class="container">
		<h2 class="text-center font-weight-light text-info">${museumObject.name}</h2>
		<img src="../images/${museumObject.imageName}" class="rounded"
			alt="primjerak" width=700px; height=500px>
		
		<p class="text-left">${museumObject.description}</p>
		
	</div>
	<div class="container">
		<button type="button" class="btn btn-outline-info">Audiozapis</button>
	</div>

</body>
</html>

