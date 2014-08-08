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
	<jsp:include page="/secure/menu.jsp"></jsp:include>
	<section style="padding: 1px;">
		<div class="grid">
			<div class="row">
				<div class="span5 offset2">
					<label><strong>Cadastrar Colaboradores</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="span10 offset2">
					<table id="table1" class="hovered"></table>
					<button id="createFlatWindow" class="button bd-blue">Adicionar</button>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>