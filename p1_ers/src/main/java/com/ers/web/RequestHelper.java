package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.remote.rmi.RMIConnectorServer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.ManagerDAOImpl;
import com.revature.dao.RequestDAOImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Request;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.JwtEmployeeService;
import com.revature.services.JwtManagerService;
import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImpl;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImpl;

public class RequestHelper {

	private static EmployeeService eserv = new EmployeeServiceImpl(new EmployeeDAOImpl());
	private static ManagerService mserv = new ManagerServiceImpl(new ManagerDAOImpl());
	private static RequestService rserv = new RequestServiceImpl(new RequestDAOImpl());
	private static JwtEmployeeService jwtEmployeeService = new JwtEmployeeService();
	private static JwtManagerService jwtManagerService = new JwtManagerService();
	private static ObjectMapper om = new ObjectMapper();
	private static Logger log = Logger.getLogger(RequestHelper.class);

	/****************************
	 * POST METHODS
	 * 
	 * @throws IOException *
	 ****************************/
	// This method will process a post request, so we can't capture parameters from
	// the request like we would in a GET request
	public static void processEmployeeLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}

		String username = values.get(0);
		String password = values.get(1);

		System.out.println("Employee attempted to logi in with username " + username);

		Employee e = eserv.login(username, password);

		if (e != null) {

			String jwt = jwtEmployeeService.createJwt(e);
			res.addHeader("X-Auth-Token", "Bearer " + jwt);

			// grab the session and add the user to the session
			HttpSession session = req.getSession();
			session.setAttribute("employee", e);

			// print the logger in user to the screen
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");

			// convert the object with the object mapper
			out.println(om.writeValueAsString(e));

			// log it
			System.out.println("The employee " + username + " has logged in.");
		} else {
			res.setStatus(204);
		}
	}

	public static void processManagerLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}

		String username = values.get(0);
		String password = values.get(1);

		System.out.println("Manager attempted to logi in with username " + username);

		Manager m = mserv.login(username, password);

		if (m != null) {

			String jwt = jwtManagerService.createJwt(m);
			res.addHeader("X-Auth-Token", "Bearer " + jwt);

			// grab the session and add the user to the session
			HttpSession session = req.getSession();
			session.setAttribute("manager", m);

			// print the logger in user to the screen
			PrintWriter out = res.getWriter();
			res.setContentType("application/json");

			// convert the object with the object mapper
			out.println(om.writeValueAsString(m));

			// log it
			System.out.println("The employee " + username + " has logged in.");
		} else {
			res.setStatus(204);
		}

	}

	public static void processEmployeeRegistration(HttpServletRequest req, HttpServletResponse res) throws IOException {

		log.info("inside the RequestHelper...processEmployeeRegistration...");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Employee attempted to register with information:\n" + body);

		String username = values.get(0);
		String password = values.get(1);
		String firstname = values.get(2);
		String lastname = values.get(3);
		String email = values.get(4);

		Employee e = new Employee(username, password, firstname, lastname, email);

		int targetId = eserv.register(e);

		if (targetId != 0) {
			PrintWriter pw = res.getWriter();
			e.setEmployeeId(targetId);
			log.info("New Employee: " + e);
			String json = om.writeValueAsString(e);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			res.setContentType("application/json");
			res.setStatus(200); // successful
			log.info("User has successfully been created.");
		} else {
			res.setContentType("application/json");
			res.setStatus(204); // this means that the connection was successful but no user created
		}
		log.info("Leaving RequestHelper...processEmployeeRegistration...");
	}

	public static void processRequestSubmission(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("inside the RequestHelper...process Request Submission 	...");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Employee submited request with information :\n" + body);

		double amount = Double.parseDouble(values.get(0));
		LocalDate submissionDate = LocalDate.now();
		String reimbursmentSatus = values.get(2);
		String description = values.get(3);
		String category = values.get(4);
		int employeeId = Integer.parseInt(values.get(5));
		int managerId = Integer.parseInt(values.get(6));

		Request r = new Request(amount, submissionDate, reimbursmentSatus, description, category, employeeId,
				managerId);

		int targetId = rserv.register(r);

		if (targetId != 0) {
			PrintWriter pw = res.getWriter();
			r.setRequestId(targetId);
			log.info("New submitted request: " + r);
			String json = om.writeValueAsString(r);
			pw.println(json);
			System.out.println("JSON:\n " + json);

			res.setContentType("application/json");
			res.setStatus(200);
			log.info("Request has been successfully submited.");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
		log.info("Leaving RequestHelper...processRequestSubmission");

	}

	public static void processManagerRegistration(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...processManagerRegistration...");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Manager attempted to register with information: \n " + body);

		String username = values.get(0);
		String password = values.get(1);
		String firstname = values.get(2);
		String lastname = values.get(3);
		String email = values.get(4);

		Manager m = new Manager(username, password, firstname, lastname, email);

		int targetId = mserv.register(m);

		if (targetId != 0) {
			PrintWriter pw = res.getWriter();
			m.setManagerId(targetId);
			log.info("New Manager: " + m);
			;
			String json = om.writeValueAsString(m);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			res.setContentType("application/json");
			res.setStatus(200);
			log.info("User has successfully been created.");
		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
		log.info("Leaving RequestHelper...processManagerRegistration");
	}

	public static void processError(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// if something goes wrong, redirect the user to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, res);
		/*
		 * The forward() method does NOT produce a new request, it just forwards it to a
		 * new resource, and we also maintain the URL
		 */

	}

	public static void processEmployeeBySearchParam(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		log.info("Inside RequestHelper...search employee by params: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Employee search with information: \n" + body);

		if (body.startsWith("id")) {
			log.info("Employee search by id: \n" + body);
			res.setContentType("application/json");
			int id = Integer.parseInt(values.get(0));
			Employee emplyee = eserv.findEmployeeById(id);

			String json = om.writeValueAsString(emplyee);

			PrintWriter out = res.getWriter();
			out.println(json);
		}

	}

	public static void processAllEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search all employees");
		res.setContentType("application/json");
		List<Employee> allEmployees = eserv.findAllEmployees();
		String json = om.writeValueAsString(allEmployees);
		PrintWriter out = res.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper...process all employees...");
	}

	public static void processManagerBySearchParam(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search managers by params: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Manager search with information: \n" + body);

		if (body.startsWith("id")) {
			log.info("Manager search by id: \n" + body);
			res.setContentType("application/json");
			int id = Integer.parseInt(values.get(0));
			Manager manager = mserv.findManagerById(id);

			String json = om.writeValueAsString(manager);

			PrintWriter out = res.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper...manager by id: ");
		}
	}

	public static void processAllRequests(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search all requests");
		res.setContentType("application/json");
		List<Request> allRequests = rserv.finAllRequests();
		String json = om.writeValueAsString(allRequests);
		PrintWriter out = res.getWriter();
		out.println(json);
		log.info("Learving RequestHelper...processAllRequests");

	}

	public static void processEmployeeUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...processEmployeeUpdate...");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Employee update attempted with information: \n " + body);
		int id = Integer.parseInt(values.get(0));
		String username = values.get(1);
		String password = values.get(2);
		String firstname = values.get(3);
		String lastname = values.get(4);
		String email = values.get(5);

		Employee tempEmployee = new Employee();
		tempEmployee.setEmployeeId(id);
		tempEmployee.setUserName(username);
		tempEmployee.setFirstName(firstname);
		tempEmployee.setLastName(lastname);
		tempEmployee.setPassword(password);
		tempEmployee.setEmail(email);

		boolean isUpdated = eserv.editEmployee(tempEmployee);

		if (isUpdated) {
			PrintWriter pw = res.getWriter();
			log.info("Edit successful! New Employee info: " + tempEmployee);
			String json = om.writeValueAsString(tempEmployee);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			res.setContentType("application/json");
			res.setStatus(200);
			log.info("Employee has been successfully been edited.");
		} else {
			res.setContentType("application/json");
			res.setStatus(400);
		}
		log.info("leaving RequestHelper...");
	}

	public static void processRequestUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...processRequestUpdate");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Employee update attempted with information: \n  " + body);
		int id = Integer.parseInt(values.get(0));
		double amount = Double.parseDouble(values.get(1));
		LocalDate submissionDate = LocalDate.parse(values.get(2));
		String reimbursementStatus = values.get(3);
		String description = values.get(4);
		String category = values.get(5);
		int employeeId = Integer.parseInt(values.get(6));
		int managerId = Integer.parseInt(values.get(7));

		Request tempRequest = new Request();
		tempRequest.setRequestId(id);
		tempRequest.setAmount(amount);
		tempRequest.setSubmissionDate(submissionDate);
		tempRequest.setReimbursmentStatus(reimbursementStatus);
		tempRequest.setDescription(description);
		tempRequest.setCategory(category);
		tempRequest.setEmployeeId(employeeId);
		tempRequest.setManagerId(managerId);

		boolean isUpdated = rserv.editRequest(tempRequest);

		if (isUpdated) {
			PrintWriter pw = res.getWriter();
			log.info("Edit successful! New request info: " + tempRequest);
			String json = om.writeValueAsString(tempRequest);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			res.setContentType("application/json");
			res.setStatus(200);
			log.info("Request has been successfully been edited.");
		} else {
			res.setContentType("application/json");
			res.setStatus(400);
		}
		log.info("Leaving RequestHelper...");

	}

	public static void processRequestByEmployeeId(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search request by emploee id: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Attempted request search by employee id:\n " + body);

		res.setContentType("application/json");
		int employeeId = Integer.parseInt(values.get(5));
		List<Request> requestsByEmployeeId = rserv.findRequestByEmployeeId(employeeId);
		
		String json = om.writeValueAsString(requestsByEmployeeId);
		PrintWriter out = res.getWriter();
		out.println(json);
		log.info("Leaving processRequestByEmployeeId...");
	}

	public static void processRequestByResolvedManager(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search request by resolved manager: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Attempted request search by manager id:\n " + body);

		res.setContentType("application/json");
		int managerId = Integer.parseInt(values.get(6));
		List<Request> requestsByResolvedManager = rserv.findRequestByResolvedManager(managerId);
		
		String json = om.writeValueAsString(requestsByResolvedManager);
		PrintWriter out = res.getWriter();
		out.println(json);
		log.info("Leaving processRequestByResolvedManager...");
		
	}

	public static void processRequestByStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search request by emploee id: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Attempted request search by status:\n " + body);

		res.setContentType("application/json");
		String status = values.get(2);
		List<Request> requestByStatus = rserv.findRequestByStatus(status);
		
		String json = om.writeValueAsString(requestByStatus);
		PrintWriter out = res.getWriter();
		out.println(json);
		log.info("Leaving requestByStatus...");
	}

	public static void processRequestByEmployeeIdStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		log.info("Inside RequestHelper...search request by emploee id: ");
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}

		String body = sb.toString();
		String[] sepByAmp = body.split("&");

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		log.info("Attempted request search by status:\n " + body);

		res.setContentType("application/json");
		String status = values.get(0);
		int employeeId = Integer.parseInt(values.get(1));
		List<Request> requestByEmployeeIdStatus = rserv.findRequestByEmployeeIdStatus(employeeId, status);
		
		String json = om.writeValueAsString(requestByEmployeeIdStatus);
		PrintWriter out = res.getWriter();
		out.println(json);
		log.info("Leaving requestByEmployeeIdStatus");
	}

}

// stuff
