$(document).ready(function(){
	
	$( function() {
		

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
	});
});
function clickBack(){
	location.href = "TaxbreakAdmin.jsp";
}

function clickSave(){
	var id = $("#inputId").val();
	var name= $("#inputName").val();
	var month= $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();
	
	//alert(id+" "+name+" "+month+" "+year);
	
	$.ajax({
		type: 'POST',
		url: './CalTaxSrvl',
		data: 
		 {"method":"save",
		   "id":id,
		   "month":month,
		   "year":year},
		success: function(data){
			alert(data);
		}
	})
}

function clickReset(){
	location.reload();
	
}