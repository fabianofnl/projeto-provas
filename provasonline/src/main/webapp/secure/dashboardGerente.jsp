<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="grid">
	<div class="row">
		<div class="span7 offset5">
			<fieldset>
				<legend>Nota Média da Equipe</legend>
					<ul style="list-style: none;">
						<c:if test="${empty listaNotaMedia}">
							<li>Não há registros.</li>
						</c:if>
						<c:if test="${not empty listaNotaMedia}">
							<c:forEach items="${listaNotaMedia}" var="notaMedia">
								<li>
									<div class="grid">
										<div class="row">
											<div class="span3">  
												${notaMedia.nome}
											</div>
											<div class="span3">
												<table style="width:100%;">
													<tr height="13px;">
														<td width='<fmt:formatNumber maxFractionDigits="0" 
															value="${(notaMedia.acertos / notaMedia.questoes) * 100}"/>%'>
															<div style="border-top: 13px solid #3c78b5; ${(notaMedia.acertos / notaMedia.questoes) * 100 lt 1 ? 'width: 1px;' : ''}"></div>
														</td>
														<td width='<fmt:formatNumber maxFractionDigits="0" 
																value="${100 - ((notaMedia.acertos / notaMedia.questoes) * 100)}"/>%'>
															<span style="font-size: 12px; padding: 3px;" >
																<fmt:formatNumber maxFractionDigits="0" 
																		value="${(notaMedia.acertos / notaMedia.questoes) * 100}"/>
															</span>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
			</fieldset>
		</div>
	</div>
</div>
