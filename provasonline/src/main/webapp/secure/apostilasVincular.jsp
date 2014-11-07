<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<c:if test="${msgType eq 'error'}">
							<p id="idMsg" class="bg-amber fg-white">
								<span class="icon-warning padding10"></span>${msg}
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
									<th style="width:35%">Apostila</th>
									<th style="width:55%">Provas</th>
									<th style="width:10%">Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty listaApostilas}">
										<tr>
											<td colspan="5">Não há registros.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${listaApostilas}" var="apostila">
											<tr>
												<td style="width:35%" valign="top">
													<ul style="list-style: none;">
														<li>
															<c:if test="${apostila.extensao eq 'doc' or apostila.extensao eq 'docx'}">
																<i class="icon-file-word fg-blue"></i>
															</c:if>
															<c:if test="${apostila.extensao eq 'xls' or apostila.extensao eq 'xlsx'}">
																<i class="icon-file-excel fg-green"></i>
															</c:if>
															<c:if test="${apostila.extensao eq 'pdf'}">
																<i class="icon-file-pdf fg-red"></i>
															</c:if>
															<c:if test="${apostila.extensao ne 'doc' and apostila.extensao ne 'docx' 
																		and apostila.extensao ne 'xls' and apostila.extensao ne 'xlsx'
																		and apostila.extensao ne 'pdf'}">
																<i class="icon-file"></i>
															</c:if>
															${apostila.nome}
														</li>
													</ul>
												</td>
												<td style="width:55%;">
													<ul style="list-style: none;">
														<c:forEach items="${apostila.listaProvas}" var="prova">
															<li style="padding:2px;">${prova.titulo}
																<a href="${pageContext.request.contextPath}/secure/removerProvaApostila?apostilaId=${apostila.apostilaId}&provaId=${prova.provaId}">
																	<span class="icon-remove" data-hint="Remover Apostila" data-hint-position="top"></span>
																</a>
															</li>
														</c:forEach>
													</ul>
												</td>
												<td style="width:10%; text-align: center;">
													<a href="${pageContext.request.contextPath}/secure/adicionarProvasApostila?apostilaId=${apostila.apostilaId}">
														<span class="icon-box-add" data-hint="Adicionar Provas" data-hint-position="top"></span>
													</a>
													<c:if test="${true}">
														<span class="custom-separator">|</span>															
														<a href="${pageContext.request.contextPath}/secure/removerApostila?apostilaId=${apostila.apostilaId}">
															<span class="icon-remove" data-hint="Remover Apostila" data-hint-position="top"></span>
														</a>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<c:if test="${listSize ne 0}">
							<c:url var="searchUri" value="/secure/vincularApostilas?pagina=##"/>
							<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
						</c:if>
					</fieldset>
					<br>
					<br>
					<fieldset>
						<legend>Anexar Apostilas</legend>
						<form id="frmApostilas" action="vincularApostilas" method="post" enctype="multipart/form-data">
							<div class="grid">
								<div class="row">
									<div class="span12">
										<label>Título da prova:</label>
										<div class="input-control file">
											<input type="file" id="file" name="file"
												oninput="setCustomValidity('')" required
												oninvalid="setCustomValidity('Por favor, selecione um arquivo.')"/>
											<button type="button" class="btn-file"></button>
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