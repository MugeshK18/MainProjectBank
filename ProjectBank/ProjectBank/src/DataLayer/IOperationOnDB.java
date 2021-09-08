package DataLayer;

public interface IOperationOnDB {
	
	public void getAccountDetails(int accNo);
	public void createAccount();
	public void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String PhoneNo,String EmailId);
	public void updateAddressDetails(int accHolderId,String city,String addressLine);
	public void deleteAccount(int accHolderId);
	public void deleteAccount(int accHolderId,int accTyId);
	public int getAccountNumber(int accHolderId,int accTyId);
	public double getMainBalance(int accNo);
	public void changeMainBalance(int accNo,double mainBalance);
	public boolean accountCheck(int accNo,String phoneNo);
	public void showTransactionHistory(int accNo);
	public boolean getAccountStatus(int accNo);
	public void changeAccountStatus(int accNo,boolean status);
	//public void accountAction(int accNo,String action);
	public void totalAccountHolderDetails();
	public void totalAccount();
	public int totalNoOfAccount();
	public double totalAmountInBank();
	public void setNotifications(int accNo,String action);
	public void getNotifications(int accNo);
	public void setChecker(int accNo);
	public int getChecker(int accNo);
	public void setChecker(int accNo,int checker);
	public boolean accountCheck(int accNo);
	
	public void addCoupons(int accNo,double amount);
	public double getCoupons(int accNo);
	
}
