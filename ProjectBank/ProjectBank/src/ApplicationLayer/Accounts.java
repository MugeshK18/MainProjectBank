package ApplicationLayer;


import DataLayer.AccountHolder;
import DataLayer.IOperationOnDB;
import DataLayer.Lists;
import DataLayer.OperationOnDB;
import DataLayer.*;

public abstract class Accounts implements IServiceMethods {
	
	double mainBalance,dest_mainBalance;
	IOperationOnDB accountHolder=null;
	double discountAmount=0;
	Lists listss=Lists.get_instance();
	
//	public abstract void debit(int accNo,double amount);
//	public abstract void credit(int accNo,double amount);
	
	public Accounts(AccountHolder obj) {
			accountHolder=new OperationOnDB(obj);
	}
	
	public final void getAccountDetails(int accNo) {
		accountHolder.getAccountDetails(accNo);
	}
	public void createAccount()                    //
	{
		accountHolder.createAccount();
	}
	public final void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String phoneNo,String emailId)
	{
		accountHolder.updateAccountHolderDetails(accHolderId,fName,lName,age,gender,phoneNo,emailId);
	}
	public final void updateAddressDetails(int accHolderId,String city,String addressLine)
	{
		accountHolder.updateAddressDetails(accHolderId,city,addressLine);
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
	public void transferAmount(int accNo,int dest_accNo,String phoneNo,double amount) {
		
		if(accountHolder.accountCheck(dest_accNo,phoneNo))
		{
			mainBalance=getMainBalance(accNo);
			if(amount<mainBalance)
			{
				mainBalance=mainBalance-amount;
				changeMainBalance(accNo,mainBalance);
				dest_mainBalance=getMainBalance(dest_accNo);
				dest_mainBalance=dest_mainBalance+amount;
				changeMainBalance(dest_accNo,dest_mainBalance);
				System.out.println("Transaction Completed!! "+amount+" is successfully transfered to account No."+dest_accNo+" from accNo."+accNo);
			}
			else {
				System.out.println("Transaction failed!! No sufficient Balance");
				System.out.println();
			}
		}
		else {
			System.out.println("There is no account(no: "+dest_accNo+") exist in our record! please try again.");
			System.out.println();
		}
	}
	public void showTransactionHistory(int accNo) {
		accountHolder.showTransactionHistory(accNo);
	}
	public boolean getAccountStatus(int accNo) {
		return accountHolder.getAccountStatus(accNo);
	}
	public void changeAccountStatus(int accNo,boolean status) {
		accountHolder.changeAccountStatus(accNo, status);
	}
	public void get_totalAccountHolderDetails() {
		
		
		accountHolder.totalAccountHolderDetails();
		int size=listss.accountHolderIdL.size();
		System.out.println("Total Account Holder Details:-");
		for(int i=0;i<size;i++) {
		System.out.print("AccountHolderId: "+listss.accountHolderIdL.get(i));
		System.out.print(" FristName: "+listss.firstNameL.get(i));
		System.out.print(" LastName: "+listss.lastNameL.get(i));
		System.out.print(" Age: "+listss.ageL.get(i));
		System.out.print(" Gender: "+listss.genderL.get(i));
		System.out.print(" PhoneNo: "+listss.phoneNoL.get(i));
		System.out.print(" EmailId: "+listss.emailIdL.get(i));
		System.out.print(" City: "+listss.cityL.get(i));
		System.out.print(" Address: "+listss.addressL.get(i));
		System.out.println();
		}
		System.out.println();
	}
	public double get_totalAmountInBank() {
		
		return accountHolder.totalAmountInBank();
	}
	public void get_totalAccount() {
		accountHolder.totalAccount();
		System.out.println("All Account Number exist in the Database:-");
		System.out.println("Account Number ");
		int size=listss.accountNumberL.size();
		for(int i=0;i<size;i++) {
			System.out.println(" "+listss.accountNumberL.get(i));
		}
		System.out.println();
	}
	public int get_totalNoOfAccount()
	{
		ITotalLog totalLog=TotalLog.get_instance();
		return totalLog.get_totalNoOfAccount();
	}
	public void get_Notifications(int accNo) {
		accountHolder.getNotifications(accNo);
	}
	public void set_Notifications(int accNo,String action) {
		accountHolder.setNotifications(accNo, action);
	}
	public void set_Checker(int accNo) {
		accountHolder.setChecker(accNo);
	}
	public void set_Checker(int accNo,int checker) {
		accountHolder.setChecker(accNo, checker);
	}
	public int get_Checker(int accNo) {
		return accountHolder.getChecker(accNo);
	}
	public boolean account_Check(int accNo) {
		return accountHolder.accountCheck(accNo);
	}
	
}
