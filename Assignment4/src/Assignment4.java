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
				System.out.println("Please enter a string:");
				String str=sc.next();
				System.out.println(uppercaseVowels(str));

							
			} //check

			if(option == 2) { 
				System.out.println("Please enter the array length:");
				int num=sc.nextInt();
				int []arr=new int [num];
				System.out.println("Please enter the array values:");
				for (int i=0;i<arr.length;i++)
				{
					arr[i]=sc.nextInt();
				}
				System.out.println("Please enter a number to search:");
				int x=sc.nextInt();;
				System.out.println( "The number "+x+ " appears "+countOccurrences(arr,x)+  " times in this array ");
			}

			if(option == 3) { 
				System.out.println("Please enter str1:");
				String str1=sc.next();
				System.out.println("Please enter str2:");
				String str2=sc.next();	
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
	public static String uppercaseVowels(String s) {
		if(s.length()==0)
		{
			return "";
		}
		if(s.charAt(0)=='a'|| s.charAt(0)=='e'|| s.charAt(0)=='i'|s.charAt(0)=='o'||s.charAt(0)=='u')	
		{
			
			return (char)((int)s.charAt(0)-32)+uppercaseVowels(s.substring(1));
		}
		else
			return s.charAt(0)+uppercaseVowels(s.substring(1));
		}
		
	
	public static int countOccurrences(int[] arr, int x) {
		return countOccurrences(arr,x,arr.length-1);
	}
	
	public static int countOccurrences(int[] arr, int x, int i) {
		if(i==-1)
			return 0;
		if(arr[i]==x)
			return 1+countOccurrences(arr,x,i=i-1);
		else
			return 0+countOccurrences(arr,x,i=i-1);
	}
	


public static String LCS(String str1, String str2) {
//if (str1.length()>str2.length())
//{
	//CHECK

} 






