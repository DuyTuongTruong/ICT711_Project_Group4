package GroupProject;

import java.util.ArrayList;

public class AddInstruction extends Instruction {

	// Constructor for AddInstruction class
	public AddInstruction(String param) {
		super(param);
	}
	
	// Implementation of the instruction for adding or updating contacts
	@Override
	public void implement(ArrayList<Contact> contactList, QueryResult queryResult) {
		// Splitting the parameters based on semicolon
		String[] params = getParam().split(";");
		// Creating a temporary contact object
		Contact tmpContact = new Contact();
		
		 // Iterating through each parameter to set contact properties
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
		
		// Searching for an existing contact with the same name and birthday
		Contact foundContact = null;
		for (Contact c : contactList) {
			if (c.getName().equals(tmpContact.getName()) && c.getBirthday().equals(tmpContact.getBirthday())) {
				foundContact = c;
				break;
			}
		}
		
		// Updating or adding the contact based on whether it already exists
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
