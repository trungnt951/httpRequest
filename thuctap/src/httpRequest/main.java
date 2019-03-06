package httpRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import httpRequest.ping;
import httpRequest.readFile;

public class main extends Thread {
	
	static URL url;
	static int sttcode;
	int sttcode2;
	
	public static void createthread(ArrayList<String> listurl) {
		for (String surl : listurl) {
			ExecutorService executor = Executors.newFixedThreadPool(listurl.size());
			
			
			executor.submit(new ping(surl));
			// new Thread(ping(surl)).start();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> listurl = new ArrayList<String>();
		readFile.read(listurl);

		createthread(listurl);

	}

}
