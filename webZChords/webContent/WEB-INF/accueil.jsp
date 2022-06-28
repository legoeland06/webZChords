<%@ include file="entete.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="timeDefault"
		value="${ !empty dernierTime ? dernierTime : 2 }" />
	<c:set var="fondamentaleDefault"
		value="${ !empty dernierFondamentale ? dernierFondamentale : 'C' }" />
	<c:set var="accordDefault"
		value="${ !empty dernierQuality ? dernierQuality : 'm7' }" />
	<c:set var="basseDefault"
		value="${ !empty dernierBasse ? dernierBasse : 'C' }" />

	<c:out value="${ grike.chords[longGrille - 1]  }" />
	<c:set var="maGrille" value="${ grike }" scope="session" />
	<div class="row container-fluid">
			<%@ include file="menu.jsp"%></div>
<!-- 		<div class="form col-5"> -->
<!-- 			<form action="accueil" method="post"> -->
<!-- 				<p> -->
<!-- 					<label class="text-primary form-label" for="nom">Entrez -->
<!-- 						votre nom</label> <input class="form-input" type="text" name="nom" -->
<!-- 						id="nom"> -->
<!-- 				</p> -->
<!-- 				<p> -->
<!-- 					<label class="text-primary form-label" for="password">Entrez -->
<!-- 						votre password</label> <input class="form-input" type="text" -->
<!-- 						name="password" id="password"> -->
<!-- 				</p> -->
<!-- 				<input type="submit" class="btn btn-primary" /> -->

<%-- 				<c:out value="${ form.message } " /> --%>
<%-- 				<c:if test="${ form.loginId > 0 }"> --%>
<!-- 					<p> -->
<!-- 						Vous êtes connectés, votre numéro est employé est: -->
<%-- 						<c:out value="${form.loginId }" /> --%>
<!-- 					</p> -->
<%-- 				</c:if> --%>
<!-- 			</form> -->
<!-- 		</div> -->
	<hr>
	<p class="container h3">
		Création de grille <a href="#grille" class="btn btn-badge"><span>(<c:out
					value="${ maGrille.chords.size() }" />)
		</span></a>
	</p>


	<div class="container">
		<hr>
		<div class="row">
			<%@ include file="content.jsp"%>
		</div>
		<hr>
	</div>

	<div class="container-fluid mt-5">

		<%@ include file="grille.jsp"%>
	</div>
	<hr>
	<div class="container-fluid">
		<%@ include file="menu.jsp"%></div>
</body>
</html>