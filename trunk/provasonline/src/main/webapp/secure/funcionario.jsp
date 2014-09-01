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
				<div class="span9 offset4">
					<label><strong>Cadastrar Colaboradores</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="span9 offset4">
					<div class="tableHeader">
						<table border="1" style="width: 100%">
							<thead>
								<tr>
									<th style="width:10%">Matricula</th>
									<th style="width:25%">Nome</th>
									<th style="width:25%">E-mail</th>
									<th style="width:20%">Perfil</th>
									<th style="width:20%">Usuário</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="tableBody" style="position:relative; top: -1px;">
						<table border="1" style="width: 100%">
							<tbody>
								<c:forEach items="${listaFuncionario}" var="func">
									<tr>
										<td style="width:10%; text-align: center;">${func.matricula}</td>
										<td style="width:25%">${func.nome}</td>
										<td style="width:25%">${func.email}</td>
										<td style="width:20%">${func.descricao}</td>
										<td style="width:20%; text-align: center;">${func.usuario}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="tableFooter" style="position:relative; top: -2px;">
						<table border="1" style="width: 100%">
							<tfoot>
								<tr>
									<td>Navegação</td>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="paddingTop5">
						<button id="createFlatWindow" class="button bd-blue">Adicionar</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>