package com.ers.models;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="requests")
public class Request {
	
	@Id
	@Column(name="request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	
	@Column(name="amount", unique=true, nullable=false)
	private double amount;
	
	@Column(name="submission_date", unique=true, nullable=false)
	private LocalDate submissionDate;
	
	@Column(name="reimbursement_status", unique=true, nullable=false)
	private String reimbursmentStatus;
	
	@Column(name="description", unique=true, nullable=false)
	private String description;
	
	@Column(name="category", unique=true, nullable=false)
	private String category;
	
	@OneToOne(targetEntity = Employee.class)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
	private int employeeId;
	
	@OneToOne(targetEntity = Manager.class)
	@JoinColumn(name = "manager_id", referencedColumnName = "manager_id" )
	private int managerId;
	// private picture;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Request(double amount, LocalDate submissionDate, String reimbursmentStatus, String description,
			String category, int employeeId, int managerId) {
		super();
		this.amount = amount;
		this.submissionDate = submissionDate;
		this.reimbursmentStatus = reimbursmentStatus;
		this.description = description;
		this.category = category;
		this.employeeId = employeeId;
		this.managerId = managerId;
	}
	public Request(int requestId, double amount, LocalDate submissionDate, String reimbursmentStatus,
			String description, String category, int employeeId, int managerId) {
		super();
		this.requestId = requestId;
		this.amount = amount;
		this.submissionDate = submissionDate;
		this.reimbursmentStatus = reimbursmentStatus;
		this.description = description;
		this.category = category;
		this.employeeId = employeeId;
		this.managerId = managerId;
	}
	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", amount=" + amount + ", submissionDate=" + submissionDate
				+ ", reimbursmentStatus=" + reimbursmentStatus + ", description=" + description + ", category="
				+ category + ", employeeId=" + employeeId + ", managerId=" + managerId + "]";
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}
	public String getReimbursmentStatus() {
		return reimbursmentStatus;
	}
	public void setReimbursmentStatus(String reimbursmentStatus) {
		this.reimbursmentStatus = reimbursmentStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, category, description, employeeId, managerId, reimbursmentStatus, requestId,
				submissionDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& employeeId == other.employeeId && managerId == other.managerId
				&& Objects.equals(reimbursmentStatus, other.reimbursmentStatus) && requestId == other.requestId
				&& Objects.equals(submissionDate, other.submissionDate);
	}
	
	

}
