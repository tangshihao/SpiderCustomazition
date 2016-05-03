package sinafinancespider;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class OpenUrl {
	private int relinkCount=1;
	public void setRelinkCount(int relinkCount){
		this.relinkCount=relinkCount;
	}
	public String getPageContent(String url) throws IOException{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		CloseableHttpResponse response=null;
		HttpGet hg=null;
		try{
			for(int i=0;i<relinkCount;i++){
				hg=new HttpGet(url);
				hg.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				hg.addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
				hg.addHeader("Accept-Encoding","gzip,deflate");
				hg.addHeader("Connection","keep-alive");
				hg.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
				response=httpclient.execute(hg);
				if(response.getStatusLine().getStatusCode()==200){
					return EntityUtils.toString(response.getEntity(),"gb2312");
				}
			}
			System.out.println(url+"£º·ÃÎÊ³ö´í£¡");
		}finally{
			if(response!=null)
				response.close();
			if(httpclient!=null)
				httpclient.close();
		}
		return null;
	}
}
