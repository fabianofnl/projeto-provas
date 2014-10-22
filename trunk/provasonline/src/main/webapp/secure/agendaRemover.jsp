<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
						<legend>Cancelar Agendamento</legend>
						<p id="idMsg" class="bg-amber fg-white">
							<span class="icon-warning padding10"></span>
							Ao confirmar as informações você removerá o agendamento do sistema.
						</p>
						<form id="frmRemover" action="cancelarAgenda" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Funcionário:</label>
										<label>${agenda.funcionario.nome}</label>
										
										<label>Prova:</label>
										<label>${agenda.prova.titulo}</label>
										
										<label>Data agendada:</label>
										<label><fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.provaAgendada}"/></label>
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
							<input type="hidden" id="agendaId" name="agendaId" value="${agenda.agendaId}">
							<input type="hidden" id="provaId" name="provaId" value="${agenda.prova.provaId}">
							<input type="hidden" id="matricula" name="matricula" value="${agenda.funcionario.matricula}">
							<fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.provaAgendada}" var="dataFormat"/>
							<input type="hidden" id="data" name="data" value="${dataFormat}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>