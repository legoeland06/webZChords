
<%@ include file="entete.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bonjour</title>
</head>
<body>
	<h1>BONJOUR ! ! !</h1>
	<p>Et Bienvenu à vous !!!</p>
	<p>
		<c:out value="${ maVariable[0] }" />
	</p>


	<%@ include file="menu.jsp"%>
</body>
</html>