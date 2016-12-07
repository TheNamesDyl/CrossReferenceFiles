package CrossReference;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class Runner {
	public static void main(String[] args){
		TreeMap tree = new TreeMap();
		String wordFileName = null;
		String docFileName = null;
		ArrayList<Word> wordArray = new ArrayList<Word>();
		ArrayList<Document> docArray = new ArrayList<Document>();
		wordArray.add(0, new Word("dummyword"));
		int wordCounter = 1;
		int argCounter = 1;
		if(args.length > 0){
			for(String s: args){
				if(argCounter == 1){
					wordFileName = s;
				}else{
					docFileName = s;
				}
			}
		}else{
			Scanner sc = new Scanner(System.in);
			System.out.println("Name for word file name: ");
			wordFileName = sc.nextLine().trim();
			System.out.println("Name for document file name: ");
			docFileName = sc.nextLine().trim();
		}
		try{


			  FileInputStream fstream = new FileInputStream(wordFileName);
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;


			  while ((strLine = br.readLine()) != null)   {


				  wordArray.add(wordCounter, new Word(strLine));
				  wordCounter++;
			  }
			  in.close();
			  
			  FileInputStream fstream2 = new FileInputStream(docFileName);
			  DataInputStream in2 = new DataInputStream(fstream2);
			  BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			  String strLine2;
			  String constructedString = "";
			  int counter = 0;
			  int spaces = 0;
			  int idCounter = -1;
			  
			  while ((strLine2 = br2.readLine()) != null)   {
				  Document doc = null;
				  idCounter++;
				  while(counter != strLine2.length()-1){
					  if(strLine2.charAt(counter) != ' ' && spaces == 0){
						  constructedString = constructedString + strLine2.charAt(counter);
						  counter++;
						  if(counter == strLine2.length() -1){
							  constructedString = constructedString + strLine2.charAt(counter);
							  doc = new Document(constructedString);
							  docArray.add(idCounter, doc);
						  }
					  }else if(strLine2.charAt(counter) == ' ' && spaces == 0){
						  spaces = 1;
						  doc = new Document(constructedString);
						  docArray.add(idCounter, doc);
						  constructedString = "";
						  counter++;
					  }
					  
					  if(strLine2.charAt(counter) != ' ' && spaces > 0){
						  constructedString = constructedString + strLine2.charAt(counter);
						  counter++;
						  if(counter == strLine2.length() -1){
							  constructedString = constructedString + strLine2.charAt(counter);
							  doc.addWord(wordArray.get(Integer.parseInt(constructedString)));
							  constructedString = "";
						  }
						  
					  }else if(strLine2.charAt(counter) == ' ' && spaces > 0){
						  doc.addWord(wordArray.get(Integer.parseInt(constructedString)));
						  constructedString = "";
						  counter++;
					  }
					  
				  }
				  counter = 0;
				  spaces = 0;
			  }
			  in2.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}


		for(int i = 0; i < wordArray.size()-1; i++){
			if(wordArray.get(i).numOfDocs() > 0){
				tree.put(i, wordArray.get(i));
			}
		}
		
		Scanner sc = new Scanner(System.in);
		for(;;){
			System.out.println("Inquiry loop started. Options: wordlist document_id, doclist word, occurswith word, quit");
			String userChoice = sc.nextLine();
			
			if(userChoice.charAt(0) == 'w'){
				int id = Integer.parseInt(userChoice.substring(18));
				System.out.println("The words: " + docArray.get(id).toString());
				
			}else if (userChoice.charAt(0) == 'd'){
				String word = userChoice.substring(8);
				System.out.println(word);
				for(int i = 0; i < wordArray.size()-1; i++){					
					if(word.trim().equals( wordArray.get(i).contains().trim().toLowerCase())){
						System.out.println("Documents: " + wordArray.get(i).toString());
					}
				}
			}else if (userChoice.charAt(0) == 'o'){
				String word = userChoice.substring(11);
				for(int i = 0; i < wordArray.size()-1; i++){					
					if(word.trim().equals( wordArray.get(i).contains().trim().toLowerCase())){
						System.out.println("Documents: " + wordArray.get(i).toStringReturns());
					}
				}
			}else if (userChoice.charAt(0) == 'q'){
				System.exit(1);
			}
		}
	}


}