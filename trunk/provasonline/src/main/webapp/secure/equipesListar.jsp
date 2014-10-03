<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/paginator"%>
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
				<div class="span10 offset4">
					<fieldset>
						<legend>Lista de Equipes</legend>
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
										<th style="width:40%">Gerente</th>
										<th style="width:60%">Colaborador</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaEquipes}">
											<tr>
												<td colspan="3">Não há registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaEquipes}" var="equipes">
											
												<tr>
													<td  style="width:40%" valign="top">
														<ul style="list-style: none;">
															<li>${equipes.gerente.nome} (${equipes.gerente.funcao})</li>
														</ul>
													</td>
													<td  style="width:60%">
														<ul style="list-style: none;">
															<c:forEach items="${equipes.listaColaboradores}" var="col">
																<li>
																	${col.nome} (${col.funcao}) 
																	<span class="custom-separator">|</span>
																	<a href="${pageContext.request.contextPath}/secure/removerColaborador?matricula=${col.matricula}">
																		<span class="icon-remove" title="Remover Colaborador"></span>
																	</a>
																</li>
															</c:forEach>
														</ul>
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
												<c:url var="searchUri" value="/secure/listarEquipes?pagina=##"/>
												<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
											</c:if>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>