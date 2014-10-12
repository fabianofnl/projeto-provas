<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<legend>Definir Opção Correta</legend>
						<form id="frmDefinirOpcao" action="definirOpcao" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										
										<label>Título:</label>
										<label>${questao.tituloQuestao}</label>
										
										<label>Descrição:</label>
										<label>${questao.descricaoQuestao}</label>
										
										<label>Tema:</label>
										<label>${questao.titulo}</label>
									
									</div>
								</div>
								
								<div class="row">
									<div class="span10">
										<c:forEach items="${questao.listaOpcoes}" var="opcao">
											<div class="input-control radio">
												<label>
													<input type="radio" id="radio" name="radio" required value="${opcao.opcaoId}" ${opcao.flag ? 'checked' : ''} />
													<span class="check"></span>
													${opcao.tituloOpcao}
												</label>
											</div>
											<br>
										</c:forEach>
									</div>
								</div>
								
								<div class="row">
									<div class="span10">
										<div class="form-actions">
											<button class="button primary">Enviar</button>
										</div>
									</div>
								</div>
							</div>
							<input id="opcoesId" name="opcoesId" type="hidden" value="${opcoesId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>