package com.revature.services;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeService {

	public Employee login(String username, String password);
	
	public int register(Employee employee);
	
	public Employee findEmployeeById(int id);
	
	public Employee findEmployeeByUserName(String userName);
	
	public List<Employee> findAllEmployees();
	
	public boolean editEmployee(Employee employee);
	
	public boolean deleteEmployee(Employee employee);
}
