<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/tlds/paginator"%>
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
				<div class="span12 offset3">
					<fieldset>
						<legend>Lista de provas agendadas</legend>
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

						<div class="divHeader">
							<table class="tableClass tableHeader">
								<thead>
									<tr>
										<th style="width:30%">Colaborador</th>
										<th style="width:35%">Prova</th>
										<th style="width:10%">Realização</th>
										<th style="width:15%">Situação</th>
										<th style="width:10%">Ações</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaAgendas}">
											<tr>
												<td colspan="5">Não há registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaAgendas}" var="agenda">
												<tr>
													<td style="width:30%; text-align: center;">${agenda.funcionario.nome}</td>
													<td style="width:35%;">${agenda.prova.titulo}</td>
													<td style="width:10%; text-align: center;">
														<fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.provaAgendada}" />
													</td>
													<td style="width:15%; text-align: center;">
														<c:choose>
															<c:when test="${agenda.flag}">
																<label>Realizado</label>
															</c:when>
															<c:otherwise>
																<label>Não Realizado</label>
															</c:otherwise>
														</c:choose>
													</td>
													<td style="width:10%; text-align: center;">
														<a href="">
															<span class="icon-wrench" data-hint="Alterar" 
																	data-hint-position="top"></span>
														</a>
														<span class="custom-separator">|</span>
														<a href="">
															<span class="icon-remove" data-hint="Remover" 
																	data-hint-position="top"></span>
														</a>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<div class="divFooter">
							<table class="tableClass tableFooter">
								<tfoot>
									<tr>
										<td>
											<c:if test="${listSize ne 0}">
												<c:url var="searchUri" value="/secure/agendarProvas?pagina=##"/>
												<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
											</c:if>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>
					
					<fieldset>
						<legend>Agendar Provas</legend>
						<form id="frmAgendar" action="agendarProvas" method="post">
							<div class="grid">
								<div class="row">
									<div class="span8 offset2">
										<label>Colaborador:</label>
										<div class="input-control select">
											<select id="matricula" name="matricula" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<c:forEach var="col" items="${listaColaboradores}">
													<option value="${col.matricula}">${col.nome}</option>
												</c:forEach>
											</select>
										</div>
										<label>Provas:</label>
										<div class="input-control select">
											<select id="provaId" name="provaId" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
												<option value="">Selecione</option>
												<c:forEach var="prova" items="${listaProvas}">
													<option value="${prova.provaId}">${prova.titulo}</option>
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
											<input type="text" id="data" name="data">
											<button class="btn-date" type="button"></button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="span8 offset2">
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