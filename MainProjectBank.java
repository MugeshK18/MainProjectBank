PK  #�S              META-INF/MANIFEST.MF��  �M��LK-.�K-*��ϳR0�3���� PK��      PK
    �S�A�U�  �     ApplicationLayer/FDAccount.javapackage ApplicationLayer;
import DataLayer.*;

public class FDAccount extends Accounts {
	
	double mainBalance,discountAmount=0;
	static int count=0;
	public FDAccount(IAccountHolder obj) {
		super(obj);
		obj.setAccTypeId(3);
	}
	public void debit(int accNo,double amount)
	{
		System.out.println("Transaction Failed! Cant debit amount in this type of Account");
	}
	public void credit(int accNo,double amount)
	{
		if(count==0)
		{
		mainBalance=getMainBalance(accNo);
		mainBalance=mainBalance+amount;
		changeMainBalance(accNo,mainBalance);
		System.out.println(amount+" :Credited Successfully");
		createCoupon(amount);
		System.out.println("Available Balance is: "+mainBalance);
		count++;
		}
		else if(count>1)
		{
			System.out.println("Transaction Failed! Cant credit amount more than once in this type of Account");
		}
		else
		{
			System.out.println("Transaction Failed! Please visit your nearby branch");
		}
	}
	public void createCoupon(double amount)
	{
		double discountPercentage=6;
		discountAmount=(double)(discountPercentage *amount)/100;
	}
	public void displayCoupon()
	{
		System.out.println("Congratulations!! You have won "+discountAmount+" worth of Coupon at KFC!!");
	}

}
PK
    �SxJ�y    $   ApplicationLayer/CurrentAccount.javapackage ApplicationLayer;
import DataLayer.*;

public class CurrentAccount extends Accounts  {
	
	double mainBalance,discountAmount=0;
	public CurrentAccount(IAccountHolder obj) {
		super(obj);
		obj.setAccTypeId(2);
	}
	public void debit(int accNo,double amount)
	{
		mainBalance=getMainBalance(accNo);
		if(amount<mainBalance)
		{
			mainBalance=mainBalance-amount;
			changeMainBalance(accNo,mainBalance);
			System.out.println(amount+" :Debited Successfully");
			System.out.println("Available Balance is: "+mainBalance);	
		}
		else
		{
			System.out.println("No suffcient balance");
			System.out.println("Available Balance is: "+mainBalance);
		}
		
	}
	public void credit(int accNo,double amount)
	{
		mainBalance=getMainBalance(accNo);
		mainBalance=mainBalance+amount;
		changeMainBalance(accNo,mainBalance);
		System.out.println(amount+" :Credited Successfully");
		createCoupon(amount);
		System.out.println("Available Balance is: "+mainBalance);
	}
	public void createCoupon(double amount)
	{
		double discountPercentage=3;
		discountAmount=(double)(discountPercentage *amount)/100;
	}
	public void displayCoupon()
	{
		System.out.println("Congratulations!! You have won "+discountAmount+" worth of Coupon at KFC!!");
	}

}
PK
    P�S!      !   ApplicationLayer/ILoginCheck.javapackage ApplicationLayer;

public interface ILoginCheck {
	public boolean loginCheck(String loginId,String password);

}
PK
    ѮSD��    %   ApplicationLayer/IServiceMethods.javapackage ApplicationLayer;

public interface IServiceMethods {
	
	
    public void createAccount();
    public void updateAccountHolderDetails(String fName,String lName,int age,String gender);
    public void updateAddressDetails(String city,String addressLine);
    public void deleteAccount(int accHolderId);
    public void deleteAccount(int accHolderId,int accTyId);
    public int getAccountNo(int accHolderId,int accTyId);
    public double getMainBalance(int accNo);
    public void changeMainBalance(int accNo,double mainBalance);
    public void showBalance(int accNo);
    
	public void debit(int accNo,double amount);
	public void credit(int accNo,double amount);
	public void createCoupon(double amount);
	public void displayCoupon();
	
}
PK
    "�S�a�  �      ApplicationLayer/CODAccount.javapackage ApplicationLayer;
import DataLayer.*;

public class CODAccount extends Accounts {
	static int count=0;
	double mainBalance,discountAmount=0;
	public CODAccount(IAccountHolder obj) {
		super(obj);
		obj.setAccTypeId(4);
	}
	public void debit(int accNo,double amount)
	{
		System.out.println("Transaction Failed! Cant debit amount from this type of Account");
	}
	public void credit(int accNo,double amount)
	{
		if(count==0)
		{
		mainBalance=getMainBalance(accNo);
		mainBalance=mainBalance+amount;
		changeMainBalance(accNo,mainBalance);
		System.out.println(amount+" :Credited Successfully");
		createCoupon(amount);
		System.out.println("Available Balance is: "+mainBalance);
		count++;
		}
		else if(count>1)
		{
			System.out.println("Transaction Failed! Cant credit amount more than once in this type of Account");
		}
		else
		{
			System.out.println("Transaction Failed! Please visit your nearby branch");
		}
	}	
	public void createCoupon(double amount)
	{
		double discountPercentage=4;
		discountAmount=(double)(discountPercentage *amount)/100;
	}
	public void displayCoupon()
	{
		System.out.println("Congratulations!! You have won "+discountAmount+" worth of Coupon at KFC!!");
	}

}
PK
    w�S#J         ApplicationLayer/Accounts.javapackage ApplicationLayer;

import DataLayer.*;

public abstract class Accounts implements IServiceMethods {
	
	double mainBalance;
	IOperationOnDB accountHolder=null;
	double discountAmount=0;
	
//	public abstract void debit(int accNo,double amount);
//	public abstract void credit(int accNo,double amount);
	
	public Accounts(IAccountHolder obj) {
			accountHolder=new OperationOnDB(obj);
	}
	public void createAccount()                    //
	{
		accountHolder.createAccount();
	}
	public final void updateAccountHolderDetails(String fName,String lName,int age,String gender)
	{
		accountHolder.updateAccountHolderDetails(fName,lName,age,gender);
	}
	public final void updateAddressDetails(String city,String addressLine)
	{
		accountHolder.updateAddressDetails(city,addressLine);
	}
	public final void deleteAccount(int accHolderId)
	{
		accountHolder.deleteAccount(accHolderId);
	}
	public final void deleteAccount(int accHolderId,int accTyId)
	{
		accountHolder.deleteAccount(accHolderId,accTyId);
	}
	public final int getAccountNo(int accHolderId,int accTyId)
	{
		return accountHolder.getAccountNumber(accHolderId,accTyId);
	}
	public final double getMainBalance(int accNo)
	{
		return accountHolder.getMainBalance(accNo);
	}
	public final void changeMainBalance(int accNo,double mainBalance)
	{
		accountHolder.changeMainBalance(accNo,mainBalance);
	}
	public final void showBalance(int accNo)
	{
		System.out.println("Available Balance is: "+getMainBalance(accNo));
	}
	
}
PK
    \�S�*я    #   ApplicationLayer/SavingAccount.javapackage ApplicationLayer;
import DataLayer.*;

public class SavingAccount extends Accounts {
	double mainBalance,discountAmount=0;
	public SavingAccount(IAccountHolder obj) {
		super(obj);
		obj.setAccTypeId(1);
	}
	
	public void debit(int accNo,double amount)
	{
		mainBalance=getMainBalance(accNo);
		if(amount<mainBalance)
		{
			mainBalance=mainBalance-amount;
			changeMainBalance(accNo,mainBalance);
			System.out.println(amount+" :Debited Successfully");
			System.out.println("Available Balance is: "+mainBalance);
			
		}
		else
		{
			System.out.println("No suffcient balance");
			System.out.println("Available Balance is: "+mainBalance);
		}
	}
	public void credit(int accNo,double amount)
	{
		mainBalance=getMainBalance(accNo);
		mainBalance=mainBalance+amount;
		changeMainBalance(accNo,mainBalance);
		System.out.println(amount+" :Credited Successfully");
		createCoupon(amount);
		System.out.println("Available Balance is: "+mainBalance);
	}
	public void createCoupon(double amount)
	{
		double discountPercentage=2;
		discountAmount=(double)(discountPercentage *amount)/100;
	}
	public void displayCoupon()
	{
		System.out.println("Congratulations!! You have won "+discountAmount+" worth of Coupon at KFC!!");
	}

}
PK
    ��S���        ApplicationLayer/LoginCheck.javapackage ApplicationLayer;
import DataLayer.*;

public class LoginCheck implements ILoginCheck
{
	IBank Application_Login=new Bank();
	public boolean loginCheck(String loginId,String password)
	{
		return Application_Login.login_Credentials(loginId,password);
	}
	
}
PK
    ��S�D���   �      DataLayer/IBank.javapackage DataLayer;

public interface IBank {
	
	public String getBankname();
	public String getBankaddress();
	public String getBankIFSCCode();
	public boolean login_Credentials(String userId,String password);
}
PK
    ��S���f%  f%     DataLayer/OperationOnDB.javapackage DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import PresentationLayer.MainProjectBank;

public class OperationOnDB implements IOperationOnDB {
	
	IAccountHolder accHolder=null;
	public OperationOnDB(IAccountHolder obj)
	{
		accHolder=obj;
	}
	public final void createAccount()
	{
		
	String insert_sql1="INSERT INTO AccHolderDetails (AccHolderId,FirstName,LastName,Age,Gender) VALUES (?,?,?,?,?)";
	String insert_sql2="INSERT INTO AddressDetails (AccHolderId,City,AddressLine) VALUES (?,?,?)";
	String insert_sql3="INSERT INTO AccountDetails (AccHolderId,AccTyId) VALUES (?,?)";
	String insert_sql4="INSERT INTO Balance (AccNo) VALUES (?)";
	String db_URL=MainProjectBank.db_URL;               //////NEEDS TO CHANGE 
	int gen_accNo;
	Connection con=null;
	PreparedStatement pstmt=null;
	try
	{
		con=DriverManager.getConnection(db_URL);
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(insert_sql1);
	    pstmt.setInt(1, accHolder.getAccHolderId());
		pstmt.setString(2, accHolder.getAccHolderFirstName());
		pstmt.setString(3, accHolder.getAccHolderLastName());
		pstmt.setInt(4, accHolder.getAccHolderAge());
		pstmt.setString(5, accHolder.getAccHolderGender());
		pstmt.executeUpdate();
		con.commit();
		pstmt.close();
		
		pstmt=con.prepareStatement(insert_sql2);
		pstmt.setInt(1, accHolder.getAccHolderId());
		pstmt.setString(2, accHolder.getAccHolderCity());
		pstmt.setString(3, accHolder.getAccHolderAddress());
		pstmt.executeUpdate();
		con.commit();
		pstmt.close();
		
		
		pstmt=con.prepareStatement(insert_sql3);
		pstmt.setInt(1, accHolder.getAccHolderId());
		pstmt.setInt(2, accHolder.getAccTypeId());
		pstmt.executeUpdate();
		con.commit();
		pstmt.close();
		
		gen_accNo=getAccountNumber(accHolder.getAccHolderId(), accHolder.getAccTypeId());
		pstmt=con.prepareStatement(insert_sql4);
		pstmt.setInt(1, gen_accNo);
		pstmt.executeUpdate();
		con.commit();
		pstmt.close();
	    con.setAutoCommit(true);
	    TotalLog.get_instance().total_Acc_Log();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	}
	public final void updateAccountHolderDetails(String fName,String lName,int age,String gender)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String update_sql="UPDATE AccHolderDetails SET  FirstName=?,LastName=?,Age=?,Gender=? WHERE AccHolderId=?";
		//accHolder.setAccHolderId(accHolderId);
		accHolder.setAccHolderFirstName(fName);
		accHolder.setAccHolderLastName(lName);
		accHolder.setAccHolderAge(age);
		accHolder.setAccHolderGender(gender);
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, accHolder.getAccHolderFirstName());
			pstmt.setString(2, accHolder.getAccHolderLastName());
			pstmt.setInt(3, accHolder.getAccHolderAge());
			pstmt.setString(4, accHolder.getAccHolderGender());
			
			pstmt.setInt(5, accHolder.getAccHolderId());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			System.out.println("Error at updateAccHolderDetails");
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final void updateAddressDetails(String city,String addressLine)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String update_sql="UPDATE AddressDetails SET  City=?,AddressLine=? WHERE AccHolderId=?";
		//accHolder.setAccHolderId(accHolderId);
		accHolder.setAccHolderCity(city);
		accHolder.setAccHolderAddress(addressLine);
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, accHolder.getAccHolderCity());
			pstmt.setString(2, accHolder.getAccHolderAddress());
			pstmt.setInt(3, accHolder.getAccHolderId());
			//System.out.println("commiting updateAddressDetails");
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.setAutoCommit(true);		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final void deleteAccount(int accHolderId)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String delete_sql="DELETE FROM AccHolderDetails WHERE AccHolderId=?";
		String delete_sql2="DELETE FROM AddressDetails WHERE AccHolderId=?";
		String delete_sql3="DELETE FROM AccountDetails WHERE AccHolderId=?";
		String delete_sql4="DELETE FROM Balance WHERE AccNo=?";
		String delete_sql5="SELECT AccNo FROM AccountDetails WHERE AccHolderId=?";
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);

			pstmt=con.prepareStatement(delete_sql5);
     		pstmt.setInt(1, accHolderId);
     		ResultSet rs=pstmt.executeQuery();
     		con.commit();
     		while(rs.next()) {
     			pstmt=con.prepareStatement(delete_sql4);
     			pstmt.setInt(1, rs.getInt("AccNo"));
     			pstmt.executeUpdate();
     			con.commit();
     			pstmt.close();
     		}
     		pstmt.close();
			
			pstmt=con.prepareStatement(delete_sql3);
			pstmt.setInt(1, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			pstmt=con.prepareStatement(delete_sql2);
			pstmt.setInt(1, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			pstmt=con.prepareStatement(delete_sql);
			pstmt.setInt(1, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			
			con.setAutoCommit(true);
			TotalLog.get_instance().red_total_Acc_Log();
			System.out.println("Account having ID="+accHolderId+" is deleted");	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final void deleteAccount(int accHolderId,int accTyId)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String delete_sql3="DELETE FROM AccountDetails WHERE (AccHolderId=? AND AccTyId=?)";
		String delete_sql4="DELETE FROM Balance WHERE AccNo=?";
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			
			int current_accNo=getAccountNumber(accHolderId,accTyId);
			pstmt=con.prepareStatement(delete_sql4);
			pstmt.setInt(1, current_accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
						
			pstmt=con.prepareStatement(delete_sql3);
			pstmt.setInt(1, accHolderId);
			pstmt.setInt(2, accTyId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final int getAccountNumber(int accHolderId,int accTyId)
	{
		int accNo=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String db_URL=MainProjectBank.db_URL;
		String select_sql="SELECT AccNo from AccountDetails where (AccHolderId=? AND AccTyId=?)";
		try
		{
			con=DriverManager.getConnection(db_URL);
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, accHolderId);
			pstmt.setInt(2, accTyId);
			ResultSet rs=pstmt.executeQuery();
			accNo=rs.getInt("AccNo");
			if(accNo==0)
			{
				System.out.println("Account doesn't exist");
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return accNo;
	}
	public final double getMainBalance(int accNo)
	{
		double balance=0;
		Connection con=null;
		String db_URL=MainProjectBank.db_URL;
		PreparedStatement pstmt=null;
		String select_sql="SELECT MainBalance FROM Balance WHERE AccNo=?";
		try {
			con=DriverManager.getConnection(db_URL);
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			balance=rs.getDouble("MainBalance");	
			rs.close();
			pstmt.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return balance;
		
	}
	public final void changeMainBalance(int accNo,double mainBalance)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		String db_URL=MainProjectBank.db_URL;
		String update_sql="UPDATE Balance SET  MainBalance=? WHERE AccNo=?";
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false); 
			pstmt=con.prepareStatement(update_sql);
			pstmt.setDouble(1, mainBalance);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			//System.out.println("Amount successfully added!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				pstmt.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

PK
    ��Si{��2  2     DataLayer/AccountHolder.javapackage DataLayer;

public class AccountHolder implements IAccountHolder{
	private final int accHolderId;
	private String accHolderFirstName;
	private String accHolderLastName;
	private int accHolderAge;
	private String accHolderGender;
	private int accTypeId;
	private String accHolderAddress;
	private String accHolderCity;
	
	public AccountHolder(int accHolderId,String fName,String lName,int age,String gender,String city,String address) {
		
		 this.accHolderId=accHolderId;
		 accHolderFirstName=fName.toUpperCase();
		 accHolderLastName=lName.toUpperCase(); 
		 accHolderAge=age;
		 accHolderGender=gender.toUpperCase();
		 accHolderCity=city;
		 accHolderAddress=address;
	}

	public int getAccHolderId() {
		return accHolderId;
	}
	public String getAccHolderFirstName() {
		return accHolderFirstName;
	}
	public String getAccHolderLastName() {
		return accHolderLastName;
	}
	public int getAccHolderAge() {
		return accHolderAge;
	}
	public String getAccHolderGender() {
		return accHolderGender;
	}
	public int getAccTypeId() {
		return accTypeId;
	}
	public String getAccHolderAddress() {
		return accHolderAddress;
	}
	public String getAccHolderCity() {
		return accHolderCity;
	}


//	public void setAccHolderId(int accHolderId) {
//		this.accHolderId = accHolderId;
//	}
	public void setAccHolderFirstName(String accHolderFirstName) {
		this.accHolderFirstName = accHolderFirstName.toUpperCase();
	}
	public void setAccHolderLastName(String accHolderLastName) {
		this.accHolderLastName = accHolderLastName.toUpperCase();
	}
	public void setAccHolderAge(int accHolderAge) {
		this.accHolderAge = accHolderAge;
	}
	public void setAccHolderGender(String accHolderGender) {
		this.accHolderGender = accHolderGender.toUpperCase();
	}
	public void setAccTypeId(int accTypeId) {
		this.accTypeId = accTypeId;
	}
	public void setAccHolderAddress(String accHolderAddress) {
		this.accHolderAddress = accHolderAddress;
	}
	public void setAccHolderCity(String accHolderCity) {
		this.accHolderCity = accHolderCity;
	}
}
PK
    H�S,�s�       DataLayer/TotalLog.javapackage DataLayer;

public class TotalLog implements ITotalLog {
	
	public static ITotalLog first_instance;
	private long total_Acc;
	private TotalLog() {
	}
	public static ITotalLog get_instance()
	{
		if(first_instance==null)
			first_instance=new TotalLog();
		return first_instance;
	}

	public final void total_Acc_Log()
	{
		total_Acc++;
	}
	public final void red_total_Acc_Log()
	{
		total_Acc--;
	}
	public final long get_total_Acc_Log()
	{
		return total_Acc++;
	}            
}
PK
    ,�S�Zp�z  z     DataLayer/Bank.javapackage DataLayer;

public final class Bank implements IBank{
	final String bankName="STATE BANK OK INDIA";
	final String bankAddress="123,1st Main road,Mumbai";
	final String bankIFSCCode="SBI0012345";
	private static final String USERID="Manager@SBI";
	private final static String PASSWORD="Hello@123";
	public String getBankname() {
		return bankName;
	}
	public String getBankaddress() {
		return bankAddress;
	}
	public String getBankIFSCCode() {
		return bankIFSCCode;
	}
	public final boolean login_Credentials(String userId,String password)
	{
		return ((USERID==userId) && (PASSWORD==password));
	}

}
PK
    �S����       DataLayer/IOperationOnDB.javapackage DataLayer;

public interface IOperationOnDB {
	
	public void createAccount();
	public void updateAccountHolderDetails(String fName,String lName,int age,String gender);
	public void updateAddressDetails(String city,String addressLine);
	public void deleteAccount(int accHolderId);
	public void deleteAccount(int accHolderId,int accTyId);
	public int getAccountNumber(int accHolderId,int accTyId);
	public double getMainBalance(int accNo);
	public void changeMainBalance(int accNo,double mainBalance);
}
PK
    d�S� 	       DataLayer/IAccountHolder.javapackage DataLayer;

public interface IAccountHolder {
	
	
	public int getAccHolderId();
	public String getAccHolderFirstName();
	public String getAccHolderLastName();
	public int getAccHolderAge();
	public String getAccHolderGender();
	public int getAccTypeId();
	public String getAccHolderAddress();
	public String getAccHolderCity();

	//public void setAccHolderId(int accHolderId);
	public void setAccHolderFirstName(String accHolderFirstName);
	public void setAccHolderLastName(String accHolderLastName);
	public void setAccHolderAge(int accHolderAge);
	public void setAccHolderGender(String accHolderGender);
	public void setAccTypeId(int accTypeId);
	public void setAccHolderAddress(String accHolderAddress);
	public void setAccHolderCity(String accHolderCity);

}
PK
    ��S�]�   �      DataLayer/ITotalLog.javapackage DataLayer;

public interface ITotalLog {

	public void total_Acc_Log();
	public void red_total_Acc_Log();
	public long get_total_Acc_Log();
}
PK
    �S� :�  �  &   PresentationLayer/MainProjectBank.javapackage PresentationLayer;

import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import ApplicationLayer.*;
import DataLayer.*;

public class MainProjectBank {
	public static final String db_URL="jdbc:sqlite:C:\\sqlite3\\Accounts.db";

	public static void main(String[] args) {
		
		ILoginCheck login=new LoginCheck();
		if(!(login.loginCheck("Manager@SBI","Hello@123"))) 
		{
			System.out.println("Invalid User Name or Password! Please try again later");
			System.exit(0);
		}
		Connection con=null;
		int accNo=0;
//		int accHolderId=0,age,accTyId=1;
//		String fName,lName,gender,city,addressLine;
		try
		{
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection(db_URL);
			System.out.println("Database connected");
			IAccountHolder accHolder1=new AccountHolder(240,"Bruce","Wayne",50,"male","Gowtham","1,10th Main road,New Nagar");
			IServiceMethods user1=new SavingAccount(accHolder1);
			//System.out.println("Creating Account");
			//user1.createAccount();
			//user1.updateAccountHolderDetails("Bat", "Man",55, "Male");
			//user1.updateAddressDetails("Chennai", "3,2nd Main road,Adyar");
			  //user1.deleteAccount();
//			accNo=user1.getAccountNo( 240, 1); 
//			System.out.println("accNo: "+accNo);
//			user1.showBalance(accNo);	
//			user1.credit(accNo, 1000);
//			user1.debit(accNo, 999);
         	user1.displayCoupon();
			System.out.println();
			
			IAccountHolder accHolder2=new AccountHolder(241,"Clark","Kent",55,"male","Metropolitan","2,12th Main road,Old Nagar");
			IServiceMethods user2=new SavingAccount(accHolder2);
			//System.out.println("Creating Account");
			//user2.createAccount();
			//user2.updateAccountHolderDetails("Super", "Man",55, "Male");
			//user2.updateAddressDetails("Pondy", "3,2nd Main road,Gorimedu");
			  //user2.deleteAccount();
//			accNo=user2.getAccountNo( 102, 1);
//			System.out.println("accNo: "+accNo);
//			user2.showBalance(accNo);	
//			user2.credit(accNo, 1000);
//			user2.debit(accNo, 999);
         	user2.displayCoupon();
			System.out.println();
//			
			ITotalLog log=TotalLog.get_instance();
			System.out.println("Total account created in this session is "+log.get_total_Acc_Log());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}	
//		while(true)
//		{
//			Scanner sc=new Scanner(System.in);
//			System.out.println("Enter 0 for creating new account"+"\n"+"Enter 1 for Updating account holder's details"+"\n"+"Enter 2 for updating account holder's address details"+"Enter 3 for Deleting an existing account"+"\n"+"Enter 4 for Debit"+"\n"+"Enter 5 for Credit"+"\n"+"Enter 6 for ShowBalance"+"\n"+"Enter 7 for EXIT: Type a value--");
//			int op=sc.nextInt();
//			System.out.println();
//			if(op==0) {
//				//Scanner sc0=new Scanner(System.in);
//				System.out.println("Enter the Account Holder Id: ");
//				accHolderId=Integer.parseInt(sc.nextLine());              //sc.nextInt();
//				System.out.println("Enter the Account Holder's First Name: ");
//				fName=sc.nextLine();
//				System.out.println("Enter the Account Holder's Last Name: ");
//				lName=sc.nextLine();
//				System.out.println("Enter the Account Holder's Age: ");
//				age=Integer.parseInt(sc.nextLine());                        //sc.nextInt();
//				System.out.println("Enter the Account Holder's Gender: ");
//				gender=sc.nextLine();
//				System.out.println("Enter the Account Holder's City: ");
//				city=sc.nextLine();
//				System.out.println("Enter the Account Holder's FullAddress: ");
//				addressLine=sc.nextLine();
//				System.out.println("Type of Account? 1-Savings,2-Current,3-Fixed,4-COD, Enter: ");
//				accTyId=sc.nextInt();
//				System.out.println();
//				if(accTyId==1){
//					accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine);
//					user=new SavingAccount(accHolder);
//					System.out.println("Creating Account");
//					user.createAccount();	
//				}
//				else if(accTyId==2) {
//					accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine);
//					user=new CurrentAccount(accHolder);
//					System.out.println("Creating Account");
//					user.createAccount();
//				}
//				else if(accTyId==3) {
//					accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine);
//					user=new FDAccount(accHolder);
//					System.out.println("Creating Account");
//					user.createAccount();					
//									}
//				else if(accTyId==4) {
//					accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine);
//					user=new CODAccount(accHolder);
//					System.out.println("Creating Account");
//					user.createAccount();
//				}
//				else {
//					System.out.println("Creating Account Unsuccessful");
//				}
//			}
//			else if(op==1) {
//				//Scanner sc1=new Scanner(System.in);
//				System.out.println("Enter the Account Holder's First Name: ");
//				fName=sc.nextLine();
//				System.out.println("Enter the Account Holder's Last Name: ");
//				lName=sc.nextLine();
//				System.out.println("Enter the Account Holder's Age: ");
//				age=sc.nextInt();
//				System.out.println("Enter the Account Holder's Gender: ");
//				gender=sc.nextLine();
//				System.out.println();
//				
//				user.updateAccountHolderDetails( fName, lName,age, gender);
//			}
//			else if(op==2) {
//				//Scanner sc2=new Scanner(System.in);
//				System.out.println("Enter the Account Holder's City: ");
//				city=sc.nextLine();
//				System.out.println("Enter the Account Holder's FullAddress: ");
//				addressLine=sc.nextLine();
//		
//				System.out.println();
//				user.updateAddressDetails(city, addressLine);	
//			}
//			else if(op==3) {
//				//Scanner sc3=new Scanner(System.in);
//				System.out.println("Enter 3.1 for deleting specific account type (or) Enter 3.2 for deleting account");
//				double a=sc.nextDouble();
//				if(a==3.1) {
//					System.out.println("Enter the Account holder Id");
//					int current_accholderId=sc.nextInt();
//					System.out.println("Enter the Account Type Id");
//					int current_accTyId=sc.nextInt();
//					
//					System.out.println();
//					user.deleteAccount(current_accholderId,current_accTyId);
//				}
//				else if(a==3.2) {
//					System.out.println("Enter the Account holder Id which u want to delete.");
//					int current_accholderId=sc.nextInt();
//					
//					System.out.println();
//					user.deleteAccount(current_accholderId);
//				}
//				else {
//					System.out.println("Deleting process Unsuccessfull");
//				}
//			}
//			else if(op==4) {
//				accNo=user.getAccountNo( accHolderId, accTyId);
//				//Scanner sc4=new Scanner(System.in);
//				System.out.println("Enter the amount to be 'debited'");
//				double amount=sc.nextDouble();
//				
//				System.out.println();
//				user.debit(accNo, amount);
//			}
//			else if(op==5) {
//				accNo=user.getAccountNo( accHolderId, accTyId);
//				//Scanner sc5=new Scanner(System.in);
//				System.out.println("Enter the amount to be 'credited'");
//				double amount=sc.nextDouble();
//				System.out.println();
//				user.credit(accNo, amount);	
//			}
//			else if(op==6) {
//				accNo=user.getAccountNo( accHolderId, accTyId);
//				user.showBalance(accNo);
//				System.out.println();
//			}
//			else if(op==7) {
//				System.out.println("Application terminated. Thank You!");
//				sc.close();
//				System.exit(0);
//			}
//			else {
//				System.out.println("Unsuccessfull! Please Enter a option from 1 to 6!"+"\n");
//				System.out.println();
//			}
//		}
	}
}
PK
    %�S��dR      
   .gitignore/bin/
PK
    }�Sr�0  0  
   .classpath<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8">
		<attributes>
			<attribute name="module" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="lib" path="C:/Users/NEW/Desktop/jdbc driver/sqlite-jdbc-3.36.0.1.jar" sourcepath="C:/Users/NEW/Desktop/jdbc driver/sqlite-jdbc-3.36.0.1"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
PK
    r�S,����  �     .project<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>ProjectBank</name>
	<comment></comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
	</natures>
</projectDescription>
PK
    w�S ꋷ  �     ApplicationLayer/Accounts.class����   4 j  ApplicationLayer/Accounts  java/lang/Object   ApplicationLayer/IServiceMethods mainBalance D accountHolder LDataLayer/IOperationOnDB; discountAmount <init> (LDataLayer/IAccountHolder;)V Code
     ()V	   	 
	      DataLayer/OperationOnDB
     LineNumberTable LocalVariableTable this LApplicationLayer/Accounts; obj LDataLayer/IAccountHolder; createAccount " $ # DataLayer/IOperationOnDB    updateAccountHolderDetails :(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V " ( % & fName Ljava/lang/String; lName age I gender updateAddressDetails '(Ljava/lang/String;Ljava/lang/String;)V " 2 / 0 city addressLine deleteAccount (I)V " 8 5 6 accHolderId (II)V " < 5 : accTyId getAccountNo (II)I " A B ? getAccountNumber getMainBalance (I)D " F C D accNo changeMainBalance (ID)V " K H I showBalance	 N P O java/lang/System Q R out Ljava/io/PrintStream; T java/lang/StringBuilder V Available Balance is: 
 S X  Y (Ljava/lang/String;)V
  F
 S \ ] ^ append (D)Ljava/lang/StringBuilder;
 S ` a b toString ()Ljava/lang/String;
 d f e java/io/PrintStream g Y println 
SourceFile Accounts.java!              	 
         
        _     *� *� *� *� Y+� � �              	 	                               8     
*� � ! �       
     	         
      % &     e     *� +,� ' �       
          4            ) *     + *     , -     . *   / 0     N     *� +,� 1 �       
                       3 *     4 *   5 6     C     *� � 7 �       
     
                  9 -   5 :     N     *� � ; �       
    #  $                 9 -     = -   > ?     J     *� � @ �           '                 9 -     = -   C D     ?     *� � E �           +                G -   H I     N     *� (� J �       
    /  0                 G -         L 6     S     � M� SYU� W*� Z� [� _� c�       
    3  4                G -   h    iPK
    "�Sz�U8  8  !   ApplicationLayer/CODAccount.class����   4 k  ApplicationLayer/CODAccount  ApplicationLayer/Accounts count I mainBalance D discountAmount <clinit> ()V Code	     LineNumberTable LocalVariableTable <init> (LDataLayer/IAccountHolder;)V
    	   	     DataLayer/IAccountHolder   setAccTypeId (I)V this LApplicationLayer/CODAccount; obj LDataLayer/IAccountHolder; debit (ID)V	 $ & % java/lang/System ' ( out Ljava/io/PrintStream; * ?Transaction Failed! Cant debit amount from this type of Account
 , . - java/io/PrintStream / 0 println (Ljava/lang/String;)V accNo amount credit
  5 6 7 getMainBalance (I)D	  9  
  ; < " changeMainBalance > java/lang/StringBuilder
 @ B A java/lang/String C D valueOf (D)Ljava/lang/String;
 = F  0 H  :Credited Successfully
 = J K L append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 = N O P toString ()Ljava/lang/String;
  R S T createCoupon (D)V V Available Balance is: 
 = X K Y (D)Ljava/lang/StringBuilder; [ MTransaction Failed! Cant credit amount more than once in this type of Account ] 3Transaction Failed! Please visit your nearby branch StackMapTable@      @Y       discountPercentage displayCoupon f Congratulations!! You have won  h  worth of Coupon at KFC!! 
SourceFile CODAccount.java !                   	      
      %      � �                         R     *+� *� +�  �              
 	  
                     ! "     K     	� #)� +�       
               	       	 1     	 2    3 "     �     � � a**� 4� 8*Y� 8(c� 8**� 8� :� #� =Y(� ?� EG� I� M� +*(� Q� #� =YU� E*� 8� W� M� +� `� � � � � #Z� +� � #\� +�       :           "  ;  @  Y  a  d  k  s  v ! ~ #                 1      2   ^    � d  S T     V      _J*)'k ao� �           &  '  (                 2     c    d      M     � #� =Ye� E*� � Wg� I� M� +�       
    +  ,              i    jPK
    �S�/�f�  �  %   ApplicationLayer/CurrentAccount.class����   4 e  ApplicationLayer/CurrentAccount  ApplicationLayer/Accounts mainBalance D discountAmount <init> (LDataLayer/IAccountHolder;)V Code
    		        DataLayer/IAccountHolder   setAccTypeId (I)V LineNumberTable LocalVariableTable this !LApplicationLayer/CurrentAccount; obj LDataLayer/IAccountHolder; debit (ID)V
      getMainBalance (I)D	  "  
  $ %  changeMainBalance	 ' ) ( java/lang/System * + out Ljava/io/PrintStream; - java/lang/StringBuilder
 / 1 0 java/lang/String 2 3 valueOf (D)Ljava/lang/String;
 , 5  6 (Ljava/lang/String;)V 8  :Debited Successfully
 , : ; < append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 , > ? @ toString ()Ljava/lang/String;
 B D C java/io/PrintStream E 6 println G Available Balance is: 
 , I ; J (D)Ljava/lang/StringBuilder; L No suffcient balance accNo I amount StackMapTable credit S  :Credited Successfully
  U V W createCoupon (D)V@      @Y       discountPercentage displayCoupon ()V ` Congratulations!! You have won  b  worth of Coupon at KFC!! 
SourceFile CurrentAccount.java !                     	  
   R     *+� *� +�  �              
 	  
                       
   �     |**� � !(*� !�� K*Y� !(g� !**� !� #� &� ,Y(� .� 47� 9� =� A� &� ,YF� 4*� !� H� =� A� $� &K� A� &� ,YF� 4*� !� H� =� A�       * 
    	      %  >  W  Z  b  {          |       | M N    | O   P    � Z   Q   
   �     T**� � !*Y� !(c� !**� !� #� &� ,Y(� .� 4R� 9� =� A*(� T� &� ,YF� 4*� !� H� =� A�            	      ! 5 " : # S $         T       T M N    T O    V W  
   V      XJ*)'k Zo� �           '  (  )                 O     \    ] ^  
   M     � &� ,Y_� 4*� � Ha� 9� =� A�       
    ,  -              c    dPK
    �S��&u3  3      ApplicationLayer/FDAccount.class����   4 k  ApplicationLayer/FDAccount  ApplicationLayer/Accounts mainBalance D discountAmount count I <clinit> ()V Code	    	 LineNumberTable LocalVariableTable <init> (LDataLayer/IAccountHolder;)V
    	        DataLayer/IAccountHolder   setAccTypeId (I)V this LApplicationLayer/FDAccount; obj LDataLayer/IAccountHolder; debit (ID)V	 $ & % java/lang/System ' ( out Ljava/io/PrintStream; * =Transaction Failed! Cant debit amount in this type of Account
 , . - java/io/PrintStream / 0 println (Ljava/lang/String;)V accNo amount credit
  5 6 7 getMainBalance (I)D	  9  
  ; < " changeMainBalance > java/lang/StringBuilder
 @ B A java/lang/String C D valueOf (D)Ljava/lang/String;
 = F  0 H  :Credited Successfully
 = J K L append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 = N O P toString ()Ljava/lang/String;
  R S T createCoupon (D)V V Available Balance is: 
 = X K Y (D)Ljava/lang/StringBuilder; [ MTransaction Failed! Cant credit amount more than once in this type of Account ] 3Transaction Failed! Please visit your nearby branch StackMapTable@      @Y       discountPercentage displayCoupon f Congratulations!! You have won  h  worth of Coupon at KFC!! 
SourceFile FDAccount.java !                    	     
      %      � �                         R     *+� *� +�  �           	   
 
                       ! "     K     	� #)� +�       
               	       	 1 	    	 2    3 "     �     � � a**� 4� 8*Y� 8(c� 8**� 8� :� #� =Y(� ?� EG� I� M� +*(� Q� #� =YU� E*� 8� W� M� +� `� � � � � #Z� +� � #\� +�       :           "  ;  @  Y  a  d  k  s  v " ~ $                 1 	     2   ^    � d  S T     V      _J*)'k ao� �           '  (  )                 2     c    d      M     � #� =Ye� E*� � Wg� I� M� +�       
    ,  -              i    jPK
    P�S*���   �   "   ApplicationLayer/ILoginCheck.class����   4 	  ApplicationLayer/ILoginCheck  java/lang/Object 
loginCheck '(Ljava/lang/String;Ljava/lang/String;)Z 
SourceFile ILoginCheck.java                 PK
    ѮSA�H  H  &   ApplicationLayer/IServiceMethods.class����   4    ApplicationLayer/IServiceMethods  java/lang/Object createAccount ()V updateAccountHolderDetails :(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V updateAddressDetails '(Ljava/lang/String;Ljava/lang/String;)V deleteAccount (I)V (II)V getAccountNo (II)I getMainBalance (I)D changeMainBalance (ID)V showBalance debit credit createCoupon (D)V displayCoupon 
SourceFile IServiceMethods.java                	 
                                                PK
    ��S���v�  �  !   ApplicationLayer/LoginCheck.class����   4 #  ApplicationLayer/LoginCheck  java/lang/Object  ApplicationLayer/ILoginCheck Application_Login LDataLayer/IBank; <init> ()V Code
   	 
  DataLayer/Bank
  	     LineNumberTable LocalVariableTable this LApplicationLayer/LoginCheck; 
loginCheck '(Ljava/lang/String;Ljava/lang/String;)Z    DataLayer/IBank   login_Credentials loginId Ljava/lang/String; password 
SourceFile LoginCheck.java !              	 
     B     *� *� Y� � �                                   J     *� +,�  �           	                            !    "PK
    ѮS��6M�  �  $   ApplicationLayer/SavingAccount.class����   4 e  ApplicationLayer/SavingAccount  ApplicationLayer/Accounts mainBalance D discountAmount <init> (LDataLayer/IAccountHolder;)V Code
    		        DataLayer/IAccountHolder   setAccTypeId (I)V LineNumberTable LocalVariableTable this  LApplicationLayer/SavingAccount; obj LDataLayer/IAccountHolder; debit (ID)V
      getMainBalance (I)D	  "  
  $ %  changeMainBalance	 ' ) ( java/lang/System * + out Ljava/io/PrintStream; - java/lang/StringBuilder
 / 1 0 java/lang/String 2 3 valueOf (D)Ljava/lang/String;
 , 5  6 (Ljava/lang/String;)V 8  :Debited Successfully
 , : ; < append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 , > ? @ toString ()Ljava/lang/String;
 B D C java/io/PrintStream E 6 println G Available Balance is: 
 , I ; J (D)Ljava/lang/StringBuilder; L No suffcient balance accNo I amount StackMapTable credit S  :Credited Successfully
  U V W createCoupon (D)V@       @Y       discountPercentage displayCoupon ()V ` Congratulations!! You have won  b  worth of Coupon at KFC!! 
SourceFile SavingAccount.java !                     	  
   R     *+� *� +�  �              
   	                       
   �     |**� � !(*� !�� K*Y� !(g� !**� !� #� &� ,Y(� .� 47� 9� =� A� &� ,YF� 4*� !� H� =� A� $� &K� A� &� ,YF� 4*� !� H� =� A�       * 
    	      %  >  W  Z  b  {          |       | M N    | O   P    � Z   Q   
   �     T**� � !*Y� !(c� !**� !� #� &� ,Y(� .� 4R� 9� =� A*(� T� &� ,YF� 4*� !� H� =� A�            	      ! 5 " : # S $         T       T M N    T O    V W  
   V      XJ*)'k Zo� �           '  (  )                 O     \    ] ^  
   M     � &� ,Y_� 4*� � Ha� 9� =� A�       
    ,  -              c    dPK
    ��S'i0	  0	     DataLayer/AccountHolder.class����   4 K  DataLayer/AccountHolder  java/lang/Object  DataLayer/IAccountHolder accHolderId I accHolderFirstName Ljava/lang/String; accHolderLastName accHolderAge accHolderGender 	accTypeId accHolderAddress accHolderCity <init> _(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V Code
     ()V	    
    java/lang/String   toUpperCase ()Ljava/lang/String;	    	 
	  "  
	  $  	  &  
	  (  
	  *  
 LineNumberTable LocalVariableTable this LDataLayer/AccountHolder; fName lName age gender city address getAccHolderId ()I getAccHolderFirstName getAccHolderLastName getAccHolderAge getAccHolderGender getAccTypeId	  =   getAccHolderAddress getAccHolderCity setAccHolderFirstName (Ljava/lang/String;)V setAccHolderLastName setAccHolderAge (I)V setAccHolderGender setAccTypeId setAccHolderAddress setAccHolderCity 
SourceFile AccountHolder.java !            	 
     
          
          
     
           �     5*� *� *,� � *-� � !*� #*� � %*� '*� )�    +   & 	      	        (  .  4  ,   R    5 - .     5      5 / 
    5 0 
    5 1     5 2 
    5 3 
    5 4 
   5 6     /     *� �    +        ,        - .    7      /     *� �    +        ,        - .    8      /     *� !�    +        ,        - .    9 6     /     *� #�    +       " ,        - .    :      /     *� %�    +       % ,        - .    ; 6     /     *� <�    +       ( ,        - .    >      /     *� )�    +       + ,        - .    ?      /     *� '�    +       . ,        - .    @ A     A     	*+� � �    +   
    6  7 ,       	 - .     	 	 
   B A     A     	*+� � !�    +   
    9  : ,       	 - .     	  
   C D     >     *� #�    +   
    <  = ,        - .          E A     A     	*+� � %�    +   
    ?  @ ,       	 - .     	  
   F D     >     *� <�    +   
    B  C ,        - .          G A     >     *+� )�    +   
    E  F ,        - .       
   H A     >     *+� '�    +   
    H  I ,        - .       
   I    JPK
    }�Sxu��0  0     DataLayer/Bank.class����   4 2  DataLayer/Bank  java/lang/Object  DataLayer/IBank bankName Ljava/lang/String; ConstantValue  STATE BANK OK INDIA bankAddress  123,1st Main road,Mumbai bankIFSCCode  
SBI0012345 USERID  Manager@SBI PASSWORD  	Hello@123 <init> ()V Code
    	    	     	  "   LineNumberTable LocalVariableTable this LDataLayer/Bank; getBankname ()Ljava/lang/String; getBankaddress getBankIFSCCode login_Credentials '(Ljava/lang/String;Ljava/lang/String;)Z userId password StackMapTable 
SourceFile 	Bank.java 1          	    
     	         	         	         	             Q     *� *
� *� *� !�    #          
      $        % &    ' (     -     
�    #       
 $        % &    ) (     -     �    #        $        % &    * (     -     �    #        $        % &    + ,     W     +� ,� ��    #        $         % &      -      .   /      0    1PK
    d�SPկXX  X     DataLayer/IAccountHolder.class����   4   DataLayer/IAccountHolder  java/lang/Object getAccHolderId ()I getAccHolderFirstName ()Ljava/lang/String; getAccHolderLastName getAccHolderAge getAccHolderGender getAccTypeId getAccHolderAddress getAccHolderCity setAccHolderFirstName (Ljava/lang/String;)V setAccHolderLastName setAccHolderAge (I)V setAccHolderGender setAccTypeId setAccHolderAddress setAccHolderCity 
SourceFile IAccountHolder.java                	    
                                                     PK
    }�S���L       DataLayer/IBank.class����   4   DataLayer/IBank  java/lang/Object getBankname ()Ljava/lang/String; getBankaddress getBankIFSCCode login_Credentials '(Ljava/lang/String;Ljava/lang/String;)Z 
SourceFile 
IBank.java                    	 
        PK
    �S�Ύ�  �     DataLayer/IOperationOnDB.class����   4   DataLayer/IOperationOnDB  java/lang/Object createAccount ()V updateAccountHolderDetails :(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V updateAddressDetails '(Ljava/lang/String;Ljava/lang/String;)V deleteAccount (I)V (II)V getAccountNumber (II)I getMainBalance (I)D changeMainBalance (ID)V 
SourceFile IOperationOnDB.java                	 
                            PK
    }�S�H,�   �      DataLayer/ITotalLog.class����   4   DataLayer/ITotalLog  java/lang/Object total_Acc_Log ()V red_total_Acc_Log get_total_Acc_Log ()J 
SourceFile ITotalLog.java                 	    
    PK
    ��S�+?�&  �&     DataLayer/OperationOnDB.class����   4  DataLayer/OperationOnDB  java/lang/Object  DataLayer/IOperationOnDB 	accHolder LDataLayer/IAccountHolder; <init> (LDataLayer/IAccountHolder;)V Code
   	  ()V	     LineNumberTable LocalVariableTable this LDataLayer/OperationOnDB; obj createAccount  [INSERT INTO AccHolderDetails (AccHolderId,FirstName,LastName,Age,Gender) VALUES (?,?,?,?,?)  HINSERT INTO AddressDetails (AccHolderId,City,AddressLine) VALUES (?,?,?)  =INSERT INTO AccountDetails (AccHolderId,AccTyId) VALUES (?,?)  &INSERT INTO Balance (AccNo) VALUES (?)   "jdbc:sqlite:C:\sqlite3\Accounts.db
 " $ # java/sql/DriverManager % & getConnection )(Ljava/lang/String;)Ljava/sql/Connection; ( * ) java/sql/Connection + , setAutoCommit (Z)V ( . / 0 prepareStatement 0(Ljava/lang/String;)Ljava/sql/PreparedStatement; 2 4 3 DataLayer/IAccountHolder 5 6 getAccHolderId ()I 8 : 9 java/sql/PreparedStatement ; < setInt (II)V 2 > ? @ getAccHolderFirstName ()Ljava/lang/String; 8 B C D 	setString (ILjava/lang/String;)V 2 F G @ getAccHolderLastName 2 I J 6 getAccHolderAge 2 L M @ getAccHolderGender 8 O P 6 executeUpdate ( R S  commit 8 U V  close 2 X Y @ getAccHolderCity 2 [ \ @ getAccHolderAddress 2 ^ _ 6 getAccTypeId
  a b c getAccountNumber (II)I
 e g f DataLayer/TotalLog h i get_instance ()LDataLayer/ITotalLog; k m l DataLayer/ITotalLog n  total_Acc_Log
 p r q java/lang/Exception s  printStackTrace ( U insert_sql1 Ljava/lang/String; insert_sql2 insert_sql3 insert_sql4 db_URL 	gen_accNo I con Ljava/sql/Connection; pstmt Ljava/sql/PreparedStatement; e Ljava/lang/Exception; StackMapTable � java/lang/String � java/lang/Throwable updateAccountHolderDetails :(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V � VUPDATE AccHolderDetails SET  FirstName=?,LastName=?,Age=?,Gender=? WHERE AccHolderId=? 2 � � � setAccHolderFirstName (Ljava/lang/String;)V 2 � � � setAccHolderLastName 2 � � � setAccHolderAge (I)V 2 � � � setAccHolderGender	 � � � java/lang/System � � out Ljava/io/PrintStream; � Error at updateAccHolderDetails
 � � � java/io/PrintStream � � println fName lName age gender 
update_sql updateAddressDetails '(Ljava/lang/String;Ljava/lang/String;)V � CUPDATE AddressDetails SET  City=?,AddressLine=? WHERE AccHolderId=? 2 � � � setAccHolderCity 2 � � � setAccHolderAddress city addressLine deleteAccount � 0DELETE FROM AccHolderDetails WHERE AccHolderId=? � .DELETE FROM AddressDetails WHERE AccHolderId=? � .DELETE FROM AccountDetails WHERE AccHolderId=? � !DELETE FROM Balance WHERE AccNo=? � 4SELECT AccNo FROM AccountDetails WHERE AccHolderId=? 8 � � � executeQuery ()Ljava/sql/ResultSet; � AccNo � � � java/sql/ResultSet � � getInt (Ljava/lang/String;)I � � � � next ()Z k � �  red_total_Acc_Log � java/lang/StringBuilder � Account having ID=
 � � 	 �
 � � � � append (I)Ljava/lang/StringBuilder; �  is deleted
 � � � � -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 � � � @ toString accHolderId 
delete_sql delete_sql2 delete_sql3 delete_sql4 delete_sql5 rs Ljava/sql/ResultSet; � >DELETE FROM AccountDetails WHERE (AccHolderId=? AND AccTyId=?) accTyId current_accNo � DSELECT AccNo from AccountDetails where (AccHolderId=? AND AccTyId=?) � Account doesn't exist � U accNo 
select_sql getMainBalance (I)D � -SELECT MainBalance FROM Balance WHERE AccNo=?  MainBalance � 	getDouble (Ljava/lang/String;)D balance D changeMainBalance (ID)V
 /UPDATE Balance SET  MainBalance=? WHERE AccNo=? 8 	setDouble mainBalance 
SourceFile OperationOnDB.java !            	  	 
     O     *� *� *+� �              	                            �    �LMN::::� !:� ' +� - :*� � 1 � 7 *� � = � A *� � E � A *� � H � 7 *� � K � A � N W� Q � T ,� - :*� � 1 � 7 *� � W � A *� � Z � A � N W� Q � T -� - :*� � 1 � 7 *� � ] � 7 � N W� Q � T **� � 1 *� � ] � `6� - :� 7 � N W� Q � T � ' � d� j � 4:		� o� t � 4:� o� *:
� t � 
:� o
�� t � 
:� o�  �� p��� p ��  ��� p��� p     � 9        	            &   0 ! A " R # c $ t % � & � ' � ( � * � + � , � - � . � / � 0 � 3 � 4	 5 6" 7) 80 :H ;S <] =e >l ?s @{ A� B� C� D� H� I� J� K� F� H� I� J� K� M� H� I� J� K� N    �   �     � u v  � w v  	� x v  � y v  � z v H ; { |  � } ~  �  � �  � � 	�  � � �  � � �  � �  �   z �� 	  � � � � �  ( 8  pP pI ��    � � � � �  ( 8  �  p�  	  � � � � �  ( 8  I p  � �    �    :::�:*� +� � *� ,� � *� � � *� � � � !:� ' � - :*� � = � A *� � E � A *� � H � 7 *� � K � A *� � 1 � 7 � N W� Q � T � ' � <:	� ��� �	� o� t � 4:� o� *:
� t � 
:� o
�� t � 
:� o�  7 � � p � � � p 7 � �   � � � p 
 p     � '   Q  R  S 
 T  V  W " X , Y 7 \ > ] F ^ Q _ b ` s a � b � d � e � f � g � i � j � k � l � m � q � r � s � t � o � q � r � s � t � v  q r
 s t w    �          � v    � v    � |    � v   } ~    �  
 z v   � v  �  � � 	 �  � �  �  � �   � �  �   z � � 	  � � � ( 8 � �  pX pI ��    � � � ( 8 � �  �  p�  	  � � � ( 8 � �  I p  � �    x  
   �N::�:*� +� � *� ,� � � !N-� ' -� - :*� � W � A *� � Z � A *� � 1 � 7 � N W-� Q � T -� ' � 2:� o-� t � 2:		� o� (:-� t � 
:		� o�-� t � 
:		� o�  ! � � p � � � p ! � �   � � � p � � � p     � "   z  {  | 	 }    � ! � ' � . � 8 � I � Z � k � s � y � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �    p    �       � � v    � � v   � } ~   �  �  	 � z v   � � v  �  � �  �  � � 	 �  � � 	 �  � � 	 �   n � �   � � ( 8 � �  pO pI �� 
 	  � � ( 8 � �  �  p�    � � ( 8 � �  H p  � �    �    `MN:�:�:�:�:�:	� !M,� ' ,	� - N-� 7 -� � :
,� Q � /,� - N-
ǹ � � 7 -� N W,� Q -� T 
� � ���-� T ,� - N-� 7 -� N W,� Q -� T ,� - N-� 7 -� N W,� Q -� T ,� - N-� 7 -� N W,� Q -� T ,� ' � d� � � �� �Yط �� �� � � �� 2:

� o,� t � 2:� o� (:,� t � 
:� o�,� t � 
:� o�    p'-0 p ':  <BE pOUX p     � :   �  �  �  �  �  �  �  �  � " � ) � 2 � : � B � H � K � T � d � k � q � w � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �  �" �' �- �0 �2 �: �< �B �E �G �L �O �U �X �Z �_ �    �   `      ` � |  ^ } ~  \  �  X z v  T � v  P � v  L � v  H � v  D � v 	 B � � � 
"  � � 
2  � � G  � � Z  � �  �   � 
� K   ( 8 � � � � � � �  +� � 
  ( 8 � � � � � �  pO pI �� 
   ( 8 � � � � � �  �  p�  
  ( 8 � � � � � �  H p  � <    �     �N::�:�:� !N-� ' *� `6-� - :� 7 � N W-� Q � T -� - :� 7 � 7 � N W-� Q � T -� ' � 2:� o-� t � 2:

� o� (:	-� t � 
:

� o	�-� t � 
:

� o�   � � p � � � p  � �   � � � p � � � p     � &   �  �  � 	 �  �  �  �  � & � 0 � : � B � H � O � Y � b � k � s � y � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �     �    �       � � |    � � |   � } ~   �  �  	 � z v   � � v   � � v  & a � |  �  � �  �  � � 
 �  � � 
 �  � � 
 �   k � �   ( 8 � � �  pO pI �� 
 
  ( 8 � � �  �  p�    ( 8 � � �  H p  b c    y     �>:::�:� !:� - :� 7 � 7 � � :ǹ � >� � ��� �� � � T � 4:� o� t � 4:

� o� *:	� t � 
:

� o	�� t � 
:

� o�   a d p k r u p  k    � � � p � � � p     � !       
  " + 4 = G K S Z a d f k r u w  � � � � �! � � � � �"    �    �       � � |    � � |   � � |   � } ~   �  �   � z v   � � v  = $ � �  f  � �  w  � � 
 �  � � 
 �  � � 
 �   � 	� S 	  ( 8 � � �  �    ( 8 � �  pP pI ��  
  ( 8 � �  �  p�    ( 8 � �  I p  � �    /     �I:::�:� !:� - :� 7 � � :�� I� � � T � 4:� o� t � 4:

� o� *:	� t � 
:

� o	�� t � 
:

� o(�   L O p V ] ` p  V j   l s v p � � � p     z   & ' ( 	) * , - ". +/ 40 >1 E2 L4 O5 Q6 V: ]; `< b= j8 l: s; v< x= }? �: �; �< �= �@    z    �       � � |   �   � } ~  	 � z v   �  �   � � v  4  � �  Q  � �  b  � � 
 x  � � 
 �  � � 
 �   b � O   ( � 8 �  pP pI ��  	  ( � 8 �  �  p�    ( � 8 �  I p     *     �:::	:� !:� ' � - :(� � 7 � N W� Q � ' � 4:� o� T � 4:

� o� *:	� T � 
:

� o	�� T � 
:

� o�   R U p \ c f p  \ p   r y | p � � � p     z   E F G 
H K L M )N 2O ;P CQ JR RT UU WW \\ c] f^ h_ pZ r\ y] |^ ~_ �a �\ �] �^ �_ �b    p    �       � � |    �   � } ~   �  �  
 � z v   � � v  W  � �  h  � � 
 ~  � � 
 �  � � 
 �   b � U   ( 8 � �  pP pI ��  	  ( 8 � �  �  p�    ( 8 � �  I p    PK
    }�S�?s'*  *     DataLayer/TotalLog.class����   4 "  DataLayer/TotalLog  java/lang/Object  DataLayer/ITotalLog first_instance LDataLayer/ITotalLog; 	total_Acc J <init> ()V Code
     LineNumberTable LocalVariableTable this LDataLayer/TotalLog; get_instance ()LDataLayer/ITotalLog;	    
   StackMapTable total_Acc_Log	   	 
 red_total_Acc_Log get_total_Acc_Log ()J 
SourceFile TotalLog.java !      	      	 
           3     *� �       
                   	       E      � � � Y� � � �                                  9     *Y� 
a� �       
     
                     9     *Y� 
e� �       
     
                     6     *Y� ]
a� �                              !PK
    �Sl>�"l  l  '   PresentationLayer/MainProjectBank.class����   4 �  !PresentationLayer/MainProjectBank  java/lang/Object db_URL Ljava/lang/String; ConstantValue 	 "jdbc:sqlite:C:\sqlite3\Accounts.db <init> ()V Code
   
  LineNumberTable LocalVariableTable this #LPresentationLayer/MainProjectBank; main ([Ljava/lang/String;)V  ApplicationLayer/LoginCheck
    Manager@SBI  	Hello@123    ApplicationLayer/ILoginCheck   ! 
loginCheck '(Ljava/lang/String;Ljava/lang/String;)Z	 # % $ java/lang/System & ' out Ljava/io/PrintStream; ) 5Invalid User Name or Password! Please try again later
 + - , java/io/PrintStream . / println (Ljava/lang/String;)V
 # 1 2 3 exit (I)V 5 org.sqlite.JDBC
 7 9 8 java/lang/Class : ; forName %(Ljava/lang/String;)Ljava/lang/Class;
 = ? > java/sql/DriverManager @ A getConnection )(Ljava/lang/String;)Ljava/sql/Connection; C Database connected E DataLayer/AccountHolder G Bruce I Wayne K male M Gowtham O 1,10th Main road,New Nagar
 D Q 
 R _(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V T ApplicationLayer/SavingAccount
 S V 
 W (LDataLayer/IAccountHolder;)V Y [ Z  ApplicationLayer/IServiceMethods \  displayCoupon
 + ^ .  ` Clark b Kent d Metropolitan f 2,12th Main road,Old Nagar
 h j i DataLayer/TotalLog k l get_instance ()LDataLayer/ITotalLog; n java/lang/StringBuilder p )Total account created in this session is 
 m r 
 / t v u DataLayer/ITotalLog w x get_total_Acc_Log ()J
 m z { | append (J)Ljava/lang/StringBuilder;
 m ~  � toString ()Ljava/lang/String;
 � � � java/lang/Exception �  printStackTrace � � � java/sql/Connection �  close args [Ljava/lang/String; login LApplicationLayer/ILoginCheck; con Ljava/sql/Connection; accNo I 
accHolder1 LDataLayer/IAccountHolder; user1 "LApplicationLayer/IServiceMethods; 
accHolder2 user2 log LDataLayer/ITotalLog; e Ljava/lang/Exception; StackMapTable � � java/lang/Throwable 
SourceFile MainProjectBank.java !                 
      /     *� �           	             	      � 	    �� Y� L+�  � � "(� *� 0M>4� 6W� <M� "B� *� DY �FH2JLN� P:� SY� U:� X � "� ]� DY �_a7Jce� P:� SY� U:� X � "� ]� g:� "� mYo� q� s � y� }� *� 2:� �,� � � 2:

� �� (:	,� � � 
:

� �	�,� � � 
:

� ��  % � � � � � � � % � �   � � � � � � � �     � %          !  #  %  +  1  9  Q  \ ) c * i , � - � 8 � 9 � ; � < � = � > � @ � E � F � G � I � C � E � F � G � I � L � E � F � G � I � �    �    � � �    � � �  # � � �  % � � �  Q i � �  \ ^ � �  � 9 � �  � . � �  �  � �  �  � �  �  � � 
 �  � � 
 �  � � 
 �   W 	� ! � �  �  �  �O �I �� 
 
 �  �      �  ��   �  �  H �  �    �PK   #�S��                      META-INF/MANIFEST.MF��  PK
 
    �S�A�U�  �               a   ApplicationLayer/FDAccount.javaPK
 
    �SxJ�y    $               ApplicationLayer/CurrentAccount.javaPK
 
    P�S!      !             �
  ApplicationLayer/ILoginCheck.javaPK
 
    ѮSD��    %             �  ApplicationLayer/IServiceMethods.javaPK
 
    "�S�a�  �                �  ApplicationLayer/CODAccount.javaPK
 
    w�S#J                   �  ApplicationLayer/Accounts.javaPK
 
    \�S�*я    #             &  ApplicationLayer/SavingAccount.javaPK
 
    ��S���                  h  ApplicationLayer/LoginCheck.javaPK
 
    ��S�D���   �                �   DataLayer/IBank.javaPK
 
    ��S���f%  f%               �!  DataLayer/OperationOnDB.javaPK
 
    ��Si{��2  2               kG  DataLayer/AccountHolder.javaPK
 
    H�S,�s�                 �O  DataLayer/TotalLog.javaPK
 
    ,�S�Zp�z  z               R  DataLayer/Bank.javaPK
 
    �S����                 �T  DataLayer/IOperationOnDB.javaPK
 
    d�S� 	                 W  DataLayer/IAccountHolder.javaPK
 
    ��S�]�   �                WZ  DataLayer/ITotalLog.javaPK
 
    �S� :�  �  &             +[  PresentationLayer/MainProjectBank.javaPK
 
    %�S��dR      
             z  .gitignorePK
 
    }�Sr�0  0  
             0z  .classpathPK
 
    r�S,����  �               �|  .projectPK
 
    w�S ꋷ  �               1~  ApplicationLayer/Accounts.classPK
 
    "�Sz�U8  8  !             %�  ApplicationLayer/CODAccount.classPK
 
    �S�/�f�  �  %             ��  ApplicationLayer/CurrentAccount.classPK
 
    �S��&u3  3                {�  ApplicationLayer/FDAccount.classPK
 
    P�S*���   �   "             �  ApplicationLayer/ILoginCheck.classPK
 
    ѮSA�H  H  &             �  ApplicationLayer/IServiceMethods.classPK
 
    ��S���v�  �  !             o�  ApplicationLayer/LoginCheck.classPK
 
    ѮS��6M�  �  $             E�  ApplicationLayer/SavingAccount.classPK
 
    ��S'i0	  0	                �  DataLayer/AccountHolder.classPK
 
    }�Sxu��0  0               ��  DataLayer/Bank.classPK
 
    d�SPկXX  X               ��  DataLayer/IAccountHolder.classPK
 
    }�S���L                 ��  DataLayer/IBank.classPK
 
    �S�Ύ�  �               ��  DataLayer/IOperationOnDB.classPK
 
    }�S�H,�   �                ��  DataLayer/ITotalLog.classPK
 
    ��S�+?�&  �&               ��  DataLayer/OperationOnDB.classPK
 
    }�S�?s'*  *               ��  DataLayer/TotalLog.classPK
 
    �Sl>�"l  l  '             �  PresentationLayer/MainProjectBank.classPK    & & 	  ��    