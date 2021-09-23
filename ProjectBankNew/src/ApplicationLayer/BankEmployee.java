package ApplicationLayer;

import DataLayer.*;

public class BankEmployee implements IBankEmployee{
	
	IOperationOnDB employee=null;
	public BankEmployee() {
		
	}
	
	public void getEmpDetails(int empId) {
		employee=OperationOnDB.get_instance();
		employee.getEmpDetails(empId);
	}
	public void createEmp() {
		
	}
}
