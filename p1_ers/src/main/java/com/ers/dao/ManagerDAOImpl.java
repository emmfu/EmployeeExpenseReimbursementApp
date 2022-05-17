package com.ers.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.util.HibernateUtil;

public class ManagerDAOImpl implements ManagerDAO {

	private static Logger log = Logger.getLogger(ManagerDAOImpl.class);

	@Override
	public int insert(Manager manager) {
		log.info("Adding Manager to database. Manager info: " + manager);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(manager);
		tx.commit();
		log.info("Insert successful! New Manager Id is: " + pk);
		return pk;
	}

	@Override
	public List<Manager> selectAll() {
		log.info("Retrieving all Managers from database....");
		Session ses = HibernateUtil.getSession();
		List<Manager> managerList = ses.createQuery("from Manager", Manager.class).list();
		log.info("Manager list retrieved! Size: " + managerList.size());
		return managerList;
	}

	@Override
	public Manager selectById(int id) {
		log.info("Searching for Manager by id: " + id);
		Session ses = HibernateUtil.getSession();
		Manager manager = ses.get(Manager.class, id);
		log.info("Search complete! Found Manager by id: " + id);
		return manager;
	}

	@Override
	public boolean update(Manager manager) {
		log.info("Updating Manager, Manager info: " + manager);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(manager);
		tx.commit();
		log.info("Manager update complete.  ");
		return true;
	}

	@Override
	public boolean delete(Manager manager) {
		log.info("Deleting Manager, Manager info: " + manager);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(manager);
		tx.commit();
		log.info("Manager deletion complete.   ");
		return true;
	}

	@Override
	public Manager selectByUName(String uName) {
		log.info("Searching Manager by uName: " + uName);
		Session ses = HibernateUtil.getSession();
		log.info("Search complete! Found:  Manager by username: " + uName);
		Manager manager = (Manager) ses.createQuery("from Manager where userName = " + uName, Manager.class);
		return manager;
	}

}























































