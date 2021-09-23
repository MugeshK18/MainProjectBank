package ApplicationLayer;


public class SavingAccount extends Accounts {
	double mainBalance,discountAmount=0;
	public SavingAccount() {
		super();
		accTypeId=1;
	}
	
	public void debit(int accNo,double amount)
	{
		
		mainBalance=getMainBalance(accNo);
		if(amount<mainBalance)
		{
			mainBalance=mainBalance-amount;
			changeMainBalance(accNo,mainBalance);
			String action="Debited "+amount;
			account.setNotifications(accNo, action);
			System.out.println(amount+" :Debited Successfully");
			System.out.println("Available Balance is: "+mainBalance+"\n");
			
		}
		else
		{
			String action="Failed attempt to Debit "+amount;
			account.setNotifications(accNo,action);
			System.out.println("No suffcient balance");
			System.out.println("Available Balance is: "+mainBalance+"\n");
		}
	}
	public void credit(int accNo,double amount)
	{
		mainBalance=getMainBalance(accNo);
		mainBalance=mainBalance+amount;
		changeMainBalance(accNo,mainBalance);
		String action="Credited "+amount;
		account.setNotifications(accNo, action);
		System.out.println(amount+" :Credited Successfully");
		createCoupon(accNo,amount);
		System.out.println("Available Balance is: "+mainBalance+"\n");
	}
	@Override
	public void transferAmount(int accNo,int dest_accNo,String phoneNo,double amount) {
		super.transferAmount(accNo, dest_accNo,phoneNo, amount);
		String action="Transfered "+amount+" from accNo("+Integer.toString(accNo)+") to accNo("+Integer.toString(dest_accNo)+")";
		account.setNotifications(accNo, action);
		String action1="Transfered "+amount+" from accNo("+Integer.toString(accNo)+")";
		account.setNotifications(dest_accNo, action1);
	}
	public void createCoupon(int accNo,double amount)
	{
		double discountPercentage=2;
		amount=(double)(discountPercentage *amount)/100;
		account.addCoupons(accNo, amount);
	}
	public void displayCoupon(int accNo)
	{
		
		System.out.println("Congratulations!! You have won "+account.getCoupons(accNo)+" worth of Coupon at KFC!!\n");
	}

}
