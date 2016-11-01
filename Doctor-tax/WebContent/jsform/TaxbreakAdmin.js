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

// function setOrderTax(id) {
// $.ajax({
// type : 'POST',
// url : './TaxbreakAdminSrvl',
// data : {
// "method" : "UpdateTax",
// "ID" : id
// },
// success : function(data) {
// alert("Success");
// // location.href="SetTax.jsp";
// }
// });
// }
function getID(id) {
	$("#var1").val(id);
	$("#var2").val("update");
	form.submit();
}
