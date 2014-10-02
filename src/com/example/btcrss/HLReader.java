package com.example.btcrss;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;


public class HLReader {

	
	private String rssUrl;

	private List<Headlines> rssItems;
	public HLReader(String rssUrl) {
		this.rssUrl = rssUrl;
		rssItems = new ArrayList<Headlines>();
	}
	
	// Used to reference item while parsing
	//private Headlines currentItem;

	
	public List<Headlines> getItems(){
		
		Log.d("test", "Get news");
	    try{HttpURLConnection con = (HttpURLConnection) (new URL(rssUrl)).openConnection();
	    con.setRequestMethod("GET");
	    con.connect();
	    InputStream is = con.getInputStream();
	 
	    // Start parsing XML
	    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
	    parser.setInput(is, null);
	    int event = parser.getEventType();
	    String tagName = null;
	    String currentTag = null;
	    Headlines currentItem = new Headlines();
	     
	    while (event != XmlPullParser.END_DOCUMENT) {

            tagName = parser.getName();
            Log.d("Srv", "Tag:" + tagName);
            if (event == XmlPullParser.START_TAG) {
            	currentTag = tagName;
            }
            else if (event == XmlPullParser.TEXT) {
            	if ("Title".equalsIgnoreCase(currentTag)) 
            		 currentItem.setTitle(parser.getText());
            	else if("link".equalsIgnoreCase(currentTag)){
            		currentItem.setLink(parser.getText());
            	}
            	else if("PubDate".equalsIgnoreCase(currentTag)) {                    	
                	currentItem.setPubDate(parser.getText());
                	Headlines h= new Headlines(currentItem.getTitle(),currentItem.getLink(),currentItem.getPubDate());
                	rssItems.add(h);
            	}
            }
            event = parser.next();

	    }
	 
	} catch (Exception e) {
	    e.printStackTrace();
	}
		return rssItems;
}

		
/*		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		HLParseHandler handler = new HLParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
*/
		
	}



