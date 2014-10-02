package com.example.btcrss;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.getheadline.adapter.MyHeadlineAdapter;

public class HLMainActivity extends Activity {


	private HLMainActivity local;
	
	//private SharedPreferences newsInfo;
	
	Button refreshNews;
	ListView newsListView;
	
	//TextView setTitleTextView;
	//TextView setPubDateTextView;
	TextView lastUpdateTextView;
	
	
	
	// XML node keys
	static final String KEY_ITEM = "Item"; // parent node
	static final String KEY_TITLE = "Title";
	static final String KEY_PUB_DATE = "PubDate";
	static final String KEY_LAST_UPDATE = "LastUpdate";

	//XML to retrieve
	String title = "";
	String link = "";
	String pubDate = "";
	String lastUpdate = "";
	
	String feedsURL = "http://feeds.finance.yahoo.com/rss/2.0/headline?s=BTCUSD=X&region=US&lang=en-US";
	
	//String[][] xmlPullParserArray = {{"title","0"},{"link","0"},{"pubDate","0"},{"lastUpdate","0"}};
	
	//int parserArrayIncrement = 0;
	
	String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hlmain);
		
		local = this;
		
		//newsListView = (ListView) findViewById(R.id.newsListView);
		refreshNews = (Button) findViewById(R.id.refreshButton);
		
		//setTitleTextView = (TextView) findViewById(R.id.setTitleTextView);
		//setPubDateTextView = (TextView) findViewById(R.id.setPubDatetextView);
		lastUpdateTextView = (TextView) findViewById(R.id.updateTextView);
		
		refreshNews.setOnClickListener(refreshBtnListener);
		
		
		//newsListView.setAdapter(new ArrayAdapter<String>(this,R.layout.hl_list, getData()));
		
	}
	public  OnClickListener refreshBtnListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			new MyAsyncTask().execute(feedsURL);
			Log.d("NewsReader", Thread.currentThread().getName()); 
			
			lastUpdateTextView.setText("  "+ currentDateTimeString);
			
		}
		
	};
	private class MyAsyncTask extends AsyncTask<String, Void, List<Headlines>>{

		@Override
		protected List<Headlines> doInBackground(String... urls) {
			
			// Debug the task thread name
			Log.d("HLRssReader", Thread.currentThread().getName());
			
			try {
				// Create RSS reader
				HLReader hlReader = new HLReader(urls[0]);
			
				// Parse RSS, get items
				return hlReader.getItems();
				
			
			} catch (Exception e) {
				Log.e("HLRssReader", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(List<Headlines> result) {
			
			ListView rssItems = (ListView) findViewById(R.id.newsListView);
			
			/*for(int i=0; i<result.size();i++){
				System.out.println(result.get(i).getTitle());
			}
			*/
			
			// Create a list adapter
			//ArrayAdapter<Headlines> adapter = new ArrayAdapter<Headlines>(local,android.R.layout.simple_list_item_1, result);
			MyHeadlineAdapter hladapter= new MyHeadlineAdapter(HLMainActivity.this, result);
			
			// Set list adapter for the ListView
			rssItems.setAdapter(hladapter);
						
			// Set list view item click listener
			rssItems.setOnItemClickListener(new HeadlinesListener(result, local));
		}
	
	}
	
	

}
