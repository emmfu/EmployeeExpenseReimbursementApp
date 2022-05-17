package com.revature.dao;

import java.util.List;

import com.revature.models.Manager;

public interface ManagerDAO {
	
	public int insert(Manager manager);
	
	public List<Manager>selectAll();
	
	public Manager selectById(int id);
	
	public boolean update(Manager manager);
	
	public boolean delete(Manager manager);

	public Manager selectByUName(String uName);
}
