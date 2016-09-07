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
			String url="http://tieba.baidu.com/f?kw=%E5%B9%BF%E5%91%8A&ie=utf-8&pn="+i*50;
			urls.add(url);
		}
		return urls;
	}
}
