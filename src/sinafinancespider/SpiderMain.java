package sinafinancespider;

import java.io.IOException;

import spiderinterface.DataParser;
import spiderinterface.UrlsAnalyze;

public class SpiderMain {
	public static void main(String[] args) throws IOException, InterruptedException{
//		DataParser dp=new DataParserImp("C:\\Users\\m1364\\Desktop\\config.txt");
//		for(int i=0,j=0;i+39<3201;i=(j+1)*40,j++){
//			UrlsAnalyze ua=new UrlsAnalyzeImp(i,i+39);
//			SpiderCore sc=new SpiderCore(dp,ua);
//			sc.setRelinkCount(5);
//			sc.setFilePath("C:\\Users\\m1364\\Desktop\\output.txt");
//			sc.run();
//			System.out.println(i+"-"+(i+39));
//		}
		DataParser dp=new DataParserImp("C:\\Users\\m1364\\Desktop\\config.txt");
		UrlsAnalyze ua=new UrlsAnalyzeImp(0,60);
		SpiderCore sc=new SpiderCore(dp,ua);
		sc.setRelinkCount(5);
		sc.setFilePath("C:\\Users\\m1364\\Desktop\\output.txt");
		sc.run();
	}
}
