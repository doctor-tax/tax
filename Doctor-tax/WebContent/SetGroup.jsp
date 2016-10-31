<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jquery-ui.css" rel="stylesheet">
		
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="jsform/SetGroup.js"></script>
	<title>Set Group</title>
</head>
<body>
	<div class="row form-group"></div>
		
		<div class="form-horizontal">
		
			<div id="dialogDelete" title="Delete">
  				<b>Confirm ?</b>
			</div>
					
			<div id="dialogReset" title="Reset">
  				<b>Confirm ?</b>
			</div>
					
			<div id="dialogSave" title="Save">
  				<b>Confirm ?</b>
			</div>
			
			<div class="container">
				
				<input id="inputMode" type="hidden" class="form-control input-sm" value="New">
			
				<div class="panel panel-primary">
					<div class="panel-heading text-center">
						<b>Set Group Tax Break</b>
					</div>
					
					<div class="panel-body">
						<div class="row">
					 		<div class="col-xs-6 col-sm-3 control-label">
					 			<p class="texy-right"><b>ID :</b></p>
					 		</div>
					 		<div class = "col-xs-6 col-sm-3 ">
								<input id = "inputId" type="text" class="form-control input-sm" disabled >
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
					 			<p class="texy-right"><b>List :</b></p>
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
				
				<h3><b>Group</b></h3>
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID_GROUP</th>
							<th>NAME_GROUP</th>
							<th>LIST_GROUP</th>
						</tr>
					</thead>
					<tbody id="tb">
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>