package sinafinancespider;

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
}
