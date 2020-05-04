package model;


import java.util.Date;

public class AppointmentModel {


	private Integer id;
	private String patientId;
	private String hospital;
	private String doctor;
	private Date date;

	
	public AppointmentModel() {
	
	}

	
	public AppointmentModel(Integer id, String patientId, String hospital, String doctor, Date date) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.hospital = hospital;
		this.doctor = doctor;
		this.date = date;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getHospital() {
		return hospital;
	}


	public void setHospital(String hospital) {
		this.hospital = hospital;
	}


	public String getDoctor() {
		return doctor;
	}


	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", patientId=" + patientId + ", hospital=" + hospital + ", doctor=" + doctor + ", date="
				+ date + "]";
	}
	
	
}
