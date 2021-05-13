
<%@page import="com.Buyer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buyer Details</title>

<!--Link Bootstrap, jQuery, and Buyer.js to the BuyerDetails page-->
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Buyer.js"></script>

<style>
h1 {
	text-align: Center;
	text-transform: uppercase;
	color:black;
	background:#ffff33;
	
}

h2 {
	text-align: Center;
	text-transform: uppercase;
	color:black;
	background:#4da6ff;
	
}



body{
background:linear-gradient(to right, rgb(128, 179, 255), rgb(0, 71, 179));
font-weight: bold;

}
</style>

</head>
<!--Structure of the page-->
<body>

	<h1>GadgetBadget System - Buyer</h1>
	

	<h2>---Add Buyer Details--- </h2>
	<br>

	<!--Develop the form-->
	
	<div class="addform">
		<form name="formBuyerinfo" id="formBuyerinfo" class="form-horizontal font-weight-bold" action="BuyerDetails.jsp" method="post" >
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Buyer ID :</label>
				<div class="col-sm-10">
					<input type="text" id="BuyerID" name="BuyerID" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Name :</label>
				<div class="col-sm-10">
					<input type="text" id="Name" name="Name" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Telephone NO :</label>
				<div class="col-sm-10">
					<input type="text" id="Telno" name="Telno" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Address :</label>
				<div class="col-sm-10">
					<input type="text" id="address" name="address" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Company Name :</label>
				<div class="col-sm-10">
					<input type="text" id="companyname" name="companyname" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Email :</label>
				<div class="col-sm-10">
					<input type="text" id="Email" name="Email" class="form-control" >
				</div>
			</div>
			
<br>			
			<div class="form-group">
			
				<input type="button" id="btnSave" name="btnSave" class="btn btn-primary btn-lg" value ="save">
				<input type="hidden" id="hiddenBuyerIDsave" name="hiddenBuyerIDsave" value="">
				
			</div>
		
		</form>		
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
 <div id="divBuyerGrid">
 	<%
 	{
 	 		Buyer BuyerObj = new Buyer();
 	 		out.print(BuyerObj.readBuyer());}
 	%>
</div>	
</div>
	

</body>
</html>