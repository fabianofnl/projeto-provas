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
				<div class="row ">
					<div class="span5 offset10">
						<form id="frmLogin" name="frmLogin" action="logon"
							method="post" autocomplete="off">
							<fieldset>
								<legend>Login</legend>
								<c:if test="${msg ne null}">
									<p class="bg-lightRed fg-white">
										<span class="icon-cancel padding10"></span>${msg}
									</p>
								</c:if>
								<label>Usu�rio</label>
								<div class="input-control text" data-role="input-control">
									<input id="user" name="user" type="text" placeholder="Usu�rio"
										autofocus="autofocus" value="${user}" oninput="setCustomValidity('')"
										oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
									<button class="btn-clear" tabindex="-1" type="button"></button>
								</div>

								<label>Senha</label>
								<div class="input-control password" data-role="input-control">
									<input id="pass" name="pass" type="password"
										placeholder="Senha" value="${pass}" oninput="setCustomValidity('')"
										oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
									<button class="btn-reveal" tabindex="-1" type="button"></button>
								</div>

								<div class="divEsqueciSenha">
									<a href="${pageContext.request.contextPath}/esqueciSenha.jsp"
										class="linkEsqueciSenha">Esqueci minha senha</a>
								</div>

								<div>
									<button type="submit" id="conectar" name="conectar"
										class="button bd-green">Conectar</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>