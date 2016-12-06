$(document).ready(function(){
	genRoleGroup();
	
	modal();
	
	$('#inputList,#inputAmount,#inputMax,#inputRate,#inputPercent').keyup(function () {
	    if (this.value != this.value.replace(/[^0-9]/g, '')) {
	       this.value = this.value.replace(/[^0-9]/g, '');
	    }
	    if (this.value == '0'){
	    	this.value = this.value.replace(/[^1-9]/g, '');
	    }
	});
});

function modal(){
	
	    $( "#dialogDelete" ).dialog({
	      autoOpen: false,
	      modal: true,
	      buttons: {
	        Confirm: function() {
	          $( this ).dialog(clickDelete());
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	      
	    });
	 
	    $( "#btnDelete" ).on( "click", function() {
	      $( "#dialogDelete" ).dialog( "open" );
	    }); //delete
	 

	
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
	    }); //reset

	
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
	    }); //save
}

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
	}else if(value == "a"){
		$("#inputAmount").prop("disabled", false);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", true);
		$("#inputPercent").prop("disabled", true);
	}else if(value == "s"){
		$("#inputAmount").prop("disabled", true);
		$("#inputMax").prop("disabled", true);
		$("#inputRate").prop("disabled", false);
		$("#inputPercent").prop("disabled", false);
	}
}

function clickReset(){
	$('#inputName').val("");
	location.reload();
	
}

function clickDelete(){
	var id = $("#inputId").val();
	var oldList = $("#inputOldList").val();
	var group = $("#dropdownGroup").val();
	$.ajax({
		type: 'POST',
		url: './SetTaxSrvl',
		data: 
		 {"method":"delete",
			"id":id,
			"list":oldList,
			"group":group} ,
		 success: function(data){
			alert(data);
			clickBack();
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
	var oldList = $("#inputOldList").val();
	var maxList = $("#inputMaxList").val();
	
	if(type == "f"){
		if(id==""||name==""||amount==""||max==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
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
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					$('#btnSave').prop("disabled",true);
					alert(data);
				}
			})
		}
	}else if(type == "r"){
		if(id==""||name==""||rate==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group,
					 "rate":rate,
					 "maxList":maxList,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "p"){
		if(id==""||name==""||percent==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
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
					 "rate":rate,
					 "maxList":maxList,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "fr"){
		if(id==""||name==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "list":list,
					 "group":group,
					 "maxList":maxList,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "pr"){
		if(id==""||name==""||percent==""||rate==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
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
					 "rate":rate,
					 "maxList":maxList,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "a"){
		if(id==""||name==""||amount==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
				url: './SetTaxSrvl',
				data: 
				 {"method":"save",
				     "mode" : mode,
					 "type":type,
					 "id":id,
					 "name":name,
					 "amount":amount,
					 "list":list,
					 "group":group,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}else if(type == "s"){
		if(id==""||name==""||percent==""||rate==""||list==""){
			alert("กรุณากรอกข้อมูลให้ครบ");
		}else{
			$.ajax({
				type: 'POST',
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
					 "rate":rate,
					 "maxList":maxList,
					 "oldList":oldList},
				success: function(data){
					//alert(data);
					alert(data);
				}
			})
		}
	}/*else{
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
	}*/
}
function checkMode(){
	var id = $("#inputParam1").val();
	var mode = $("#inputParam2").val();
	
	$("#inputId").val(id);
	$("#inputMode").val(mode);
	
	if(mode == "Update"){
		$("#btnDelete").prop("disabled", false);
		$.ajax({
			type: 'POST',
			url: './SetTaxSrvl',
			data: 
			 {"method":"GenAll",
				"id":id},
			 
			success: function(data){
				$("#btnReset").prop("disabled", true);
				$('#dropdownType').val(data.type).attr("selected", "selected");
				changeDropDown();
				$('#inputName').val(data.tax_order);
				$('#inputAmount').val(data.tax_amount);
				$('#inputMax').val(data.max_val);
				$('#inputRate').val(data.tax_rate);
				$('#inputPercent').val(data.tax_percent);
				$('#dropdownGroup').val(data.group_id).attr("selected", "selected");
				$('#inputList').val(data.tax_list);
				$('#inputOldList').val(data.tax_list);
				$('#inputMaxList').val(data.MAX_LIST);
				$('#inputOldGroup').val(data.group_id);
			}
		})
	}else if(mode == "New"){
		$.ajax({
			type: 'POST',
			url: './SetTaxSrvl',
			data: 
			 {"method":"GenId"},
			success: function(data){
				$("#inputId").val(data.ID);
				//$("#inputList").val(data.LIST);
				$("#inputList").prop("disabled", true);
				changeGroup();
			}
		})
	}
}
function genRoleGroup(){
	$.ajax({
		type: 'POST',
		url: './SetTaxSrvl',
		data: 
		 {"method":"genRoleGroup"} ,
		success: function(data){
			// alert(data);
			$('#dropdownGroup').append(data);
			checkMode();
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

function changeGroup(){
	var oldGroup = $("#inputOldGroup").val();
	var oldList = $("#inputOldList").val();
	var group = $("#dropdownGroup").val();
	var mode = $("#inputMode").val();
	$.ajax({
		type: 'POST',
		url: './SetTaxSrvl',
		data: 
		 {"method":"changeGroup",
			"group":group } ,
		 
		success: function(data){
			if(mode == "New"){
				$("#inputList").val(data.LIST);
			}else if(mode == "Update"){
				if(oldGroup == group){
					//alert("update old");
					$("#inputList").val(oldList);
					$("#inputMaxList").val(data.LIST-1);
				}else{
					//alert("update new");
					$("#inputList").val(data.LIST);
					$("#inputMaxList").val(data.LIST-1);
				}
			}
		}
	})
}

function checkList(){
	var max = $("#inputMaxList").val() * 1;
	var oldList = $("#inputOldList").val();
	var list = $("#inputList").val() * 1;
	if(list > max){
		alert("ค่าสูงสุดที่ใส่ได้คือ "+max);
		$("#inputList").val(oldList);
	}
}