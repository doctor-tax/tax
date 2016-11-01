function getData() {
	$("#tbDB").empty();
	$.ajax({
		type : 'GET',
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

function getID(id) {
	$("#var1").val(id);
	$("#var2").val("Update");
	form.submit();
}
function addID() {
	$("#var2").val("New");
	$("#var1").val("");
	form.submit();
}
