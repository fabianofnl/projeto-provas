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
				<div class="span12 offset4">
					<fieldset>
						<legend>Alterar Questão</legend>
						<c:if test="${msgType eq 'info'}">
							<p id="idMsg" class="bg-lightBlue fg-white">
								<span class="icon-info padding10"></span>${msg}
							</p>
							<script type="text/javascript">
								setTimeout(function(){
									$("#idMsg").fadeOut(1000);
								}, 6000);
							</script>
						</c:if>
						<form id="frmAlterar" action="alterarQuestao" method="post">
							<div class="grid">
								<div class="row">
									<div class="span12">
										<label>Tema:</label>
										<div class="input-control select">
											<select id="temaId" name="temaId" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required autofocus="autofocus">
												<option value="">Selecione</option>
												<c:forEach var="tema" items="${listaTemas}">
													<option ${questao.temaId eq tema.temaId ? 'selected' : '' } value="${tema.temaId}">${tema.titulo}</option>
												</c:forEach>
											</select>
										</div>
									
										<label>Título da questão:</label>
										<div class="input-control text">
											<input type="text" id="titulo" name="titulo" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${questao.tituloQuestao}">
											<button class="btn-clear"></button>
										</div>
										<label>Descrição:</label>
										<div class="input-control textarea">
											<textarea id="descricao" name="descricao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>${questao.descricaoQuestao}</textarea>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="span12">
										<div class="form-actions">
											<button class="button primary">Enviar</button>
										</div>
									</div>
								</div>
							</div>
							<input id="questaoId" name="questaoId" type="hidden" value="${questao.questaoId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>