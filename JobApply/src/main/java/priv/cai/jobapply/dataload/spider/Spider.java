package priv.cai.jobapply.dataload.spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import priv.cai.jobapply.constant.Constants;

public class Spider {
	
	public static String getContext(String url) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		URLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			conn = realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line).append(Constants.REGEX_NEWLINE);
			}
		} catch (Exception e) {
			//System.out.println("send EGT request have exception!" + e);
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e1) {
					in = null;
					//e1.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.setConnectTimeout(300);
				} catch (Exception e2) {
					conn = null;
					//e2.printStackTrace();
				}
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(getContext("https://www.indeed.com/"));

	}
	
	
	//java.net.ConnectException: Connection timed out: connect
	/*
	 * at java.net.DualStackPlainSocketImpl.connect0(Native Method)
at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:79)
at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172)
at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
at java.net.Socket.connect(Socket.java:589)
at sun.security.ssl.SSLSocketImpl.connect(SSLSocketImpl.java:668)
at sun.security.ssl.BaseSSLSocketImpl.connect(BaseSSLSocketImpl.java:173)
at sun.net.NetworkClient.doConnect(NetworkClient.java:180)
at sun.net.www.http.HttpClient.openServer(HttpClient.java:463)
at sun.net.www.http.HttpClient.openServer(HttpClient.java:558)
at sun.net.www.protocol.https.HttpsClient.<init>(HttpsClient.java:264)
at sun.net.www.protocol.https.HttpsClient.New(HttpsClient.java:367)
at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.getNewHttpClient(AbstractDelegateHttpsURLConnection.java:191)
at sun.net.www.protocol.http.HttpURLConnection.plainConnect0(HttpURLConnection.java:1138)
at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:1032)
at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:177)
at sun.net.www.protocol.http.HttpURLConnection.followRedirect0(HttpURLConnection.java:2701)
at sun.net.www.protocol.http.HttpURLConnection.followRedirect(HttpURLConnection.java:2623)
at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1806)
at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)
at sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:254)
at priv.cai.jobapply.dataload.spider.Spider.getContext(Spider.java:20)
at priv.cai.jobapply.springmvc.controller.CallableThread.call(CallableThread.java:16)
at priv.cai.jobapply.springmvc.controller.CallableThread.call(CallableThread.java:7)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
at java.lang.Thread.run(Thread.java:748)
	 */

}
