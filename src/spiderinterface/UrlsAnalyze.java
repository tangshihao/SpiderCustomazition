package spiderinterface;

import java.util.ArrayList;

public interface UrlsAnalyze {
	/**
	 * 返回的是所有需要爬取的网页链接list
	 * @return
	 */
	public abstract ArrayList<String> getUrls();
}
