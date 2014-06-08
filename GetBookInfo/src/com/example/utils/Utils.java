package com.example.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
	public static final String TGA = "UTILS_TGA";
	public static String downloadjsondata(String url){
		String result = "";
		StringBuffer sb = new StringBuffer();
		try {
			URL pathUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) pathUrl.openConnection();
			conn.setConnectTimeout(3000);
			conn.setDoInput(true);
			String line = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			result = sb.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
