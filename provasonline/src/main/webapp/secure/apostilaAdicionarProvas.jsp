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
						<legend>Adicionar Provas</legend>
						<form id="frmAdicionar" action="adicionarProvasApostila" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Apostila:</label>
										<label>${apostila.nome}</label>
									</div>
								</div>

								<div class="row">
									<div class="span10">
										<label>Provas:</label>
										<div class="input-control select">
											<select id="provasId" name="provasId" oninput="setCustomValidity('')" size="20" 
												multiple="multiple" oninvalid="setCustomValidity('Por favor, preencha este campo.')" 
												required>
												<c:forEach var="prova" items="${listaProvas}">
													<option value="${prova.provaId}" 
														data-hint="Descrição | ${prova.titulo}" 
														data-hint-position="bottom">${prova.titulo}
													</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="span10">
										<div class="form-actions">
											<button class="button primary">Enviar</button>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="apostilaId" name="apostilaId" value="${apostila.apostilaId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>