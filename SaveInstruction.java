package GroupProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveInstruction extends Instruction {

	public SaveInstruction(String param) {
		super(param);
	}
	
	@Override
	public void implement(ArrayList<Contact> contactList, QueryResult queryResult) {
		try {
			// save contact list
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("OUTPUT.txt")));
			for (Contact c : contactList) {
				bw.write(c.toString() + "\n");
			}
			bw.close();

			// save the latest query results
			bw = new BufferedWriter(new FileWriter(new File("REPORT.txt"), true));
			if (queryResult.empty() == false) {
				bw.write(queryResult.toString() + "\n"
						+ "----------------------------------------------------------------------------------------------\n");
			}
			bw.close();
		} catch (IOException e) {

		}
	}
}
