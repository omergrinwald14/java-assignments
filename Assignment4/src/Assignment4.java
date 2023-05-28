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

			if(option == 1) { 
							
			} //check

			if(option == 2) { 
						
			}

			if(option == 3) { 
					
			}

			if(option == 4) {
			
			}
		}
	}	
	public static int printMenu() { // print menu and ask user to choose an option //Welcome! Please choose an option:
		int option;
		System.out.println("~ Photo Analyzed ~\n"
				+ "0. End Program\n"
				+ "1. Uppercase Vowels\n"
				+ "2. Count Occurrences\n"
				+ "3. Longest Common Subsequence\n"
				+ "4. Count Substrings\n");
		option = sc.nextInt();
		return option;
	}

}






