package DataLayer;

import java.sql.*;


import PresentationLayer.MainProjectBank;

public class OperationOnDB implements IOperationOnDB {
	
	AccountHolder accHolder=null;
	public OperationOnDB(AccountHolder obj)
	{
		accHolder=obj;
	}
	
	public void setChecker(int accNo,int checker)
	{
		String db_URL=MainProjectBank.db_URL;
		String update_sql1="UPDATE AccountDetails SET Checker=? WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setChecker(int accNo)
	{
		String db_URL=MainProjectBank.db_URL;
		String update_sql1="UPDATE AccountDetails SET Checker=? WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		int checker=getChecker(accNo)+1;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public int getChecker(int accNo)
	{
		int checker=0;
		String db_URL=MainProjectBank.db_URL;
		String select_sql="SELECT Checker FROM AccountDetails WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return checker;
	}
	public void getNotifications(int accNo) {
		String db_URL=MainProjectBank.db_URL;
		String select_sql="SELECT AccNo,Time,Action FROM Notifications WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setNotifications(int accNo,String action) {
		String db_URL=MainProjectBank.db_URL;
		String insert_sql="INSERT INTO Notifications (AccNo,Time,Action) VALUES (?,CURRENT_TIMESTAMP,?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void totalAccountHolderDetails() {
		
		String db_URL=MainProjectBank.db_URL;
		String select_sql2="SELECT AccHolderId,FirstName,LastName,Age,Gender,PhoneNo,EmailId FROM AccHolderDetails";
		String select_sql3="SELECT City,AddressLine FROM AddressDetails";
		Connection con=null;
		Statement stmt=null;
		Lists li=Lists.get_instance();
		try {
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery(select_sql2);
			con.commit();
			while(rs1.next()) {
				li.accountHolderIdL.add(rs1.getInt("AccHolderId"));
				li.firstNameL.add(rs1.getString("FirstName"));
				li.lastNameL.add(rs1.getString("LastName"));
				li.ageL.add(rs1.getInt("Age"));
				li.genderL.add(rs1.getString("Gender"));
				li.phoneNoL.add(rs1.getString("PhoneNo"));
				li.emailIdL.add(rs1.getString("EmailId"));
			}
			rs1.close();
			stmt.close();
			
			stmt=con.createStatement();
			ResultSet rs2=stmt.executeQuery(select_sql3);
			con.commit();
			while(rs2.next()) {
				li.cityL.add(rs2.getString("City"));
				li.addressL.add(rs2.getString("AddressLine"));
				
			}
			rs2.close();
			stmt.close();
			
			
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
	public void totalAccount() {
		String db_URL=MainProjectBank.db_URL;
		String select_sql1="SELECT AccNo from AccountDetails";
		Connection con=null;
		Statement stmt=null;
		Lists li=Lists.get_instance();
		try {
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(select_sql1);
			con.commit();
			while(rs.next()) {
				li.accountNumberL.add(rs.getInt("AccNo"));
			}
			rs.close();
			stmt.close();
			
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
	public int totalNoOfAccount() {
		int total=0;
		String db_URL=MainProjectBank.db_URL;
		String select_sql1="SELECT AccNo from AccountDetails";
		Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return total;
	}
	public double totalAmountInBank() {
		
		double totalAmount=0;
		String db_URL=MainProjectBank.db_URL;
		String select_sql1="SELECT MainBalance from Balance";
		Connection con=null;
		Statement stmt=null;
		//Lists li=Lists.get_instance();
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return totalAmount;
	}
	public double getCoupons(int accNo) {
		
		double amount=0;
		String db_URL=MainProjectBank.db_URL;
		String select_sql1="SELECT AmountWon from Coupons WHERE (AccNo=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return amount;
	}
	public void addCoupons(int accNo,double amount) {
		
		double cur_amount;
		String db_URL=MainProjectBank.db_URL;
		String update_sql1="UPDATE Coupons SET AmountWon=? WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		cur_amount=amount+getCoupons(accNo);
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public boolean getAccountStatus(int accNo) 
	{
		boolean result=false;
		String db_URL=MainProjectBank.db_URL;
		String select_sql1="SELECT Status from AccountDetails WHERE (AccNo=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
	public void changeAccountStatus(int accNo,boolean status) {
		
		String db_URL=MainProjectBank.db_URL;
		String update_sql1="UPDATE AccountDetails SET Status=? WHERE AccNo=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final void showTransactionHistory(int accNo)
	{
		String db_URL=MainProjectBank.db_URL; 
		String select_sql1="SELECT AccountNumber,Time,Action,OldBalance,CurrentBalance from BalanceLog WHERE (AccountNumber=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
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
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public final boolean accountCheck(int accNo) {

		boolean result=false;
		String db_URL=MainProjectBank.db_URL; 
		String select_sql1="SELECT AccNo from AccountDetails WHERE (AccNo=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		int existing_accNo;
		try {
			con=DriverManager.getConnection(db_URL);
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
	public final boolean accountCheck(int accNo,String phoneNo)
	{
		boolean result=false;
		String db_URL=MainProjectBank.db_URL; 
		String select_sql1="SELECT AccHolderId from AccountDetails WHERE (AccNo=?)";
		String select_sql2="SELECT AccHolderId FROM AccHolderDetails WHERE (PhoneNo=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		int first_accHolderId=0,second_accHolderId=1;
		try {
			con=DriverManager.getConnection(db_URL);
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
	public final void getAccountDetails(int accNo)
	{
		String db_URL=MainProjectBank.db_URL; 
		String select_sql1="SELECT AccHolderId,AccTyId from AccountDetails where (AccNo=?)"; 
		String select_sql2="SELECT FirstName,LastName,Age,Gender,PhoneNo,EmailId FROM AccHolderDetails WHERE (AccHolderId=?)";
		String select_sql3="SELECT City,AddressLine FROM AddressDetails WHERE (AccHolderId=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		accHolder.setAccNo(accNo);
		try {
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql1);
			pstmt.setInt(1, accNo);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			accHolder.setAccHolderId(rs.getInt("AccHolderId"));
			accHolder.setAccTypeId(rs.getInt("AccTyId"));
			rs.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql2);
			pstmt.setInt(1, accHolder.getAccHolderId());
			ResultSet rs1=pstmt.executeQuery();
			con.commit();
			accHolder.setAccHolderFirstName(rs1.getString("FirstName"));
			accHolder.setAccHolderLastName(rs1.getString("LastName"));
			accHolder.setAccHolderAge(rs1.getInt("Age"));
			accHolder.setAccHolderGender(rs1.getString("Gender"));
			accHolder.setAccHolderPhoneNo(rs1.getString("PhoneNo"));
			accHolder.setAccHolderEmailId(rs1.getString("EmailId"));
			rs1.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql3);
			pstmt.setInt(1, accHolder.getAccHolderId());
			ResultSet rs2=pstmt.executeQuery();
			con.commit();
			accHolder.setAccHolderCity(rs2.getString("City"));
			accHolder.setAccHolderAddress(rs2.getString("AddressLine"));
			rs2.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			accHolder.showAccountDetails();
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
//	public void accountAction(int accNo,String action) {
//		
//		String db_URL=MainProjectBank.db_URL; 
//		String update_sql1="UPDATE BalanceLog SET Action=? WHERE AccNo=?"; 
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		try {
//			con=DriverManager.getConnection(db_URL);
//			con.setAutoCommit(false);
//			pstmt=con.prepareStatement(update_sql1);
//			pstmt.setString(1, action.toUpperCase());
//			pstmt.setInt(2, accNo);
//			pstmt.executeUpdate();
//			con.commit();
//			pstmt.close();
//			
//			con.setAutoCommit(true);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				con.close();
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public final void createAccount()
	{
		
	String insert_sql1="INSERT INTO AccHolderDetails (AccHolderId,FirstName,LastName,Age,Gender,PhoneNo,EmailId) VALUES (?,?,?,?,?,?,?)";
	String insert_sql2="INSERT INTO AddressDetails (AccHolderId,City,AddressLine) VALUES (?,?,?)";
	String insert_sql3="INSERT INTO AccountDetails (AccHolderId,AccTyId) VALUES (?,?)";
	String insert_sql4="INSERT INTO Balance (AccNo) VALUES (?)";
	String insert_sql5="INSERT INTO CustamoreLoginDetails (Cust_UserName,Cust_Password,AccNo) VALUES (?,?,?)";
	String db_URL=MainProjectBank.db_URL;              
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
	finally {
		try {
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	}
	public final void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String phoneNo,String emailId)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String update_sql="UPDATE AccHolderDetails SET  FirstName=?,LastName=?,Age=?,Gender=?,PhoneNo=?,EmailId=? WHERE AccHolderId=?";
		accHolder.setAccHolderId(accHolderId);
		accHolder.setAccHolderFirstName(fName);
		accHolder.setAccHolderLastName(lName);
		accHolder.setAccHolderAge(age);
		accHolder.setAccHolderGender(gender);
		accHolder.setAccHolderPhoneNo(phoneNo);
		accHolder.setAccHolderEmailId(emailId);
		try
		{
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, accHolder.getAccHolderFirstName());
			pstmt.setString(2, accHolder.getAccHolderLastName());
			pstmt.setInt(3, accHolder.getAccHolderAge());
			pstmt.setString(4, accHolder.getAccHolderGender());
			pstmt.setString(5, accHolder.getAccHolderPhoneNo());
			pstmt.setString(6, accHolder.getAccHolderEmailId());
			pstmt.setInt(7, accHolder.getAccHolderId());
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
	public final void updateAddressDetails(int accHolderId,String city,String addressLine)
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		String db_URL=MainProjectBank.db_URL;
		String update_sql="UPDATE AddressDetails SET  City=?,AddressLine=? WHERE AccHolderId=?";
		accHolder.setAccHolderId(accHolderId);
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

