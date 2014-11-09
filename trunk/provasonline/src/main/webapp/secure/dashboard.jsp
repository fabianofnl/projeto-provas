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
		<c:if test="${funcionario.role eq 'ROLE_COLABORADOR'}">
			<jsp:include page="dashboardColaborador.jsp"></jsp:include>
		</c:if>

		<c:if test="${funcionario.role eq 'ROLE_GERENTE'}">
			<jsp:include page="dashboardGerente.jsp"></jsp:include>
		</c:if>

		<c:if test="${funcionario.role eq 'ROLE_INSTRUTOR'}">
			<jsp:include page="dashboardInstrutor.jsp"></jsp:include>
		</c:if>

		<c:if test="${funcionario.role eq 'ROLE_ADMIN'}">
			<jsp:include page="dashboardAdministrador.jsp"></jsp:include>
		</c:if>
	</section>

	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>