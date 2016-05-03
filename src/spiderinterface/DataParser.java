package spiderinterface;

import java.util.ArrayList;

public interface DataParser {
	public abstract ArrayList<String> getDataFromPage(String pageContent);
}
