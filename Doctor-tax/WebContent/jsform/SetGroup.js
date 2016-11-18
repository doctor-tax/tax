$( function() {
    $( "#dialogDelete" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickDelete());
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnDelete" ).on( "click", function() {
      $( "#dialogDelete" ).dialog( "open" );
    });
  } );

$( function() {
    $( "#dialogReset" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickReset());
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnReset" ).on( "click", function() {
      $( "#dialogReset" ).dialog( "open" );
    });
  } );

$( function() {
    $( "#dialogSave" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickSave());
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
      
    });
 
    $( "#btnSave" ).on( "click", function() {
      $( "#dialogSave" ).dialog( "open" );
    });
  } );//modal

$(document).ready(function(){
	genTable();
});

function genTable(){
	$.ajax({
		type: 'POST',
		url: './SetGroupSrvl',
		data: 
		 {"method":"genTable"} ,
		success: function(data){
			$('#inputId').val(data.max);
			$('#inputList').val(data.maxlist);
			//$('#tb').append(data.html);
			$('#inputMaxList').val(data.oldMax);
			loadDataTable();
		}
	})
}

function loadDataTable(){
	$('#tbGroup').dataTable().fnDestroy();
	$('#tbGroup').dataTable({
		"ajax" : {
			type : "POST",
			url : "./SetGroupSrvl",
			dataSrc : "data",
			data : {
				method : "getDbTable"
			},
		}

	});

	$('#tbGroup').on('dblclick', 'tr', function() {
		var $this = $(this);
		var row = $this.closest("tr");
		var id = row.find('td:eq(0)').text();
		var name = row.find('td:eq(1)').text();
		var list = row.find('td:eq(2)').text();
		//getID(cell1text);
		
		$('#inputId').val(id);
		$('#inputName').val(name);
		$('#inputList').val(list);
		$('#inputOldList').val(list);
		
		$('#inputMode').val("Update");
		$('#btnDelete').prop("disabled", false);
		$('#inputId').prop("disabled", true);
		$('#inputList').prop("disabled", false);
		if(id == "0"){
			$('#btnDelete').prop("disabled", true);
		}
	});
}

/*function clickRow(id,name,list){
	$('#inputId').val(id);
	$('#inputName').val(name);
	$('#inputList').val(list);
	$('#inputOldList').val(list);
	$('#inputMode').val("Update");
	$('#btnDelete').prop("disabled", false);
	$('#inputId').prop("disabled", true);
	$('#inputList').prop("disabled", false);
	if(id == "0"){
		$('#btnDelete').prop("disabled", true);
	}
}*/

function clickSave(){
	var mode = $("#inputMode").val();
	var id = $("#inputId").val();
	var name = $("#inputName").val();
	var list = $("#inputList").val();
	var oldList = $("#inputOldList").val();
	
	
	
	$.ajax({
		type: 'POST',
		url: './SetGroupSrvl',
		data: 
		 {"method":"save",
			"id":id,
			"name":name,
			"list":list,
			"mode":mode,
			"oldList":oldList} ,
		 success: function(data){
			alert(data);
			clickReset();
		}
	})
}

function clickDelete(){
	var id = $("#inputId").val();
	var list = $("#inputOldList").val();
	$.ajax({
		type: 'POST',
		url: './SetGroupSrvl',
		data: 
		 {"method":"delete",
			"id":id,
			"list":list} ,
		 success: function(data){
			alert(data);
			clickReset();
		}
	})
	
	
}

function clickReset(){
	location.reload();
}

function checkMode(){
	var ID = $("#inputId").val();
	$.ajax({
		type: 'POST',
		url: './SetGroupSrvl',
		data: 
		 {"method":"checkMode",
			"id": ID } ,
		success: function(data){
			//alert(data.NAME);
			if(data.name_group == undefined){
				//alert("New");
				$("#inputMode").val("New");
			}else{
				//alert("Update");
				$("#inputMode").val("Update");
				$("#inputId").attr("disabled", "disabled");
				$("#btnDelete").removeAttr("disabled");
				$('#inputName').val(data.name_group);
				$('#inputList').val(data.list_group);
			}
		}
	})
}

function clickBack() {
	location.href = "TaxbreakAdmin.jsp";
}

function changeList(){
	
	var list = $("#inputList").val() * 1;
	var listMax = $("#inputMaxList").val() * 1;
	
	//alert(list);
	//alert(listMax);
	if(list > listMax){
		alert("ค่าสูงสุดที่ใส่ได้คือ "+ listMax);
		$("#inputList").val(listMax);
	}
}

