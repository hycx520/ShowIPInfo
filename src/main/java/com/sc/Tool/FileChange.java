package com.sc.Tool;

import java.io.*;

public class FileChange {
	public static void deletLineByWordForFile(String orginFile,String outputFile,String markWord) throws Exception {
		FileReader fr = new FileReader(orginFile);
		BufferedReader bf = new BufferedReader(fr);
		String str;
		
		File f=new File(outputFile);
		FileOutputStream fos1=new FileOutputStream(f);
		OutputStreamWriter dos1=new OutputStreamWriter(fos1);
		
		while((str=bf.readLine())!=null) {
			if(!str.contains(markWord)) {
				dos1.write(str+"\n");
			}
		}
		dos1.close();
		bf.close();
	}
	
}
