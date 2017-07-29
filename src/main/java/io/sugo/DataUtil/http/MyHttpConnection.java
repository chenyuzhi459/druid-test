package io.sugo.DataUtil.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class MyHttpConnection {
	public static String postData(String url, String param){
		PrintWriter out = null;
		BufferedReader in = null;
		String result = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = null;
			realUrl = new URL(url);

			connection = (HttpURLConnection)realUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.connect();


			out = new PrintWriter(connection.getOutputStream());
			out.write(param);
			out.flush();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder("");
			String line;
			while (( line = in.readLine()) != null){
				sb.append(line);
				sb.append("\n");
			}
			result = sb.toString();
			connection.disconnect();
			return  result;
		} catch (MalformedURLException e){
			printErrorln("url错误！");
			e.printStackTrace();
		} catch (IOException e){
			printErrorln("post请求错误！");
			e.printStackTrace();
		} finally {

			try {
				if(null != connection)
					connection.disconnect();
				if(null != out)
					out.close();
				if(null != in)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void printErrorln(String str){
		System.err.println(str);
	}

	public static void println(String str){
		System.out.println(str);
	}
}
