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
						<legend>Alterar Tema</legend>
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
						<form id="frmAlterar" action="alterarTema" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>T�tulo:</label>
										<div class="input-control text">
											<input type="text" id="titulo" name="titulo" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												autofocus="autofocus" value="${tema.titulo}">
											<button class="btn-clear"></button>
										</div>
										<label>Descri��o:</label>
										<div class="input-control textarea">
											<textarea id="descricao" name="descricao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>${tema.descricao}</textarea>
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
							<input id="temaId" name="temaId" type="hidden" value="${tema.temaId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>