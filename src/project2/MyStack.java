package project2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class MyStack<AnyType> {

	ArrayList<AnyType> myStack = new ArrayList<AnyType>();

	public void push(AnyType c) {
		myStack.add(c);
		System.out.println("pushed in" + c);
	}

	public AnyType pop() {
		if (myStack.isEmpty()) {
			throw new EmptyStackException();
		} else {
			System.out.println("pop out" + myStack.get(myStack.size() - 1));
			return myStack.remove(myStack.size() - 1);
		}
	}
	
	public Boolean isEmpty(){
		return myStack.isEmpty();
	}

	public static void main(String[] args) throws Exception {
		Scanner inp = new Scanner(System.in);
		try {
			System.out.println("Enter the Balancing Symbols");
			String symbols = inp.nextLine();
			MyStack<Character> myStack = new MyStack<Character>();
			for (char ch : symbols.toCharArray()){
				if (ch == '[' || ch == '{' || ch == '(') {
					myStack.push(ch);
				} else if (ch == ']') {
					if (myStack.pop() != '[') {
						throw new Exception("Not Matching the opening Symbol");
					}
				} else if (ch == ')') {
					if (myStack.pop() != '(') {
						throw new Exception("Not Matching the opening Symbol");
					}
				} else if (ch == '}') {
					if (myStack.pop() != '{') {
						throw new Exception("Not Matching the opening Symbol");
					}
				} 
			}
			if(!myStack.isEmpty()){
				throw new Exception("Not all elements are popped out");
			}
			else{
				System.out.println("Symbols are balanced");
			}

		} finally {
			inp.close();
		}
	}
}
