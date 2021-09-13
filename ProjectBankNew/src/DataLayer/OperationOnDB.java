package DataLayer;

import java.sql.*; 
import java.util.*;

import PresentationLayer.MainProjectBank;


public class OperationOnDB implements IOperationOnDB
{
	String DB_Url="jdbc:sqlite:C:\\\\sqlite3\\\\Accounts.db";
	Connection con=null;
	int custAccNo=0,empId=0;
	//Static Block
	{
		try {
		Class.forName("org.sqlite.JDBC");
		con=DriverManager.getConnection(DB_Url);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }	
	
	public static IOperationOnDB first_instance;
	
	private OperationOnDB() {       //private constructor
	}
	public static IOperationOnDB get_instance()      //get_instance method
	{
		if(first_instance==null)
			first_instance=new OperationOnDB();
		return first_instance;
	}
	
	public void closeTheConnection() {
		try {
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public final synchronized boolean customer_Login_Credentials(String userName) {
		boolean result=false;
		String db_url=MainProjectBank.db_URL;
		String url2="SELECT accNo FROM CustomerLoginDetails WHERE (Cust_UserName=? )";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(url2);
			pstmt.setString(1, userName);
			ResultSet rs=pstmt.executeQuery();
			custAccNo=rs.getInt("AccNo");
			if(custAccNo!=0) {
				result=true;
			}
			rs.close();
			pstmt.close();
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			result=false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
    	return result;
	}
	public final synchronized boolean customer_Login_Credentials(String userName,String password)
	{
		boolean result=false;
		String db_url=MainProjectBank.db_URL;
		String url2="SELECT accNo FROM CustomerLoginDetails WHERE (Cust_UserName=? AND Cust_Password=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(url2);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			custAccNo=rs.getInt("AccNo");
			if(custAccNo!=0) {
				result=true;
			}
			rs.close();
			pstmt.close();
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			result=false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public final synchronized boolean emp_Login_Credentials(String userName,String password)
	{
		boolean result=false;
		String db_url=MainProjectBank.db_URL;
		String url1="SELECT Emp_Id FROM EmpLoginDetails WHERE (Emp_UserName=? AND Emp_Password=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			
			con=DriverManager.getConnection(db_url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(url1);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			empId=rs.getInt("Emp_Id");
			if(empId!=0) {
				result=true;
			}
			rs.close();
			pstmt.close();
			con.setAutoCommit(true);
		}
		catch(Exception e){
			result=false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int getCustAccNo() {
		return custAccNo;
	}
	public int getEmpId() {
		return empId;
	}
	public int getAccountType(int accNo) {
		int accType=0;
		String select_sql="SELECT AccTyId FROM AccountDetails WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1,accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			accType=rs.getInt("AccTyId");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return accType;
	}
	public void setChecker(int accNo,int checker)
	{
		
		String update_sql1="UPDATE AccountDetails SET Checker=? WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql1);
			pstmt.setInt(1, checker);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void setChecker(int accNo)
	{
		
		String update_sql1="UPDATE AccountDetails SET Checker=? WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		int checker=getChecker(accNo)+1;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql1);
			pstmt.setInt(1, checker);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public int getChecker(int accNo)
	{
		int checker=0;
		String select_sql="SELECT Checker FROM AccountDetails WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1,accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			checker=rs.getInt("Checker");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return checker;
	}
	public void getNotifications(int accNo) {
		
		String select_sql="SELECT AccNo,Time,Action FROM Notifications WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1,accNo);
			
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			while(rs.next()) {
				System.out.print("AccNo : "+rs.getInt("AccNo"));
				System.out.print("  Time : "+rs.getString("Time"));
				System.out.println("  Action : "+rs.getString("Action"));
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void setNotifications(int accNo,String action) {
		
		String insert_sql="INSERT INTO Notifications (AccNo,Time,Action) VALUES (?,CURRENT_TIMESTAMP,?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(insert_sql);
			pstmt.setInt(1,accNo);
			pstmt.setString(2, action);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void totalAccountHolderDetails() {
		
		String select_sql2="SELECT AccHolderId,FirstName,LastName,Age,Gender,PhoneNo,EmailId FROM AccHolderDetails";
		String select_sql3="SELECT City,AddressLine FROM AddressDetails";
		//Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery(select_sql2);
			con.commit();
//			while(rs1.next()) {
//				
//				
//				
////				li.accountHolderIdL.add();
////				li.firstNameL.add();
////				li.lastNameL.add();
////				li.ageL.add();
////				li.genderL.add(rs1.getString("Gender"));
////				li.phoneNoL.add(rs1.getString("PhoneNo"));
////				li.emailIdL.add(rs1.getString("EmailId"));
//			}
			
			//stmt.close();
			
			stmt=con.createStatement();
			ResultSet rs2=stmt.executeQuery(select_sql3);
			con.commit();
			System.out.println("Total Account Holder Details:-");
			while(rs1.next() && rs2.next()) {
				
				System.out.print(" AccountHolderId: "+rs1.getInt("AccHolderId"));
				System.out.print(" FirstName: "+rs1.getString("FirstName"));
				System.out.print(" LastName: "+rs1.getString("LastName"));
				System.out.print(" Age: "+rs1.getInt("Age"));
				System.out.print(" Gender: "+rs1.getString("Gender"));   //listss.genderL.get(i));
				System.out.print(" PhoneNo: "+rs1.getString("PhoneNo"));  //listss.phoneNoL.get(i));
				System.out.print(" EmailId: "+rs1.getString("EmailId"));   //listss.emailIdL.get(i));
				System.out.print(" City: "+rs2.getString("City"));        //listss.cityL.get(i));
				System.out.print(" Address: "+rs2.getString("AddressLine"));   //listss.addressL.get(i));
				System.out.println();
				
//				li.cityL.add(rs2.getString("City"));
//				li.addressL.add(rs2.getString("AddressLine"));				
			}
			System.out.println();
			rs1.close();
			rs2.close();
			stmt.close();
			
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void totalAccount() {
		
		String select_sql1="SELECT AccNo from AccountDetails";
		//Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(select_sql1);
			con.commit();
			System.out.println("All Account Number exist in the Database:-");
			System.out.println("Account Number ");
			while(rs.next()) {
				System.out.println(" "+rs.getInt("AccNo"));              //listss.accountNumberL.get(i));
				//li.accountNumberL.add(rs.getInt("AccNo"));
			}
			System.out.println();
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public int totalNoOfAccount() {
		
		int total=0;
		String select_sql1="SELECT AccNo from AccountDetails";
		//Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(select_sql1);
			con.commit();
			while(rs.next()) {
				total=total+1;
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return total;
	}
	public double totalAmountInBank() {
		
		double totalAmount=0;
		String select_sql1="SELECT MainBalance from Balance";
		//Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(select_sql1);
			con.commit();
			while(rs.next()) {
				totalAmount=totalAmount+rs.getDouble("MainBalance");
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return totalAmount;
	}
	public double getCoupons(int accNo) {
		
		double amount=0;
		String select_sql1="SELECT AmountWon from Coupons WHERE (AccNo=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			amount=rs.getDouble("AmountWon");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return amount;
	}
	public void addCoupons(int accNo,double amount) {
		
		double cur_amount;
		String update_sql1="UPDATE Coupons SET AmountWon=? WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		cur_amount=amount+getCoupons(accNo);
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql1);
			pstmt.setDouble(1, cur_amount);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public boolean getAccountStatus(int accNo) 
	{
		boolean result=false;
		String select_sql1="SELECT Status from AccountDetails WHERE (AccNo=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			
			//con=DriverManager.getConnection(DB_Url);
			
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			result=rs.getBoolean("Status");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return result;
	}
	public void changeAccountStatus(int accNo,boolean status) {
		
		String update_sql1="UPDATE AccountDetails SET Status=? WHERE AccNo=?";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql1);
			pstmt.setBoolean(1, status);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
			if(status) {
				System.out.println("Account Number ("+accNo+") is Re-Activated!!");
			}
			else {
				System.out.println("Account Number ("+accNo+") is temporarily suspended!!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public final void showTransactionHistory(int accNo)
	{
		 
		String select_sql1="SELECT AccountNumber,Time,Action,OldBalance,CurrentBalance from BalanceLog WHERE (AccountNumber=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			int check=0;
			
			while(rs.next()) {
				check=1;
				System.out.println("Acc No.: "+rs.getInt("AccountNumber")+" Time : "+rs.getString("Time")+
						     " Action : "+rs.getString("Action")+" OldBalance : "+rs.getDouble("OldBalance")+" Current Balance : "+rs.getDouble("CurrentBalance"));
			}
			if(check==0) {
				System.out.println("No recent Transaction");
			}
			System.out.println();
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public final boolean accountCheck(int accNo) {

		boolean result=false;
		String select_sql1="SELECT AccNo from AccountDetails WHERE (AccNo=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		int existing_accNo;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			existing_accNo=rs.getInt("AccHolderId");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			if(accNo==existing_accNo)
				result=true;
			
		}
		catch(Exception e) {
			result=false;	
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();	
//			}
//		}
		return result;
	}
	public final boolean accountCheck(int accNo,String phoneNo)
	{
		boolean result=false; 
		String select_sql1="SELECT AccHolderId from AccountDetails WHERE (AccNo=?)";
		String select_sql2="SELECT AccHolderId FROM AccHolderDetails WHERE (PhoneNo=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		int first_accHolderId=0,second_accHolderId=1;
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			first_accHolderId=rs.getInt("AccHolderId");
			rs.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql2);
			pstmt.setString(1, phoneNo);
			ResultSet rs1=pstmt.executeQuery();
			con.commit();
			second_accHolderId=rs1.getInt("AccHolderId");
			rs1.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			if(first_accHolderId==second_accHolderId) {
				result=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();	
//			}
//		}
		return result;
	}
	public final HashMap<String,Object> getAccountDetails(int accNo)                  //
	{
		String select_sql1="SELECT AccHolderId,AccTyId from AccountDetails where (AccNo=?)"; 
		String select_sql2="SELECT FirstName,LastName,Age,Gender,PhoneNo,EmailId FROM AccHolderDetails WHERE (AccHolderId=?)";
		String select_sql3="SELECT City,AddressLine FROM AddressDetails WHERE (AccHolderId=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		int accHolderId,accTyId;
		//int age;
		//String fName,lName,gender,phoneNo,emailId,city,addressLine;
		HashMap<String,Object> map=new HashMap<String,Object>();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			accHolderId=rs.getInt("AccHolderId");
			accTyId=rs.getInt("AccTyId");
			rs.close();
			pstmt.close();
			map.put("AccHolderId",accHolderId);
			map.put("AccTypeId",accTyId);
			map.put("AccNo", accNo);
			
			pstmt=con.prepareStatement(select_sql2);
			pstmt.setInt(1, accHolderId);
			ResultSet rs1=pstmt.executeQuery();
			con.commit();
			map.put("FirstName", rs1.getString("FirstName"));
			map.put("LastName",rs1.getString("LastName"));
			map.put("Age",rs1.getInt("Age"));
			map.put("Gender",rs1.getString("Gender"));
			map.put("PhoneNo",rs1.getString("PhoneNo"));
			map.put("EmailId",rs1.getString("EmailId"));
			rs1.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql3);
			pstmt.setInt(1, accHolderId);
			ResultSet rs2=pstmt.executeQuery();
			con.commit();
			map.put("City",rs2.getString("City"));
			map.put("AddressLine",rs2.getString("AddressLine"));
			rs2.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return map;
	}

	public final void createAccount(AccountHolder accHolder)
	{
		
	String insert_sql1="INSERT INTO AccHolderDetails (AccHolderId,FirstName,LastName,Age,Gender,PhoneNo,EmailId) VALUES (?,?,?,?,?,?,?)";
	String insert_sql2="INSERT INTO AddressDetails (AccHolderId,City,AddressLine) VALUES (?,?,?)";
	String insert_sql3="INSERT INTO AccountDetails (AccHolderId,AccTyId) VALUES (?,?)";
	String insert_sql4="INSERT INTO Balance (AccNo) VALUES (?)";
	String insert_sql5="INSERT INTO CustamoreLoginDetails (Cust_UserName,Cust_Password,AccNo) VALUES (?,?,?)";
	              
	int gen_accNo;
	//Connection con=null;
	PreparedStatement pstmt=null;
	try
	{
		//con=DriverManager.getConnection(DB_Url);
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(insert_sql1);
	    pstmt.setInt(1, accHolder.getAccHolderId());
		pstmt.setString(2, accHolder.getAccHolderFirstName());
		pstmt.setString(3, accHolder.getAccHolderLastName());
		pstmt.setInt(4, accHolder.getAccHolderAge());
		pstmt.setString(5, accHolder.getAccHolderGender());
		pstmt.setString(6, accHolder.getAccHolderPhoneNo());
		pstmt.setString(7, accHolder.getAccHolderEmailId());
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
		
		pstmt=con.prepareStatement(insert_sql5);
		pstmt.setString(1, accHolder.getCustUserName());
		pstmt.setString(2, accHolder.getCustPassword());
		pstmt.setInt(3, gen_accNo);
		pstmt.executeUpdate();
		con.commit();
		pstmt.close();
		
	    con.setAutoCommit(true);
	    TotalLog.get_instance().total_Acc_Log();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
//	finally {
//		try {
//			con.close();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	}
	public final void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String phoneNo,String emailId)
	{
		//Connection con=null;
		PreparedStatement pstmt= null;
		
		String update_sql="UPDATE AccHolderDetails SET  FirstName=?,LastName=?,Age=?,Gender=?,PhoneNo=?,EmailId=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, fName);
			pstmt.setString(2, lName);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, phoneNo);
			pstmt.setString(6, emailId);
			pstmt.setInt(7, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			System.out.println("Error at updateAccHolderDetails");
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void updateName(int accHolderId,String fName,String lName) {
		
        PreparedStatement pstmt= null;
		String update_sql="UPDATE AccHolderDetails SET  FirstName=?,LastName=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, fName.toUpperCase());
			pstmt.setString(2, lName.toUpperCase());
			pstmt.setInt(3, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateAge(int accHolderId,int age) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AccHolderDetails SET  Age=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setInt(1, age);
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateGender(int accHolderId,String gender) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AccHolderDetails SET  Gender=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, gender.toUpperCase());
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updatePhoneNo(int accHolderId,String phoneNo) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AccHolderDetails SET  PhoneNo=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, phoneNo);
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateEmailId(int accHolderId,String emailId) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AccHolderDetails SET  EmailId=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, emailId.toLowerCase());
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCity(int accHolderId,String city) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AddressDetails SET  City=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, city.toUpperCase());
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateAddress(int accHolderId,String address) {
		
		PreparedStatement pstmt= null;
		String update_sql="UPDATE AddressDetails SET  AddressLine=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, address);
			pstmt.setInt(2, accHolderId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public final void updateAddressDetails(int accHolderId,String city,String addressLine)
	{
		//Connection con=null;
		PreparedStatement pstmt= null;
		
		String update_sql="UPDATE AddressDetails SET  City=?,AddressLine=? WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, city);
			pstmt.setString(2, addressLine);
			pstmt.setInt(3, accHolderId);
			//System.out.println("commiting updateAddressDetails");
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.setAutoCommit(true);		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public final void deleteAccount(int accHolderId)
	{
		//Connection con=null;
		PreparedStatement pstmt= null;
		
		String delete_sql="DELETE FROM AccHolderDetails WHERE AccHolderId=?";
		String delete_sql2="DELETE FROM AddressDetails WHERE AccHolderId=?";
		String delete_sql3="DELETE FROM AccountDetails WHERE AccHolderId=?";
		String delete_sql4="DELETE FROM Balance WHERE AccNo=?";
		String delete_sql5="SELECT AccNo FROM AccountDetails WHERE AccHolderId=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
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
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public final void deleteAccount(int accHolderId,int accTyId)
	{
		//Connection con=null;
		PreparedStatement pstmt= null;
		
		String delete_sql3="DELETE FROM AccountDetails WHERE (AccHolderId=? AND AccTyId=?)";
		String delete_sql4="DELETE FROM Balance WHERE AccNo=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
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
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public final int getAccountNumber(int accHolderId,int accTyId)
	{
		int accNo=0;
		//Connection con=null;
		PreparedStatement pstmt=null;
		
		String select_sql="SELECT AccNo from AccountDetails where (AccHolderId=? AND AccTyId=?)";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
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
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return accNo;
	}
	public final double getMainBalance(int accNo)
	{
		double balance=0;
		//Connection con=null;
		
		PreparedStatement pstmt=null;
		String select_sql="SELECT MainBalance FROM Balance WHERE AccNo=?";
		try {
			//con=DriverManager.getConnection(DB_Url);
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
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return balance;
		
	}
	public final void changeMainBalance(int accNo,double mainBalance)
	{
		//Connection con=null;
		PreparedStatement pstmt=null;
		
		String update_sql="UPDATE Balance SET  MainBalance=? WHERE AccNo=?";
		try
		{
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false); 
			pstmt=con.prepareStatement(update_sql);
			pstmt.setDouble(1, mainBalance);
			pstmt.setInt(2, accNo);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.setAutoCommit(true);
			//System.out.println("Amount successfully added!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		finally
//		{
//			try{
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void getEmpDetails(int empId) {
		
		String select_sql="SELECT EmpFirstName,EmpLastName,EmpAge,EmpGender,EmpAddress,EmpPhoneNo,EmpEmailId,EmpDesignation FROM EmployeeDetails WHERE (EmpId=?)";
		//Connection con=null;
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=new HashMap<>();
		try {
			//con=DriverManager.getConnection(DB_Url);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, empId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			map.put("EmpFirstName",rs.getString("EmpFirstName"));
			map.put("EmpLastName",rs.getString("EmpLastName"));
			map.put("EmpAge",rs.getInt("EmpAge"));
			map.put("EmpGender",rs.getString("EmpGender"));
			map.put("EmpAddress",rs.getString("EmpAddress"));
			map.put("EmpPhoneNo",rs.getString("EmpPhoneNo"));
			map.put("EmpEmailId",rs.getString("EmpEmailId"));
			map.put("EmpDesignation",rs.getString("EmpDesignation"));
			map.put("EmpId",empId);
			rs.close();
			pstmt.close();
			con.setAutoCommit(true);
			
			for(Map.Entry<String,Object> e: map.entrySet()) {
				System.out.println(e.getKey()+" : "+e.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	
}

