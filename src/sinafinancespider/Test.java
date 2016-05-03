package sinafinancespider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args){
		Pattern p=Pattern.compile("\\?.+?k");
		String str="°×½ð¿¨;×êÊ¯¿¨						";
		str.replaceAll(".+", "kk");
		System.out.println(str.replace("\\s+", "kk"));
	}
}
