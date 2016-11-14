$(document).ready(function(){
	genStep();
});

var counter = 1;
var start = "";
var end = "";
var percent = "";

function clickAdd() {
	var newTextBoxDiv = $(document.createElement('div')).attr("id",
			'group' + counter);

	newTextBoxDiv
			.after()
			.html(
					'<div class="row">'
							+ '<div class="col-xs-6 col-sm-3 control-label">'
							+ '<p class="texy-right"><b>ขั้นที่ '+ counter
							+ ' :</b></p>'
							+ '</div>'
							+ '<div class="col-xs-6 col-sm-3"><div class="input-group">'
							+ '<input title = "'+ counter
							+ '" id="inputStart" type="number" class="form-control Step" placeholder="จำนวนเงินเริ่มต้นของขั้น" aria-describedby="basic-addon1" value="'+start+'"><span class="input-group-addon">บาท</span>'
							+ '</div></div>'
							+ '<div class="col-xs-6 col-sm-3"><div class="input-group">'
							+ '<input title = "'+ counter
							+ '" id="inputEnd" type="number" class="form-control Step" placeholder="จำนวนเงินสิ้นสุดของขั้น" aria-describedby="basic-addon1" value="'+end+'"><span class="input-group-addon">บาท</span>'
							+ '</div></div>'
							+ '<div class="col-xs-6 col-sm-3"><div class="input-group">'
							+ '<input title = "'+ counter
							+ '" id="inputPercent" type="number" class="form-control Step" placeholder="เรทที่นำไปคิด" aria-describedby="basic-addon1" value="'+percent+'"><span class="input-group-addon">%</span>'
							+ '</div></div></div>');

	newTextBoxDiv.appendTo("#groupStep");

	counter++;
	start = "";
	end = "";
	percent = "";

}

function clickRemove() {
	if (counter == 1) {
		alert("ไม่สามารถลบได้แล้ว");
		return false;
	}
	counter--;
	$("#group" + counter).remove();

}

function clickBack() {
	location.href = "TaxbreakAdmin.jsp";
}

function clickSave() {
	jsonObj = [];
	var x = 0;
	item = {};
	boo = true;
	$(".Step").each(function() {
		++x;
		//item["id"] = id;
		var id = this.id;
		// alert(id);
		var value = $(this).val();
		if(value == ""){
			alert("กรุณากรอกให้ครบทุกช่องด้วยครับ");
			
			boo = false;
			return false;
		}
		
		item[id] = value;
		if (x % 3 == 0) {
			jsonObj.push(item);
			item = {};
		}

	});
	
	if(boo){
		jsonString = JSON.stringify(jsonObj);
		console.log(jsonString);
		
		$.ajax({
			type: 'POST',
			url: './SetStepSrvl',
			dataType: 'json',
			data: 
			 {"method":"clickSave",
				"json": jsonString} ,
			success: function(data){
				alert("Success");
			}
		})
	}
	
	return boo;
}

function genStep(){
	$.ajax({
		type: 'POST',
		url: './SetStepSrvl',
		data: 
		 {"method":"genStep"} ,
		success: function(data){
			for(i=0;i<data.length;i++){		
				start = data[i].step_start;
				end = data[i].step_end;
				percent = data[i].step_percent;
				//alert(start);
				clickAdd();
			}
			//alert(data.itemb.length);
			
		}
	})
}

function clickReset(){
	var newcounter = counter-1;
	for(i=0;i<newcounter;i++){
		clickRemove();
		
	}
	clickAdd();
}