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
						<legend>Remover Questão</legend>
						<p id="idMsg" class="bg-amber fg-white">
							<span class="icon-warning padding10"></span>
							Ao confirmar as informações você removerá a questão da prova.
						</p>
						<form id="frmRemover" action="removerQuestaoProva" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Prova:</label>
										<label>${prova.tituloProva}</label>
										
										<label>Título:</label>
										<label>${questao.tituloQuestao}</label>
										
										<label>Descrição:</label>
										<label>${questao.descricaoQuestao}</label>
										
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
							<input type="hidden" id="questaoId" name="questaoId" value="${questao.questaoId}">
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