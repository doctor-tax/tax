//modal
$(function() {
	$("#dialogDelete").dialog({
		autoOpen : false,
		modal : true,
		buttons : {
			"Confirm" : function() {
				$(this).dialog(clickDelete());
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}

	});

	$("#btnDelete").on("click", function() {
		$("#dialogDelete").dialog("open");
	});
});

$(function() {
	$("#dialogReset").dialog({
		autoOpen : false,
		modal : true,
		buttons : {
			"Confirm" : function() {
				$(this).dialog(clickReset());
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}

	});

	$("#btnReset").on("click", function() {
		$("#dialogReset").dialog("open");
	});
});

$(function() {
	$("#dialogSave").dialog({
		autoOpen : false,
		modal : true,
		buttons : {
			"Confirm" : function() {
				$(this).dialog(clickSave());
				$(this).dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}

	});

	$("#btnSave").on("click", function() {
		$("#dialogSave").dialog("open");
	});
});
// modal

$(document).ready(function() {
	getOrderTax();
});

function getOrderTax() {
	$("#tbDB").empty();
	$.ajax({
		type : 'POST',
		url : './TaxbreakCal',
		data : {
			"method" : "getOrder"
		},
		success : function(data) {
			// alert(data);
			$('#myCollapse').append(data);
		}
	})
}

function ClickSave() {

	var myVal;
	myObj = {};
	jsonObj = [];

	$(".Tax").each(function() {

		myVal = $(this).val();
		if (myVal == null || myVal == "") {
			myVal = "0";
		}
		myObj["id"] = this.id;
		myObj["value"] = myVal;
		jsonObj.push(myObj);
		myObj = {};
	});

	var json = JSON.stringify(jsonObj);
	InsertTra(json);

}

function InsertTra(json) {
	$.ajax({
		type : 'POST',
		url : './TaxbreakCal',
		dataType : 'json',
		data : {
			"method" : "InsertTra",
			"json" : json
		},
		success : function(data) {
			alert(data.result);
			// $('select option[value="0"]').attr("selected", true);
			// $('input').val('');
			$(".collapse").collapse('hide');
			location.reload();
		}
	})
}
function ClickCancel(){
	location.reload();
}
