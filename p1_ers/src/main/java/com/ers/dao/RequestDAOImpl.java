package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.util.HibernateUtil;

public class RequestDAOImpl implements RequestDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public void insertwithoutCasting(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insert(Request request) {
		log.info("Adding Request to database. User info: " + request);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(request);
		tx.commit();
		log.info("Insert successful! New Request id is: " + pk);
		return pk;
	}

	@Override
	public List<Request> selectAll() {
		log.info("Retrieving all Requests from database....");
		Session ses = HibernateUtil.getSession();
		List<Request> requestList = ses.createQuery("from Request", Request.class).list();
		log.info("Request list retrieved! Size: " + requestList.size());
		return requestList;
	}

	@Override
	public Request selectById(int id) {
		log.info("Searching Request by id: " + id);
		Session ses = HibernateUtil.getSession();
		Request request = ses.get(Request.class, id);
		log.info("Search complete! Found Request by id: " + id);
		return request;
	}

	@Override
	public List<Request> selectByEmployeeId(int employeeId) {
		log.info("Searching Request by Empl. Id: " + employeeId);
		Session ses = HibernateUtil.getSession();
		List<Request> requestListByEmpId = ses
				.createQuery("from Request where employeeId = " + employeeId, Request.class).list();
		log.info("Retrieved Request list by Empl. Id: " + employeeId);
		return requestListByEmpId;
		
		
	}

	@Override
	public List<Request> selectByManagerId(int managerId) {
		log.info("Searching Request by Manager Id: " + managerId);
		Session ses = HibernateUtil.getSession();
		List<Request> requestListByEmpId = ses
				.createQuery("from Request where employeeId = " + managerId, Request.class).list();
		log.info("Retrieved Request list by Manager Id: " + managerId);
		return requestListByEmpId;
	}

	@Override
	public List<Request> selectByReimbursmentStatus(String reimbursmentStatus) {
		log.info("Searching Request by Status: " + reimbursmentStatus);
		Session ses = HibernateUtil.getSession();
		List<Request> requestListByStatus= ses
				.createQuery("from Request where reimbursementStatus = " + reimbursmentStatus, Request.class)
				.list();
		log.info("Retrieved Request list by Status: " + reimbursmentStatus);
		return requestListByStatus;
	}

	@Override
	public boolean update(Request request) {
		log.info("Updating Request.  Request info: " + request);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(request);
		tx.commit();
		log.info("Request update complete: " + request);
		return true;
	}

	@Override
	public boolean delete(Request request) {
		log.info("Deleting Request, Request info: " + request);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(request);
		tx.commit();
		log.info("Request deletion complete: ");
		return true;
	}

	@Override
	public List<Request> selectByResolvedManager(int managerId) {
		log.info("Searching Request by resloved manager: " + managerId);
		Session ses = HibernateUtil.getSession();
		List<Request> requestByResolvedManager = ses
				.createQuery("FROM Request WHERE reimbursementStatus NOT LIKE 'Pending' and managerId = " + managerId, Request.class)
				.list();
		log.info("Retreived Request list by resolved manager: " + managerId);
		return requestByResolvedManager;
	}

	@Override
	public List<Request> selectByStatus(String status) {
		log.info("Searching Request by status: " + status);
		Session ses = HibernateUtil.getSession();
		List<Request> requestByStatus = ses
				.createQuery("FROM Request WHERE reimbursementStatus like = " + status, Request.class)
				.list();
		log.info("Retrieved Request list by status: " + status);
		return requestByStatus;
	}

	@Override
	public List<Request> selectByemployeeIdStatus(int employeeId, String status) {
		log.info("Searching Requests by employeeId and status: " + employeeId + " and " + status + ".");
		Session ses = HibernateUtil.getSession();
		List<Request> requestByEmployeeIdStatus = ses
				.createQuery("FROM Request WHERE employeeId = " + employeeId + " and reimbursementStatus = " + status, Request.class)
				.list();
		log.info("Retrieved Request list by imployeeId and status: " + employeeId + " and " + status);
		return requestByEmployeeIdStatus;
	}

}


















































