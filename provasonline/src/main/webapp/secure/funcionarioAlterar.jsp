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
				<div class="span6 offset5">
					<fieldset>
						<legend>Alterar Funcionario</legend>
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
						<form id="frmAlterar" action="alterarFuncionario" method="post">
							<div class="grid">
								<div class="row">
									<div class="span3">
										<label>Matrícula:</label>
										<div class="input-control text">
											<input type="text" id="matricula" name="matricula" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												autofocus="autofocus" value="${func.matricula}">
											<button class="btn-clear"></button>
										</div>
										<label>Nome:</label>
										<div class="input-control text">
											<input type="text" id="nome" name="nome" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.nome}">
											<button class="btn-clear"></button>
										</div>
										<label>Função:</label>
										<div class="input-control text">
											<input type="text" id="funcao" name="funcao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.funcao}">
											<button class="btn-clear"></button>
										</div>
									</div>
									<div class="span3">
										<label>E-mail:</label>
										<div class="input-control text">
											<input type="text" id="email" name="email" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												value="${func.email}">
											<button class="btn-clear"></button>
										</div>
										<label>Perfil:</label>
										<div class="input-control select">
											<select id="perfil" name="perfil" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<c:forEach var="per" items="${listaPerfis}">
													<option ${per.perfilId eq func.perfilId ? 'selected' : ''} value="${per.perfilId}">${per.descricao}</option>
												</c:forEach>
											</select>
										</div>
										<label>Status:</label>
										<div class="input-control select">
											<select id="status" name="status" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<option ${func.status eq 'Ativo' ? 'selected' : ''} value="Ativo">Ativo</option>
												<option ${func.status eq 'Inativo' ? 'selected' : ''} value="Inativo">Inativo</option>
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
							<input id="matriculaAntiga" name="matriculaAntiga" type="hidden" value="${func.matricula}">
							<input id="usuario" name="usuario" type="hidden" value="${func.usuario}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>