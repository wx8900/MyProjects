package priv.preprocess.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;


public class MyDataValidation {// :\ .\ -\ \, :\ \, "ove

	public static void main(String[] args) throws IOException, InterruptedException {
		for (int i = 1; i <= 20; i ++) {
			try {
				String desktop = "/home/cloudera/test/Elect_Data_"+i+".txt";
				BufferedReader br = new BufferedReader(new FileReader(new File(desktop)));
				
				for (int j = 0; j < 900000000; j++) {
					Review review = JSON.parseObject(br.readLine(), Review.class);
					if (review == null) break;
					System.out.println(i +" == review : " + review);
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
		/*String desktop =  "/home/cloudera/test/Elect_Data_6.txt";
		File input = new File(desktop);
		BufferedReader br = new BufferedReader(new FileReader(input));
		
		for (int i =0; i < 500000; i++) {
			Review review = JSON.parseObject(br.readLine(), Review.class);
			if (review == null) break;
			System.out.println("review : "+review);
		}*/
		

	}
}
