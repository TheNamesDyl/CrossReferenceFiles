package CrossReference;
import java.util.ArrayList;
import java.util.List;


public class Word {
	private String stem;
	private List<Document> docList;
	
	public Word(String stem){
		this.stem = stem;
		docList = new ArrayList<Document>();
	}
	public void addDocument(Document doc){
		docList.add(doc);
	}
	public String contains(){
		return stem;
	}
	public int numOfDocs(){
		return docList.size();
	}
	public String toString(){
		String returnString = "";
		for(int i = 0; i < docList.size()-1; i++){
			returnString = returnString + " " + docList.get(i).getId() + ",";
		}
		return returnString;
	}
	public String toStringReturns(){
		String returnString = "";
		for(int i = 0; i < docList.size()-1; i++){
			returnString = returnString + "\n " + docList.get(i).getId() + "\n Words: " + docList.get(i).toString();
		}
		return returnString;
	}


}
