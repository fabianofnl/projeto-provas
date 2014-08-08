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

