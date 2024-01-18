package GroupProject;

import java.util.ArrayList;

public class AddInstruction extends Instruction {

	public AddInstruction(String param) {
		super(param);
	}

	@Override
	public void implement(ArrayList<Contact> contactList, QueryResult queryResult) {
		String[] params = getParam().substring(4).split(";");
		Contact tmpContact = new Contact();
		for (String param : params) {
			param = param.trim();
			if (param.indexOf("name ") == 0) {
				tmpContact.setName(param.substring(5).trim());
			} else if (param.indexOf("birthday ") == 0) {
				tmpContact.setBirthday(param.substring(9).trim());
			} else if (param.indexOf("phone ") == 0) {
				tmpContact.setPhone(param.substring(6).trim());
			} else if (param.indexOf("email ") == 0) {
				tmpContact.setEmail(param.substring(6).trim());
			} else if (param.indexOf("address ") == 0) {
				tmpContact.setAddress(param.substring(8).trim());
			}
		}
		Contact foundContact = null;
		for (Contact c : contactList) {
			if (c.getName().equals(tmpContact.getName()) && c.getBirthday().equals(tmpContact.getBirthday())) {
				foundContact = c;
				break;
			}
		}
		if (foundContact != null) {
			if (tmpContact.getEmail().equals("") == false)
				foundContact.setEmail(tmpContact.getEmail());
			if (tmpContact.getPhone().equals("") == false)
				foundContact.setPhone(tmpContact.getPhone());
			if (tmpContact.getAddress().equals("") == false)
				foundContact.setAddress(tmpContact.getAddress());

			System.out.println("=> Update contact for " + tmpContact.getName());
		} else {
			contactList.add(tmpContact);
			System.out.println("=> Add new contact for " + tmpContact.getName());
		}
	}

}
