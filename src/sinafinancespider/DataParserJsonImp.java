package sinafinancespider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constant.Constant;
import net.sf.json.JSONObject;
import spiderinterface.DataParser;

public class DataParserJsonImp implements DataParser{
	/* 解析json格式的数据
	 * (non-Javadoc)
	 * @see spiderinterface.DataParser#getDataFromPage(java.lang.String)
	 */
	public ArrayList<String> getDataFromPage(String pageContent) {
		// TODO Auto-generated method stub
		if(pageContent.length()==0){
			System.out.println("该页面爬取失败");
			return null;
		}
		Pattern p=Pattern.compile("\\{.*\\}");
		Matcher m=p.matcher(pageContent);
		m.find();
		JSONObject jo=JSONObject.fromObject(m.group());
		List<String> jsonList=jo.getJSONArray("comments");
		ArrayList<String> records=new ArrayList<String>();
		for(int i=0;i<jsonList.size();i++){
			String record="";
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("id")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("id").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("content")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("content").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("referenceName")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("referenceName").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("referenceTime")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("referenceTime").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("replyCount")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("replyCount").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("score")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("score").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("usefulVoteCount")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("usefulVoteCount").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("userLevelId")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("userLevelId").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("userProvince")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("userProvince").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("userRegisterTime")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("userRegisterTime").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("nickname")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("nickname").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA"+Constant.sep;
			if(JSONObject.fromObject(jsonList.get(i)).containsKey("images")){
				String str=JSONObject.fromObject(jsonList.get(i)).getString("images").trim();
				if(str.length()==0){
					record+="NA"+Constant.sep;
				}else{
					record+=str+Constant.sep;
				}
			}
			else
				record+="NA";
			records.add(record);
		}
		return records;
	}
}
