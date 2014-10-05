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
				<div class="span10 offset4">
					<fieldset>
						<legend>Lista de Temas</legend>
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
										<th style="width:30%">T�tulo</th>
										<th style="width:60%">Descri��o</th>
										<th style="width:10%">A��es</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaTemas}">
											<tr>
												<td colspan="5">N�o h� registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaTemas}" var="tema">
												<tr>
													<td style="width:30%; text-align: center;">${tema.titulo}</td>
													<td style="width:60%;">${tema.descricao}</td>
													<td style="width:10%; text-align: center;">
													
													
														<a href="${pageContext.request.contextPath}/secure/alterarTemas?temaId=${tema.temaId}">
															<span class="icon-wrench" title="Alterar"></span>
														</a>
														<c:if test="${tema.quantidadeQuestoes eq 0}">
															<span class="custom-separator">|</span>															
															<a href="${pageContext.request.contextPath}/secure/inativarFuncionario?matricula=${tema.temaId}">
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
												<c:url var="searchUri" value="/secure/temas?pagina=##"/>
												<paginator:display maxLinks="10" currPage="${pagina}" totalPages="${numeroDePaginas}" uri="${searchUri}" />
											</c:if>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>

					<fieldset>
						<legend>Cadastrar Temas</legend>
						<form id="frmCadastrarTemas" action="temas" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>T�tulo:</label>
										<div class="input-control text">
											<input type="text" id="titulo" name="titulo" oninput="setCustomValidity('')"
												oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
												autofocus="autofocus">
											<button class="btn-clear"></button>
										</div>
										<label>Descri��o:</label>
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