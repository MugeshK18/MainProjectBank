package ApplicationLayer;
import DataLayer.*;

public class LoginCheck implements ILoginCheck
{
	IOperationOnDB Application_Login=OperationOnDB.get_instance();
	public boolean cust_loginCheck(String loginId,String password)
	{
		return Application_Login.customer_Login_Credentials(loginId,password);
	}
	public boolean emp_loginCheck(String loginId,String password)
	{
		return Application_Login.emp_Login_Credentials(loginId, password);
	}
	public int getCustAccNo() {
		return Application_Login.getCustAccNo();
	}
	public int getEmpId() {
		return Application_Login.getEmpId();
	}
	public boolean cust_loginCheck(String userName) {
		return Application_Login.customer_Login_Credentials(userName);
	}
	public Accounts getAccount(int accNo) {
		
		IOperationOnDB account=OperationOnDB.get_instance();
		
		int accountNo=account.getAccountType(accNo);
		Accounts obj=null;
		switch(accountNo) {
		case 1:
			return obj=new SavingAccount();
		case 2:
			return obj=new CurrentAccount();
		case 3:
			return obj=new FDAccount();
		case 4:
			return obj=new CODAccount();
		default:
			return obj;
		}
	}
}
