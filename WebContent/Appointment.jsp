<%@page import="com.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (request.getParameter("id") != null)
{
		AppointmentService appointmentService = new AppointmentService();
		String stsMsg = "";

if (request.getParameter("hidAppointmentIdSave") == "")
{
	 stsMsg = appointmentService.insertAppointment(request.getParameter("patientId"),
										request.getParameter("hospital"),
										request.getParameter("doctor"),
										request.getDateHeader("date"));
}
else
{
stsMsg = appointmentService.updateAppointment
(request.getParameter("id"),
request.getParameter("patientId"),
request.getParameter("hospital"),
request.getParameter("doctor"),
request.getParameter("date"));
}
session.setAttribute("statusMsg", stsMsg); 
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="Component/jquery-3.5.0.min.js"></script>
<link href="Veiws/css/bootstrap.min.css" rel="stylesheet">
<link href="Veiws/css/style.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Online Appointments</h1>
				<form id="formAppointment" name="formAppointment">
					Appointment ID: <input id="appointmentId" name="appointmentId" type="text"
						class="form-control form-control-sm"> <br> Patient
					Name: <input id="patientName" name="patientName" type="text"
						class="form-control form-control-sm"> <br> Amount: <input
						id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br> Payment
					Date: <input id="paymentdate" name="paymentdate" type="date"
						class="form-control form-control-sm"> <br> Address: <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> Contact
					Number: <input id="contactNo" name="contactNo" type="text"
						class="form-control form-control-sm"> <br> Email: <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Submit"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>

				<div id="divPaymentsGrid">
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