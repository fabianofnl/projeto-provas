<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="grid">
	<div class="row">
		<div class="span8 offset5">
			<fieldset>
				<legend>Dados do Sistema</legend>
				<table class="table hovered">
					<thead>
						<tr>
							<th class="text-left" style="width: 50%">Descri��o</th>
							<th class="text-left" style="width: 50%">Valores</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Funcion�rios Ativos</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdFuncionariosAtivos}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Funcion�rios Inativos</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdFuncionariosInativos}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Equipes</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdEquipes}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Provas Agendadas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdProvasAgendadas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Provas Realizadas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdProvasRealizadas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Provas n�o Realizadas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdProvasNaoRealizadas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Temas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdTemas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Provas</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdProvas}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Quest�es
							</td>
							<td class="text-left" style="width: 50%">${relatorio.qtdQuestoes}</td>
						</tr>
						<tr>
							<td class="text-left" style="width: 50%">Quantidade Op��es</td>
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
				<legend>Mem�ria do Sistema</legend>
				<ul style="list-style: none;">
					<li>
						<div class="grid">
							<div class="row">
								<div class="span2"> 
									Uso da mem�ria
								</div>
								<div class="span2">
									<table style="width:100%; border-spacing: 0; padding: 0;">
										<tr height="13px;">
											<td width='<fmt:formatNumber maxFractionDigits="0" 
												value="${(memoriaAlocada / memoriaMax) * 100}"/>%' bgcolor="#CC33333">
											</td>
											<td width='<fmt:formatNumber maxFractionDigits="0" 
													value="${100 - ((memoriaAlocada / memoriaMax) * 100)}"/>%' bgcolor="#00CC00">
											</td>
										</tr>
									</table>
								</div>
								<div class="span3">
									<span style="font-size: 13px;" >
										<fmt:formatNumber maxFractionDigits="0" 
												value="${(memoriaAlocada / memoriaMax) * 100}"/>%
										(Uso: <fmt:formatNumber maxFractionDigits="0" 
												value="${memoriaAlocada / (1024 * 1024)}"/>MB | 
										Total: <fmt:formatNumber maxFractionDigits="0" 
												value="${memoriaMax / (1024 * 1024)}"/>MB)
									</span>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</fieldset>
		</div>
	</div>
</div>
