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
						<p id="idMsg" class="bg-amber fg-white">
							<span class="icon-warning padding10"></span>
							Ao confirmar as informa��es voc� inativar� o funcionario do sistema.
						</p>
						<form id="frmDialogInativar" action="inativarFuncionario" method="post">
							<div class="grid">
								<div class="row">
									<div class="span3">
										<label>Matr�cula:</label>
										<label>${func.matricula}</label>
										<input id="matricula" name="matricula" type="hidden" value="${func.matricula}">

										<label>Nome:</label>
										<label>${func.nome}</label>
										
										<label>Fun��o:</label>
										<label>${func.funcao}</label>
									</div>
									
									<div class="span3">
										<label>E-mail:</label>
										<label>${func.email}</label>
										
										<label>Perfil:</label>
										<label>${func.descricao}</label>
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