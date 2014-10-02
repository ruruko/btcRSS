package com.example.getheadline.adapter;

import java.util.List;

import com.example.btcrss.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyHeadlineAdapter extends BaseAdapter {
	Context mContext;
	List<com.example.btcrss.Headlines> mList;
	public  MyHeadlineAdapter(Context context, List<com.example.btcrss.Headlines> mList){
		this.mContext=context;
		this.mList=mList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=View.inflate(mContext, R.layout.hl_list, null);
		TextView tv_title=(TextView) view.findViewById(R.id.setTitleTextView);
		TextView tv_pubDate = (TextView) view.findViewById(R.id.setPubDatetextView);
		
		tv_title.setText(mList.get(position).getTitle());
		tv_pubDate.setText(mList.get(position).getPubDate());
		
		return view;
	}
}
