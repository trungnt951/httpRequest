package httpRequest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ping implements Runnable {

	private String url;
	URL url1;
	Long tkt;
	Logger logger = Logger.getLogger("LogError");
	static int sttcode, tkError, flagError = 0;

	public int getSttcode() {
		return sttcode;
	}

	public void setSttcode(int sttcode) {
		this.sttcode = sttcode;
	}

	public static int getFlagError() {
		return flagError;
	}

	public static void setFlagError(int flagError) {
		ping.flagError = flagError;
	}

	static Hashtable<String, Integer> hashurl = new Hashtable<String, Integer>();
	static Hashtable<String, Long> hashurltime = new Hashtable<String, Long>();

	public ping(String url) {
		this.url = url;
	}

	Long begin = System.currentTimeMillis();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// String url2 = null;

		FileHandler fh;

		while (true) {
			try {
				url1 = new URL(url);
				Integer tk, tk2;
				Long dt = null;
				
				//Ket noi den url va thuc hien ping 
				HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();

				sttcode = connection.getResponseCode(); 	// lay ra Status code
				
				hashurl.put(url, sttcode);					// push status code vao hash table 
				tk = hashurl.get(url);						// Lay ra cac bien de kiem tra status code va bao loi
				tk2 = hashurl.get("flag" + url);
				
				Long end = System.currentTimeMillis();		// tao bien thoi gian de kiem tra khoang thoi gian giua 2 lan ping
				tkt = hashurltime.get(url);

				if (tkt == null) {							// Thuc hien tinh thoi gian giua 2 lan ping
					dt = end - begin;
				} else if (tkt != null) {
					dt = end - hashurltime.get(url);
				}
				hashurltime.put(url, end);					// push vao hash table bien thoi gian

				if (tk < 500) {								// Kiem tra xem url co bi loi hay khong va dung co
					flagError = 0;
				} else if (tk2 == null) {
					flagError++;
				} else if (tk != null) {
					flagError = tk2;
					flagError++;
				}
				hashurl.put("flag" + url, flagError);		// push vao hash table
				
				System.out.println(
						url + " -------- STATUS CODE: " + hashurl.get(url) + "  Time: " + dt );
				
				sttcode = hashurl.get(url);					// lay ra cac bien ve status code va co` bao loi
				tkError = hashurl.get("flag" + url);

				// Kiem tra co` bao loi neu qua so lan quy dinh thong bao loi tren console va luu lai vao log
				if (tkError >= 2) {
					System.out.print("\t\t\t **** ERROR ****\n");

					fh = new FileHandler("/home/trung/Desktop/LogError.log", true);
					logger.addHandler(fh);
					SimpleFormatter sf = new SimpleFormatter();
					fh.setFormatter(sf);
					logger.setUseParentHandlers(false);
					logger.info(url + " | liên tiếp 3 lần request lỗi");
					fh.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Kiem tra status code tra ve va set khoang thoi gian cho lan ping tiep theo
			if (sttcode >= 200 && sttcode <= 299) {
				try {
					Thread.sleep(3000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sttcode >= 300 && sttcode <= 499) {
				try {
					Thread.sleep(5000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sttcode >= 500 && sttcode <= 600) {
				try {
					Thread.sleep(10000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (sttcode == 0) {
				System.out.println("Khong co status code");
			}
		}
	}
}
