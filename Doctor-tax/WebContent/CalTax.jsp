<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="js/jquery-1.12.3.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/dataTables.responsive.min.js"></script>
<script src="js/responsive.bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="css/responsive.bootstrap.min.css" rel="stylesheet">

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
					<!-- <div class="row">
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


					</div> -->

					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>Month :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<select class="form-control input-sm" id="dropdownMonth"
								onchange="changeDate()">
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
							<select class="form-control input-sm" id="dropdownYear"
								onchange="changeDate()">
								<option value="2016">2016</option>

							</select>
						</div>
					</div>

					<!-- <div id="cal" class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="texy-right">
								<b>Tax :</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 ">
							<input id="inputTax" class="form-control input-sm">
						</div>


					</div> -->

					<div class="row">
						<div class="col-xs-6 col-sm-3">
							<button id="btnBack" class="btn btn-default"
								onclick="clickBack()">
								<b>Back</b>
							</button>
						</div>

						<div class="col-xs-6 col-sm-3">

							<button id="btnRollBack" class="btn btn-default"
								onclick="clickRollBack()">
								<b>Roll Back</b>
							</button>

							<button id="btnClose" class="btn btn-default"
								onclick="clickClose()">
								<b>Close</b>
							</button>
						</div>

						<div class="col-xs-6 col-sm-3"></div>

						<div class="col-xs-6 col-sm-3 text-right">
							<button id="btnStop" class="btn btn-danger"
								onclick="clickStop()" disabled>
								<b>Stop</b>
							</button>
							
							<button id="btnSave" class="btn btn-default"
								onclick="clickSave()">
								<b>Calculate</b>
							</button>
						</div>
					</div>
				</div>

				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12 col-xs-12">
							<h3>Doctor Income</h3>
							<div class="table-responsive" width="100%">
								<table id="doc_income"
									class="table table-striped table-bordered dt-responsive nowrap" width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Income</th>
											<th>Hospital Code</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody id="tbdata"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>