package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constant.Constant;

public class ImagesCount {
	public static void main(String[] args) throws Exception{
		File file=new File("C:\\Users\\m1364\\Desktop\\output.txt");
		ArrayList<String> arr=new ArrayList<String>();
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
		BufferedReader br=new BufferedReader(isr);
		String line="";
		while((line=br.readLine())!=null){
			String[] contents=line.split(Constant.sep);
			Pattern p=Pattern.compile("\\{.*?\\}");
			Matcher m=p.matcher(contents[contents.length-1]);
			int count=0;
			while(m.find()){
				count++;
			}
			String record="";
			for(int i=0;i<contents.length-1;i++){
				if(record.length()!=0)
					record+=Constant.sep;
				record+=contents[i];
			}
			record+=Constant.sep+count;
			arr.add(record);
		}
		File output=new File("C:\\Users\\m1364\\Desktop\\final.txt");
		FileOutputStream fos=new FileOutputStream(output);
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		for(int i=0;i<arr.size();i++)
			osw.append(arr.get(i)+"\n");
		osw.close();
		fos.close();
	}
}
