package GroupProject;

import java.util.ArrayList;

public class QueryResult {
	
	private ArrayList<Contact> resultList;
	
	public QueryResult() {
		resultList = new ArrayList<Contact>();
	}

	public ArrayList<Contact> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Contact> resultList) {
		this.resultList = resultList;
	}

	public void clear() {
		this.resultList = new ArrayList<Contact>();
	}
	
	public void insert(Contact c) {
		this.resultList.add(c);
	}

	public boolean empty() {
		return resultList.isEmpty();
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (Contact c : resultList) {
			if (ret.equals("") == false)
				ret += "\n";
			ret += c.toString();
		}
		return ret;
	}
}
