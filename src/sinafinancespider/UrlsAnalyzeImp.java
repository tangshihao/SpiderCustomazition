package sinafinancespider;

import java.util.ArrayList;

import spiderinterface.UrlsAnalyze;

public class UrlsAnalyzeImp implements UrlsAnalyze{
	private int start=0;
	private int count=0;
	public UrlsAnalyzeImp(int start,int count){
		this.start=start;
		this.count=count;
	}
	public ArrayList<String> getUrls() {
		// TODO Auto-generated method stub
		ArrayList<String> urls=new ArrayList<String>();
		int pageCount=count;
		for(int i=start;i<pageCount+1;i++){
			String url="http://sclub.jd.com/productpage/p-1867154-s-0-t-3-p-"+i+".html?callback=fetchJSON_comment98vv5807";
			urls.add(url);
		}
		return urls;
	}
}
