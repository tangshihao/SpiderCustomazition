package spiderinterface;

import java.util.ArrayList;

public interface DataParser {
	/**
	 * ���ΪҪ��ȡ�����ݣ�ÿһ��������list��һ��Ԫ��
	 * @param pageContent
	 * @return
	 */
	public abstract ArrayList<String> getDataFromPage(String pageContent);
}
