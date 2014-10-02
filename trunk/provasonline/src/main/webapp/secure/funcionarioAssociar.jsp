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
						<legend>Associar Funcionarios</legend>
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
						<form id="frmAssociar" action="associarFuncionario" method="post">
							<div class="grid">
								<div class="row">
									<div class="span3">
										<label>Gerente:</label>
										<div class="input-control select">
											<select id="gerente" name="gerente" oninput="setCustomValidity('')" autofocus="autofocus"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<c:forEach var="ger" items="${listaGerentes}">
													<option value="${ger.matricula}">${ger.nome}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label>Colaborador:</label>
										<div class="input-control select">
											<select id="colaborador" name="colaborador" oninput="setCustomValidity('')" size="15" 
												multiple="multiple" oninvalid="setCustomValidity('Por favor, preencha este campo.')" 
												required>
												<c:forEach var="col" items="${listaColaboradores}">
													<option value="${col.matricula}">${col.nome}</option>
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