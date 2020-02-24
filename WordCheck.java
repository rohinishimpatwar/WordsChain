
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
/*
 * For comparing words from dictionary I have used brute-force approach as
 * comparing each input word with words from dictionary .
 * The sample dictionary format I assumed is added to bundle of deliverables.
 * 
 */
public class WordCheck {
	public static boolean check_word_validity(String input) {

		try {
			
			//Please add the local file path for the dictionary in the next line where I have added my local path 
			File f1 = new File("/Users/rohini/Downloads/english3.txt"); 
			String[] words = null; 
			FileReader fr = new FileReader(f1); // Creation of File Reader object
			BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
			String s;
			// Reading from the file 
			while ((s = br.readLine()) != null) // Reading Content from the file
			{
				
				words = s.split("\\n"); //Splitting the words in each line

				for (String word : words) 
					{ 

					if (word.equals(input)) //Comparing input with the each word.
					{
						
						return true;
					}

				}
			}
			fr.close();
		} catch (IOException e) {
			
			System.out.println("The is exception occured is : "+e.getClass().getSimpleName()+" please add valid dictionary file");
			
		}
		catch (Exception e) {
		
			System.out.println("The is exception occured is : "+e.getClass().getSimpleName());
			
		}

		return false;

	}

	private static LinkedHashMap<String, TreeSet<String>> stringChain(String str,
			LinkedHashMap<String, TreeSet<String>> hp, String original_str) {

		/*
		 * The approach I have used is Recursion (Dynamic programming) 
		 * while the word is given as input. E.g Starting - for each letter removed it calls itself
		 * and thus generates the desired LinkedHashMap using recursion.
		 * 
		 * The reason I have used LinkedHashMap is it maintains order and 
		 * The TreeSet inside LinkedHashMap is to maintain the sorted order and to avoid Repetition.
		 */
try {
		String temp = null;

		for (int i = 0; i < str.length(); i++) {

			temp = str.substring(0, i) + str.substring(i + 1);

			if (temp == null || temp.length() == 0 || temp == " ") {

				continue;

			}
           /*
            * Checking the generated subset is valid dictionary word or not .
            */
			else if (check_word_validity(temp) == true) {
				if (hp.containsKey(str)) {
					TreeSet<String> ar = hp.get(str);
					ar.add(temp);
					hp.put(str, ar);

				} else {
					TreeSet<String> ar = new TreeSet<String>();
					ar.add(temp);
					hp.put(str, ar);
					
				}
				stringChain(temp, hp, original_str);
			}
		}
     }
     catch (Exception e) {
			
			System.out.println("The is exception occured is : "+e.getClass().getSimpleName());
			
		}
     

		return hp;
	}

	private static ArrayList<String> printwordschain(LinkedHashMap<String, TreeSet<String>> hp, String original,String mixed, ArrayList<String> arrlist) {
		/*
		 * This method is used to display the generated LinkedHashMap
		 * I have used recursion here as well .
		 */
		try {

			if (!hp.containsKey(original))
				return arrlist;
			for (String s : hp.get(original)) {

				if (s.length() > 1 && hp.containsKey(s)) {
					printwordschain(hp, s, mixed + " => " + s, arrlist);

				} else {
					arrlist.add(mixed + " => " + s);
				}

			}
		}
	catch (Exception e) {
	
		System.out.println("The is exception occured is : "+e.getClass().getSimpleName());
		
	}
		return arrlist;

	}

	public static void main(String[] args) {

    try {
		while (true) {
			
			/*
			 * Taking input from the user 
			 * 
			 */
			Scanner myObj = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter the word or If you want to terminate the program type -  !EXIT!");

			String sampleString = myObj.nextLine();

			if (sampleString.equals("!EXIT!")) {
				break;
			}

			if (sampleString == null || sampleString.length() == 0) {
				System.out.println("String not entered");
			}

			System.out.println();

			sampleString = sampleString.toLowerCase();

			ArrayList<String> arrlist = new ArrayList<String>();
			LinkedHashMap<String, TreeSet<String>> list = new LinkedHashMap<String, TreeSet<String>>();

			int count = 1;
			String original = sampleString;
			
			//Calling stringChain method to get LinkedHashMap .
			LinkedHashMap<String, TreeSet<String>> hp = stringChain(sampleString, list, original);

			
            // Print the string chain .
			ArrayList<String> arr = printwordschain(hp, sampleString, original, arrlist);

			for (String s : arr) {
				System.out.println(s);
			}

		}
	}
    catch (Exception e) {
		
		System.out.println("The is exception occured is : "+e.getClass().getSimpleName());
		
	}
	}

}
