package spiderinterface;

import java.util.ArrayList;

public interface DataParser {
	/**
	 * 输出为要爬取的数据，每一条数据是list的一个元素
	 * @param pageContent
	 * @return
	 */
	public abstract ArrayList<String> getDataFromPage(String pageContent);
}
