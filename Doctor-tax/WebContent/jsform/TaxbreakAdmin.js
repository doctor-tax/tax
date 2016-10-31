function getData() {
	$("#tbDB").empty();
	$.ajax({
		type : 'GET',
		url : './TaxbreakAdmin',
		data : {
			"method" : "getDbTable"
		},
		success : function(data) {
			// alert(data);
			$('#tbDB').append(data);
		}
	})
}
function Update(id) {
	var text = $('#row' + id);
	var getData = "";
	text.children().each(function() {
		getData = $(this).html();
		alert(getData);
	});
}