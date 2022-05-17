package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.models.Manager;

public class ManagerServiceImpl implements ManagerService {

	public ManagerServiceImpl(ManagerDAOImpl dao) {
		super();
		this.mdao = dao;
	}

	private ManagerDAO mdao;
	private static Logger log = Logger.getLogger(ManagerServiceImpl.class);

	@Override
	public Manager login(String username, String password) {
		log.info(
				"In service layer. Loggin in Manager with user name and password: " + username + ", " + password + ".");
		// Streams API
		Optional<Manager> manager = mdao.selectAll().stream()
				.filter(e -> (e.getUserName().equals(username) && e.getPassword().equals(password))).findFirst();
		return manager.isPresent() ? manager.get() : null;
	}

	@Override
	public int register(Manager manager) {
		log.info("In service layer. Register Manager: " + manager + ".");
		return mdao.insert(manager);
	}

	@Override
	public Manager findManagerById(int id) {
		log.info("In service layer. Finding Manager by id: " + id + ".");
		return mdao.selectById(id);
	}

	@Override
	public Manager findManagerByUserName(String userName) {
		log.info("In service layer. Finding Employee by user name: " + userName + ".");
		return mdao.selectByUName(userName);
	}

	@Override
	public List<Manager> findAllManagers() {
		log.info("In service layer. Finding  all Managers.");
		return mdao.selectAll();
	}

	@Override
	public boolean editManager(Manager manager) {
		log.info("In service layer. Edit Manager: " + manager + ".");
		return mdao.update(manager);
	}

	@Override
	public boolean deleteEmployee(Manager manager) {
		log.info("In service layer. Delete Employee: " + manager + ".");
		return mdao.delete(manager);
	}
}
