package GroupProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	private static ArrayList<Contact> contactList;
	private static ArrayList<String> instructionList;
	private static String queryResult = "";

	public static void main(String[] args) {
		loadContactList("contacts_1_3fbd59c2fa9d5e1ac543a3c5d20bd90a.txt");
		loadInstruction("instructions_3_2e2bbf1f1f9e2f221827413d6c1f46cf.txt");
//		loadInstruction("instructions_5_0fb22ea91b9fea61388a4c765a1e707e.txt");

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
		if (instruction.indexOf("add ") == 0) {
			String[] params = instruction.substring(4).split(";");
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
		} else if (instruction.indexOf("delete ") == 0) {
			String[] params = instruction.substring(7).split(";");
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
		} else if (instruction.indexOf("query ") == 0) {
			queryResult = "";
			String[] params = instruction.substring(6).split(" ");
			for (Contact c : contactList) {
				if (params[0].equals("name") && c.getName().equals(params[1])
						|| params[0].equals("phone") && c.getPhone().equals(params[1])
						|| params[0].equals("birthday") && c.getBirthday().equals(params[1])) {
					if (queryResult.equals("") == false)
						queryResult += "\n";
					queryResult += c.toString();
				}
			}
			System.out.println("=> Query result: \n" + queryResult);
		} else if (instruction.indexOf("save") == 0) {
			try {
				// save contact list
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("FINAL_OUTPUT.txt")));
				for (Contact c : contactList) {
					bw.write(c.toString() + "\n");
				}
				bw.close();

				// save the latest query results
				bw = new BufferedWriter(new FileWriter(new File("FINAL_REPORT.txt"), true));
				bw.write(queryResult + "\n"
						+ "----------------------------------------------------------------------------------------------\n");

				bw.close();
			} catch (IOException e) {

			}
		}
	}
}
