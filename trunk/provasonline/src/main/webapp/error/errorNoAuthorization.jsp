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
	<section>
		<div class="container">
			<div class="grid">
				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
				<div class="row ">
					<div class="span5 offset10">
						<fieldset>
							<legend>Erro</legend>
							<c:if test="${msgType eq 'error'}">
								<p id="idMsg" class="bg-amber fg-white">
									<span class="icon-warning padding10"></span>
									Você não tem autorização para acessar essa página.
								</p>
							</c:if>							
							<a href="${pageContext.request.contextPath}/secure/home.jsp" class="marginTop20 button bd-gray">
								Voltar
							</a>
						</fieldset>
					</div>
				</div>

				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
				<div class="row"></div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>