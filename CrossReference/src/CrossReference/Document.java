package CrossReference;

import java.util.ArrayList;
import java.util.List;


public class Document {
	private String id;
	private List<Word> wordList;
		
	public Document(String id){
		wordList = new ArrayList<Word>();
		this.id = id;
	}
		
	public void addWord(Word word){		
		wordList.add(word);
		word.addDocument(this);
	}
	public String toString(){
		String returnString = "";
		for(int i = 0; i < wordList.size()-1; i++){
			returnString = returnString + " " + wordList.get(i).contains() + ",";
		}
		return returnString;
	}
	public String getId(){
		return id;
	}


}



