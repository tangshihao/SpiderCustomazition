package sinafinancespider;

import java.util.ArrayList;

import spiderinterface.UrlsAnalyze;

public class UrlsAnalyzeImp implements UrlsAnalyze{

	public ArrayList<String> getUrls() {
		// TODO Auto-generated method stub
		ArrayList<String> urls=new ArrayList<String>();
		int pageCount=152;
		for(int i=1;i<pageCount+1;i++){
			String url="http://money.finance.sina.com.cn/creditcard/view/vMerchantsearch.php?num=8&city=%C9%CF%BA%A3&page="+i;
			urls.add(url);
		}
		return urls;
	}

}
