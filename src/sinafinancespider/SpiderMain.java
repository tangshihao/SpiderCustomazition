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
		sc.setFilePath("G:\\银联项目资料\\银联项目资料\\新浪财经银行卡优惠信息.csv");
		sc.run();
	}
}
