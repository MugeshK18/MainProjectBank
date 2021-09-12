package ApplicationLayer;
import DataLayer.*;

public class LoginCheck implements ILoginCheck
{
	Bank Application_Login=new Bank();
	public boolean cust_loginCheck(String loginId,String password)
	{
		return Application_Login.custamore_Login_Credentials(loginId,password);
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
		return Application_Login.custamore_Login_Credentials(userName);
	}
}
