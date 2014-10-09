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
						<legend>Lista de Questões</legend>
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
										<th style="width:30%">Questão</th>
										<th style="width:60%">Resposta/Opções</th>
										<th style="width:10%">Ações</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaQuestoes}">
											<tr>
												<td colspan="5">Não há registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaQuestoes}" var="questoes">
												<tr>
													<td style="width:30%; text-align: center;">${questoes.tituloQuestao}</td>
													<td style="width:60%;">${questoes.descricaoQuestao}</td>
													<td style="width:10%; text-align: center;">
													
													
														<a href="${pageContext.request.contextPath}/secure/alterarQuestao?questaoId=${tema.temaId}">
															<span class="icon-wrench" title="Alterar"></span>
														</a>
														<c:if test="${questoes.quantidadeQuestoes eq 0}">
															<span class="custom-separator">|</span>															
															<a href="${pageContext.request.contextPath}/secure/alterarQuestao?questaoId=${tema.temaId}">
																<span class="icon-remove" title="Remover"></span>
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
												<c:url var="searchUri" value="/secure/questoes?pagina=##"/>
												<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
											</c:if>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>

					<fieldset>
						<legend>Cadastrar Questões</legend>
						<form id="frmCadastrarQuestoes" action="questoes" method="post">
							<div class="grid">
								<div class="row">
									<div class="span12">
									
										<label>Tema:</label>
										<div class="input-control select">
											<select id="temaId" name="temaId" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required autofocus="autofocus">
												<option value="">Selecione</option>
												<c:forEach var="tema" items="${listaTemas}">
													<option value="${tema.temaId}">${tema.titulo}</option>
												</c:forEach>
											</select>
										</div>
									
										<label>Título da questão:</label>
										<div class="input-control text">
											<input type="text" id="titulo" name="titulo" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
											<button class="btn-clear"></button>
										</div>
										<label>Descrição:</label>
										<div class="input-control textarea">
											<textarea id="descricao" name="descricao" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required></textarea>
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