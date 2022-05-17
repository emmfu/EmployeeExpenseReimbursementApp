package com.revature.dao;

import java.util.List;

import com.revature.models.Request;

public interface RequestDAO {
	public void insertwithoutCasting(Request request);
	public int insert(Request request);
	public boolean update(Request request);
	public boolean delete(Request request);
	public List<Request> selectAll();
	public Request selectById(int id);
	public List<Request> selectByEmployeeId(int employeeId);
	public List<Request> selectByManagerId(int managerId);
	public List<Request> selectByReimbursmentStatus(String reimbursmentStatus);
	public List<Request> selectByResolvedManager(int managerId);
	public List<Request> selectByStatus(String status);
	public List<Request> selectByemployeeIdStatus(int employeeId, String status);


}
