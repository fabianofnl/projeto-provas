<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/include/include_css.jsp"></jsp:include>
<jsp:include page="/include/include_js.jsp"></jsp:include>
<title>${tituloPagina}</title>
</head>
<body class="metro">
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/secure/menu.jsp"></jsp:include>
	<section style="padding: 1px;">
		<div class="grid">
			<div class="row">
				<div class="span10 offset4">
					<fieldset>
						<legend>Detalhes da Prova</legend>
						
						<c:if test="${msgType eq 'info'}">
							<p id="idMsg" class="bg-lightBlue fg-white padding10">
								<span class="icon-info padding10"></span>${msg}
							</p>
							<br>
							<br>
							<form id="frmRealizarProva" action="realizarProva" method="post">
								<input type="hidden" id="provaId" name="provaId" value="${prova.provaId}">
								<input type="hidden" id="tituloProva" name="tituloProva" value="${prova.titulo}">
								<input type="hidden" id="agendaId" name="agendaId" value="${agenda.agendaId}">
	
								<div class="row">
									<div class="span10">
										<div class="form-actions">
											<button class="button primary">Confirmar a realização da prova</button>
										</div>
									</div>
								</div>
							</form>
						</c:if>
						<c:if test="${msgType eq 'warn'}">
							<p id="idMsg" class="bg-amber fg-white">
								<span class="icon-warning padding10"></span>${msg}
							</p>
						</c:if>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>