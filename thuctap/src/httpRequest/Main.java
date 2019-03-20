package httpRequest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void ThreadCreate(ArrayList<UrlSpec> list) {
		ExecutorService executor = Executors.newFixedThreadPool(list.size());
		for(UrlSpec urlObj :list) {
			executor.submit(new Ping(urlObj));
		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<UrlSpec> listUrl= new ArrayList<UrlSpec>();
		ReadFile rf =new ReadFile();
		rf.read(listUrl);
//		for(UrlSpec urlObj :listUrl) {
//			System.out.println(urlObj.getUrl());
//		}
		ThreadCreate(listUrl);
	}

}
