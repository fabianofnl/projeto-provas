/**
 * Scripts específicos do sistema (Página Funcionario)
 */
var table, table_data;
 
table_data = [
    {id:"1",invdate:"2007-04-02",name:"test",note:"note",amount:"100.00",tax:"10.00",total:"120.00"},
    {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
    {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
    {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
    {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
    {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
    {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
    {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
    {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
];
 
$(function(){
    table = $("#table1").tablecontrol({
        cls: 'table hovered border striped',
        colModel: [
            {field: 'id', caption: 'No', width: 50, sortable: false, cls: 'text-center', hcls: ""},
            {field: 'invdate', caption: 'Date', width: 120, sortable: false, cls: 'text-center', hcls: ""},
            {field: 'name', caption: 'Client', width: '', sortable: false, cls: 'text-left', hcls: "text-left"},
            {field: 'amount', caption: 'Amount', width: '80', sortable: false, cls: 'text-right', hcls: "text-right"},
            {field: 'tax', caption: 'Tax', width: '80', sortable: false, cls: 'text-right', hcls: "text-right"},
            {field: 'total', caption: 'Total', width: '80', sortable: false, cls: 'text-right', hcls: "text-right"},
            {field: 'note', caption: 'Notes', width: '', sortable: false, cls: 'span1', hcls: ""}
        ],
 
        data: table_data
    });
});


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

