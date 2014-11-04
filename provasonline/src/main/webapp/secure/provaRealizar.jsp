<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<legend>Realização da Prova</legend>
						<label>
							${msgTempoProva}h
							<br>
							Iniciado em: <fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${provaRealizada.dataHoraInicio}" /> 
							- Término em: <fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${provaRealizada.dataHoraFim}" />
						</label>
						<br>
						<br>
						
						<label><strong>Prova:</strong> ${provaRealizada.tituloProva}</label>
						<br>
						<form id="frmEntregarProva" action="entregarProva" method="post">

							<c:forEach items="${listaQuestoes}" var="questao" varStatus="loop">
								<label><strong>Questão ${loop.count}</strong></label>
								<br>
								<label>${questao.descricaoQuestao}</label>
								<br>
							
								<c:forEach items="${questao.listaOpcoes}" var="opcao">
									<div class="input-control radio">
										<label>
											<input type="radio" id="radio-${opcao.opcaoId}" 
												name="radio-${questao.questaoId}" 
												value="${opcao.opcaoId}"/>
											<span class="check"></span>
											${opcao.tituloOpcao}
										</label>
									</div>
									<br>
								</c:forEach>
								<br>
								<hr>
							</c:forEach>
							<div class="row">
								<div class="span10">
									<div class="form-actions">
										<button class="button primary">Entregar prova</button>
									</div>
								</div>
							</div>
						
							<input type="hidden" id="provaRealizadaId" name="provaRealizadaId" 
								value="${provaRealizada.provaRealizadaId}">
							<input type="hidden" id="agendaId" name="agendaId" value="${provaRealizada.agenda.agendaId}">
							<input type="hidden" id="provaId" name="provaId" value="${provaRealizada.provaId}">
						</form>
						
						
						
						
						
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>