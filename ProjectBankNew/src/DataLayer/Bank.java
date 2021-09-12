package DataLayer;

import java.sql.*;

import PresentationLayer.MainProjectBank;

public class Bank {
	final String bankName="STATE BANK OK INDIA";
	final String bankAddress="123,1st Main road,Mumbai";
	final String bankIFSCCode="SBI0012345";
	int custAccNo=0,empId=0;
	public String getBankname() {
		return bankName;
	}
	public String getBankaddress() {
		return bankAddress;
	}
	public String getBankIFSCCode() {
		return bankIFSCCode;
	}
	public int getCustAccNo() {
		return custAccNo;
	}
	public int getEmpId() {
		return empId;
	}
	
	public final synchronized boolean custamore_Login_Credentials(String userName) {
		boolean result=false;
		String db_url=MainProjectBank.db_URL;
		String url2="SELECT accNo FROM CustamoreLoginDetails WHERE (Cust_UserName=? )";
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
	public final synchronized boolean custamore_Login_Credentials(String userName,String password)
	{
		boolean result=false;
		String db_url=MainProjectBank.db_URL;
		String url2="SELECT accNo FROM CustamoreLoginDetails WHERE (Cust_UserName=? AND Cust_Password=?)";
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

}
