package sinafinancespider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import spiderinterface.DataParser;
import spiderinterface.UrlsAnalyze;

public class SpiderCore {
	private DataParser dp;
	private UrlsAnalyze ua;
	private int relinkCount;
	public SpiderCore(DataParser dp,UrlsAnalyze ua){
		this.dp=dp;
		this.ua=ua;
	}
	public void setRelinkCount(int relinkCount){
		this.relinkCount=relinkCount;
	}
	private File file;
	public void setFilePath(String path){
		this.file=new File(path);
	}
	public void run() throws IOException{
		ArrayList<String> totalData=new ArrayList<String>();
		ArrayList<String> urls=ua.getUrls();
		for(int i=0;i<urls.size();i++){
			System.out.println("Ŀǰ��ȡ��"+(i+1)+"��ҳ�棬����"+urls.size()+"��ҳ�档");
			OpenUrl ou=new OpenUrl();
			ou.setRelinkCount(this.relinkCount);
			String pageContent=ou.getPageContent(urls.get(i));
			ArrayList<String> data=dp.getDataFromPage(pageContent);
			try{
				totalData.addAll(data);
			}catch(NullPointerException e){
				continue;
			}
		}
		FileWriter fw=null;
		try{
			fw=new FileWriter(this.file);
			String line;
			for(int i=0;i<totalData.size();i++){
				fw.append(totalData.get(i)+"\n");
			}
		}finally{
			if(fw!=null)
				fw.close();
		}
	}
}
