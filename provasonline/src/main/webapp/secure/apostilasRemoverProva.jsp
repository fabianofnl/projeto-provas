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
						<legend>Remover Prova</legend>
						<p id="idMsg" class="bg-amber fg-white">
							<span class="icon-warning padding10"></span>
							Ao confirmar as informações você removerá a prova da apostila.
						</p>
						<form id="frmRemover" action="removerProvaApostila" method="post">
							<div class="grid">
								<div class="row">
									<div class="span10">
										<label>Apostila:</label>
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

										<label>Título da Prova:</label>
										<label>${prova.titulo}</label>

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
							<input type="hidden" id="apostilaId" name="apostilaId" value="${apostila.apostilaId}">
							<input type="hidden" id="provaId" name="provaId" value="${prova.provaId}">
						</form>
					</fieldset>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>