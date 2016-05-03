package sinafinancespider;

import java.io.IOException;

import spiderinterface.DataParser;
import spiderinterface.UrlsAnalyze;

public class SpiderMain {
	public static void main(String[] args) throws IOException{
		DataParser dp=new DataParserImp();
		UrlsAnalyze ua=new UrlsAnalyzeImp();
		SpiderCore sc=new SpiderCore(dp,ua);
		sc.setRelinkCount(5);
		sc.setFilePath("G:\\������Ŀ����\\������Ŀ����\\���˲ƾ����п��Ż���Ϣ.csv");
		sc.run();
	}
}
