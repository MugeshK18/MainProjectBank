package DataLayer;

public class EmployeeData {
	
	String empFirstName,empLastName,empGender,empPhoneNo,empEmailId,empDesignation,empAddress;
	int empId,empAge;
	public EmployeeData(String empFirstName, String empLastName, String empGender, String empPhoneNo, String empEmailId,
			String empDesignation, String empAddress, int empId, int empAge) {
		
		this.empFirstName = empFirstName.toUpperCase();
		this.empLastName = empLastName.toUpperCase();
		this.empGender = empGender.toUpperCase();
		this.empPhoneNo = empPhoneNo;
		this.empEmailId = empEmailId.toLowerCase();
		this.empDesignation = empDesignation;
		this.empAddress = empAddress;
		this.empId = empId;
		this.empAge = empAge;
	}
	public EmployeeData() {
		
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public String getEmpGender() {
		return empGender;
	}
	public String getEmpPhoneNo() {
		return empPhoneNo;
	}
	public String getEmpEmailId() {
		return empEmailId;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public int getEmpId() {
		return empId;
	}
	public int getEmpAge() {
		return empAge;
	}
	
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public void setEmpPhoneNo(String empPhoneNo) {
		this.empPhoneNo = empPhoneNo;
	}
	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public void showEmpDetails() {
		
		System.out.println("Employee Information:-");
		System.out.println("Id  : "+empId);
		System.out.println("Designation: "+empDesignation);
		System.out.println("First Name : "+empFirstName);
		System.out.println("Last Name  : "+empLastName);
		System.out.println("Age        : "+empAge);
		System.out.println("Gender     : "+empGender);
		System.out.println("Address    : "+empAddress);
		System.out.println("PhoneNo    : "+empPhoneNo);
		System.out.println("Email Id   : "+empEmailId);
		System.out.println();
	}
	
}
