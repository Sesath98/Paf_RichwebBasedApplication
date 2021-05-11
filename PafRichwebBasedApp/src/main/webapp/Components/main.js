$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}



	$("#alertError").hide();



});




// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "UserAPI",
			type: type,
			data: $("#formUser").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onUserSaveComplete(response.responseText, status);
			}
		});



});




function onUserSaveComplete(response, status) {



	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUserGrid").html(resultSet.data);
		}



		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}



	$("#hidUserIDSave").val("");
	$("#formUser")[0].reset();



}





// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidUserIDSave").val($(this).data("itemid"));
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#phone_no").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#designation").val($(this).closest("tr").find('td:eq(3)').text());
});




// CLIENT-MODEL================================================================
function validateUserForm() {
	// CODE
	if ($("#userName").val().trim() == "") {
		return "Insert  users username.";
	}
	// NAME
	if ($("#phone_no").val().trim() == "") {
		return "Insert  users phone_no.";
	}

	if ($("#address").val().trim() == "") {
		return "Insert  users address.";
	}


	// DESCRIPTION------------------------
	if ($("#designation").val().trim() == "") {
		return "Insert users designation.";
	}



	return true;
}






///REMOVE============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "UserAPI",
			type: "DELETE",
			data: "userId=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onUserDeleteComplete(response.responseText, status);
			}



		});



});




function onUserDeleteComplete(response, status) {



	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divUserGrid").html(resultSet.data);
		}



		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}



	}



	else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}



	else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();



	}



}