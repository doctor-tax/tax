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

	<div class="form-horizontal">
		<div class="row form-group"></div>
		
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading text-center">
					<b>Set Tax Break</b>
				</div>
					
				<div class="panel-body">
					<div id="group-step">
						<div class="row">
							<div class="col-sm-3"></div>
							
							<div class="col-sm-3">
								<div class="input-group">
								  <input id="inputStart" type="number" class="form-control" placeholder="เริ่มต้น" aria-describedby="basic-addon1">
								  <span class="input-group-addon">บาท</span>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="input-group">
								  <input id="inputEnd" type="number" class="form-control" placeholder="สิ้นสุด" aria-describedby="basic-addon1">
								  <span class="input-group-addon">บาท</span>
								</div>
							</div>
								<div class="input-group">
								  <input id="inputPercent" type="number" class="form-control" placeholder="เรทการคิด" aria-describedby="basic-addon1">
								  <span class="input-group-addon">%</span>
								</div>
							<div class="col-sm-3">
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>