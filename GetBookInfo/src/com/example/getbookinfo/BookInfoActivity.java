package com.example.getbookinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.BookInfo;

public class BookInfoActivity extends Activity {
	private TextView mTitle;
	private ImageView mCover;
	private TextView mAuthor;
	private TextView mPublisher;
	private TextView mPublishDate;
	private TextView mISBN;
	private TextView mSummary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookinfo);
		Intent intent = getIntent();
		BookInfo bookinfo = (BookInfo)intent.getParcelableExtra("bookinfo");
		mTitle = (TextView) this.findViewById(R.id.book_detail_title);
		mAuthor = (TextView) this.findViewById(R.id.book_detail_author);
		mPublisher = (TextView) this.findViewById(R.id.book_detail_publisher);
		mPublishDate = (TextView) this.findViewById(R.id.book_detail_pubdate);
		mISBN = (TextView) this.findViewById(R.id.book_detail_isbn);
		mSummary = (TextView) this.findViewById(R.id.book_detail_summary);
		mCover = (ImageView) this.findViewById(R.id.book_detail_cover);
		mTitle.setText(bookinfo.getmTitle());
		mAuthor.setText(bookinfo.getAuthor());
		mPublisher.setText(bookinfo.getmPublisher());
		mPublishDate.setText(bookinfo.getmPublishDate());
		mISBN.setText(bookinfo.getmISBN());
		mSummary.setText(bookinfo.getmSummary());
		mCover.setImageBitmap(bookinfo.getmCover());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_info, menu);
		return true;
	}

}
