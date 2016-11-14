$(document).ready(function(){
	
	/*$( function() {
		

		$("#inputName").autocomplete({
			
			minLength: 1,
	        autoFocus: true,
	        cacheLength: 1,
	        scroll: true,
	        highlight: false,
	
			 source: function(request, response) {
		            $.ajax({
		             type: "POST",
		                url: "./AutocompleteSrvl",
		                dataType: "json",
		                data: {
		                    term: request.term,
		                    tb:"DOC_NAME"
		                },
		                success: function(data) {
		                    response(data);
		                }
		                
		            });
			 },
			 select: function(event, ui) {
             	$("#inputId").val(ui.item.id);
             }
		});
	});*/
});

function autocompleteName(){
	var value = $("#inputName").val();
	//alert(value);
	$("#inputName").autocomplete({
		
		minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,

		 source: function(request, response) {
	            $.ajax({
	             type: "POST",
	                url: "./AutocompleteSrvl",
	                dataType: "json",
	                data: {
	                    term: request.term,
	                    tb:"DOC_NAME",
	                    value:value
	                },
	                success: function(data) {
	                    response(data);
	                    $("#inputId").val("");
	                }
	                
	            });
		 },
		 select: function(event, ui) {
         	$("#inputId").val(ui.item.id);
         }
	});
}
function clickBack(){
	location.href = "TaxbreakAdmin.jsp";
}

function clickSave(){
	
	var id = $("#inputId").val();
	var name= $("#inputName").val();
	var month= $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();
	
	if(id ==""||name==""){
		alert("กรุณาเลือกคุณหมอด้วยครับ");
	}else{
		$.ajax({
			type: 'POST',
			url: './CalTaxSrvl',
			data: 
			 {"method":"save",
			   "id":id,
			   "month":month,
			   "year":year},
			success: function(data){
				$("#inputTax").val(data);
				$(".hidden").show();
			}
		})
	}
	//alert(id+" "+name+" "+month+" "+year);
	
	
}

function clickReset(){
	location.reload();
	
}