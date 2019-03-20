package httpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	//ArrayList<UrlSpec> listurl =new ArrayList<UrlSpec>();
	
	public void read(ArrayList<UrlSpec> list) throws FileNotFoundException {
		int i=0;
		Scanner s = new Scanner(new File("config.properties"));
		while(s.hasNextLine()) {
			UrlSpec urlObj =new UrlSpec();
			list.add(urlObj);
			list.get(i).setUrl(s.next());
			i++;			
		}
		s.close();
		
	}
}
