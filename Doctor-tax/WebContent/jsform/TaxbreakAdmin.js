/*function getData() {
	$("#tbDB").empty();
	$.ajax({
		type : 'POST',
		url : './TaxbreakAdminSrvl',
		data : {
			"method" : "getDbTable"
		},
		success : function(data) {
			// alert(data);
			$('#tbDB').append(data);
		}
	})
}
 */
function getID(id) {
	$("#id").val(id);
	$("#mode").val("Update");
	form.submit();
}
function addID() {
	$("#id").val("");
	$("#mode").val("New");
	form.submit();
}
function addStep() {
	location.href = "SetStep.jsp";
}
function addGroup() {
	location.href = "SetGroup.jsp";
}
// ++++++++++++++

function loadDataTable() {
	$('#order_tax').dataTable().fnDestroy();
	$('#order_tax').DataTable({
		"ajax" : {
			type : "POST",
			url : "./TaxbreakAdminSrvl",
			dataSrc : "data",
			data : {
				medthod : "getDbTable"
			},
		}

	});

	$('#tbdata').on('dblclick', 'tr', function() {
		var $this = $(this);
		var row = $this.closest("tr");
		var cell1text = row.find('td:eq(0)').text();
		getID(cell1text);
	});

}
