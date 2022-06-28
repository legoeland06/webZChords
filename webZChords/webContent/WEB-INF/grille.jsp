<%@page import="test1.servlets.Grille"%>
<%@page import="test1.servlets.Chord"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="entete.jsp"%>
<!DOCTYPE html>
<p class="h3 text-success">
	Grille <span style="font-size: .6em">[ <c:out
			value="${ longGrille>0 ? longGrille : 'vide' }" /> 
			<c:out value="${ longGrille>0 ? 'éléments' : ''}"/>]
	</span>
</p>
[
<span class="p text-light"> <c:out value="${ grike.chords }" /></span>
]

<hr>
<div class="container-fluid" id="grille">

	<div class=row>
		<c:forEach var="accord" items="${ grike.chords }">
			<c:set var="time" value="${fn:substring(accord, 0, 1)}" />
			<c:set var="hashcode" value="${ accord.hashCode() }" />
			<div
				class='card col-<c:if test = "${time == 1}">6</c:if><c:if test = "${time == 2}">3</c:if><c:if test = "${time == 3}">2</c:if><c:if test = "${time == 4 || time == 5 || time == 6 || time == 7 }">2</c:if>'>
				<a class="btn btn-outline-success" id='btn-
					<c:out value="${ accord }"/>'
					href='play?acc=<c:out value="${ accord.toString() }" escapeXml="false" />'
					style="margin: .25em; padding: .25em; font-size: 1em"> <span
					class='text-light'> <c:out value="${accord.getTime()}" />:
				</span> <span> <c:out
							value="${accord.getFondamental()}${accord.getQuality()}" />/<c:out value="${accord.getBasse()}" escapeXml="false" /></span>
				</a> <a class="text-danger btn-badge" id='rem-
					<c:out value="${ accord.toString() }"/>' href='remove?hash=
					<c:out value="${hashcode}"/>'
					style="margin-left: .25em; margin-right: .25em; padding-left: .5em; padding-right: .5em">
					<span class="fa fa-minus"></span>
				</a>
			</div>
		</c:forEach>
	</div>
</div>