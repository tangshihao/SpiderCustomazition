package sinafinancespider;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) throws Exception{
		String s="  ";
		System.out.println(s.trim().length());
=======
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args){
		Pattern p=Pattern.compile("\\?.+?k");
		String str="°×½ð¿¨;×êÊ¯¿¨						";
		str.replaceAll(".+", "kk");
		System.out.println(str.replace("\\s+", "kk"));
>>>>>>> 590d7fc3f5e4c13e6b580d6a7bb95cab37ec485c
	}
}
