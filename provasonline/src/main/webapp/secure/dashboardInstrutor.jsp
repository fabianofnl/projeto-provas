<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="grid">
	<div class="row">
		<div class="span7 offset5">
			<fieldset>
				<legend>Relatório Geral</legend>
				<table class="table hovered">
					<thead>
						<tr>
							<th class="text-left" style="width: 50%">Descrição</th>
							<th class="text-left" style="width: 50%">Valores</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Temas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdTemas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Provas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdProvas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Questões
							</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdQuestoes}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Opções</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdOpcoes}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Apostilas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdApostilas}</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<br>
			<br>
			<fieldset>
				<legend>Nota Médias por Equipes/Gerentes</legend>
				<ul style="list-style: none;">
					<c:forEach items="${listaMediaEquipes}" var="equipe">
						<c:if test="${equipe.qtdQuestoes ne null}">
							<li>
								<div class="grid">
									<div class="row">
									
										<div class="span3">
											${equipe.nomeGerente}
										</div>
										<div class="span3">
											<table style="width:100%;">
												<tr height="13px;">
													<td width='<fmt:formatNumber maxFractionDigits="0" 
														value="${(equipe.qtdAcertos / equipe.qtdQuestoes) * 100}"/>%'>
														<div style="border-top: 13px solid #3c78b5; ${(equipe.qtdAcertos / equipe.qtdQuestoes) * 100 lt 1 ? 'width: 1px;' : ''}"></div>
													</td>
													<td width='<fmt:formatNumber maxFractionDigits="0" 
															value="${100 - ((equipe.qtdAcertos / equipe.qtdQuestoes) * 100)}"/>%'>
														<span style="font-size: 12px; padding: 3px;" >
															<fmt:formatNumber maxFractionDigits="0" 
																	value="${(equipe.qtdAcertos / equipe.qtdQuestoes) * 100}"/>
														</span>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</fieldset>
		</div>
	</div>
</div>
