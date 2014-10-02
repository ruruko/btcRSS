package com.example.btcrss;

public class Headlines {
	private String title = "";
	private String link = "";
	private String pubDate = "";
	
	public Headlines(){
		
	}
	
	public Headlines(String title, String link, String pubDate) {

		this.title = title;
		this.link = link;
		this.pubDate = pubDate;
	}
	public String getTitle(){
		return title;
	}
	public String getLink(){
		return link;
	}
	public String getPubDate(){
		return pubDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return title;
	}
	
}
