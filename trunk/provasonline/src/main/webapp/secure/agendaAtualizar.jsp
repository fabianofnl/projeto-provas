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
						<legend>Atualizar Agendamento</legend>
						<form id="frmRemover" action="atualizarAgenda" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Funcionário:</label>
										<label>${agenda.funcionario.nome}</label>
										
										<label>Provas:</label>
										<div class="input-control select">
											<select id="provaId" name="provaId" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<c:forEach var="prova" items="${listaProvas}">
													<option ${agenda.prova.provaId eq prova.provaId ? 'selected' : '' } 
														value="${prova.provaId}">${prova.titulo}</option>
												</c:forEach>
											</select>
										</div>
										<label>Data:</label>
										<div class="input-control text" style="width: 150px;" 
											data-role="datepicker"
											data-format="dd/mm/yyyy"
											data-position="top"
											data-week-start="0"
											data-effect="none">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.provaAgendada}" var="dataFormat"/>
											<input type="text" id="data" name="data" value="${dataFormat}"
												oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
											<button class="btn-date" type="button"></button>
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
							<input type="hidden" id="agendaId" name="agendaId" value="${agenda.agendaId}">
							<input type="hidden" id="matricula" name="matricula" value="${agenda.funcionario.matricula}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>