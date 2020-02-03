package priv.preprocess.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SplitBigFile {
	public static void main(String[] args) throws IOException {
		String desktop =  "C:/Users/D13/Desktop/";
		String input = desktop + "Electronics_5.json";
		String output = desktop + "test/Elect_Data_";
		splitBigFile(input, output, 20);
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private static void splitBigFile(String inputStr, String outputStr, int divideNumber) throws IOException {
		// check input parameters
		if (divideNumber <= 1) return;
		if (inputStr == null) return;
		if (outputStr == null) return;
		
		// initial variables 
		int i = 0;
		int offset = 1;
		int temp = (int) Math.ceil(1689208/divideNumber);
		BufferedReader br = null;
		BufferedWriter bw = null;
		File input = new File(inputStr);
		File output = new File(outputStr + String.valueOf(offset) +".txt");
		
		try {
			if (!output.exists()) {
				output.createNewFile();
			}
			br = new BufferedReader(new FileReader(input));
			bw = new BufferedWriter(new FileWriter(output));
			String sCurrentLine;
			//"reviewerID": "AO94DHGC771SJ", "asin": "0528881469"
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.endsWith("}")) {
					//System.out.println(sCurrentLine);
					bw.write(sCurrentLine + System.lineSeparator());
					i++;
				}
				if (((temp*offset) == i) || (i == 1689188)) {
					offset++;
					bw.flush();
					bw.close();
					System.out.println("File " + output + " written Successfully !!!" + " offset is " + offset +", i is " + i);
					
					if (i != 1689188) {
						output = new File(outputStr + String.valueOf(offset)
								+ ".txt");
						if (!output.exists())
							output.createNewFile();
						bw = new BufferedWriter(new FileWriter(output));
					}
				}
			}
			System.out.println("The number of total records are : "+ i); 
			//System.out.println("The number of total records are : "+ i); 
			//1,478,973,494 KB                // 1689208
			//1,478,971,446 KB (correct size) // 1689188
		} finally {
				if(bw != null) bw.close();
				if(br != null) br.close();
				bw = null;
				br = null;
		}
	}
}
