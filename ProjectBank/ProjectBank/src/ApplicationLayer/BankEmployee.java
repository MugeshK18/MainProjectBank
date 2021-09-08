package ApplicationLayer;

import DataLayer.*;

public class BankEmployee implements IBankEmployee{
	
	IEmployee employee=null;
	public BankEmployee(EmployeeData obj) {
		employee=new Employee(obj);
	}
	
	public void getEmpDetails(int empId) {
		employee.getEmpDetails(empId);
	}
	
}
