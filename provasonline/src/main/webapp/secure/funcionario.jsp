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
				<div class="span12 offset3">
					<fieldset>
						<legend>Lista de Funcion�rios</legend>
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
										<th style="width:9%">Matricula</th>
										<th style="width:18%">Nome</th>
										<th style="width:20%">E-mail</th>
										<th style="width:16%">Fun��o</th>
										<th style="width:11%">Perfil</th>
										<th style="width:10%">Usu�rio</th>
										<th style="width:7%">Status</th>
										<th style="width:9%">A��es</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="divBody">
							<table class="tableClass tableBody">
								<tbody>
									<c:choose>
										<c:when test="${empty listaFuncionario}">
											<tr>
												<td colspan="5">N�o h� registros.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${listaFuncionario}" var="func">
												<tr>
													<td style="width:9%; text-align: center;">${func.matricula}</td>
													<td style="width:18%">${func.nome}</td>
													<td style="width:20%">${func.email}</td>
													<td style="width:16%">${func.funcao}</td>
													<td style="width:11%">${func.descricao}</td>
													<td style="width:10%; text-align: center;">${func.usuario}</td>
													<td style="width:7%; text-align: center;">${func.status}</td>
													<td style="width:9%; text-align: center;">
														<a href="${pageContext.request.contextPath}/secure/alterarFuncionario?matricula=${func.matricula}">
															<span class="icon-wrench" title="Alterar"></span>
														</a>
														<span class="custom-separator">|</span>
														<a href="${pageContext.request.contextPath}/secure/inativarFuncionario?matricula=${func.matricula}">
															<span class="icon-remove" title="Inativar"></span>
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
											<jsp:include page="/pagination.jsp"></jsp:include>
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