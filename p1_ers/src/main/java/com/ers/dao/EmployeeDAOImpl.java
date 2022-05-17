package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public int insert(Employee employee) {
		log.info("Adding Employee to database. User info: " + employee);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(employee);
		tx.commit();
		log.info("Insert successful! New employee id is: " + pk);
		return pk;
	}

	@Override
	public Employee selectById(int id) {
		log.info("Searching Employee by id: " + id);
		Session ses = HibernateUtil.getSession();
		Employee employee = ses.get(Employee.class, id);
		log.info("Search complete! Found Employee by id: " + id);
		return employee;
	}

	@Override
	public Employee selectByUName(String uName) {
		log.info("Searching Employee by uName: " + uName);
		Session ses = HibernateUtil.getSession();
		log.info("Search complete! Found:  Employee by id: " + uName);
		Employee employee = (Employee) ses.createQuery("from Employee where userName = " + uName, Employee.class);
		return employee;
	}

	@Override
	public List<Employee> selectAll() {
		log.info("Retrieving all users from database....");
		Session ses = HibernateUtil.getSession();
		List<Employee> employeeList = ses.createQuery("from Employee", Employee.class).list();
		log.info("Employee list retrieved! Size: " + employeeList.size());
		return employeeList;
	}

	@Override
	public boolean update(Employee employee) {
		log.info("Updating Employer, Employee info: " + employee);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(employee);
		tx.commit();
		log.info("Employer update complete");
		return true;
	}

	@Override
	public boolean delete(Employee employee) {
		log.info("Deleting Employer, Employee info: " + employee);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(employee);
		tx.commit();
		log.info("Employer deletion complete");
		return true;
	}

}






















































