package GroupProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	private static ArrayList<Contact> contactList;
	private static ArrayList<String> instructionList;
	private static QueryResult queryResult = new QueryResult();

	public static void main(String[] args) {
		loadContactList("contact_1.txt");
//		loadInstruction("instruction_1.txt");
		loadInstruction("instruction_2.txt");

		for (String inst : instructionList) {
			implementInstruction(inst);
		}
	}

	private static void loadContactList(String filename) {
		contactList = new ArrayList<Contact>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			Contact tmpContact = new Contact();
			while ((line = br.readLine()) != null) {
				if (line.equals("") == false) {
					if (line.indexOf("name ") == 0) {
						tmpContact.setName(line.substring(5).trim());
					} else if (line.indexOf("birthday ") == 0) {
						tmpContact.setBirthday(line.substring(9).trim());
					} else if (line.indexOf("phone ") == 0) {
						tmpContact.setPhone(line.substring(6).trim());
					} else if (line.indexOf("email ") == 0) {
						tmpContact.setEmail(line.substring(6).trim());
					} else if (line.indexOf("address ") == 0) {
						tmpContact.setAddress(line.substring(8).trim());
					}
				} else {
					contactList.add(tmpContact);
					tmpContact = new Contact();
				}
			}
			if (tmpContact.getName().equals("") == false) {
				contactList.add(tmpContact);
			}
		} catch (Exception e) {

		}
	}

	private static void loadInstruction(String filename) {
		instructionList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("") == false) {
					instructionList.add(line);
				}
			}
		} catch (Exception e) {

		}
	}

	private static void implementInstruction(String instruction) {
		System.out.println("------------------------------------------------------------\nInstruction: " + instruction);
		Instruction inst = null;
		if (instruction.indexOf("add ") == 0) {
			inst = new AddInstruction(instruction.substring(4));
		} else if (instruction.indexOf("delete ") == 0) {
			inst = new DeleteInstruction(instruction.substring(7));
		} else if (instruction.indexOf("query ") == 0) {
			inst = new QueryInstruction(instruction.substring(6));
		} else if (instruction.indexOf("save") == 0) {
			inst = new SaveInstruction("");
		}
		
		if(inst != null) {
			inst.implement(contactList, queryResult);
		}
	}
}
