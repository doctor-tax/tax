//modal
$( function() {
    $( "#dialogDelete" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "Confirm": function() {
          $( this ).dialog(clickDelete());
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
  } );
// modal

$(document).ready(function(){
	genRoleGroup();
});

function changeDropDown(){
	var value = $("#dropdownType").val();
	if(value == "f"){
		$("#inputAmount").prop("disabled", false);
		$("#inputMax").prop("disabled", false);
		$("#inputRate").prop("disabled", true);
		$("#inputPercent").prop("disabled", true);
	}else if(value == "r"){
		$("#inputAmount").prop("disabled", true);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", false);
		$("#inputPercent").prop("disabled", true);
	}else if(value == "p"){
		$("#inputAmount").prop("disabled", true);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", true);
		$("#inputPercent").prop("disabled", false);
	}else if(value == "fr"){
		$("#inputAmount").prop("disabled", true);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", true);
		$("#inputPercent").prop("disabled", true);
	}else if(value == "pr"){
		$("#inputAmount").prop("disabled", true);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", false);
		$("#inputPercent").prop("disabled", false);
	}
}

function clickReset(){
	location.reload();
	
}

function clickDelete(){
	var id = $("#inputId").val();
	$.ajax({
		type: 'GET',
		url: './SetTaxSrvl',
		data: 
		 {"method":"delete",
			"id":id } ,
		 success: function(data){
			alert(data);
			clickReset();
		}
	})
}

function clickSave(){
	var n = "\n";
	var id = $("#inputId").val();
	var name = $("#inputName").val();
	var amount = $("#inputAmount").val();
	var type = $("#dropdownType").val();
	var max = $("#inputMax").val();
	var rate = $("#inputRate").val();
	var percent = $("#inputPercent").val();
	var group = $("#dropdownGroup").val();
	var list = $("#inputList").val();
	var mode = $("#inputMode").val();
	
	if(type == "f"){
		if(id==""||name==""||amount==""||max==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบด้วยครับ");
		}else{
			$.ajax({
				type: 'GET',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "amount":amount,
					 "max":max,
					 "list":list,
					 "group":group},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "r"){
		if(id==""||name==""||rate==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบด้วยครับ");
		}else{
			$.ajax({
				type: 'GET',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group,
					 "rate":rate },
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "p"){
		if(id==""||name==""||percent==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบด้วยครับ");
		}else{
			$.ajax({
				type: 'GET',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group,
					 "percent":percent,
					 "rate":rate },
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "fr"){
		if(id==""||name==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบด้วยครับ");
		}else{
			$.ajax({
				type: 'GET',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "pr"){
		if(id==""||name==""||percent==""||rate==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบด้วยครับ");
		}else{
			$.ajax({
				type: 'GET',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group,
					 "percent":percent,
					 "rate":rate },
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else{
		$.ajax({
			type: 'GET',
			url: './SetTaxSrvl',
			data: 
			 {"method":"save",
			     "mode" : mode,
				 "type":type,
				 "id":id,
				 "name":name,
				 "amount":amount,
				 "max":max,
				 "list":list,
				 "group":group,
				 "percent":percent,
				 "rate":rate },
			success: function(data){
				//alert(data);
				alert(data);
			}
		})
	}
}

function genRoleGroup(){
	$.ajax({
		type: 'GET',
		url: './SetTaxSrvl',
		data: 
		 {"method":"genRoleGroup"} ,
		success: function(data){
			// alert(data);
			$('#dropdownGroup').append(data);
			
		}
	})
}

function genUpdate(){
	var mode = $("#mode").val();
	var id = $("#id").val();
	alert("id "+id);
	alert("mode "+mode);
}

function clickBack(){
	location.href = "TaxbreakAdmin.jsp";
}