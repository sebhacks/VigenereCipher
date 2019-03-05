/*
 * Sebastian Lopez 
 * Vigenere Cipher encryptor
 * CIS3360 security in computing, mcalpin MW 1:30-2:45
 */

//enable if running on IDE 
//package vigenereCipher; 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class VigenereCipher {
	
	public static final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
											'p','q','r','s','t','u','v','w','x','y','z'};
	
	
	public VigenereCipher(String plaintext, String key){
		
		readInFile(plaintext, key); 
	}
	
	
	public String readInFile(String inputFile, String inputFile2) {
		
		String plaintext = "";
		String keyText = "";
		int checkBounds = 0; 
		
		//read in plaintext file 
		try {
			FileReader file = new FileReader(inputFile);    
			BufferedReader bf = new BufferedReader(file); 
			
			int x;
				try {	
					//scan each char at a time
					while((x = bf.read()) != -1){
						
						//convert x to a char for assignment
						char input = (char) x;
						
						//make sure char is only alphabetic 
						if(Character.isLetter(input)) {
							
							//make sure plaintext doesnt exceed 512 chars 
							if(checkBounds >= 512) {
								break; 
							}
							
							//append to our plaintext string and make lowercase
							plaintext += Character.toLowerCase(input); 
							
							//increment number of chars
							//we have in String
							checkBounds++;
							
							} else {
								continue; 
							}
						
					 
					}
				} catch (IOException e) {
					System.out.println("File not found exception");
				}
		} 
		catch (FileNotFoundException e) { 
			System.out.println("File not found exception");
		}
		
		

		//if plaintext is less than 512 chars pad with x until we reach 512
		if(checkBounds < 512) {
			while(checkBounds < 512) {
				plaintext += 'x';
				checkBounds++; 
			}
		} 
		
		
		
		//---------------------------------------------------------------------------------------second file 
		
		//read in key file 
		try {
			FileReader file2 = new FileReader(inputFile2);    
			BufferedReader bf2 = new BufferedReader(file2); 
			
			int y;
				try {	
					//scan each char at a time
					while((y = bf2.read()) != -1){
						
						//convert y to a char for assignment
						char input2 = (char) y;
						
						if(Character.isLetter(input2)) {
							
							//append to our keyText string & make lowercase 
							keyText += Character.toLowerCase(input2);
							
						} else {
							continue; 
						}
						  
					}
				} catch (IOException e) {
					System.out.println("File not found exception");
				}
		} 
		catch (FileNotFoundException e) { 
			System.out.println("File not found exception");
		}
		
		
		//remove all non-readable whitespace from our strings 
		plaintext = plaintext.replaceAll("\\s+", "");
		keyText = keyText.replaceAll("\\s+", "");
		
		
		//will store our encrypted text 
		String encrypted = "";
			
		//looping through plaintext file, comparing each letter (index of strings)
		// 	and encrypting each letter 
		int i, j; 
		for(i=0, j=0; i<plaintext.length(); i++) {
			
			//get our encrypted letter & append it to 'encrypted' string
			char c = encrypt(plaintext.charAt(i), keyText.charAt(j));
			encrypted += c; 
			
			//makes sure key just keeps resetting 
			j = ++j % keyText.length(); 
		}
		
		
		
		//counter that will be used for all output loops
		int ctr=0;
		
		// print keyText to console and format   
		// output to have only 80 chars per line
		System.out.print("\n \n");
		System.out.print("Key: ");  
		while(ctr < keyText.length()) {
			
			if(ctr % 80 == 0) {
				System.out.print("\n");
			}	
			System.out.print(keyText.charAt(ctr));
			ctr++; 
		}
		
		System.out.print("\n \n");
		
		// print plaintext to console and format   
		// output to have only 80 chars per line
		System.out.print("Plaintext: "); 
		ctr=0; 
		while(ctr < plaintext.length()) {
			
			if(ctr % 80 == 0) {
				System.out.print("\n");
			}	
			System.out.print(plaintext.charAt(ctr));
			ctr++; 
		}
		
		System.out.print("\n \n");
		
		// print ciphertext to console and format   
		// output to have only 80 chars per line
		System.out.print("Ciphertext: "); 
		ctr=0; 
		while(ctr < encrypted.length()) {
			
			if(ctr % 80 == 0) {
				System.out.print("\n");
			}	
			System.out.print(encrypted.charAt(ctr));
			ctr++; 
		}
		
		System.out.print("\n \n");
		
		return plaintext; 
			
	}
	
	
	
	//encrypts 1 char at a time 
	public char encrypt(char plaintext, char key) {
		
		char encryptedChar; 
		int plaintextNum = 0;
		int keyNum = 0;
		
		int i; 
		for(i=0; i<ALPHABET.length; i++) {
			if(ALPHABET[i] == plaintext) {
				plaintextNum = i; 
			}
		}
		for(i=0; i<ALPHABET.length; i++) {
			if(ALPHABET[i] == key) {
				keyNum = i; 
			}
		}
		
		//get the index of the new char 
		int indexEncrypted = (plaintextNum + keyNum) % 26; 
		
		//get the letter of that index 
		encryptedChar = ALPHABET[indexEncrypted];
		
		return encryptedChar; 
	}
	
	
	
	public static void main(String[] args) {
		
		//for running from the command line 
		if (0 < args.length) {
			
			//reading in file from args, assigning to VigenereCipher
			String key = args[0];
		    String plaintext = args[1];
		    
		    
		    VigenereCipher vc = new VigenereCipher(plaintext, key);
		    
		  } 
		else {
			//if file was not read in properly
			System.out.println("\n file not read in. \n");
		}
		

		 //if running program directly			
		 // VigenereCipher vc = new VigenereCipher("p1.txt", "k1.txt"); 
		 
		
		
	}
}
