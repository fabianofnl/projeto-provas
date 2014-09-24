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
					<label><strong>Cadastrar Colaboradores</strong></label>
					<c:choose>
						<c:when test="${msgType eq 'info'}">
							<p id="idMsg" class="bg-lightBlue fg-white">
								<span class="icon-info padding10"></span>${msg}
							</p>
							<script type="text/javascript">
								$("#idMsg").fadeOut(10000);
							</script>
						</c:when>
						<c:otherwise>
							<p id="idMsg" class="bg-lightRed fg-white">
								<span class="icon-cancel padding10"></span>${msg}
							</p>
							<script type="text/javascript">
								$("#idMsg").fadeOut(10000);
							</script>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="row">
				<div class="span12 offset3">
					<div class="tableHeader">
						<table border="1" style="width: 100%">
							<thead>
								<tr>
									<th style="width:10%">Matricula</th>
									<th style="width:25%">Nome</th>
									<th style="width:20%">E-mail</th>
									<th style="width:20%">Função</th>
									<th style="width:15%">Perfil</th>
									<th style="width:10%">Usuário</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="tableBody" style="position:relative; top: -1px;">
						<table border="1" style="width: 100%">
							<tbody>
								<c:choose>
									<c:when test="${empty listaFuncionario}">
										<tr>
											<td colspan="5">Não há registros.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${listaFuncionario}" var="func">
											<tr>
												<td style="width:10%; text-align: center;">${func.matricula}</td>
												<td style="width:25%">${func.nome}</td>
												<td style="width:20%">${func.email}</td>
												<td style="width:20%">${func.funcao}</td>
												<td style="width:15%">${func.descricao}</td>
												<td style="width:10%; text-align: center;">${func.usuario}</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<div class="tableFooter" style="position:relative; top: -2px; border-top: 1px solid;">
						<table style="width: 100%">
							<tfoot>
								<tr>
									<td>
										<jsp:include page="/pagination.jsp"></jsp:include>
									</td>
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
		<div id="divFrmCadastrar" style="display: none;">
			<form id="frmDialogCadastrar" action="funcionario" method="post">
			
			<div class="grid">
				<div class="row">
					<div class="span3">
						<label>Matrícula:</label>
						<div class="input-control text">
							<input type="text" id="matricula" name="matricula" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
								autofocus="autofocus">
							<button class="btn-clear"></button>
						</div>
						<label>Nome:</label>
						<div class="input-control text">
							<input type="text" id="nome" name="nome" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-clear"></button>
						</div>
						<label>Função:</label>
						<div class="input-control text">
							<input type="text" id="funcao" name="funcao" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-clear"></button>
						</div>
						<label>Perfil:</label>
						<div class="input-control select">
							<select id="perfil" name="perfil" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
								<option value="">Selecione</option>
								<c:forEach var="per" items="${listaPerfis}">
									<option value="${per.perfilId}">${per.descricao}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="span3">
						<label>E-mail:</label>
						<div class="input-control text">
							<input type="text" id="email" name="email" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-clear"></button>
						</div>
						<label>Usuário:</label>
						<div class="input-control text">
							<input type="text" id="user" name="user" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-clear"></button>
						</div>
						<label>Senha:</label>
						<div class="input-control password" data-role="input-control">
							<input id="pass" name="pass" type="password" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-reveal" type="button"></button>
						</div>
						<label>Confirmar Senha:</label>
						<div class="input-control password" data-role="input-control">
							<input id="repass" name="repass" type="password" oninput="setCustomValidity('')"
								oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<button class="btn-reveal" type="button"></button>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="span3">
						<div class="form-actions">
							<button class="button primary">Enviar</button>
							<button class="button" type="button" onclick="$.Dialog.close()">Cancel</button>
						</div>
					</div>
				</div>
			</div>
			<%--
				<label>Matrícula:</label>
				<div class="input-control text">
					<input type="text" id="matricula" name="matricula"><button class="btn-clear"></button>
				</div>
				<label>Nome:</label>
				<div class="input-control text">
					<input type="text" id="nome" name="nome"><button class="btn-clear"></button>
				</div>
				<label>Função:</label>
				<div class="input-control text">
					<input type="text" id="funcao" name="funcao"><button class="btn-clear"></button>
				</div>
				<label>E-mail:</label>
				<div class="input-control text">
					<input type="text" id="email" name="email"><button class="btn-clear"></button>
				</div>
				<label>Perfil:</label>
				<div class="input-control select">
					<select id="perfil" name="perfil">
						<option value="">Selecione</option>
						<c:forEach var="per" items="${listaPerfis}">
							<option value="${per.perfilId}">${per.descricao}</option>
						</c:forEach>
					</select>
				</div>
				<label>Usuário:</label>
				<div class="input-control text">
					<input type="text" id="user" name="user"><button class="btn-clear"></button>
				</div>
				<label>Senha:</label>
				<div class="input-control password" data-role="input-control">
					<input id="pass" name="pass" type="password"
						placeholder="Senha" value="" oninput="setCustomValidity('')"
						oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
					<button class="btn-reveal" type="button"></button>
				</div>
				<label>Confirmar Senha:</label>
				<div class="input-control password" data-role="input-control">
					<input id="repass" name="repass" type="password"
						placeholder="Confirmar Senha" value="" oninput="setCustomValidity('')"
						oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
					<button class="btn-reveal" type="button"></button>
				</div>
				<div class="form-actions">
					<button class="button primary">Enviar</button>
					<button class="button" type="button" onclick="$.Dialog.close()">Cancel</button>
				</div>
			 --%>
			</form>
		</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>