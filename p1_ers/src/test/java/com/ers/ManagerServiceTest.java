package com.ers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ers.dao.ManagerDAOImpl;
import com.ers.models.Manager;
import com.ers.services.ManagerServiceImpl;
import junit.framework.TestCase;

public class ManagerServiceTest extends TestCase {
	private ManagerDAOImpl mockdao;
	private ManagerServiceImpl manv;
	private Manager man1, man2;
	List<Manager> dummyManagerDb;
	
	@Before
	public void setUp() {
		//1. mock the dependency classes
		mockdao = Mockito.mock(ManagerDAOImpl.class);
		
		//2. inject service with mocked classes
		manv = new ManagerServiceImpl(mockdao);
		
		//Dummy DB =(id, username, password, firstname, lastname, email)
		man1 = new Manager(1, "WSmith", "Password", "Will", "Smith,", "WSmith@gmail.com" );
		man2 = new Manager(2, "CRock", "password", "Chris", "Rock,", "CRock@gmail.com" );
		
		dummyManagerDb =  new ArrayList<Manager>();
		dummyManagerDb.add(man1);
		dummyManagerDb.add(man2);
	}
	
	//happy path scenario
	@Test
	public void testLogin_success() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyManagerDb);
		
		// expected, actual
		assertEquals(man2, manv.login("CRock", "password"));
	}
	
	//
	@Test
	public void testLoginFail_returnNull() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyManagerDb);
		
		assertNull(manv.login("", ""));
	}
	
	@Test
	public void testLoginEmpty_returnNull() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyManagerDb);
		
		//no credentials entered
		assertNull(manv.login("", ""));
	}
	@Test
	public void testRegister_returnsEmployee() {
		//arrange
		Manager man3 = new Manager(3, "test", "test", "test", "test", "test");
		
		when(mockdao.insert(man3)).thenReturn(1);
		
		//act, assert
		assertEquals(1, manv.register(man3));
	}
	
	@Test
	public void testRegisterNullManager() {
		Manager man3 = new Manager(3, "", "", "", "", "");
		
		when(mockdao.insert(man3)).thenReturn(1);
		
		assertEquals(1, manv.register(man3));
	}
}
