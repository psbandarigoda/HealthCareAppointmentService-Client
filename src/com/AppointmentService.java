package com;

import java.sql.*;
import java.text.SimpleDateFormat;

import database.DBConnection;
import model.AppointmentModel;

public class AppointmentService {

	// Insert method which insert data to Appointment table
	public String insertAppointment(String patientId, String hospital, String doctor, String date) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into appointment(`id`,`patientId`,`hospital`,`doctor`,`date`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, patientId);
			preparedStmt.setString(3, hospital);
			preparedStmt.setString(4, doctor);
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date date1 = sdf1.parse(date);
			java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime()); 
			preparedStmt.setDate(5, sqlStartDate);

			preparedStmt.execute();
			con.close();
			output = "success";
		} catch (Exception e) {
			output = "error";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// GetAll inserted Appointments data
	public String readAppointment() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// displaying HTML table
			output = "<table border=\"1\"><tr><th>id</th><th>user</th><th>hospital</th><th>doctor</th><th>date</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String user = rs.getString("patientId");
				String hospital = rs.getString("hospital");
				String doctor = rs.getString("doctor");
				String date = rs.getString("date");

				// Add into the HTML table
//				output += "<tr><td>" + id + "</td>";
				output += "<tr><td><input id=\"hidItemIDUpdate\"name=\"hidItemIDUpdate\"type=\"hidden\" value=\"" + id + "\">" + id + "</td>";
				output += "<td>" + user + "</td>";
				output += "<td>" + hospital + "</td>";
				output += "<td>" + doctor + "</td>";
				output += "<td>" + date + "</td>";

				// buttons
//				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
//						+ "<td><form method=\"post\" action=\"Appointment.jsp\">"
//						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Delete\"class=\"btn btn-danger\">"
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='\"" + id + "\"'>";
//						+ "<form method=\"post\" action=\"Appointment.jsp\">"
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\"value=\"" + id + "\">" + "</form></td></tr>"; 
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Update Appointment table
	public String updateAppointment(String id, String userId, String hosId, String docId, String date) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE appointment SET patientId=?,hospital=?,doctor=?,date=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, userId);
			preparedStmt.setString(2, hosId);
			preparedStmt.setString(3, docId);
			preparedStmt.setString(4, date);
			preparedStmt.setInt(5, Integer.parseInt(id));

			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Delete Appointment table
	public String deleteAppointment(String id) {
		String output = "";
		java.sql.Date sqlDate = null;

		//  Get Appointment data by ID
//		try {
//			Connection con1 = DBConnection.connect();
//
//			AppointmentModel appointment = new AppointmentModel();
//
//			String query1 = "select * from appointment where id=?";
//			PreparedStatement preparedStmt1 = con1.prepareStatement(query1);
//			preparedStmt1.setInt(1, Integer.parseInt(id));
//
//			ResultSet rs = preparedStmt1.executeQuery();

//			while (rs.next()) {
//				appointment.setId(Integer.parseInt(id));
//				appointment.setPatientId(rs.getString("patientId").toString());
//				appointment.setHospital(rs.getString("hospital"));
//				appointment.setDoctor(rs.getString("doctor"));
//				java.util.Date utilDate = rs.getDate("date");
//				sqlDate = rs.getDate("date");
//				appointment.setDate(utilDate);

				// Insert method which insert data to rm_appointment table
//				try {
//					Connection con2 = DBConnection.connect();
//
//					String query2 = " insert into rm_appointment(`id`,`patientId`,`hospital`,`doctor`,`date`)"
//							+ " values (?, ?, ?, ?, ?)";
//					PreparedStatement preparedStmt2 = con2.prepareStatement(query2);
//
//					preparedStmt2.setInt(1, Integer.parseInt(id));
//					preparedStmt2.setString(2, appointment.getPatientId());
//					preparedStmt2.setString(3, appointment.getHospital());
//					preparedStmt2.setString(4, appointment.getDoctor());
//					preparedStmt2.setDate(5, sqlDate);
//
//					preparedStmt2.execute();

					//Delete Appointment from appointment table
					try {
						Connection con = DBConnection.connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}
						// create a prepared statement
						String query = "delete from appointment where id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(id.substring(1, id.length()-1)));
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Deleted successfully";
					} catch (Exception e) {
						e.printStackTrace();
						output = "Error while deleting the item.";
						System.err.println(e.getMessage());
					}
//					con2.close();
//				} catch (Exception e) {
//					output = "Error while inserting";
//					System.err.println(e.getMessage());
//				}
//			con1.close();
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
		return output;
	}

	// Get Appointment data by ID
	public AppointmentModel getAppointmentById(String aId) {

		AppointmentModel appointment = new AppointmentModel();

		try {
			Connection con = DBConnection.connect();

			String query = "select * from appointment where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(aId));

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				appointment.setId(Integer.parseInt(aId));
				appointment.setPatientId(rs.getString("patientId").toString());
				appointment.setHospital(rs.getString("hospital"));
				appointment.setDoctor(rs.getString("doctor"));
				java.util.Date utilDate = rs.getDate("date");
				appointment.setDate(utilDate);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return appointment;
	}

	// Get Appointment data by Hospital
	public AppointmentModel getAppointmentByHos(String hospital) {

		AppointmentModel appointment = new AppointmentModel();

		try {
			Connection con = DBConnection.connect();

			String query = "select * from appointment where hospital=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, hospital);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				appointment.setId(Integer.parseInt(rs.getString("id")));
				appointment.setPatientId(rs.getString("patientId"));
				appointment.setHospital(hospital);
				appointment.setDoctor(rs.getString("doctor"));
				java.util.Date utilDate = rs.getDate("date");
				appointment.setDate(utilDate);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return appointment;
	}

	// Get Appointment data by Doctor
	public AppointmentModel getAppointmentByDoc(String doctor) {

		AppointmentModel appointment = new AppointmentModel();

		try {
			Connection con = DBConnection.connect();

			String query = "select * from appointment where doctor=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, doctor);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				appointment.setId(Integer.parseInt(rs.getString("id")));
				appointment.setPatientId(rs.getString("patientId"));
				appointment.setHospital(rs.getString("hospital"));
				appointment.setDoctor(doctor);
				java.util.Date utilDate = rs.getDate("date");
				appointment.setDate(utilDate);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return appointment;
	}
	
	// Get Appointment data by Date
	public AppointmentModel getAppointmentByDate(Date date) {

		AppointmentModel appointment = new AppointmentModel();

		try {
			Connection con = DBConnection.connect();

			String query = "select * from appointment where date=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setDate(1, date);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				appointment.setId(Integer.parseInt(rs.getString("id")));
				appointment.setPatientId(rs.getString("patientId"));
				appointment.setHospital(rs.getString("hospital"));
				appointment.setDoctor(rs.getString("doctor"));
				java.util.Date utilDate = date;
				appointment.setDate(utilDate);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return appointment;
	}

	
}
