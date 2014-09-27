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
								<button class="btn-clear"></button>
							</div>
							<label>Nome:</label>
							<div class="input-control text">
								<input type="text" id="nome" name="nome" oninput="setCustomValidity('')"
									oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
									value="${func.nome}" readonly="readonly">
								<button class="btn-clear"></button>
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
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>