<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<div class="row ">
					<div class="span5 offset10">
						<form action="logon.jsp" method="post">
							<fieldset>
								<legend>Login</legend>
								<c:if test="${msg ne null}">
									<p class="bg-lightRed fg-white">
										<span class="icon-cancel padding10"></span>${msg}
									</p>
								</c:if>
								<label>Usuário</label>
								<div class="input-control text" data-role="input-control">
									<input id="user" name="user" type="text" placeholder="Usuário"
										autofocus="autofocus" value="${user}">
									<button class="btn-clear" tabindex="-1" type="button"></button>
								</div>
								<label>Senha</label>
								<div class="input-control password" data-role="input-control">
									<input id="pass" name="pass" type="password"
										placeholder="Senha" value="${pass}">
									<button class="btn-reveal" tabindex="-1" type="button"></button>
								</div>
								<button type="submit" id="conectar" name="conectar" class="button bd-green">Conectar</button>
								<button class="button bd-red">Limpar</button>
							</fieldset>
						</form>
					</div>
				</div>

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