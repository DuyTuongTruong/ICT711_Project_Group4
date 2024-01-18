package GroupProject;

import java.util.ArrayList;

public class QueryInstruction extends Instruction {

	public QueryInstruction(String param) {
		super(param);
	}

	@Override
	public void implement(ArrayList<Contact> contactList, QueryResult queryResult) {
		queryResult.clear();
		String[] params = getParam().split(" ");
		for (Contact c : contactList) {
			if (params[0].equals("name") && c.getName().equals(params[1])
					|| params[0].equals("phone") && c.getPhone().equals(params[1])
					|| params[0].equals("birthday") && c.getBirthday().equals(params[1])) {
				queryResult.insert(c);
			}
		}
		System.out.println("=> Query result: \n" + queryResult.toString());
	}
	
}
