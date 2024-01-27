package GroupProject;

import java.util.ArrayList;

public class DeleteInstruction extends Instruction {

	public DeleteInstruction(String param) {
		super(param);
	}

	@Override
	public void implement(ArrayList<Contact> contactList, QueryResult queryResult) {
		String[] params = getParam().split(";");
		String name = params[0].trim();
		String birthday = params.length > 1 ? params[1].trim() : "";
		int delete_cnt = 0;
		for (int i = 0; i < contactList.size(); i++) {
			Contact c = contactList.get(i);
			if (c.getName().equals(name) && (birthday.equals("") || c.getBirthday().equals(birthday))) {
				contactList.remove(i);
				i--;
				delete_cnt++;
			}
		}
		System.out.println("=> Deleted " + delete_cnt + " record(s)");	
	}

}
