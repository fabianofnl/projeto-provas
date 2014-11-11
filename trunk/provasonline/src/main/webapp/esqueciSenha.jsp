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
					<div class="span5 offset2">
						<form id="frmEsqueciSenha" name="frmEsqueciSenha" action="esqueciSenha"
							method="post" autocomplete="off">
							<fieldset>
								<legend>Esqueci minha senha</legend>
								<c:if test="${msg ne null}">
									<p class="bg-lightRed fg-white">
										<span class="icon-cancel padding10"></span>${msg}
									</p>
								</c:if>
								<c:if test="${msg1 ne null}">
									<p class="bg-lightBlue fg-white">
										<span class="icon-info padding10"></span>${msg1}
									</p>
								</c:if>

								<label>Usuário</label>
								<div class="input-control text" data-role="input-control">
									<input id="user" name="user" type="text" placeholder="Usuário"
										autofocus="autofocus" value="${user}"oninput="setCustomValidity('')"
										oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
									<button class="btn-clear" tabindex="-1" type="button"></button>
								</div>
								
								<label>E-mail</label>
								<div class="input-control text" data-role="input-control">
									<input id="email" name="email" type="email" placeholder="E-mail"
										value="${email}"oninput="setCustomValidity('')"
										oninvalid="setCustomValidity(value == '' ? 'Por favor, preencha este campo.': (validity.typeMismatch ? 'Por favor, preencha com email válido.' : ''))" required>
									<button class="btn-clear" tabindex="-1" type="button"></button>
								</div>

								<div>
									<button type="submit" id="btnEsqueciSenha"
										name="btnEsqueciSenha" class="button bd-green">Enviar</button>
									<a href="${pageContext.request.contextPath}/logon"
										style="float:right;">Logon</a>
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