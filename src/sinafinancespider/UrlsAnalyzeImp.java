package sinafinancespider;

import java.util.ArrayList;

import spiderinterface.UrlsAnalyze;

public class UrlsAnalyzeImp implements UrlsAnalyze{
<<<<<<< HEAD
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
=======

	public ArrayList<String> getUrls() {
		// TODO Auto-generated method stub
		ArrayList<String> urls=new ArrayList<String>();
		int pageCount=152;
		for(int i=1;i<pageCount+1;i++){
			String url="http://money.finance.sina.com.cn/creditcard/view/vMerchantsearch.php?num=8&city=%C9%CF%BA%A3&page="+i;
>>>>>>> 590d7fc3f5e4c13e6b580d6a7bb95cab37ec485c
			urls.add(url);
		}
		return urls;
	}
<<<<<<< HEAD
=======

>>>>>>> 590d7fc3f5e4c13e6b580d6a7bb95cab37ec485c
}
