package httpRequest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import httpRequest.ping;
import httpRequest.readFile;

public class main extends Thread {
	private static String surl;
	static URL url;
	static int sttcode;
	int sttcode2;
	static int i;
	
	public static void createthread(ArrayList<String> listurl) {
	ExecutorService executor = Executors.newFixedThreadPool(listurl.size());
	for(i=0;i<listurl.size();i++) {
		{
			surl=listurl.get(i).toString();
			Runnable pg =new ping(surl);
			executor.submit(pg);
			
		}
	}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> listurl = new ArrayList<String>();
		readFile.read(listurl);

		createthread(listurl);

	}

}
