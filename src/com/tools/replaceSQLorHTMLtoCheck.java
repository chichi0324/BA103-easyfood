package com.tools;

public class replaceSQLorHTMLtoCheck {
	public static String check(String content){
		//防止XSS攻擊
		content = content.replace("<", "&amp");
		content=content.replace(">","&lt");
		content=content.replace("\"","&gt");
		content=content.replace("\'","&quot");
		content=content.replace("/","&#x27");
		content=content.replace("&","&#x2F");
		//防止SQL隱碼攻擊
		content = content.replace("'", "』");
		content=content.replace("-","『");
		content=content.replace("；","＃");
		return content;
	}
}
