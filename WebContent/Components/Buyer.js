//implement both the controller and client-server in Buyer.js

$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateBuyerForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hiddenBuyerIDsave").val() == "") ? "POST": "PUT";
$.ajax(
		{
		 url : "BuyerAPI",
		 type : type,
		 data : $("#formBuyerinfo").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onBuyerSaveComplete(response.responseText, status);
		 }
		});
});

function onBuyerSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Saved successfully");
 $("#alertSuccess").show();
 $("#divBuyerGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occurred while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
 $("#hiddenBuyerIDSave").val("");
 $("#formBuyerinfo")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hiddenBuyerIDSave").val($(this).closest("tr").find('#hiddenBuyerIDUpdate').val());
 $("#BuyerID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#TelNO").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Address").val($(this).closest("tr").find('td:eq(3)').text());
 $("#CompanyName").val($(this).closest("tr").find('td:eq(4)').text());
 $("#Email").val($(this).closest("tr").find('td:eq(5)').text());
});

// REMOVE 
$(document).on("click", "#btnRemove", function(event)
		{$.ajax(
				{
					 url : "BuyerAPI",
					 type : "DELETE",
					 data : "BuyerID=" + $(this).data("BuyerID"),
					 dataType : "text",
					 complete : function(response, status)
					 {
					 onBuyerDeleteComplete(response.responseText, status);
					 }
					});
			});

function onBuyerDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully Removed.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occured while Removing.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
}

// CLIENTMODEL=========================================================================
function validateBuyerForm()
{
// ID
if ($("#BuyerID").val().trim() == "")
 {
 return "Insert Buyer ID!";
 }
// validate name field
if ($("#Name").val().trim() == "")
 {
 return "Insert Name!";
 } 
// validate telephone field
if ($("#TelNO").val().trim() == "")
{
return "Insert Telephone number!";
}
// validate telno 
var tmpTel = $("#TelNO").val().trim();
if (!$.isNumeric(tmpTel))
 {
 return "Insert a valid Telephone number.";
 }


// validate Address field
if ($("#Address").val().trim() == "")
 {
 return "Insert Address.";
 }
//validate CompanyName field
if ($("#CompanyName").val().trim() == "")
 {
 return "Insert CompanyName.";
 }
// validate email field
if ($("#Email").val().trim() == "")
{
return "Insert Email.";
}
return true;
}
