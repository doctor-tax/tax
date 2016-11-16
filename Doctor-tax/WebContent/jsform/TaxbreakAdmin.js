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
	$("#id").val(id);
	$("#mode").val("Update");
	form.submit();
}
function addID() {
	$("#id").val("");
	$("#mode").val("New");
	form.submit();
}
function addStep(){
	location.href = "SetStep.jsp";
}
function addGroup(){
	location.href = "SetGroup.jsp";
}
