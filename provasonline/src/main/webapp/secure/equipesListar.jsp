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
						<table class="table hovered">
							<thead>
								<tr>
									<th style="width:40%">Gerente</th>
									<th style="width:60%">Colaborador</th>
								</tr>
							</thead>
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
														<li>${equipes.gerente.nome} 
														<br><span style="font-size: 9pt !important;"><strong>Função:</strong> ${equipes.gerente.funcao}</span></li>
													</ul>
												</td>
												<td  style="width:60%">
													<ul style="list-style: none;">
														<c:forEach items="${equipes.listaColaboradores}" var="col">
															<li style="padding:2px;">
																${col.nome} 
																<span class="custom-separator">|</span>
																<a href="${pageContext.request.contextPath}/secure/removerColaborador?matricula=${col.matricula}">
																	<span class="icon-remove" title="Remover Colaborador"></span>
																</a>
																<br>
																<span style="font-size: 9pt !important;"><strong>Função:</strong> ${col.funcao}</span>
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
						<c:if test="${listSize ne 0}">
							<c:url var="searchUri" value="/secure/listarEquipes?pagina=##"/>
							<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
						</c:if>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>