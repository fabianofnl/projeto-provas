<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="paginator" uri="/WEB-INF/tlds/paginator"%>
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
						<legend>Vincular Apostilas</legend>
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
										<th style="width:30%">Apostila</th>
										<th style="width:60%">Provas</th>
										<th style="width:10%">Ações</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaProvas}">
											<tr>
												<td colspan="5">Não há registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaProvas}" var="prova">
												<tr>
													<td style="width:30%" valign="top">
														<ul style="list-style: none;">
															<li data-hint="Descrição | ${prova.titulo}" data-hint-position="top">${prova.titulo}
																<c:if test="${prova.quantidadeQuestoes gt 0}">
																	<br>
																	<span style="font-size: 9pt !important;">
																		${prova.quantidadeTemas} temas
																	</span>
																	<br>
																	<span style="font-size: 9pt !important;">
																		${prova.quantidadeQuestoes} questões
																	</span>
																</c:if>
															</li>
														</ul>
													</td>
													<td style="width:60%;">
														<ul style="list-style: none;">
															<c:forEach items="${prova.listaTemas}" var="tema">
																<li style="padding:2px;">
																	<span style="font-size: 9pt !important;">
																		<Strong>Tema: </Strong>${tema.titulo}
																	</span>
																</li>
															</c:forEach>
														</ul>
													</td>
													<td style="width:10%; text-align: center;">
														<a href="${pageContext.request.contextPath}/secure/adicionarQuestoesProva?provaId=${prova.provaId}">
															<span class="icon-box-add" data-hint="Adicionar Questões" data-hint-position="top"></span>
														</a>
														<c:if test="${prova.quantidadeTemas eq 0}">
															<span class="custom-separator">|</span>															
															<a href="${pageContext.request.contextPath}/secure/removerProva?provaId=${prova.provaId}">
																<span class="icon-remove" data-hint="Remover Prova" data-hint-position="top"></span>
															</a>
														</c:if>
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
												<c:url var="searchUri" value="/secure/provas?pagina=##"/>
												<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
											</c:if>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>

					<fieldset>
						<legend>Cadastrar Provas</legend>
						<form id="frmCadastrarProvas" action="provas" method="post">
							<div class="grid">
								<div class="row">
									<div class="span12">
										<label>Título da prova:</label>
										<div class="input-control text">
											<input type="text" id="titulo" name="titulo" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
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
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>