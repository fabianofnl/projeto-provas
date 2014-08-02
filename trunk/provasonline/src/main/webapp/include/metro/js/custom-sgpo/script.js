$(function(){
	$("#createSysEventsWindow").on('click', function(){
		$.Dialog({
			shadow: true,
			overlay: false,
			icon: '',
			title: 'Title',
			width: 500,
			padding: 10,
			content: 'Window content here',
			draggable: true,
			sysButtons: {
				btnMin: true,
				btnMax: true,
				btnClose: true
			},
			sysBtnCloseClick: function(e){
				alert('Close button click');
			},
			sysBtnMinClick: function(e){
				alert('Min button click');
			},
			sysBtnMaxClick: function(e){
				alert('Max button click');
			}
		});
	});
	$("#createWindow").on('click', function(){
		$.Dialog({
			shadow: true,
			overlay: false,
			icon: '<span class="icon-rocket"></span>',
			title: 'Title',
			width: 500,
			padding: 10,
			content: 'Window content here'
		});
	});
	$("#createWindowDraggable").on('click', function(){
		$.Dialog({
			shadow: true,
			overlay: false,
			draggable: true,
			icon: '<span class="icon-bus"></span>',
			title: 'Draggable window',
			width: 500,
			padding: 10,
			content: 'This Window is draggable by caption.',
			onShow: function(){
				var content = '<form id="login-form-1">' +
						'<label>Login</label>' +
						'<div class="input-control text"><input type="text" funcao="login"><button class="btn-clear"></button></div>' +
						'<label>Password</label>'+
						'<div class="input-control password"><input type="password" funcao="password"><button class="btn-reveal"></button></div>' +
						'<div class="input-control checkbox"><label><input type="checkbox" funcao="c1" checked/><span class="check"></span>Check me out</label></div>'+
						'<div class="form-actions">' +
						'<button class="button primary">Login to...</button>&nbsp;'+
						'<button class="button" type="button" onclick="$.Dialog.close()">Cancel</button> '+
						'</div>'+
						'</form>';

				$.Dialog.title("User login");
				$.Dialog.content(content);
			}

		});
	});
	$("#createFlatWindow").on('click', function(){
		$.Dialog({
			overlay: true,
			shadow: true,
			flat: true,
			draggable: true,
			//icon: '<img src="images/excel2013icon.png">',
			title: 'Cadastrar Colaborador',
			content: '',
			width: 400,
			padding: 20,
			onShow: function(_dialog){
				/*
				var content = '<form class="user-input">' +
						'<label>Login</label>' +
						'<div class="input-control text"><input type="text" funcao="login"><button class="btn-clear"></button></div>' +
						'<label>Password</label>'+
						'<div class="input-control password"><input type="password" funcao="password"><button class="btn-reveal"></button></div>' +
						'<div class="input-control checkbox"><label><input type="checkbox" funcao="c1" checked/><span class="check"></span>Check me out</label></div>'+
						'<div class="form-actions">' +
						'<button class="button primary">Login to...</button>&nbsp;'+
						'<button class="button" type="button" onclick="$.Dialog.close()">Cancel</button> '+
						'</div>'+
						'</form>';
				*/
				var content = 	"<form>"+
								"<label>Matrícula</label>"+
								"<div class='input-control text'><input type='text' name='matricula'><button class='btn-clear'></button></div>"+
								"<label>Nome</label>"+
								"<div class='input-control text'><input type='text' name='nome'><button class='btn-clear'></button></div>"+
								"<label>Função</label>"+
								"<div class='input-control text'><input type='text' name='funcao'><button class='btn-clear'></button></div>"+
								"<label>Perfil</label>"+
								"<div class='input-control select'><select><option>Perfil 1</option><option>Perfil 2</option><option>Perfil 3</option></select></div>"+
								"<div class='form-actions'>"+
								"<button class='button primary'>Enviar</button>"+
								"<button class='button' type='button' onclick='$.Dialog.close()'>Cancel</button>"+
								"</div>"+
								"</form>";
				$.Dialog.title("Cadastrar Colaborador");
				$.Dialog.content(content);
			}
		});
	});
	$("#createWindowYoutube").on('click', function(){
		$.Dialog({
			overlay: false,
			shadow: true,
			flat: false,
			icon: '<img src="images/excel2013icon.png">',
			title: 'Window 8.1 Everywhere For Everything!',
			content: '',
			onShow: function(_dialog){
				var html = [
					'<iframe width="560" height="315" src="//www.youtube.com/embed/RVrXpfmKk18" frameborder="0" allowfullscreen></iframe>'
				].join("");

				$.Dialog.content(html);
			}
		});
	});
	$("#conectar").on('click', function(event){
		event.preventDefault();
		window.location = "pages/home.html";
	});
});

var table, table_data;
 
table_data = [
    {matricula:"1111",nome:"João da Silva",funcao:"DBA",perfil:"perfil",action:"<a href='#' data-hint='Alterar|Alterar colaborador.'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"2222",nome:"Pedro Joaquim",funcao:"Analista",perfil:"perfil2",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"3333",nome:"Ana Maria",funcao:"Programador",perfil:"perfil3",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"4444",nome:"Maria Joaquina",funcao:"Programador",perfil:"perfil",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"5555",nome:"Carlos Nascimento",funcao:"Gerente",perfil:"perfil2",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"6666",nome:"Luiz Oliveira",funcao:"Gerente",perfil:"perfil3",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"7777",nome:"Carmen Carminha",funcao:"Administrador",perfil:"perfil",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"8888",nome:"João Pedro",funcao:"Contador",perfil:"perfil2",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"},
    {matricula:"9999",nome:"Pedro João",funcao:"Contador",perfil:"perfil3",action:"<a href='#'><i class='icon-wrench'></i></a> | <a href='#'><i class='icon-remove'></i></a>"}
];
 
$(function(){
    table = $("#table1").tablecontrol({
        cls: 'table hovered border',
        colModel: [
            {field: 'matricula', caption: 'Matrícula', width: 50, sortable: false, cls: 'text-center', hcls: ""},
            {field: 'nome', caption: 'Nome', width: 120, sortable: false, cls: 'text-center', hcls: ""},
            {field: 'funcao', caption: 'Função', width: 120, sortable: false, cls: 'text-left', hcls: "text-left"},
            {field: 'perfil', caption: 'Perfil', width: 100, sortable: false, cls: 'text-left', hcls: "text-left"},
			{field: 'action', caption: 'Ações', width: 120, sortable: false, cls: 'text-center', hcls: 'text-center'}
        ],
 
        data: table_data
    });
});

$(function() {
 
    // grab the initial top offset of the navigation 
    var navigation_id = $('#navigation_id').offset().top;
     
    // our function that decides weather the navigation bar should have "fixed" css position or not.
    var sticky_navigation = function(){
        var scroll_top = $(window).scrollTop(); // our current vertical position from the top
         
        // if we've scrolled more than the navigation, change its position to fixed to stick to top,
        // otherwise change it back to relative
        if (scroll_top > navigation_id) { 
            $('#navigation_id').css({ 'position': 'fixed', 'top':0, 'left':0, 'width': '100%' });
        } else {
            $('#navigation_id').css({ 'position': 'relative' }); 
        }   
    };
     
    // run our function on load
    sticky_navigation();
     
    // and run it again every time you scroll
    $(window).scroll(function() {
         sticky_navigation();
    });
 
});
