package ApplicationLayer;

public interface IServiceMethods {
	
	public void getAccountDetails(int accNo);
    public void createAccount();
    public void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String phoneNo,String emailId);
    public void updateAddressDetails(int accHolderId,String city,String addressLine);
    public void deleteAccount(int accHolderId);
    public void deleteAccount(int accHolderId,int accTyId);
    public int getAccountNo(int accHolderId,int accTyId);
    public double getMainBalance(int accNo);
    public void changeMainBalance(int accNo,double mainBalance);
    public void showBalance(int accNo);
    public void transferAmount(int accNo,int dest_accNo,String phoneNo,double amount);
    public void showTransactionHistory(int accNo);
    public boolean getAccountStatus(int accNo);
    public void changeAccountStatus(int accNo,boolean status);
    public void get_totalAccountHolderDetails();
    public double get_totalAmountInBank();
    public void get_totalAccount();
    public int get_totalNoOfAccount();
    public void get_Notifications(int accNo);
    public void set_Notifications(int accNo,String action);
    public void set_Checker(int accNo);
	public int get_Checker(int accNo); 
	public void set_Checker(int accNo,int checker);
	public boolean account_Check(int accNo);
    
	public void debit(int accNo,double amount);
	public void credit(int accNo,double amount);
	
	public void createCoupon(int accNo,double amount);
	public void displayCoupon(int accNo);
	
}
