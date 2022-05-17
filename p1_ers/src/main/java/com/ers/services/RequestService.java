package com.revature.services;

import java.util.List;

import com.revature.models.Request;

public interface RequestService {

	public int register(Request request);

	public Request findRequestById(int id);

	public List<Request> findRequestByEmployeeId(int employeeId);

	public List<Request> findRequestByManagerId(int employeeId);

	public List<Request> finAllRequests();

	public boolean editRequest(Request request);

	public boolean deleteRequest(Request request);

	public List<Request> findRequestByResolvedManager(int managerId);

	public List<Request> findRequestByStatus(String status);

	public List<Request> findRequestByEmployeeIdStatus(int employeeId, String status);
}
