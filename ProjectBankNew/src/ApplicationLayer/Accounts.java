package ApplicationLayer;


import DataLayer.AccountHolder;
import DataLayer.IOperationOnDB;
import DataLayer.Lists;
import DataLayer.OperationOnDB;
import DataLayer.*;

public abstract class Accounts implements IServiceMethods {
	
	double mainBalance,dest_mainBalance;
	IOperationOnDB account=OperationOnDB.get_instance();
	double discountAmount=0;
	Lists listss=Lists.get_instance();
	int accTypeId;
	
//	public abstract void debit(int accNo,double amount);
//	public abstract void credit(int accNo,double amount);
	
	public Accounts() {
			
	}
	
	public final void closeConnection() {
		account.closeTheConnection();
	}
	public final int get_AccountType(int accNo) {
		return account.getAccountType(accNo);
	}
	public final void getAccountDetails(int accNo) {
		account.getAccountDetails(accNo);
	}
	public void createAccount(int accHolderId,String fName,String lName,int age,String gender,String city,String address,String phoneNo,String emailId,String userName,String password)                    //
	{
		AccountHolder accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,address,phoneNo,emailId,userName,password,accTypeId);
		account.createAccount(accHolder);
	}
	public final void updateAccountHolderDetails(int accHolderId,String fName,String lName,int age,String gender,String phoneNo,String emailId)
	{
		account.updateAccountHolderDetails(accHolderId,fName,lName,age,gender,phoneNo,emailId);
	}
	public final void updateAddressDetails(int accHolderId,String city,String addressLine)
	{
		account.updateAddressDetails(accHolderId,city,addressLine);
	}
	public void updateName(int accHolderId,String fName,String lName) {
		account.updateName(accHolderId,fName,lName);
		System.out.println("Updating Name Successfull!");
	}
	public void updateAge(int accHolderId,int age) {
		account.updateAge(accHolderId,age);
		System.out.println("Updating Age Successfull!");
	}
	public void updateGender(int accHolderId,String gender) {
		account.updateGender(accHolderId,gender);
		System.out.println("Updating Gender Successfull!");
	}
	public void updatePhoneNo(int accHolderId,String phoneNo) {
		account.updatePhoneNo(accHolderId,phoneNo);
		System.out.println("Updating Phone No. Successfull!");
	}
	public void updateEmailId(int accHolderId,String emailId) {
		account.updateEmailId(accHolderId,emailId);
		System.out.println("Updating Email Id Successfull!");
	}
	public void updateCity(int accHolderId,String city) {
		account.updateCity(accHolderId,city);
		System.out.println("Updating City Successfull!");
	}
	public void updateAddress(int accHolderId,String address) {
		account.updateAddress(accHolderId,address);
		System.out.println("Updating Address Successfull!");
	}
	public final void deleteAccount(int accHolderId)
	{
		account.deleteAccount(accHolderId);
	}
	public final void deleteAccount(int accHolderId,int accTyId)
	{
		account.deleteAccount(accHolderId,accTyId);
	}
	public final int getAccountNo(int accHolderId,int accTyId)
	{
		return account.getAccountNumber(accHolderId,accTyId);
	}
	public final double getMainBalance(int accNo)
	{
		return account.getMainBalance(accNo);
	}
	public final void changeMainBalance(int accNo,double mainBalance)
	{
		account.changeMainBalance(accNo,mainBalance);
	}
	public final void showBalance(int accNo)
	{
		System.out.println("Available Balance is: "+getMainBalance(accNo));
	}
	public void transferAmount(int accNo,int dest_accNo,String phoneNo,double amount) {
		
		if(account.accountCheck(dest_accNo,phoneNo))
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
		account.showTransactionHistory(accNo);
	}
	public boolean getAccountStatus(int accNo) {
		return account.getAccountStatus(accNo);
	}
	public void changeAccountStatus(int accNo,boolean status) {
		account.changeAccountStatus(accNo, status);
	}
	public void get_totalAccountHolderDetails() {
		
		
		account.totalAccountHolderDetails();
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
		
		return account.totalAmountInBank();
	}
	public void get_totalAccount() {
		account.totalAccount();
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
		account.getNotifications(accNo);
	}
	public void set_Notifications(int accNo,String action) {
		account.setNotifications(accNo, action);
	}
	public void set_Checker(int accNo) {
		account.setChecker(accNo);
	}
	public void set_Checker(int accNo,int checker) {
		account.setChecker(accNo, checker);
	}
	public int get_Checker(int accNo) {
		return account.getChecker(accNo);
	}
	public boolean account_Check(int accNo) {
		return account.accountCheck(accNo);
	}
	
}
