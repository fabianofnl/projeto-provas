<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="divFrmCadastrar" style="display: none;">
	<form id="frmDialogCadastrar" action="funcionario" method="post">
		<div class="grid">
			<div class="row">
				<div class="span3">
					<label>Matrícula:</label>
					<div class="input-control text">
						<input type="text" id="matricula" name="matricula" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required
							autofocus="autofocus">
						<button class="btn-clear"></button>
					</div>
					<label>Nome:</label>
					<div class="input-control text">
						<input type="text" id="nome" name="nome" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-clear"></button>
					</div>
					<label>Função:</label>
					<div class="input-control text">
						<input type="text" id="funcao" name="funcao" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-clear"></button>
					</div>
					<label>Perfil:</label>
					<div class="input-control select">
						<select id="perfil" name="perfil" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
							<option value="">Selecione</option>
							<c:forEach var="per" items="${listaPerfis}">
								<option value="${per.perfilId}">${per.descricao}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="span3">
					<label>E-mail:</label>
					<div class="input-control text">
						<input type="text" id="email" name="email" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-clear"></button>
					</div>
					<label>Usuário:</label>
					<div class="input-control text">
						<input type="text" id="user" name="user" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-clear"></button>
					</div>
					<label>Senha:</label>
					<div class="input-control password" data-role="input-control">
						<input id="pass" name="pass" type="password" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-reveal" type="button"></button>
					</div>
					<label>Confirmar Senha:</label>
					<div class="input-control password" data-role="input-control">
						<input id="repass" name="repass" type="password" oninput="setCustomValidity('')"
							oninvalid="setCustomValidity('Por favor, preencha este campo.')" required>
						<button class="btn-reveal" type="button"></button>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="span3">
					<div class="form-actions">
						<button class="button primary">Enviar</button>
						<button class="button" type="button" onclick="$.Dialog.close()">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>