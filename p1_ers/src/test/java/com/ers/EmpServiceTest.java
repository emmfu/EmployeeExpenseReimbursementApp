package com.ers;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ers.dao.EmployeeDAOImpl;
import com.ers.models.Employee;
import com.ers.services.EmployeeServiceImpl;

import junit.framework.TestCase;

public class EmpServiceTest extends TestCase {

	private EmployeeDAOImpl mockdao;
	private EmployeeServiceImpl emplv;
	private Employee empl1, empl2;
	List<Employee> dummyEmpDb;
	
	@Before
	public void setUp() {
		//1. mock the dependency classes
		mockdao = Mockito.mock(EmployeeDAOImpl.class);
		
		//2. inject service with mocked classes
		emplv = new EmployeeServiceImpl(mockdao);
		
		//Dummy DB =(id, username, password, firstname, lastname, email)
		empl1 = new Employee(1, "WSmith", "Password", "Will", "Smith,", "WSmith@gmail.com" );
		empl2 = new Employee(2, "CRock", "password", "Chris", "Rock,", "CRock@gmail.com" );
	}
	
	//happy path scenario
	@Test
	public void testLogin_success() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyEmpDb);
		
		// expected, actual
		assertEquals(empl2, emplv.login("CRock", "password"));
	}
	
	//
	@Test
	public void testLoginFail_returnNull() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyEmpDb);
		
		assertEquals(empl2, emplv.login("Ramen", "Sushi"));
	}
	
	@Test
	public void testLoginEmpty_returnNull() throws Exception{
		when(mockdao.selectAll()).thenReturn(dummyEmpDb);
		
		//no credentials entered
		assertEquals(empl2, emplv.login("", ""));
	}
	@Test
	public void testRegister_returnsEmployee() {
		//arrange
		Employee empl3 = new Employee(3, "test", "test", "test", "test", "test");
		
		when(mockdao.insert(empl3)).thenReturn(1);
		
		assertEquals(1, emplv.register(empl3));
	}
}
