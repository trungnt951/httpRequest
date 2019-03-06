package httpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {
	static Scanner s = null ;
	public static  void read (ArrayList<String> listurl2){
	try {
		s = new Scanner(new File("config.properties"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	while (s.hasNextLine()) {
		listurl2.add(s.nextLine());

	}
	s.close(); // ket thuc doc file
	}
}
