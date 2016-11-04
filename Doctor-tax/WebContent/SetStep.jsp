<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/jquery-ui.css" rel="stylesheet">
		
		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="js/jquery-ui.min.js"></script>
		<script src="jsform/SetStep.js"></script>
		
		<title>SetStep</title>
	</head>
	  
	<body>
		<div class="row form-group"></div>
		
		<div class="form-horizontal">
		
			<input id="inputMode" type="hidden" class="form-control input-sm" value="Update">
		
			<div class="container">
				<div class="panel panel-primary">
					<div class="panel-heading">Set Step</div>
					<div class="panel-body">
						<div id="groupStep">
							<div id = "group1">
								<div class="row">
									<div class="col-xs-6 col-sm-3 control-label">
					 					<p class="texy-right"><b>ขั้นที่ 1 :</b></p>
					 				</div>
					 				<div class="col-xs-6 col-sm-3">
					 					<div class="input-group">
										  <input title = "1" id="inputStart" type="number" class="form-control Step" placeholder="จำนวนเงินเริ่มต้นของขั้น" aria-describedby="basic-addon1">
										  <span class="input-group-addon">บาท</span>
										</div>
					 				</div>
					 				<div class="col-xs-6 col-sm-3">
					 					<div class="input-group">
										  <input title = "1" id="inputEnd" type="number" class="form-control Step" placeholder="จำนวนเงินสิ้นสุดของขั้น" aria-describedby="basic-addon1">
										  <span class="input-group-addon">บาท</span>
										</div>
					 				</div>
					 				<div class="col-xs-6 col-sm-3">
					 					<div class="input-group">
										  <input title = "1" id="inputPercent" type="number" class="form-control Step" placeholder="เรทที่นำไปคิด" aria-describedby="basic-addon1">
										  <span class="input-group-addon">%</span>
										</div>
					 				</div>
								</div>
							</div>
							
						</div>
						
						<div class="row">
						  <div class="col-xs-6 col-sm-3">
						  	<button id="btnBack" class="btn btn-default" onclick="clickBack()">
									<b>Back</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3">
						  	<button id="btnDelete" class="btn btn-default" onclick = "clickReset()">
									<b>Reset</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3">
						  	<button id="btnDelete" class="btn btn-default" onclick = "clickRemove()">
									<b>Remove</b>
							</button>
						  
						  	<button id="btnReset" class="btn btn-default" onclick = "clickAdd('','','')">
									<b>Add</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3 text-right">
							<button id="btnSave" class="btn btn-default" onclick = "clickSave()">
									<b>Save</b>
							</button>
						  </div>	
				  		</div>
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>