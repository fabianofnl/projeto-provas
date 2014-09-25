<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="divFrmInativar" style="display: none;">
	<form id="frmDialogInativar" action="funcionarioInativar" method="post">
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