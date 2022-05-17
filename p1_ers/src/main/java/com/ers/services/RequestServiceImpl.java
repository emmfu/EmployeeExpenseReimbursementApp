package com.ers.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.ers.dao.RequestDAO;
import com.ers.dao.RequestDAOImpl;
import com.ers.models.Employee;
import com.ers.models.Request;

public class RequestServiceImpl implements RequestService {
	
	public RequestServiceImpl(RequestDAOImpl dao) {
		super();
		this.rdao = dao;
	}
	
	private RequestDAO rdao;
	private static Logger log = Logger.getLogger(RequestServiceImpl.class);

	@Override
	public int register(Request request) {
		log.info("In service layer. Register request: " + request + "." );
		return rdao.insert(request);
	}
	
	@Override
	public Request findRequestById(int id) {
		log.info("In service layer. Finding Request by id: " + id + "." );
		return rdao.selectById(id);
	}

	@Override
	public List<Request>  findRequestByEmployeeId(int employeeId) {
		log.info("In service layer. Finding Request by employee id: " + employeeId + "." );
		return rdao.selectByEmployeeId(employeeId);
	}

	@Override
	public List<Request>  findRequestByManagerId(int managerId) {
		log.info("In service layer. Finding Request by manger id: " + managerId + "." );
		return rdao.selectByManagerId(managerId);
	}

	@Override
	public List<Request> finAllRequests() {
		log.info("In service layer. Finding  all Requests." );
		return rdao.selectAll();
	}

	@Override
	public boolean editRequest(Request request) {
		log.info("In service layer. Edit Employee: " + request + "." );
		return rdao.update(request);
	}

	@Override
	public boolean deleteRequest(Request request) {
		log.info("In service layer. Delete Employee: " + request + "." );
		return rdao.delete(request);
	}

	@Override
	public List<Request> findRequestByResolvedManager(int managerId) {
		log.info("In service layer. Requests by reseloved manager: " + managerId + ".");
		return rdao.selectByResolvedManager(managerId);
	}

	@Override
	public List<Request> findRequestByStatus(String status) {
		log.info("In service layer. Requests by status: " + status + ".");
		return rdao.selectByStatus(status);
	}

	@Override
	public List<Request> findRequestByEmployeeIdStatus(int employeeId, String status) {
		log.info("In service layer. Requests by employeeId status " + employeeId + " and " + status + ".");
		return rdao.selectByemployeeIdStatus(employeeId, status);
	}

}
