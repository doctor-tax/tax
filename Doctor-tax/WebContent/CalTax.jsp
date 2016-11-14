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
<script src="jsform/CalTax.js"></script>
<title>Calculate</title>
</head>
<body>
	<div class="row form-group"></div>

	<div class="form-horizontal">
		<div class="container">
			<div class="panel panel-primary">

				<div class="panel-heading text-center">
					<b>Calculate</b>
				</div>

				<div class="panel-body">
					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>DOCTOR Name :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<input id="inputName" type="text" class="form-control input-sm" onkeyup="autocompleteName()">
						</div>

						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>DOCTOR ID :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<input id="inputId" type="text" class="form-control input-sm" disabled>
						</div>


					</div>

					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>Month :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<select class="form-control input-sm" id="dropdownMonth">
								<option value="01">มกราคม</option>
								<option value="02">กุมภาพันธ์</option>
								<option value="03">มีนาคม</option>
								<option value="04">เมษายน</option>
								<option value="05">พฤษภาคม</option>
								<option value="06">มิถุนายน</option>
								<option value="07">กรกฎาคม</option>
								<option value="08">สิงหาคม</option>
								<option value="09">กันยายน</option>
								<option value="10">ตุลาคม</option>
								<option value="11">พฤศจิกายน</option>
								<option value="12">ธันวาคม</option>
							</select>
						</div>
						
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>Year :</b>
							</p>
						</div>
						
						
						
						<div class="col-xs-6 col-sm-3 ">
							<select class="form-control input-sm" id="dropdownYear">
								<option value="2016">2016</option>
								
							</select>
						</div>
					</div>
					
					<div id="cal" class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>Tax :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<input id="inputTax" class="form-control input-sm">
						</div>


					</div>
					
					<div class="row">
						  <div class="col-xs-6 col-sm-3">
						  	<button id="btnBack" class="btn btn-default" onclick="clickBack()">
									<b>Back</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3">
						  
						  	<button id="btnReset" class="btn btn-default" onclick="clickReset()">
									<b>Reset</b>
							</button>
						  </div>
						  
						  <div class="col-xs-6 col-sm-3"></div>
						  
						  <div class="col-xs-6 col-sm-3 text-right">
							<button id="btnSave" class="btn btn-default" onclick="clickSave()">
									<b>Calculate</b>
							</button>
						  </div>	
				  		</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>