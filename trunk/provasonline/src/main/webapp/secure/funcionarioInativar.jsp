<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/include/include_css.jsp"></jsp:include>
<jsp:include page="/include/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/include/custom/js/funcionarioSGPO.js"></script>
<title>${tituloPagina}</title>
</head>
<body class="metro">
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/secure/menu.jsp"></jsp:include>
	<section style="padding: 1px;">
		<div class="grid">
			<div class="row">
				<div class="span7 offset5">
					<fieldset>
						<legend>Inativar Funcionario</legend>
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
						<c:if test="${msgType ne 'info'}">
							<p id="idMsg" class="bg-amber fg-white">
								<span class="icon-warning padding10"></span>
								Ao confirmar as informações você inativará o funcionario do sistema.
							</p>
						</c:if>
						<form id="frmDialogInativar" action="inativarFuncionario" method="post">
							<div class="grid">
								<div class="row">
									<div class="span3">
										<label>Matrícula:</label>
										<div class="input-control text">
											<input type="text" id="matricula" name="matricula" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												autofocus="autofocus" value="${func.matricula}" readonly="readonly">
										</div>
										<label>Nome:</label>
										<div class="input-control text">
											<input type="text" id="nome" name="nome" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.nome}" readonly="readonly">
										</div>
										<label>Função:</label>
										<div class="input-control text">
											<input type="text" id="funcao" name="funcao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.funcao}" readonly="readonly">
										</div>
									</div>
									
									<div class="span3">
										<label>E-mail:</label>
										<div class="input-control text">
											<input type="text" id="email" name="email" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.email}" readonly="readonly">
										</div>
										<label>Perfil:</label>
										<div class="input-control select">
											<select id="perfil" name="perfil" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												disabled="disabled">
												<option value="">Selecione</option>
												<c:forEach var="per" items="${listaPerfis}">
													<option selected="${per.perfilId eq func.perfilId ? 'selected' : ''}" value="${per.perfilId}">${per.descricao}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="span3">
										<div class="form-actions">
											<button class="button primary">Enviar</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>