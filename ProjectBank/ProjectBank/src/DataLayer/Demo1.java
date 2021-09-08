package DataLayer;

public class Demo1 implements IOperationOnDB{

	AccountHolder accHolder=null;
	public Demo1(AccountHolder obj) {
		accHolder=obj;
	}
	public  void getAccountDetails(int accNo) {}
	public  void createAccount() {}
	public  void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String PhoneNo,String EmailId) {}
	public  void updateAddressDetails(int accHolderId,String city,String addressLine) {}
	public  void deleteAccount(int accHolderId) {}
	public  void deleteAccount(int accHolderId,int accTyId) {}
	public  int getAccountNumber(int accHolderId,int accTyId) {
		return 0;
	}
	public  double getMainBalance(int accNo) {
		return 0;
	}
	public  void changeMainBalance(int accNo,double mainBalance) {}
	public  boolean accountCheck(int accNo,String phoneNo) {
		return false;
	}
	public  void showTransactionHistory(int accNo) {}
	public boolean getAccountStatus(int accNo) {
		return false;
	}
	public void changeAccountStatus(int accNo,boolean status) {}
	//public void accountAction(int accNo,String action);
	public void totalAccountHolderDetails() {}
	public void totalAccount() {}
	public int totalNoOfAccount() {
		return 0;
	}
	public double totalAmountInBank() {
		return 0;
	}
	public void setNotifications(int accNo,String action) {}
	public void getNotifications(int accNo) {}
	public void setChecker(int accNo) {}
	public int getChecker(int accNo) {
		return 0;
	}
	public void setChecker(int accNo,int checker){}
	public boolean accountCheck(int accNo){
		return false;
	}
	
	public void addCoupons(int accNo,double amount){}
	public double getCoupons(int accNo){
		return 0;
	}
}
