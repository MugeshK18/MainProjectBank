package DataLayer;

public final class AccountHolder {
	private final int accHolderId;
	private final String accHolderFirstName;
	private final String accHolderLastName;
	private final int accHolderAge;
	private final String accHolderGender;
	private final int accTypeId;
	private final String accHolderAddress;
	private final String accHolderCity;
	private final String custUserName,custPassword;
	int accNo=0;
	private final String accHolderPhoneNo,accHolderEmailId;
	
	public AccountHolder(int accHolderId,String fName,String lName,int age,String gender,String city,String address,String phoneNo,String emailId,String userName,String password,int accTyId) {
		
		 this.accHolderId=accHolderId;
		 accTypeId=accTyId;
		 accHolderFirstName=fName.toUpperCase();
		 accHolderLastName=lName.toUpperCase(); 
		 accHolderAge=age;
		 accHolderGender=gender.toUpperCase();
		 accHolderCity=city.toUpperCase();
		 accHolderAddress=address.toUpperCase();
		 accHolderPhoneNo=phoneNo;
		 accHolderEmailId=emailId.toLowerCase();
		 custUserName=userName.toLowerCase();
		 custPassword=password;
	}
//	public AccountHolder() {
//		custUserName=null;
//		custPassword=null;
//	}

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
	public String getCustUserName() {
		return custUserName;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public int getAccNo() {
		return accNo;
	}
	public String getAccHolderPhoneNo() {
		return accHolderPhoneNo;
	}
	public String getAccHolderEmailId() {
		return accHolderEmailId;
	}
	
//	public void setAccHolderPhoneNo(String accHolderPhoneNo) {
//		this.accHolderPhoneNo = accHolderPhoneNo;
//	}
//	public void setAccHolderEmailId(String accHolderEmailId) {
//		this.accHolderEmailId = accHolderEmailId;
//	}
//	public void setAccHolderId(int accHolderId) {
//		this.accHolderId = accHolderId;
//	}
//	public void setAccHolderFirstName(String accHolderFirstName) {
//		this.accHolderFirstName = accHolderFirstName.toUpperCase();
//	}
//	public void setAccHolderLastName(String accHolderLastName) {
//		this.accHolderLastName = accHolderLastName.toUpperCase();
//	}
//	public void setAccHolderAge(int accHolderAge) {
//		this.accHolderAge = accHolderAge;
//	}
//	public void setAccHolderGender(String accHolderGender) {
//		this.accHolderGender = accHolderGender.toUpperCase();
//	}
//	public void setAccTypeId(int accTypeId) {
//		this.accTypeId = accTypeId;
//	}
//	public void setAccHolderAddress(String accHolderAddress) {
//		this.accHolderAddress = accHolderAddress;
//	}
//	public void setAccHolderCity(String accHolderCity) {
//		this.accHolderCity = accHolderCity;
//	}
//	public void setAccNo(int accNo) {
//		this.accNo=accNo;
//	}
//	public void showAccountDetails() {
//		
//		System.out.println("Account Holder Information:-\n");
//		System.out.println("Account Holder Id : "+accHolderId);
//		System.out.println("Account Number    : "+accNo);
//		switch(accTypeId) {
//		case 1:
//			System.out.println("Account Type      : Savings Account");
//			break;
//		case 2:
//			System.out.println("Account Type 	  : Current Account");
//			break;
//		case 3:
//			System.out.println("Account Type 	  : Fixed Deposit");
//			break;
//		case 4:
//			System.out.println("Account Type 	  : Certificate Of Deposit");
//			break;
//		}
//		System.out.println("Name        : "+accHolderFirstName+" "+accHolderLastName);
//		System.out.println("Age         : "+accHolderAge);
//		System.out.println("Gender      : "+accHolderGender);
//		System.out.println("city        : "+accHolderCity);
//		System.out.println("AddressLine : "+accHolderAddress);
//		System.out.println("Phone Number: "+accHolderPhoneNo);
//		System.out.println("Email Id    : "+accHolderEmailId);
//		Bank bank=new Bank();
//		System.out.println("Bank Information:-\n");
//		System.out.println("Bank Name : "+bank.getBankname());
//		System.out.println("IFSC Code : "+bank.getBankIFSCCode());
//		System.out.println("Bank Address : "+bank.getBankaddress());
//		System.out.println();
//	}
}
