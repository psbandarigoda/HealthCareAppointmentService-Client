/**
 * 
 */

$(document).ready(function() {
	$("#alertSuccess").hide();
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
	var status = validatePatientForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidAppointmentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppointmentAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppointmenSaveComplete(response.responseText, status);
		}
	});
});

function onAppointmenSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
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
	$("#hidAppointmentIDSave").val("");
	$("#formAppointment")[0].reset();
}

// CLIENT-MODEL================================================================
function validatePatientForm() {

	// patient
	if ($("#patient").val().trim() == "") {
		return "Insert patient.";
	}
	// hospital
	if ($("#hospital").val().trim() == "") {
		return "Insert hospital.";
	}
	// doctor
	if ($("#doctor").val().trim() == "") {
		return "Insert doctor.";
	}

	// date
	if ($("#date").val().trim() == "") {
		return "Insert date.";
	}

	return true;
}

// Delete-----------------------------
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentAPI",
		type : "DELETE",
		data : "id=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAppointmentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

	
//UPDATE==========================================
$(document).on('click', '.btnUpdate', function(event)
{
 $('#hidAppointmentIDSave').val($(this).closest('tr').find('#hidItemIDUpdate').val());
 $('#patient').val($(this).closest('tr').find('td:eq(1)').text());
 $('#hospital').val($(this).closest('tr').find('td:eq(2)').text());
 $('#doctor').val($(this).closest('tr').find('td:eq(3)').text());
 $('#date').val($(this).closest('tr').find('td:eq(4)').text());
});

