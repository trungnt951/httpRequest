package httpRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class Ping implements Runnable{
	
	private UrlSpec urlS;
	String surl;
	URL url;
	int flagError;
	long dt;
	int tkFlag;
	long begin=System.currentTimeMillis();
	
	public Ping(UrlSpec urlS) {
		this.urlS=urlS;
	}
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		while(true){
			try {
				
				surl=urlS.getUrl();
				url=new URL(surl);
				
				//Ket noi den url va thuc hien ping 
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				
				int code=connection.getResponseCode();    // lay ra Status code
				urlS.setSttcode(code);
				
				long end=System.currentTimeMillis();
				long time=urlS.getTime();
				
				if(time==0) {
					 dt =end-begin;
				}else {
					 dt = end-time;
				}
				
				urlS.setTime(end);
				
				tkFlag=urlS.getFlag();
				if(code<500) {
					tkFlag=0;
				}else {
					tkFlag++;
				}
				urlS.setFlag(tkFlag);
				
				System.out.println(surl+"\t\t---STATUS CODE: "+code+" |Times:"+dt+" |Flag:" +tkFlag+" |Thread: "+Thread.currentThread().getName());
				if(tkFlag>=3) {
					System.out.println("\t\t\t ****ERROR****");
				}
				if(code>=200 && code<=299) {
					//Thread.sleep(3000);
					TimeUnit.SECONDS.sleep(3);
				}else if(code>=300 && code<=499) {
					//Thread.sleep(5000);
					TimeUnit.SECONDS.sleep(5);
				}else if(code>=500 && code<=599) {
					//Thread.sleep(10000);
					TimeUnit.SECONDS.sleep(10);
				}	
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
