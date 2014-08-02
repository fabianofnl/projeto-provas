<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
							<legend>Erro 404</legend>
							<label>Página não encontrada</label>
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