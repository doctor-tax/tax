$(document).ready(function() {
	changeDate();

});

/*
 * function autocompleteName(){ var value = $("#inputName").val();
 * //alert(value); $("#inputName").autocomplete({
 * 
 * minLength: 1, autoFocus: true, cacheLength: 1, scroll: true, highlight:
 * false,
 * 
 * source: function(request, response) { $.ajax({ type: "POST", url:
 * "./AutocompleteSrvl", dataType: "json", data: { term: request.term,
 * tb:"DOC_NAME", value:value }, success: function(data) { response(data);
 * $("#inputId").val(""); }
 * 
 * }); }, select: function(event, ui) { $("#inputId").val(ui.item.id); } }); }
 */
function clickBack() {
	location.href = "TaxbreakAdmin.jsp";
}

function clickSave() {
	/* var check = ""; */
	var month = $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();
	var table = $('#doc_income').DataTable();

	if (!table.data().count()) {
		alert('This month is Empty');
	} else {
		// alert(document.getElementById("#doc_income").rows.length);

		$("tbody tr")
				.each(
						function() {
							/*
							 * alert(check); if(check == 'This Month is
							 * Close!!!'){ return false; }else if(check == 'This
							 * Month has Calculateed Please RollBack First'){
							 * return false; }
							 */

							var $this = $(this);
							var row = $this.closest("tr");
							var id = row.find('td:eq(0)').text();
							var hcode = row.find('td:eq(3)').text();
							row
									.find('td:eq(4)')
									.html(
											'<span class="glyphicon glyphicon-refresh glyphicon-refresh-animate">Loading...</span>');

							$
									.ajax({
										type : 'POST',
										url : './CalTaxSrvl',
										data : {
											"method" : "save",
											"month" : month,
											"year" : year,
											"id" : id,
											"hcode" : hcode
										},
										success : function(data) {
											// alert(data);
											var a = 'Calculate Success!!!';
											// var b = 'This Month has
											// Calculateed Please RollBack
											// First';
											// alert(data+" = "+a);
											if (data == a) {
												row
														.find('td:eq(4)')
														.html(
																'<span class="glyphicon glyphicon-ok text-success"><b>  '
																		+ data
																		+ '</b></span>');
											} else {
												row
														.find('td:eq(4)')
														.html(
																'<span class="glyphicon glyphicon-remove text-danger"><b>  '
																		+ data
																		+ '</b></span>');
											}

										}
									})
						});
	}

	// alert(id+" "+name+" "+month+" "+year);

}

function clickReset() {
	location.reload();

}

function clickRollBack() {
	var month = $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();

	$.ajax({
		type : 'POST',
		url : './CalTaxSrvl',
		data : {
			"method" : "rollback",
			"month" : month,
			"year" : year
		},
		success : function(data) {
			alert(data);
		}
	})
}

function changeDate() {
	var month = $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();

	$('#doc_income').dataTable().fnDestroy();
	$('#doc_income').DataTable({
		"ajax" : {
			type : "POST",
			url : "./CalTaxSrvl",
			dataSrc : "data",
			data : {
				"method" : "getDbTable",
				"month" : month,
				"year" : year
			},
		}

	});

}

function clickClose() {
	var month = $("#dropdownMonth").val();
	var year = $("#dropdownYear").val();

	$.ajax({
		type : 'POST',
		url : './CalTaxSrvl',
		data : {
			"method" : "close",
			"month" : month,
			"year" : year
		},
		success : function(data) {
			alert(data);
			clickReset();
		}
	})
}