package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	public EmployeeServiceImpl(EmployeeDAOImpl dao) {
		super();
		this.edao = dao;
	}

	private EmployeeDAO edao;
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	

	@Override
	public Employee login(String username, String password) {
		log.info("In service layer. Loggin in Employee with user name and password: " + username + ", " + password + "." );
		//Streams API
		Optional<Employee> employee = edao.selectAll()
				.stream()
				.filter(e -> (e.getUserName().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		return employee.isPresent() ? employee.get() : null ;
	}

	@Override
	public int register(Employee employee) {
		log.info("In service layer. Register Employee: " + employee + "." );
		return edao.insert(employee);
	}

	@Override
	public Employee findEmployeeById(int id) {
		log.info("In service layer. Finding Employee by id: " + id + "." );
		return edao.selectById(id);
	}

	@Override
	public Employee findEmployeeByUserName(String userName) {
		log.info("In service layer. Finding Employee by user name: " + userName+ "." );
		return edao.selectByUName(userName);
	}

	@Override
	public List<Employee> findAllEmployees() {
		log.info("In service layer. Finding  all Employees." );
		return edao.selectAll();
	}

	@Override
	public boolean editEmployee(Employee employee) {
		log.info("In service layer. Edit Employee: " + employee + "." );
		return edao.update(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		log.info("In service layer. Delete Employee: " + employee + "." );
		return edao.delete(employee);
	}

	
	
}
