package sinafinancespider;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constant.Constant;
import spiderinterface.DataParser;

public class DataParserImp implements DataParser{
	private String filePath;
	public DataParserImp(String filePath){
		this.filePath=filePath;
	}
	/* 从文件filePath获取要提取的配置信息，返回所有要提取的数据，每一条数据为一个list元素
	 * (non-Javadoc)
	 * @see spiderinterface.DataParser#getDataFromPage(java.lang.String)
	 */
	public ArrayList<String> getDataFromPage(String pageContent) {
		// TODO Auto-generated method stub
		ArrayList<String[]> arr=new ArrayList<String[]>();
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try{
			fis=new FileInputStream(this.filePath);
			isr=new InputStreamReader(fis,"utf-8");
			br=new BufferedReader(isr);
			String line="";
			while((line=br.readLine())!=null){
				if(!isNote(line)){
					String[] contents=line.split("\\$\\*\\&");
					String[] newStr=new String[2];
					newStr[0]=contents[0].trim();
					newStr[1]=contents[1].trim();
					arr.add(newStr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(fis!=null)
					fis.close();	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		ArrayList<Matcher> matchers=new ArrayList<Matcher>();
		for(int i=0;i<arr.size();i++){
			if(arr.get(i)[1].equals("customization")){
				String[] contents=arr.get(i)[0].split("&&");
				Pattern p=Pattern.compile(contents[0]);
				Matcher m=p.matcher(pageContent);
				matchers.add(m);
			}else{
				Pattern p=Pattern.compile(arr.get(i)[0]);
				Matcher m=p.matcher(pageContent);
				matchers.add(m);
			}
		}
		Set<Integer> set=new HashSet<Integer>();
		for(int i=0;i<matchers.size();i++){
			set.add(i);
		}
		ArrayList<String> finalData=new ArrayList<String>();
		while(isEnd(matchers,set)){
			String dataLine="";
			for(int i=0;i<matchers.size();i++){
				if(dataLine.length()!=0){
					dataLine+=Constant.sep;
				}
				if(set.contains(i)){
					dataLine+=getTargetData(matchers.get(i).group(),arr.get(i)[0].trim(),arr.get(i)[1].trim());
				}else{
					dataLine+="NA";
				}
			}
			finalData.add(dataLine);
		}
		return finalData;
	}
	private String getTargetData(String extract,String regex,String target){
		if(target.equals("text")){
			Pattern p=Pattern.compile(">.*?<");
			Matcher m=p.matcher(extract);
			m.find();
			String result=m.group();
			return result.substring(1, result.length()-1);
		}else if(target.equals("customization")){
			String[] regexs=regex.split("&&");
			Pattern p=Pattern.compile(regexs[1]+".*?"+regexs[2]);
			Matcher m=p.matcher(extract);
			m.find();
			String result=m.group();
			return result.substring(regexs[1].length(), result.length()-regexs[2].length());
		}else{
			Pattern p=Pattern.compile(target+"=\".*?\"");
			Matcher m=p.matcher(extract);
			m.find();
			String result=m.group();
			p=Pattern.compile("\".*\"");
			m=p.matcher(result);
			m.find();
			result=m.group();
			return result.substring(1, result.length()-1);
		}
	}
	/**
	 * target是要定位的标签，targetData是要提取的标签里的数据
	 * @param target
	 * @param targetData
	 * @return
	 */
	private boolean isEnd(ArrayList<Matcher> matchers,Set<Integer> set){
		boolean result=false;
		for(int i=0;i<matchers.size();i++){
			if(matchers.get(i).find())
				result=true;
			else
				set.remove(i);
		}
		return result;
	}
	private boolean isNote(String line){
		Pattern p=Pattern.compile("\\s*//.*");
		Matcher m=p.matcher(line);
		return m.matches();
	}
//	public static void main(String[] args) throws Exception{
//		File html=new File("C:\\Users\\m1364\\Desktop\\html.txt");
//		FileInputStream fis=new FileInputStream(html);
//		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
//		BufferedReader br=new BufferedReader(isr);
//		String line="";
//		String pageContent="";
//		while((line=br.readLine())!=null){
//			pageContent+=line;
//		}
//		DataParserImp dp=new DataParserImp("C:\\Users\\m1364\\Desktop\\config.txt");
//		System.out.println(dp.getDataFromPage(pageContent));
//	}
=======
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
>>>>>>> 590d7fc3f5e4c13e6b580d6a7bb95cab37ec485c
}
