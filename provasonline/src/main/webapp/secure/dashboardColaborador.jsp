<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="grid">
	<div class="row">
		<div class="span7 offset5">
			<fieldset>
				<legend>Agenda</legend>
				<c:if test="${msgType eq 'warn'}">
					<p id="idMsg" class="bg-amber fg-white">
						<span class="icon-warning padding10"></span>${msg}
					</p>
					<script type="text/javascript">
						setTimeout(function(){
							$("#idMsg").fadeOut(1000);
						}, 10000);
					</script>
				</c:if>
				<c:if test="${msgType eq 'info'}">
					<p id="idMsg" class="bg-lightBlue fg-white">
						<span class="icon-info padding10"></span>${msg}
					</p>
					<script type="text/javascript">
						setTimeout(function(){
							$("#idMsg").fadeOut(1000);
						}, 10000);
					</script>
				</c:if>

				<table class="table hovered">
					<thead>
						<tr>
							<th class="text-left" style="width:50%">Prova</th>
							<th class="text-left" style="width:30%">Data agendada</th>
							<th class="text-left" style="width:20%">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty listaAgendas}">
								<tr>
									<td colspan="3">Não há registros.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${listaAgendas}" var="agenda">
									<tr>
										<td style="width:50%">${agenda.prova.titulo}</td>
										<td style="width:30%">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${agenda.provaAgendada}"/>
										</td>
										<td style="width:20%">
											<c:if test="${agenda.hoje eq 1}">
												<a href="${pageContext.request.contextPath}/secure/realizarProva?provaId=${agenda.prova.provaId}&agendaId=${agenda.agendaId}">
													<span class="icon-book" data-hint="Realizar Prova" data-hint-position="top"></span>
												</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</fieldset>
			<br>
			<br>
			<fieldset>
				<legend>Apostilas</legend>
				
				<table class="table hovered">
					<thead>
						<tr>
							<th class="text-left" style="width:80%">Apostila</th>
							<th class="text-left" style="width:20%">Download</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty listaApostilas}">
								<tr>
									<td colspan="3">Não há registros.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${listaApostilas}" var="apostila">
									<tr>
										<td style="width:80%">
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
										</td>
										<td style="width:20%">
											<form id="frmDownload" action="downloadApostila" method="post">
												<c:set var="fileNameFull" value="${apostila.hashName}_${apostila.nome}" />
												<input id="fileName" name="fileName" type="hidden" 
													value="${fileNameFull}">
												<button class="inputLink"
														data-hint="Baixar Apostila" data-hint-position="top">
													<i class="icon-download-2 fg-lightBlue"></i>
												</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</fieldset>
			<br>
			<br>
			<fieldset>
				<legend>Resultados</legend>

				<%--Existe um problema com a altura do grafico, quanto mais provas, maior deveria ser o grafico - height --%>
				<div id="barChartResult" style="width: 530px; height: 300px;"></div>

				<script type="text/javascript">
					google.load("visualization", "1", {packages: ["corechart"]});
					google.setOnLoadCallback(drawChart);
					
					function drawChart() {
						var data = google.visualization.arrayToDataTable([
							['Prova', 'Nota', { role : 'annotation' }],
							<c:forEach items="${listaProvasRealizadas}" var="provaRealizada">
								['${provaRealizada.tituloProva} (<fmt:formatDate pattern="dd/MM" value="${provaRealizada.dataHoraInicio}"/>)',
								 <fmt:formatNumber value="${provaRealizada.quantidadeAcertos / provaRealizada.quantidadeQuestoes * 100}" maxFractionDigits="0"/>,
								 '<fmt:formatNumber value="${provaRealizada.quantidadeAcertos / provaRealizada.quantidadeQuestoes * 100}" maxFractionDigits="0"/>'],
							</c:forEach>
							['Média Equipe',<fmt:formatNumber value="${mediaEquipe}" maxFractionDigits="0"/>,'<fmt:formatNumber value="${mediaEquipe}" maxFractionDigits="0"/>'],
							['Pontuação Max.', 100,' 100']
						]);
						
						var chart = new google.visualization.BarChart(document.getElementById("barChartResult"));
						chart.draw(data);
					}
				</script>
			</fieldset>
		</div>
	</div>
</div>
