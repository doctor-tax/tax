<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tax Order</title>
<meta name="viewport" content="width=divice-width,initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="jsform/TaxbreakAdmin.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="row form-group"></div>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading text-center">Tax Break</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3 col-xs-3 text-right"></div>
					<div class="col-sm-3 col-xs-3 text-right">
						<button class="btn btn-primary" onclick="loadDataTable()">ดึงข้อมูล</button>
					</div>
					<div class="col-sm-3 col-xs-3">
						<button class="btn btn-primary" onclick="addID()">เพิ่ม</button>
						<button class="btn btn-primary" onclick="addGroup()">แก้ไข
							Group</button>
					</div>
					<div class="col-sm-3 col-xs-3">

						<button class="btn btn-primary" onclick="addStep()">แก้ไขเงื่อนไขขั้นบันได</button>
					</div>
				</div>
			</div>
		</div>
		<form style="display: hidden" action="SetTax.jsp" method="POST"
			id="form">
			<input type="hidden" id="id" name="id" value="" /> <input
				type="hidden" id="mode" name="mode" value="" />
		</form>
		<h3>Tax Order</h3>
		<table id="order_tax"
			class="table table-striped table-bordered dataTable table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Group</th>
					<th>List</th>
					<th>Order</th>
				</tr>
			</thead>
			<tbody id="tbdata">
			</tbody>
		</table>
	</div>
</body>
</html>