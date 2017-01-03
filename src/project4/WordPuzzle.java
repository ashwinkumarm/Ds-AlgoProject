package project4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	
	static ArrayList<String> possibleWords = new ArrayList<String>();

	private static char[][] formGrid(int row, int col) {
		char[][] gridElements = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Random randomAlphabet = new Random();
				char alphabet = (char) (randomAlphabet.nextInt(26) + 'a');
				gridElements[i][j] = alphabet;

			}
		}
		return gridElements;
	}

	private static void displayGrid(int row, int col, char[][] gridElements) {
		for (int i = 0; i < row; i++) {
			System.out.println("");
			for (int j = 0; j < col; j++) {
				System.out.print(gridElements[i][j]);
				System.out.print(" ");
			}
		}
	}
	
	private static void addWords(char[][] gridElements, StringBuilder stringHorizontalForward, int i, int j){
		stringHorizontalForward.append(gridElements[i][j]);
		if(!possibleWords.contains(new String(stringHorizontalForward))){
		possibleWords.add(new String(stringHorizontalForward));
		}
	}
	
	private static ArrayList<String> possibleWordsInGrid(int row, int col, char[][] gridElements){
		for(int i =0; i < row; i++){
			for(int j =0; j < col; j++){
				StringBuilder stringHorizontalForward = new StringBuilder();
				StringBuilder stringHorizontalBackward = new StringBuilder();
				StringBuilder stringVerticalDownward = new StringBuilder();
				StringBuilder stringVerticalUpward = new StringBuilder();
				StringBuilder stringDiagonalLeftUpward = new StringBuilder();
				StringBuilder stringDiagonalLeftDownward = new StringBuilder();
				StringBuilder stringDiagonalRightUpward = new StringBuilder();
				StringBuilder stringDiagonalRightDownward = new StringBuilder();
				int jhf = j , jhb = j, ivd = i, ivu = i, idlu = i, jdlu = j, idld = i,
						jdld = j, idru = i, jdru = j, idrd = i, jdrd = j;
				
				//adding horizontal forward
				while(jhf < col){
					addWords(gridElements, stringHorizontalForward, i, jhf);
					jhf++;	
				}
				
				//adding horizontal backward
				while(jhb >= 0){
					addWords(gridElements, stringHorizontalBackward, i, jhb);
					jhb--;
				}
				
				//adding vertical downwards
				while(ivd < row){
					addWords(gridElements, stringVerticalDownward, ivd, j);
					ivd++;	
				}
				
				//adding vertical upward
				while(ivu >= 0){
					addWords(gridElements, stringVerticalUpward, ivu, j);
					ivu--;
				}
				
				//adding diagonal left upward
				while(jdlu >= 0 && idlu >= 0){
					addWords(gridElements, stringDiagonalLeftUpward, idlu, jdlu);
					idlu--;jdlu--;
				}
				
				//adding diagonal left downward
				while(jdld >= 0 && idld < row){
					addWords(gridElements, stringDiagonalLeftDownward, idld, jdld);
					idld++;jdld--;
					
				}
				
				//adding diagonal right upward
				while(idru >= 0 && jdru < col){
					addWords(gridElements, stringDiagonalRightUpward, idru, jdru);
					idru--;jdru++;
				}
				
				//adding diagonal right downward
				while(jdrd < col && idrd < col){
					addWords(gridElements, stringDiagonalRightDownward, idrd, jdrd);
					idrd++;jdrd++;
				}
			}
		}
		return possibleWords;
	}
	

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		BufferedReader br = null;
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		System.out.println("Enter the value for number of rows for the grid");
		int row = inp.nextInt();
		System.out.println("Enter the value for number of columns for the grid");
		int col = inp.nextInt();
		inp.close();
		System.out.println("");
		System.out.println("Randomly Populated " + row + "x" + col + " Grid");
		char[][] gridElements = new char[row][col];
		gridElements = formGrid(row, col);
		displayGrid(row, col, gridElements);
		ArrayList<String> possWords = possibleWordsInGrid(row, col, gridElements);	
		
		System.out.println("");
		System.out.println("");
		System.out.println("Possbile words that could be formed from the grid are listed below:");
		System.out.println("");
		System.out.println(possWords.toString());
		ArrayList<String> matchedWords = new ArrayList<String>();
		
		try {
			
			//Inserting from dictionary to a Linked List
			String word;
			br = new BufferedReader(new FileReader("res/dictionary.txt"));
			LinkedList<String> dictLinkedList  = new LinkedList<String>();
			int startReadDictLinkedList = (int) System.currentTimeMillis();
				while ((word = br.readLine()) != null) {
					if(word != null && word.length() > 0 && word.length() <= Math.max(row, col)){
					dictLinkedList.insert(word);
				     }
				   }	
			int endReadDictLinkedList = (int) System.currentTimeMillis();
			System.out.println("");
			System.out.println("--------------Linked List----------------------");
			System.out.println("");
			System.out.println("Time taken to insert into a linked list in milliseconds: " + (endReadDictLinkedList - startReadDictLinkedList));
			int startCompLinkedList = (int) System.currentTimeMillis();
			for(String words: possWords){
				if(dictLinkedList.isPresent(words)){
					matchedWords.add(words);
				}
			}
			int endCompLinkedList = (int) System.currentTimeMillis();
			System.out.println("Time taken to check if the word is present in the linked list in milliseconds: " + (endCompLinkedList - startCompLinkedList));
			System.out.println("List of matched words :");
			System.out.println(matchedWords.toString());
			matchedWords.clear();
			//br.close();
			
			
			// Inserting from dictionary to AVL Tree
			br1 = new BufferedReader(new FileReader("res/dictionary.txt"));
			int startReadTree = (int) System.currentTimeMillis();
			AVLTreeMine<String> tree = new AVLTreeMine<String>();
			String w;
			while ((w = br1.readLine()) != null) {
				if(w != null && w.length() > 0 && w.length() <= Math.max(row, col)){
				tree.insert(w);
			}
			}

			int endReadDictTree = (int) System.currentTimeMillis();	
			System.out.println("");
			System.out.println("---------------------TREE----------------------");
			System.out.println("");
			System.out.println("Time taken to insert into a AVL Tree in milliseconds: " + (endReadDictTree - startReadTree));
			
			int startReadTreeEx = (int) System.currentTimeMillis();
			
			for(String words: possWords){
				if(tree.isPresent(words)){
				matchedWords.add(words);}
			}
			
			int endReadTreeEx = (int) System.currentTimeMillis();	
			
			System.out.println("Time taken to check if the word is present in the AVL Tree in milliseconds: " + (endReadTreeEx - startReadTreeEx));
			System.out.println("List of matched words :");
			System.out.println(matchedWords);
		    matchedWords.clear();
		    br1.close();
		    
		    
		    // Inserting from dictionary to hash map
		    
		    br2 = new BufferedReader(new FileReader("res/dictionary.txt"));
			
			MyHashTable<String> dicthash = new MyHashTable<>();
			
			int startReadMap = (int) System.currentTimeMillis();
			
			while((w = br2.readLine()) != null) {
				if(w != null && w.length() > 0 && w.length() <= Math.max(row, col)){
					dicthash.insert(w);
				}
			}
			
			int endReadMap = (int) System.currentTimeMillis();	
			System.out.println("");
			System.out.println("-------------------Hash Map----------------------");
			System.out.println("");
			System.out.println( "Time taken to insert into a Hash Map in milliseconds: " + (endReadMap - startReadMap));
			matchedWords.clear(); 
			
			int startReadMapEx = (int) System.currentTimeMillis();
			
			for(String words: possWords){
				if(dicthash.contains(words)){
					matchedWords.add(words);
				}
			}
			
			int endReadMapEx = (int) System.currentTimeMillis();	

			System.out.println( "Time taken to check if the word is present in HashMap in milliseconds: " + (endReadMapEx - startReadMapEx));
			System.out.println("List of matched words :");
			System.out.println(matchedWords);
			br2.close();
			} catch (IOException e) {
				System.out.println("Exception Occured while reading Dictionary" + e.getMessage());
				
			}
	}

}
