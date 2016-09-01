package sinafinancespider;

import java.io.IOException;

import spiderinterface.DataParser;
import spiderinterface.UrlsAnalyze;

public class SpiderMain {
<<<<<<< HEAD
	public static void main(String[] args) throws IOException, InterruptedException{
		DataParser dp=new DataParserJsonImp("");
		for(int i=0,j=0;i+39<3201;i=(j+1)*40,j++){
			UrlsAnalyze ua=new UrlsAnalyzeImp(i,i+39);
			SpiderCore sc=new SpiderCore(dp,ua);
			sc.setRelinkCount(5);
			sc.setFilePath("C:\\Users\\m1364\\Desktop\\output.txt");
			sc.run();
			System.out.println(i+"-"+(i+39));
		}
=======
	public static void main(String[] args) throws IOException{
		DataParser dp=new DataParserImp();
		UrlsAnalyze ua=new UrlsAnalyzeImp();
		SpiderCore sc=new SpiderCore(dp,ua);
		sc.setRelinkCount(5);
		sc.setFilePath("G:\\银联项目资料\\银联项目资料\\新浪财经银行卡优惠信息.csv");
		sc.run();
>>>>>>> 590d7fc3f5e4c13e6b580d6a7bb95cab37ec485c
	}
}
