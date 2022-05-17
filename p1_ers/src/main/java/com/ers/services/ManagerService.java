package com.revature.services;

import java.util.List;

import com.revature.models.Manager;

public interface ManagerService {
	
	public Manager login(String username, String password);
	
	public int register(Manager employee);
	
	public Manager findManagerById(int id);
	
	public Manager findManagerByUserName(String userName);
	
	public List<Manager> findAllManagers();
	
	public boolean editManager(Manager employee);
	
	public boolean deleteEmployee(Manager employee);

}
