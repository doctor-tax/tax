<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TaxBreak Calculator</title>
<meta name="viewport" content="width=divice-width,initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="jsform/taxbreakCal.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="row form-group"></div>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading text-center">Calculator The tax abatement</div>
			<div class="panel-body">
				<div class="row">
					<div class="panel-group" id="myCollapse"></div>
					<div class="text-right col-sm-12">
						<button class="btn btn-success" onclick="ClickSave()">SAVE</button>
						<button class="btn btn-warning" onclick="ClickCancel()">CANCEL</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>