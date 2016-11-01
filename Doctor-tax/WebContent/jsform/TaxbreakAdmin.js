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
	$("#var2").val("update");
	form.submit();
}
function addID() {
	$("#var2").val("add");
	$("#var1").val("");
	form.submit();
}
