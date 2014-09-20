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

