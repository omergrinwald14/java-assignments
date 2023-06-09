import java.util.Scanner;

public class Assignment4 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = true; // will get the value 'false' when the user choose to end program
		int option; 

		while(flag)	{
			option = printMenu(); // asking user for input
			if(option <0 || option >4) 
				System.out.println("Invalid input");

			if(option == 0) { // ending the program
				flag = false; 
				System.out.println("Bye Bye!");
			}

			if(option == 1) { // Upper-case vowels
				String str;
				System.out.println("Please enter a string:");
				do {
					str=sc.nextLine(); // user's input
				}
				while(str.length()==0);
				System.out.println(uppercaseVowels(str)); // call the function and print it's result
			} 

			if(option == 2) { // find how many times 'x' occurres in an array
				System.out.println("Please enter the array length:");
				int arrLength=sc.nextInt(); 
				int []arr=new int [arrLength]; // creating an array in the desired length
				System.out.println("Please enter the array values:");
				for (int i=0;i<arr.length;i++)
					arr[i]=sc.nextInt(); // array values from user
				System.out.println("Please enter a number to search:");
				int x=sc.nextInt();;
				System.out.println( "The number "+ x +" appears " + countOccurrences(arr,x) + " times in this array"); // printing result
			}

			if(option == 3) { // longest common sequence
				String str1;
				String str2;
				System.out.println("Please enter str1:");
				do {
					str1=sc.nextLine();  // input for string 1
				}
				while(str1.length() == 0); 
				System.out.println("Please enter str2:");
				do {
					str2=sc.nextLine(); // input for string 1
				}
				while(str2.length()==0); 
				System.out.println("The longest common subsequence is: " + LCS(str1,str2)); // calling the function with the 2 inputs and print result
			}

			if(option == 4) { // count substrings
				System.out.println("Please enter a string:");
				String str;
				do {
					str=sc.nextLine(); // input
				}
				while(str.length() == 0);
				System.out.println("The number of substrings is " + countSubstrings(str)); // calling function and print result
			}
		}
	}	

	public static int printMenu() { // print menu and ask user to choose an option
		int option;
		System.out.println("Welcome! Please choose an option:\n"
				+ "0. End Program\n"
				+ "1. Uppercase Vowels\n"
				+ "2. Count Occurrences\n"
				+ "3. Longest Common Subsequence\n"
				+ "4. Count Substrings");
		option = sc.nextInt();
		return option;
	}
	public static String uppercaseVowels(String s) { // replace vowels with upper-case letters
		if(s.length() == 0) 
			return ""; // base case - return empty string when the string is empty
		char currentChar = s.charAt((0));
		if(currentChar == ' ')
			return ' ' + uppercaseVowels(s.substring(1));
		if(currentChar=='a'|| currentChar=='e'|| currentChar=='i'||currentChar=='o'||currentChar=='u') // vowels
			return (char)(currentChar-32) + uppercaseVowels(s.substring(1)); // add upper-case letter and call the function with the rest of the string

		else
			return currentChar + uppercaseVowels(s.substring(1)); //leave the letter as it is and call the function on the rest of the string
	}	
	public static int countOccurrences(int[] arr, int x) { // count occurrences of 'x' in a certain array of integers
		return countOccurrences(arr,x,arr.length-1); 
	}	
	public static int countOccurrences(int[] arr, int x, int i) {
		if(i<0)
			return 0; // base case - will get here after we finished the check on index 0
		if(arr[i]==x)
			return 1+countOccurrences(arr,x,i-1); // add one when the value equals x
		else
			return countOccurrences(arr,x,i-1); // call the function and move and index 1 to the left
	}
	public static String LCS(String str1, String str2) { // longest common sequence
		if(str1.length()==0 || str2.length()==0) // base case - if one of the strings is empty, there is nothing to compare
			return "";
		if(str1.charAt(0)==str2.charAt(0)) // the first char at each string is the same
			return str1.charAt(0) + LCS(str1.substring(1),str2.substring(1)); // return the specific char and add the function with the rest of the strings

		// when it's not stop condition, and the first char is not the same - split into 2 options
		String left = LCS(str1,str2.substring(1)); // promoting the 2nd string only
		String right = LCS(str1.substring(1),str2); // promoting the 1st string only
		if(left.length()>=right.length()) // return the longest sequence
			return left;
		return right;
	}
	public static int countSubstrings(String str) {  // count substrings that begin and end with the same char
		return countSubstrings(str,0,str.length()-1);
	}
	public static int countSubstrings(String str, int start, int end){  
		if (start==str.length()-1) // base case - a one char substrings
			return 1;
		if (end<0) // after we finished to compare first char to all of the rest
			return countSubstrings(str.substring(1)); // promote the start index by 1, and start the check again
		if(str.charAt(start)==str.charAt(end)) // first char equals to another
			return 1 + countSubstrings(str,start,end-1); // add 1 to the count, move the 'end' index 1 to the left and check again
		else
			return countSubstrings(str,start,end-1); // move the 'end' index 1 to the left and check again
	}

}








