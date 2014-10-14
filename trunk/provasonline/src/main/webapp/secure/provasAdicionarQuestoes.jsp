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
						<legend>Adicionar Questoes</legend>
						<form id="frmAdicionar" action="adicionarQuestoesProva" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Prova:</label>
										<label>${prova.titulo}</label>
									</div>
								</div>

								<div class="row">
									<div class="span10">
										<label>Questões:</label>
										<div class="input-control select">
											<select id="questoesId" name="questoesId" oninput="setCustomValidity('')" size="20" 
												multiple="multiple" oninvalid="setCustomValidity('Por favor, preencha este campo.')" 
												required>
												<c:forEach var="questao" items="${listaQuestoes}">
													<option value="${questao.questaoId}" 
														data-hint="Descrição | ${questao.descricaoQuestao}" 
														data-hint-position="right">${questao.tituloQuestao}
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
							<input type="hidden" id="provaId" name="provaId" value="${prova.provaId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>