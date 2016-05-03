package sinafinancespider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spiderinterface.DataParser;

public class DataParserImp implements DataParser{
	Pattern name=Pattern.compile("class=\"highlight\">.+?</a>");
	Pattern organization=Pattern.compile("\\?bank=.+?</a>");
	Pattern city=Pattern.compile("\\?city=.+?</a>");
	Pattern type=Pattern.compile("\\?merchant_type=.+?</a>");
	Pattern cardLevel=Pattern.compile("<span>支持卡类：</span>.+?</div>");
	Pattern privilege=Pattern.compile("<span>优惠截止：.+?</span>");
	Pattern link=Pattern.compile("class=\"bll_01_content_pic\"><a href=.+?>");
	Pattern extract=Pattern.compile(">.+?<");
	public ArrayList<String> getDataFromPage(String pageContent) {
		// TODO Auto-generated method stub
		ArrayList<String> result=new ArrayList<String>();
		String realname="NA";
		String realorganization="NA";
		String realcity="NA";
		String realtype="NA";
		String realcardLevel="NA";
		String realprivilege="NA";
		String realextract="NA";
		String reallink="NA";
		Matcher nameM=name.matcher(pageContent);
		Matcher cityM=city.matcher(pageContent);
		Matcher typeM=type.matcher(pageContent);
		Matcher cardLevelM=cardLevel.matcher(pageContent);
		Matcher privilegeM=privilege.matcher(pageContent);
		Matcher organizationM=organization.matcher(pageContent);
		Matcher linkM=link.matcher(pageContent);
		String temp;
		while(nameM.find()){
			temp=nameM.group();
			realname=this.extract1(temp);
			if(organizationM.find()){
				temp=organizationM.group();
				realorganization=this.extract1(temp);
			}else{
				realorganization="NA";
			}
			if(cityM.find()){
				temp=cityM.group();
				realcity=this.extract1(temp);
			}else{
				realcity="NA";
			}
			if(typeM.find()){
				temp=typeM.group();
				realtype=this.extract1(temp);
			}else{
				realtype="NA";
			}
			if(cardLevelM.find()){
				temp=cardLevelM.group();
				realcardLevel=this.dataHandle(this.extract2(temp));
			}else{
				realcardLevel="NA";
			}
			if(privilegeM.find()){
				temp=privilegeM.group();
				realprivilege=this.extract1(temp).substring(5);
			}else{
				realprivilege="NA";
			}
			if(linkM.find()){
				temp=linkM.group();
				reallink=this.extractLink(temp);
			}else{
				reallink="NA";
			}
			System.out.println(realname+","+realcity+","+realtype+","+realorganization+","+realcardLevel+","+realprivilege+","+reallink);
			result.add(realname+","+realcity+","+realtype+","+realorganization+","+realcardLevel+","+realprivilege+","+reallink);
		}
		return result;
	}
	private String extractLink(String link){
		Pattern p=Pattern.compile("http.+?\"");
		Matcher m=p.matcher(link);
		String result="";
		if(m.find())
			result=m.group();
		return result.substring(0,result.length()-1);
	}
	private String dataHandle(String str){
		String newStr=str.replaceAll("\\s+", "");
		if(newStr.length()==0)
			return "NA";
		char[] c=newStr.toCharArray();
		for(int i=0;i<c.length;i++){
			if(c[i]==',')
				c[i]=';';
		}
		return String.valueOf(c);
	}
	private String extract1(String str){
		Matcher m=extract.matcher(str);
		String result=">NA<";
		if(m.find()){
			result=m.group();
		}
		return result.substring(1,result.length()-1);	
	}
	private String extract2(String str){
		Matcher m=extract.matcher(str);
		String result=">NA<";
		if(m.find()){
			if(m.find()){
				result=m.group();
			}
		}
		if(result.length()<3)
			return "NA";
		return result.substring(1,result.length()-1);
	}
	public static void main(String[] args) throws IOException{
		OpenUrl ou=new OpenUrl();
		ou.setRelinkCount(5);
		String content=ou.getPageContent("http://money.finance.sina.com.cn/creditcard/view/vMerchantsearch.php?num=8&city=%C9%CF%BA%A3&page=147");
		new DataParserImp().getDataFromPage(content);
	}
}
