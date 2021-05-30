 //2020113925 �迵ȿ

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Phrase {
	
	private ArrayList<String> ps = new ArrayList<String>();
	
	private void getFile() {
		
		String currentProjPath = "";
		Scanner inputStream = null;
		
		try {
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String currentFilePath = currentProjPath + "/dat/phrase/phrase.txt";
		
		try {
			inputStream = new Scanner(new FileInputStream(currentFilePath), "utf-8");
		} catch (FileNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}
	
		while (inputStream.hasNext()) {
			String n = inputStream.nextLine();
			ps.add(n);
		}
	}
	
	public String getPhrase() {
		
		getFile();
		Random random = new Random();
		int ps_s = ps.size();
		
		int num = random.nextInt(ps_s);
		String phrase = ps.get(num);
	
		return phrase;
	}

	public static void main(String[] args)
	{
		Phrase p = new Phrase();
		System.out.println(p.getPhrase());
	}
}
