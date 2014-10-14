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
						<legend>Alterar Opção</legend>
						<form id="frmAlterar" action="alterarOpcao" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Opção:</label>
										<div class="input-control text">
											<input type="text" id="tituloOpcao" name="tituloOpcao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												autofocus="autofocus" value="${opcao.tituloOpcao}">
											<button class="btn-clear"></button>
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
							<input type="hidden" id="opcaoId" name="opcaoId" value="${opcao.opcaoId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>