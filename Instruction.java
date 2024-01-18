package GroupProject;

import java.util.ArrayList;

public abstract class Instruction {

	private String param = "";
	
	public Instruction(String param) {
		this.param = param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	
	public String getParam() {
		return this.param;
	}
	
	public abstract void implement(ArrayList<Contact> contactList, QueryResult queryResult);
	
}
