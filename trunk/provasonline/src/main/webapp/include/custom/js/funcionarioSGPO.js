/**
 * Scripts específicos do sistema (Página Funcionario)
 */
$(function(){
	$("#createFlatWindow").on('click', function(){
		$.Dialog({
			overlay: true,
			shadow: true,
			flat: true,
			draggable: false,
			title: 'Cadastrar Colaborador',
			content: '',
			width: 510,
			padding: 20,
			onShow: function(_dialog){
				var content = $("#divFrmCadastrar").html();
				$.Dialog.content(content);
			}
		});
	});
});

function dialogAlterar(element){

	var sub = $(element).children();

	$.Dialog({
		overlay: true,
		shadow: true,
		flat: true,
		draggable: false,
		title: 'Alterar Colaborador',
		content: '',
		width: 510,
		padding: 20,
		onShow: function(_dialog){
			//var content = $("#divFrmAlterar").html().find("input[id='matricula']").val(sub[0].value);
			var content = $("#divFrmAlterar").html();
			$(content).find("#matricula").val(sub[0].value);
			$(content).find("#nome").val(sub[1].value);
			$(content).find("#funcao").val(sub[2].value);
			$(content).find("#email").val(sub[4].value);
			$.Dialog.content(content);
		}
	});
}

function dialogInativar(element){

	var sub = $(element).children();
	
	for(var i = 0; i < sub.length; i++){
		alert(sub[i].value);
	}
}

