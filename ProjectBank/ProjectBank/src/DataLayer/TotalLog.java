package DataLayer;

public class TotalLog implements ITotalLog {
	
	public static ITotalLog first_instance;
	private long total_Acc;
	private TotalLog() {
	}
	public static ITotalLog get_instance()
	{
		if(first_instance==null)
			first_instance=new TotalLog();
		return first_instance;
	}

	public final void total_Acc_Log()
	{
		total_Acc++;
	}
	public final void red_total_Acc_Log()
	{
		total_Acc--;
	}
	public final long get_total_Acc_Log()
	{
		return total_Acc++;
	}  
	public final int get_totalNoOfAccount() {
		
		IOperationOnDB accountHolder=new OperationOnDB(new AccountHolder());
		return accountHolder.totalNoOfAccount();
	}
}
