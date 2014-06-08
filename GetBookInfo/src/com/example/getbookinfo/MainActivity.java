package com.example.getbookinfo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.domain.BookInfo;
import com.example.utils.JsonTool;
import com.example.utils.Utils;
import com.example.zxing.IntentIntegrator;
import com.example.zxing.IntentResult;

public class MainActivity extends Activity {
	private Button button;
	private ProgressDialog mprogressDialog = null;
	private static final String BookAPI_URL = "https://api.douban.com/v2/book/isbn/";
	private IntentResult result = null;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			mprogressDialog.dismiss();
			BookInfo bookinfo = (BookInfo) msg.obj;
			System.out.println(bookinfo.getmPublishDate());
			Intent intent = new Intent(MainActivity.this,BookInfoActivity.class);
			intent.putExtra("bookinfo", bookinfo);
			startActivity(intent);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) this.findViewById(R.id.main_start_scan);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startScanner();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void startScanner(){
		IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
		integrator.initiateScan();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result == null || result.getContents()==null){
			Log.v(Utils.TGA, "User cancel scan by pressing back hardkey.");
			return;
		}
		mprogressDialog = new ProgressDialog(this);
		mprogressDialog.setMessage("Õ®–≈÷–°≠°≠");
		mprogressDialog.show();
		System.out.println(result.getContents());
		Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_SHORT).show();
		
		myRunnable r = new myRunnable();
		Thread thread = new Thread(r);
		thread.start();
		
	}
	private class myRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			BookInfo bookInfo = null;
			if(result != null || result.getContents()==null){
				String bookinfo = Utils.downloadjsondata(BookAPI_URL+result.getContents());
				System.out.println(bookinfo);
				if(bookinfo!=null){
					bookInfo = JsonTool.parseJson(bookinfo);
					System.out.println(bookInfo.getmTitle());
				}
				if(bookInfo!=null){
					System.out.println("-----------bookinfo------");
					System.out.println(bookInfo.getmPublisher());
				}
				if(bookInfo!=null){
					Message msg = Message.obtain();
					msg.obj = bookInfo;
					handler.sendMessage(msg);
				}
				
			}
			
		}

		
	}

}
