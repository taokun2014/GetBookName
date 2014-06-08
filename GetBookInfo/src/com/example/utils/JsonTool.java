 package com.example.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.domain.BookInfo;

public class JsonTool {
	
	public JsonTool(){
		
	}
	public static BookInfo parseJson(String jsonString){
		BookInfo bookInfo = null;
		try {
			bookInfo = new BookInfo();
			JSONObject jsonObject = new JSONObject(jsonString);
			bookInfo.setmTitle(jsonObject.getString("title"));
			bookInfo.setmCover(downloadBitmap(jsonObject.getString("image")));
			bookInfo.setAuthor(parseJSONArraytoString(jsonObject.getJSONArray("author")));
			bookInfo.setmPublisher(jsonObject.getString("publisher"));
			bookInfo.setmPublishDate(jsonObject.getString("pubdate"));
			bookInfo.setmISBN(jsonObject.getString("isbn13"));
			bookInfo.setmSummary(jsonObject.getString("summary"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookInfo;
	}
	private static Bitmap downloadBitmap (String bitmapUrl){
		Bitmap bm=null;
        InputStream is =null;
        BufferedInputStream bis=null;
        try{
            URL  url=new URL(bitmapUrl);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            bis=new BufferedInputStream(connection.getInputStream());
            bm= BitmapFactory.decodeStream(bis);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if(bis!=null)
                    bis.close();
                if (is!=null)
                    is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bm;
	}
	private static String parseJSONArraytoString (JSONArray arr)
    {
        StringBuffer str =new StringBuffer();
        for(int i=0;i<arr.length();i++)
        {
            try{
                str=str.append(arr.getString(i)).append(" ");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return str.toString();
    }
}
