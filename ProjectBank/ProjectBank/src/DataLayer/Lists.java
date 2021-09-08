package DataLayer;

import java.util.*;

public class Lists {

	public static Lists first_instance;
	public ArrayList<Integer> accountNumberL=new ArrayList<Integer>(); 
	public List<Integer> accountHolderIdL=new ArrayList<Integer>(); 
	public List<String> firstNameL=new ArrayList<String>();
	public List<String> lastNameL=new ArrayList<String>();
	public List<Integer> ageL=new ArrayList<Integer>(); 
	public List<String> genderL=new ArrayList<String>();
	public List<String> phoneNoL=new ArrayList<String>();
	public List<String> emailIdL=new ArrayList<String>();
	public List<String> cityL=new ArrayList<String>();
	public List<String> addressL=new ArrayList<String>();
	
	private Lists() {
	}
	
	public static Lists get_instance()
	{
		if(first_instance==null)
			first_instance=new Lists();
		return first_instance;
	}
}
