package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = -4615572874221521483L;
	private static Logger log = Logger.getLogger(FrontController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final String URI = req.getRequestURI().replace("/p1_ers/", "");
		
		switch(URI) {
		case "employees/login":
			log.info("Loggin in employee...");
			RequestHelper.processEmployeeLogin(req, res);
			break;
		case "employees/register":
			log.info("Registering new user...");
			RequestHelper.processEmployeeRegistration(req, res);
			break;
		case "employees/employee/requestsubmission":
			log.info("Submit new request...");
			RequestHelper.processRequestSubmission(req, res);
		case "managers/login":
			log.info("Loggin in manager...");
			RequestHelper.processManagerLogin(req, res);
			break;
		case "managers/register":
			log.info("Registering new manager...");
			RequestHelper.processManagerRegistration(req, res);
			break;
		default:
			log.info("Showing error message...");
			RequestHelper.processError(req, res);
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/p1_ers/", "");
		log.info("Employee URI is: " + URI);
		
		switch(URI) {
		case "employees/employee":
			log.info("Searching employees by params: " + URI);
			RequestHelper.processEmployeeBySearchParam(req, res);
			break;
		case "employees/employee/myrequests":
			log.info("Searching requests by employee id: " + URI);
			RequestHelper.processRequestByEmployeeId(req, res);
			break;
		case "employees/employee/myrequests/status":
			log.info("Searching requests by employee id and status: " + URI);
			RequestHelper.processRequestByEmployeeIdStatus(req, res);
		case "managers/manager":
			log.info("Searching for manager by params: " + URI);
			RequestHelper.processManagerBySearchParam(req, res);
			break;
		case "managers/manager/requests":
			log.info("Searching for all requests: " + URI);
			RequestHelper.processAllRequests(req, res);
			break;
		case "managers/manager/requestbyemployee":
			log.info("Searching requests by employee id: " + URI);
			RequestHelper.processRequestByEmployeeId(req, res);
			break;
		case "managers/manager/requestbyresolvedmanager":
			log.info("Searching by resolved and manager" + URI);
			RequestHelper.processRequestByResolvedManager(req, res);
			break;
		case "managers/manager/requestbystatus":
			log.info("Searching requests by params: " + URI);
			RequestHelper.processRequestByStatus(req, res);
			break;
		case "managers/manager/employees":
			log.info("Searching for all employees: " + URI);
			RequestHelper.processAllEmployees(req, res);
			break;
		default:
			log.info("Showing error message...");
			RequestHelper.processError(req, res);
			break;			
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final String URI = req.getRequestURI().replace("/p1_ers/", "");
		
		switch(URI) {
		case "employees/employee/update":
			log.info("updating employee...");
			RequestHelper.processEmployeeUpdate(req, res);
			break;
		case "managers/manager/requestupdate":
			log.info("Updating request...");
			RequestHelper.processRequestUpdate(req, res);
			break;
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, res);
			break;
		}
	}
	
	
}
