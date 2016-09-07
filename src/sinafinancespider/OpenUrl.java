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
				hg.addHeader("Cookie","TrackID=1SE2a0BrIvgo4KzWxBaa60xb1bQdb0OHrXZnD3W1IiPXUtIBx4tP7rrsSVZc1CVdrODnMmchz7DR1ErEBAheiHQjuMNOTPsK80gvSqqWsJbR8YHcEc1ssQR4hA_XGlq1c; expires=Sun, 21 Feb 2021 03:24:20 GMT; path=/; domain=.jd.com;"+
						"__jda=122270672.1160007706.1438743888.1472645663.1472647740.29; expires=Mon, 27 Feb 2017 12:55:47 GMT; path=/; domain=.jd.com;"+
						"__jdb=122270672.3.1160007706|29.1472647740; expires=Wed, 31 Aug 2016 13:25:47 GMT; path=/; domain=.jd.com;"+
						"__jdc=122270672; path=/; domain=.jd.com;"+
						"__jdu=1160007706; expires=Mon, 27 Feb 2017 12:58:31 GMT; path=/; domain=.jd.com;"+
						"__jdv=122270672|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_92fa5eef4282491faa90e3a927bc024f; expires=Thu, 15 Sep 2016 08:44:26 GMT; path=/; domain=.jd.com;"+
						"_pst=jd_4123751efda99; expires=Thu, 24 Mar 2016 03:24:20 GMT; path=/; domain=.jd.com; HttpOnly;"+
						"_tp=DplJdkEJI1PoV449JBMo5F8oKUTPNgCTpUIux2AncR8%3D; expires=Thu, 24 Mar 2016 03:24:20 GMT; path=/; domain=.jd.com;"+
						"areaId=1; expires=Sat, 10 Sep 2016 12:55:50 GMT; path=/; domain=.jd.com;"+
						"cn=4; expires=Fri, 25 Mar 2016 03:07:43 GMT; path=/; domain=.jd.com;"+
						"ipLoc-djd=1-72-2799-0; expires=Fri, 30 Sep 2016 08:44:36 GMT; path=/; domain=.jd.com;"+
						"ipLocation=%u5317%u4EAC; expires=Fri, 30 Sep 2016 12:55:50 GMT; path=/; domain=.jd.com;"+
						"pin=jd_4123751efda99; expires=Thu, 24 Mar 2016 03:24:20 GMT; path=/; domain=.jd.com;"+
						"pinId=oRKx7x6ILIxaU-vrbDlT-LV9-x-f3wj7; expires=Wed, 22 Feb 2017 03:24:20 GMT; path=/; domain=.jd.com;"+
						"unick=jd_155260895; expires=Thu, 24 Mar 2016 03:24:20 GMT; path=/; domain=.jd.com; HttpOnly;"+
						"unpl=V2_ZzNtbUpUFEVxXBIDfhpVBmILEgoSB0sUIQoWXHgfD1dmABcKclRCFXIUR11nG1wUZwQZWEdcQRFFCHZXchBYAWcCGllyBBNNIEwHDCRSBUE3XHxcFVUWF3RaTwEoSVoAYwtBDkZUFBYhW0IAKElVVTUFR21yVEMldQl2VHseXQRmBRRcRWdzEkU4dlNyGFQFZzMTbUNnAUEpAU5WfxxZSGcDFVxDVkUTdA92VUsa; expires=Thu, 15 Sep 2016 08:44:25 GMT; path=/; domain=.jd.com;"+
						"user-key=72adcbf8-ef19-4526-ba68-11fbe1fe20df; expires=Thu, 24 Mar 2016 03:23:30 GMT; path=/; domain=.jd.com;");
				response=httpclient.execute(hg);
				if(response.getStatusLine().getStatusCode()==200){
					String result=EntityUtils.toString(response.getEntity(),"utf-8");
					if(result.length()==0){
						i=0;
						try{
							Thread.sleep(1000);
						}catch(Exception e){
							e.printStackTrace();
						}
						continue;
					}else{
						return result;
					}
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
