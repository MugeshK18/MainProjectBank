package PresentationLayer;

import java.util.*;

import ApplicationLayer.*;
import DataLayer.*;

public class MainProjectBank {
	public static final String db_URL="jdbc:sqlite:C:\\sqlite3\\Accounts.db";

	public static void main(String[] args) {
		
		
		
		ILoginCheck login=new LoginCheck();
		try {
			
			String userName=null,password=null,action=null;
			int accNo=0,checker=0;
			//Lists lists=Lists.get_instance();
			Class.forName("org.sqlite.JDBC");
			Scanner sc=new Scanner(System.in);
			System.out.println("Welcome to SBI!!"+"\n*--------------------------*\n");
			while(true) {
				System.out.println("Log In Menu:-");
				System.out.println("Enter '1' for Customer"+"\nEnter '2' for Bank Employee\nEnter '3' for Terminate Program \n");
				int input=sc.nextInt();
				sc.nextLine();
				
				if(input==1) {                       //Custamore
					AccountHolder accHolder=null;
					IServiceMethods user=null;
					boolean status=false;
					System.out.println("Please Enter the UserName: ");
					userName=sc.nextLine();
					System.out.println("Please Enter your Password: ");
					password=sc.nextLine();
					if(!(login.cust_loginCheck(userName,password))) 
					{
						if(!(login.cust_loginCheck(userName))) {
							System.out.println("Invalid User Name or Password! Please try again later \n");
							continue;
						}
						accNo=login.getCustAccNo();
						System.out.println(accNo);
						if(user==null){
							accHolder=new AccountHolder();
							user=new SavingAccount(accHolder);
						}
//						if(!(user.account_Check(accNo))) {
//							System.out.println("Invalid User Name, Please try again later\n");
//							continue;
//						}
						
						user.set_Checker(accNo);
						checker=user.get_Checker(accNo);
						if(checker<3) {
							System.out.println("Invalid Password! Please try again later");
							System.out.println("Your have "+(3-checker)+" attempt left");
							System.out.println();
							continue;
						}
						else
						{
							status=false;
							user.changeAccountStatus(accNo, status);
							System.out.println("Due to exceeded attempt of log in, Your account is temporarily suspended");
							System.out.println();
							continue;
						}
					}
					else {
						accNo=login.getCustAccNo();
						if(user==null){
							accHolder=new AccountHolder();
							user=new SavingAccount(accHolder);
						}
						status=user.getAccountStatus(accNo);
						if(status) {
							checker=user.get_Checker(accNo);
							if(checker<3) {
							System.out.println("\nWelcome!! \nLog In Successfull!!\n");
							action="Successfull Log In ";
							user.set_Checker(accNo,0);
							user.set_Notifications(accNo, action);
							}
							else {
								status=false;
								user.changeAccountStatus(accNo,status);
								System.out.println("Due to exceeded attempt of log in, Your account is temporarily suspended");
								System.out.println();
								continue;
							}
						}
						else {
							System.out.println("Sorry!! Account is temporarily suspended. Please visit nearby branch"+"\n");
							action="Failed attempt to Log In";
							user.set_Notifications(accNo, action);
							continue;
						}
					}
					//Connection con=null;
					try {
	//					con=DriverManager.getConnection(db_URL);
	//					System.out.println("Database Connected");
						while(true)
						{
							
							System.out.println("Enter 1 for Accounts Details"+"\n"+"Enter 2 for Deposit amount"+"\n"+"Enter 3 for Withdraw amount"+"\n"+                       
									             "Enter 4 for CheckMyBalance"+"\n"+"Enter 5 for MiniStatement"+"\n"+"Enter 6 for Transfer amount"+"\n"
						                          +"Enter 7 for Coupons won"+"\n"+"Enter 8 for Account Notifications"+"\n"+"Enter 9 for Log Out"+
									               "Type a Value: ");
							int op=sc.nextInt();
							sc.nextLine();
							System.out.println();
							if(op==1) {              //Account Details
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.getAccountDetails(accNo);
							}
							else if(op==2) {                    //Deposit amount
								System.out.println("Enter the amount to be 'credited'");
								double amount=sc.nextDouble();
								sc.nextLine();
								System.out.println();
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.credit(accNo, amount);	
							}
							else if(op==3) {                        //withdraw amount
								//accNo=user.getAccountNo( accHolderId, accTyId);
								System.out.println("Enter the amount to be 'debited'");
								double amount=sc.nextDouble();
								sc.nextLine();
								System.out.println();
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.debit(accNo, amount);
							}
							else if(op==4) {                       //check your balance
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.showBalance(accNo);
								System.out.println();
							}
							else if(op==5) {                       //MiniStatement
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.showTransactionHistory(accNo);
								action="Printed MiniStatement";
								user.set_Notifications(accNo, action);
							}
							else if(op==6) {                      //Transfer amount
								
								System.out.println("Enter the beneficiary accNo: ");
								int dest_accNo=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the beneficiary Phone Number: ");
								String dest_phoneNo=sc.nextLine();
								System.out.println("Enter the amount that you want to send: ");
								double transfer_amount=sc.nextInt();
								sc.nextLine();
								System.out.println();
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.transferAmount(accNo,dest_accNo,dest_phoneNo,transfer_amount);
								System.out.println();
							}
							else if(op==7) {                        //coupons
	
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.displayCoupon(accNo);
								System.out.println();
							}
							else if(op==8) {                       // Notifications
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.get_Notifications(accNo);
								System.out.println();
								
							}
							else if(op==9) {
								System.out.println("Logged out successfully! Thank You!\n");
								System.out.println("/*********************/");
								break;
							}
							else {
								System.out.println("Unsuccessfull! Please Enter a option from 1 to 7!"+"\n");
								System.out.println();
							}
						}  //while ends
					} //try ends here
					catch(Exception e) {
						e.printStackTrace();
					}
					
				}  //if ends here
				else if(input==2) {                  //Employee
					int empId=0;
					System.out.println("Please Enter the UserName: ");
					userName=sc.nextLine();
					System.out.println("Please Enter your Password: ");
					password=sc.nextLine();
					if(!(login.emp_loginCheck(userName,password))) 
					{
						System.out.println("Invalid User Name or Password! Please try again later\n");
						continue;
					}
					else {
						empId=login.getEmpId();
						System.out.println("\nWelcome!! \nLog In Successfull!!");
						System.out.println();
					}
					AccountHolder accHolder=null;
					IServiceMethods user=null;
					IBankEmployee emp=null;
					EmployeeData empData=null;
					//Connection con=null;
					boolean status=true;
					int accHolderId=0,age,accTyId=1;
					String fName,lName,gender,city,addressLine,custUserName,custPassword,phoneNo,emailId;
					try {
	//					con=DriverManager.getConnection(db_URL);
	//					System.out.println("Database connected");
						while(true)
						{
							System.out.println("Enter 0 for creating new account"+"\n"+"Enter 1 for Updating account holder's details"+"\n"
						                       +"Enter 2 for updating account holder's address details"+"\n"+"Enter 3 for Deleting an existing account"
									            +"\n"+"Enter 4 for View Transaction History"+"\n"+"Enter 5 for Suspending an Account"+"\nEnter 6 for Account Status"
						                         +"\n"+"Enter 7 for Employee Account Details"+"\nEnter 8 for MORE"+"\nEnter 9 for Log Out: Type a value--");
							int op=sc.nextInt();
							sc.nextLine();
							System.out.println();
							if(op==0) {                                 //creating new account
								
								System.out.println("Enter the Account Holder Id: ");
								accHolderId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Account Holder's First Name: ");
								fName=sc.nextLine();
								System.out.println("Enter the Account Holder's Last Name: ");
								lName=sc.nextLine();
								System.out.println("Enter the Account Holder's Age: ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Account Holder's Gender: ");
								gender=sc.nextLine();
								System.out.println("Enter the Account Holder's City: ");
								city=sc.nextLine();
								System.out.println("Enter the Account Holder's FullAddress: ");
								addressLine=sc.nextLine();
								System.out.println("Enter your Phone Number: ");
								phoneNo=sc.nextLine();
								System.out.println("Enter your Email Id: ");
								emailId=sc.nextLine();
								System.out.println("Type of Account? 1-Savings,2-Current,3-Fixed,4-COD, Enter: ");
								accTyId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter a UserName for your acc: ");
								custUserName=sc.nextLine();
								System.out.println("Enter a password for your acc: ");
								custPassword=sc.nextLine();
								System.out.println("Please enter (y/n) to confirm to create an Account ");
								String s=sc.nextLine();	
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									if(accTyId==1){                          
										accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine,phoneNo,emailId,custUserName,custPassword);
										user=new SavingAccount(accHolder);
										System.out.println("Creating Account");
										user.createAccount();
										System.out.println("Savings Account is created Successfully!");
										System.out.println();
									}
									else if(accTyId==2) {
										accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine,phoneNo,emailId,custUserName,custPassword);
										user=new CurrentAccount(accHolder);
										System.out.println("Creating Account");
										user.createAccount();
										System.out.println("Current Account is created Successfully!");
										System.out.println();
									}
									else if(accTyId==3) {
										accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine,phoneNo,emailId,custUserName,custPassword);
										user=new FDAccount(accHolder);
										System.out.println("Creating Account");
										user.createAccount();
										System.out.println("Fixed Deposit Account is created Successfully!");
										System.out.println();
									}
									else if(accTyId==4) {
										accHolder=new AccountHolder(accHolderId,fName,lName,age,gender,city,addressLine,phoneNo,emailId,custUserName,custPassword);
										user=new CODAccount(accHolder);
										System.out.println("Creating Account");
										user.createAccount();
										System.out.println("Certificate of Deposit Account is created Successfully!");
										System.out.println();
									}
									else {
										System.out.println("Creating Account is Unsuccessful");
										System.out.println();
										continue;
									}
								}
								else {
									System.out.println("Account creation is cancelled ");
									System.out.println();
									continue;	
								}
							}
							else if(op==1) {                                   //Update acc holder's details
								
								System.out.println("Enter the Account Holder Id: ");
								accHolderId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Account Holder's First Name: ");
								fName=sc.nextLine();
								System.out.println("Enter the Account Holder's Last Name: ");
								lName=sc.nextLine();
								System.out.println("Enter the Account Holder's Age: ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Account Holder's Gender: ");
								gender=sc.nextLine();
								System.out.println("Enter your Phone Number: ");
								phoneNo=sc.nextLine();
								System.out.println("Enter your Email Id: ");
								emailId=sc.nextLine();
								System.out.println("Are you sure to Update the Account Details? (y/n)");
								String s=sc.nextLine();					
								System.out.println();
								if(s.equalsIgnoreCase("y")){
									if(user==null){
										accHolder=new AccountHolder();
										user=new SavingAccount(accHolder);
									}
									user.updateAccountHolderDetails(accHolderId, fName, lName,age, gender,phoneNo,emailId);
									System.out.println("Updation is successfull! ");
									System.out.println();
								}
								else {
									System.out.println("Account update is cancelled ");
									System.out.println("Updation is successfull! ");
									System.out.println();
									continue;									
								}
							}
							else if(op==2) {                              //Update acc Holder's address details
								
								System.out.println("Enter the Account Holder Id: ");
								accHolderId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Account Holder's City: ");
								city=sc.nextLine();
								System.out.println("Enter the Account Holder's FullAddress: ");
								addressLine=sc.nextLine();
								System.out.println("Are you sure to Update the Account Details? (y/n)");
								String s=sc.nextLine();	
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									if(user==null){
										accHolder=new AccountHolder();
										user=new SavingAccount(accHolder);
									}
									user.updateAddressDetails(accHolderId,city, addressLine);	
									System.out.println();
								}
								else {
									System.out.println("Account update is cancelled ");
									System.out.println();
									continue;									
								}
							}
							else if(op==3) {                              //deleting an account
							
								System.out.println("Enter 3.1 for deleting specific account type (or) Enter 3.2 for deleting account");
								double a=sc.nextDouble();
								sc.nextLine();
								if(a==3.1) {
									System.out.println("Enter the Account holder Id");
									int current_accholderId=sc.nextInt();
									System.out.println("Enter the Account Type Id");
									int current_accTyId=sc.nextInt();
									sc.nextLine();
									System.out.println("Are you sure you want to Delete this Account? (y/n)");
									String s=sc.nextLine();	
									System.out.println();
									if(s.equalsIgnoreCase("y")) {
										if(user==null){
											accHolder=new AccountHolder();
											user=new SavingAccount(accHolder);
										}
										user.deleteAccount(current_accholderId,current_accTyId);
										System.out.println();
									}
									else {
										System.out.println("Account deletion is cancelled ");
										System.out.println();
										continue;
									}
								}
								else if(a==3.2) {
									System.out.println("Enter the Account holder Id which u want to delete.");
									int current_accholderId=sc.nextInt();
									sc.nextLine();
									System.out.println("Are you sure you want to Delete the Account which Holder Id is "+current_accholderId+"? (y/n)");
									String s=sc.nextLine();	
									System.out.println(s+"--\n");
									if(s.equalsIgnoreCase("y")) {
										if(user==null){
											accHolder=new AccountHolder();
											user=new SavingAccount(accHolder);
										}
										user.deleteAccount(current_accholderId);
										System.out.println();
									}
									else {
										System.out.println("Account deletion is cancelled ");
										System.out.println();
										continue;
									}
								}
								else {
									System.out.println("Deleting process Unsuccessfull");
									System.out.println();
								}
							}
							else if(op==4) {                  //view transaction history
								System.out.println("Enter the Account No: ");
								accNo=sc.nextInt();
								sc.nextLine();
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								user.showTransactionHistory(accNo);
							}
							else if(op==5) {                 //Suspend an Account
								
								System.out.println("Enter 1 for Re-Activate an Account"+"\n"+
							                       "Enter 0 for Suspend an Account temporarily"+"\nEnter :");
								int st=sc.nextInt();
								sc.nextLine();								
								if(st==1)
									status=true;
								else if(st==0)
									status=false;
								else
									System.out.println("Ivalid input! please try again");
								if(st==1 || st==0) {
									System.out.println("Enter the Account Number: ");
									accNo=sc.nextInt();
									sc.nextLine();
									System.out.println("Are you sure? (y/n)");
									String s=sc.nextLine();
									if(s.equalsIgnoreCase("y")) {
										if(user==null){
											accHolder=new AccountHolder();
											user=new SavingAccount(accHolder);
										}
										user.changeAccountStatus(accNo, status);
										user.set_Checker(accNo, 0);
										System.out.println();
									}
									else {
										System.out.println("Changing Account status is cancelled");
										System.out.println();
										continue;
									}
								}
								else
									continue;
								
							}
							else if(op==6) {                //check Account Status
								
								System.out.println("Enter the Account number to check for: ");
								accNo=sc.nextInt();
								sc.nextLine();
								if(user==null){
									accHolder=new AccountHolder();
									user=new SavingAccount(accHolder);
								}
								status=user.getAccountStatus(accNo);
								if(status)
									System.out.println("Account Number("+accNo+") is Active");
								else
									System.out.println("Account Number("+accNo+") is Not Active");
								System.out.println();
							}
							else if(op==7) {                //Employee Account Details
								empData=new EmployeeData();
								emp=new BankEmployee(empData);
								emp.getEmpDetails(empId);
							}
							else if(op==8) {                   //MORE
								while(true) {
									String ex="null";
									System.out.println("\nEnter 1 for Total No of Accounts\nEnter 2 for Total List of Account Holder Details\nEnter 3 for Total Amount in Bank"
											+ "        \nEnter 4 for Total List of Accounts in Bank\nEnter 5 for Total No. of accounts created in this session\nEnter 6 for Exit\n");
									int choice=sc.nextInt();
									sc.nextLine();
									System.out.println();
									
									if(user==null){
										accHolder=new AccountHolder();
										user=new SavingAccount(accHolder);
									}
									switch(choice) {
									case 1:
										System.out.println("Total Number of Accounts in the database is "+user.get_totalNoOfAccount());
										break;
									case 2:
										user.get_totalAccountHolderDetails();
										break;
									case 3:
										System.out.println("Total amount in Bank is "+user.get_totalAmountInBank());
										break;
									case 4:
										user.get_totalAccount();
										break;
									case 5:
										System.out.println("Total Number of Account created in this session is "+TotalLog.get_instance().get_total_Acc_Log());
										break;
									case 6:
										ex="exit";
										break;
									default:
										continue;
									}
									if(ex=="exit")
										break;
								} // while ends
							}
							else if(op==9) {                //exiting 
								System.out.println(" Logged out successfully! Thank You!\n");
								System.out.println("/*********************/");
								break;
							}
							else {
								System.out.println("Unsuccessfull! Please Enter a option from 1 to 7!"+"\n");
								System.out.println();
							}
						}  //while loop ends
					
					}   //try ends
					catch(Exception e){
						e.printStackTrace();
					}
					
				}      //else if ends here
				else if(input==3) {
					System.out.println("Thank you! Application terminated");
					System.out.println("XXXXXXXXXXXXXXXX***********XXXXXXXXXXXXXXXXX");
					sc.close();
					System.exit(0);
				}
				else {                                           //exit
					System.out.println("InValid! Please try again");
					continue;
				}
			}//outermost while ends here
		}    //outermost try ends here
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
