<%@page import="test1.servlets.Note"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="test1.servlets.Qualities"%>
<%@page import="test1.servlets.Chord"%>
<%@page import="test1.servlets.Grille"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:set var="timeDefault" value="${ !empty dernierTime ? dernierTime : 2 }" />
<c:set var="fondamentaleDefault" value="${ !empty dernierFondamentale ? dernierFondamentale : 'C' }" />
<c:set var="accordDefault" value="${ !empty dernierQuality ? dernierQuality : 'm7' }" />
<c:set var="basseDefault" value="${ !empty dernierBasse ? dernierBasse : 'C' }" />

<body>
	<div id="formAccord">
		<p class="text-secondary h6"
			style="font-style: italic; font-weight: normal;">Veuillez remplir le formulaire.</p>
		<div class="row">
			<form method="POST" action="/webZChords/addChords">
				<div class="mb-2 times">
					<label class="text-primary form-label" for="times">Subdivision</label> <select
						class="form-select" name="times" id="times">

						<c:forEach items="${Chord.timers}" var="elem">
							<option value='<c:out value="${elem}"/>'
								<c:out value="${elem == timeDefault ? 'selected' : '' }"/>>
								<c:out value="${elem}" />
							</option>
						</c:forEach>
					</select>

				</div>
				<div class="mb-2 fondamentales">
					<label class="text-primary form-label" for="fondamentales">Fondamentale</label>
					<select class="form-select" name="fondamentales" id="fondamentales">

						<c:forEach items="${Note.NOTE_VAL_DICT}" var="elem">
							<option value='<c:out value="${elem[0]}"/>'
								<c:out value="${elem[0] == fondamentaleDefault ? 'selected' : '' }"/>>
								<c:out value="${elem[0]}" />
							</option>
						</c:forEach>
					</select>

				</div>

				<div class="mb-2 accords">
					<label class="text-primary form-label" for="accords">Accord</label>
					<select class="form-select" name="accords" id="accords">

						<c:forEach items="${Qualities.listeQualities()}" var="elem">
							<option value='<c:out value="${elem[0]}"/>'
								<c:out value="${elem[0] == accordDefault ? 'selected' : '' }"/>>
								<c:out value="${elem[0]}" />
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-2 basses">
					<label class="text-primary form-label" for="basses">Basse</label> <select
						class="form-select" name="basses" id="basses">

						<c:forEach items="${Note.NOTE_VAL_DICT}" var="elem">
							<option value='<c:out value="${elem[0]}"/>'
								<c:out value="${elem[0] == basseDefault ? 'selected' : '' }"/>>
								<c:out value="${elem[0]}" />
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-2 multiplicateur">
					<label class="text-primary form-label" for="multiplicateur">Multiplicateur</label>
					<input type="number" name="multiplicateur" placeholder="0"
						value="1" id="multiplicateur">
				</div>
				<div class="mb-2">
					<button class="btn btn-outline-primary" type="submit"
						value="submit">Envoyer</button>
				</div>
			</form>
			<form>
				<div class="row mb-3">
					<div>
						Dernier accord de la grille:

						<Span>
							<a href="play?acc=<c:out value="${ grike.chords[longGrille - 1] }"/>"><c:out value="${ grike.chords[longGrille - 1] }" /></a>
						</Span>

					</div>
					<div>
						Dernier sélectionné:
						<Span>
							<a href="play?acc=<c:out value="${ dernierAccord }"/>"><c:out value="${ dernierAccord }" /></a>
						</Span>
						</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>