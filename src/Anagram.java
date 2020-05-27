import java.io.*;
import java.util.*;


public class Anagram {
	
	/*
	 * Connects every letter with a unique prime number
	 */
	public static HashMap<Character, Integer> createAlphabet() {
		
		char ch = 'a';
		int no = 0;
		HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>();
		
		while (ch <= 'z') {
			no = nextPrime(no);
			alphabet.put(ch, no);
	        ch = (char)((int)ch+1);
		}
		return alphabet;
	}
	
	/*
	 * Finds next prime number
	 */
	public static int nextPrime(int n) {
		
		if (n <= 1) 
	        return 2;
		else if (n == 2)
			return 3;
		
		int prime = n+2;
		
		while (true) {
			
			if (isPrime(prime))
				return prime;
			
			prime+=2;
		}
	}

	/*
	 * Checks if a number is a prime number
	 */
	public static boolean isPrime(int n) {
		
		if (n%2 == 0)
			return false;
		
		for (int i = n-2; i > 2; i-=2) {
			
			if (n%i == 0)
				return false;
		}
		return true;
	}
	
	/*
	 * Reads a text file and inserts every line into a list
	 */
	public static ArrayList<String> readListFromFile(File file) throws FileNotFoundException {
		
		Scanner sc = new Scanner(file);
		ArrayList<String> words = new ArrayList<String>();
		
		while (sc.hasNextLine()) {
		    words.add(sc.nextLine());
		}
		sc.close();
		return words;
	}
	
	/*
	 * Assigns a product of primes to each word
	 */
	public static HashMap<String, Integer> multPrimes(HashMap<Character, Integer> alphabet, ArrayList<String> words) {
		
		HashMap<String, Integer> wordNumber = new HashMap<String, Integer>();
		int prod;
		
		for (String word : words) {
			prod = 1;
			
			for (int i = 0; i < word.length(); i++) {
				prod *= alphabet.get(word.charAt(i));
			}
			wordNumber.put(word, prod);
		}
		return wordNumber;
	}
	
	/*
	 * Counts the number of anagrams each set of characters has
	 */
	public static HashMap<Integer, Integer> countAnagrams(HashMap<String, Integer> wordNumber) {
		
		HashMap<Integer, Integer> numberOfAnagrams = new HashMap<Integer, Integer>();
		
		for (int v : wordNumber.values()) {
			
			if (numberOfAnagrams.containsKey(v)) 
				numberOfAnagrams.put(v, numberOfAnagrams.get(v) + 1);
			else
				numberOfAnagrams.put(v, 1);
		}
		return numberOfAnagrams;
	}
	
	/*
	 * Finds the set of characters with the largest amount of anagrams
	 */
	public static ArrayList<Integer> findMostAnagrams(HashMap<Integer, Integer> numberOfAnagrams) {
		
		int max = Collections.max(numberOfAnagrams.values());
		ArrayList<Integer> most = new ArrayList<Integer>();

		for (int t : numberOfAnagrams.keySet()) {
			
			if (numberOfAnagrams.get(t) == max) {
				most.add(t);
			}
		}
		return most;
	}
	
	/*
	 * Prints the anagrams
	 */
	public static void printAnagrams(HashMap<String, Integer> wordNumber, ArrayList<Integer> mostAnagrams) {
		
		for (Integer m : mostAnagrams) {
			
			for (String word : wordNumber.keySet()) {
				
				if (wordNumber.get(word).equals(m))
					System.out.println(word);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		HashMap<Character, Integer> alphabet = createAlphabet();
		
		ArrayList<String> words = readListFromFile(new File("src/list2.txt"));
		
		HashMap<String, Integer> wordNumber = multPrimes(alphabet, words);
		
		HashMap<Integer, Integer> numberOfAnagrams = countAnagrams(wordNumber);
		 
		ArrayList<Integer> mostAnagrams = findMostAnagrams(numberOfAnagrams);
		
		printAnagrams(wordNumber, mostAnagrams);
		

	}
}
