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
		<script src="jsform/SetTax.js"></script>
		
		<title>Set Tax Break</title>
	</head>
	  
	<body>
		<div class="row form-group"></div>
		
		<div class="form-horizontal">
			<div class="container">
				<div class="panel panel-primary">
					<div class="panel-heading text-center">
						<b>Set Tax Break</b>
					</div>
					
					<div class="panel-body">
					
					<div id="dialogDelete" title="Delete">
  						<b>Confirm ?</b>
					</div>
					
					<div id="dialogReset" title="Reset">
  						<b>Confirm ?</b>
					</div>
					
					<div id="dialogSave" title="Save">
  						<b>Confirm ?</b>
					</div>
					
					
						<div class="row">
					 		<div class="col-xs-6 col-sm-3 control-label">
					 			<p class="texy-right"><b>ID :</b></p>
					 		</div>
					 		<div class = "col-xs-6 col-sm-3 ">
								<input id = "inputId" type="text" class="form-control input-sm" >
							</div>
							<div class="col-xs-6 col-sm-3 control-label">
					 			<p class="texy-right"><b>Type :</b></p>
					 		</div>
					 		<div class = "col-xs-6 col-sm-3 ">
								<select class="form-control input-sm" id="dropdownType" onchange="changeDropDown()">
						 				<option value="f">กำหนดไว้แล้ว</option>
						 				<option value="r">รับค่าตามจริงแต่ไม่เกินเรทที่กำหนด</option>
						 				<option value="p">รับค่าตามจริงแต่ไม่เกิน % ที่กำหนด</option>
						 				<option value="fr">รับค่าตามจริง</option>
						 				<option value="pr">รับค่าตามจริงแต่ไม่เกิน % และเรทที่กำหนด</option>
						 		</select>
							</div>
					 	</div>
				 	
				 	<div class="row">
				 		<div class="col-xs-6 col-sm-3 control-label">
				 			<p class="texy-right"><b>Name :</b></p>
				 		</div>
				 		<div class = "col-xs-6 col-sm-3 ">
							<input id = "inputName" type="text" class="form-control input-sm" >
						</div>
				 	</div>
				 	
				 	<div class="row">
				 		<div class="col-xs-6 col-sm-3 control-label">
				 			<p class="texy-right"><b>กำหนดค่า :</b></p>
				 		</div>
				 		<div class = "col-xs-6 col-sm-3 ">
							<div class="input-group">
							  <input id="inputAmount" type="text" class="form-control" placeholder="จำนวนเงิน" aria-describedby="basic-addon1">
							  <span class="input-group-addon">บาท</span>
							</div>
						</div>
						
						<div class="col-xs-6 col-sm-3 control-label">
				 			<p class="texy-right"><b>ลดหย่อนได้ไม่เกิน(คน) :</b></p>
				 		</div>
				 		<div class = "col-xs-6 col-sm-3 ">
							<div class="input-group">
							  <input id="inputMax" type="text" class="form-control" placeholder="จำนวน" aria-describedby="basic-addon1">
							  <span class="input-group-addon">คน</span>
							</div>
						</div>
				 	</div>
				 	
				 	<div class="row">
				 		<div class="col-xs-6 col-sm-3 control-label">
				 			<p class="texy-right"><b>กำหนดเรทไม่เกิน(บาท) :</b></p>
				 		</div>
				 		<div class = "col-xs-6 col-sm-3 ">
							<div class="input-group">
							  <input id="inputRate" type="text" class="form-control" placeholder="จำนวนเงิน" aria-describedby="basic-addon1" disabled>
							  <span class="input-group-addon">บาท</span>
							</div>
						</div>
				 	
				 		<div class="col-xs-6 col-sm-3 control-label">
				 			<p class="texy-right"><b>กำหนดเรทไม่เกิน(%) :</b></p>
				 		</div>
				 		<div class = "col-xs-6 col-sm-3 ">
							<div class="input-group">
							  <input id="inputPercent" type="text" class="form-control" placeholder="%" aria-describedby="basic-addon1" disabled>
							  <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
				 	
				 	<div class="row">
					 		
							<div class="col-xs-6 col-sm-3 control-label">
					 			<p class="texy-right"><b>Group :</b></p>
					 		</div>
					 		<div class = "col-xs-6 col-sm-3 ">
								<select class="form-control input-sm" id="dropdownGroup">

						 		</select>
							</div>
							
							<div class="col-xs-6 col-sm-3 control-label">
					 			<p class="texy-right"><b>ลำดับ :</b></p>
					 		</div>
					 		<div class = "col-xs-6 col-sm-3 ">
								<input id = "inputList" type="text" class="form-control input-sm" >
							</div>
					 </div>
						
					 	<div class="row">
						  <div class="col-xs-6 col-sm-3"></div>
						  
						  <div class="col-xs-6 col-sm-3">
						  	<button id="btnDelete" class="btn btn-default"
								 disabled>
									<b>Delete</b>
							</button>
						  
						  	<button id="btnReset" class="btn btn-default">
									<b>Reset</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3"></div>
						  
						  <div class="col-xs-6 col-sm-3 text-right">
							<button id="btnSave" class="btn btn-default">
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