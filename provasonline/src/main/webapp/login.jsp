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
				<div class="row ">
					<div class="span5 offset10">
						<form>
							<fieldset>
								<legend>Login</legend>
								<label>Usuário</label>
								<div class="input-control text" data-role="input-control">
									<input id="user" name="user" type="text" placeholder="Usuário"
										autofocus="autofocus">
									<button class="btn-clear" tabindex="-1" type="button"></button>
								</div>
								<label>Senha</label>
								<div class="input-control password" data-role="input-control">
									<input id="pass" name="pass" type="password"
										placeholder="Senha">
									<button class="btn-reveal" tabindex="-1" type="button"></button>
								</div>
								<button id="conectar" name="conectar" class="button bd-green">Conectar</button>
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