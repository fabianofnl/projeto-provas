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
					<label><strong>Alterar Colaboradores</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="span6 offset5">
					<form id="frmDialogAlterar" action="funcionarioAlterar" method="post">
						<div class="grid">
							<div class="row">
								<div class="span3">
									<label>Matr�cula:</label>
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
									<label>Fun��o:</label>
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
												<option value="${per.perfilId}">${per.descricao}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="span3">
									<div class="form-actions">
										<div class="paddingTop5">
											<button id="btnAlterar" class="button bd-blue">OK</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>