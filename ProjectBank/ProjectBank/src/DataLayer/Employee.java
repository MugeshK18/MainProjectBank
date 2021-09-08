package DataLayer;

import java.sql.*;


import PresentationLayer.MainProjectBank;

public class Employee implements IEmployee{
	
	EmployeeData emp=null;
	public Employee(EmployeeData obj) {
		emp=obj;
	}
	public void getEmpDetails(int empId) {
		String db_URL=MainProjectBank.db_URL;
		String select_sql="SELECT EmpFirstName,EmpLastName,EmpAge,EmpGender,EmpAddress,EmpPhoneNo,EmpEmailId,EmpDesignation FROM EmployeeDetails WHERE (EmpId=?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DriverManager.getConnection(db_URL);
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, empId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			emp.setEmpFirstName(rs.getString("EmpFirstName"));
			emp.setEmpLastName(rs.getString("EmpLastName"));
			emp.setEmpAge(rs.getInt("EmpAge"));
			emp.setEmpGender(rs.getString("EmpGender"));
			emp.setEmpAddress(rs.getString("EmpAddress"));
			emp.setEmpPhoneNo(rs.getString("EmpPhoneNo"));
			emp.setEmpEmailId(rs.getString("EmpEmailId"));
			emp.setEmpDesignation(rs.getString("EmpDesignation"));
			emp.setEmpId(empId);
			rs.close();
			pstmt.close();
			con.setAutoCommit(true);
			emp.showEmpDetails();
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

}
