<%@page import="model.AppointmentModel"%>
<%@page import="com.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="Component/jquery-3.5.0.min.js"></script>
<script src="Component/Appointment.js"></script>
<link href="Veiws/css/bootstrap.min.css" rel="stylesheet">
<link href="Veiws/css/style.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Online Appointments</h1>
				<form id="formAppointment" name="formAppointment">

					Patient: <input id="patient" name="patient" type="text"
						class="form-control form-control-sm"> <br> Hospital:
					<input id="hospital" name="hospital" type="text"
						class="form-control form-control-sm"> <br> Doctor: <input
						id="doctor" name="doctor" type="text"
						class="form-control form-control-sm"> <br> Date: <input
						id="date" name="date" type="date"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidAppointmentIDSave" name="hidAppointmentIDSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>

				<div id="divAppointmentGrid">
					<%
						AppointmentService appointmentService = new AppointmentService();
					out.print(appointmentService.readAppointment());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>