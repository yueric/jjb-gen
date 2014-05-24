package com.zplan.transformer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropUtil {
	static Properties p = new Properties();
	static {
		try {
			 p.load(PropUtil.class.getClassLoader().getResourceAsStream("conf.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getProp(String key){
		return p.getProperty(key);		  
	}
	
	public static void main(String args[]){
		System.out.println(PropUtil.getProp("package"));
	}
	/**
	 * 获取转换列表
	 */
	public static Map<String, String> getParseList(String modelname) {
		Map<String, String> pl = new HashMap<String, String>();
		String cmn = getCamelName(modelname);// modelname.substring(0,1).toUpperCase()+modelname.substring(1);
		String path = PropUtil.getProp("distpath") + File.separator;
				//modelname +File.separator;
		//配置文件
		//pl.put("config.ftl", path+ "readme.txt");
		//代码文件
		pl.put("src/model.ftl", path+ PropUtil.getProp("pkpath")+modelname.toLowerCase()+"/"+cmn+".java");
		pl.put("src/controller.ftl", path+ PropUtil.getProp("pkpath")+modelname.toLowerCase()+"/"+cmn+"Controller.java");
		pl.put("src/validator.ftl", path+ PropUtil.getProp("pkpath")+modelname.toLowerCase()+"/"+cmn+"Validator.java");
		pl.put("src/interceptor.ftl", path+ PropUtil.getProp("pkpath")+modelname.toLowerCase()+"/"+cmn+"Interceptor.java");
		//页面文件
		pl.put("page/add.ftl", path+ "tpl/"+modelname+"/add.html");
		pl.put("page/list.ftl", path+ "tpl/"+modelname+"/list.html");
		pl.put("page/edit.ftl", path+ "tpl/"+modelname+"/edit.html");
		pl.put("page/form.ftl", path+ "tpl/"+modelname+"/_form.html");		
		return pl;
	}
	
	public static String getMTemplet(){
		return "module.ftl";		
	}
	public static String getMGenFile(){
		return PropUtil.getProp("distpath") + File.separator+"readme.txt";		
	}
	/**
	 * 将数据库表名转换成驼峰命名
	 * @param nstr
	 * @return
	 */
	public static String getCamelName(String nstr){
		String rt = "";
		if(nstr!=null){
			String[] names = nstr.split("_");
			for (int i = 0; i < names.length; i++) {
				rt += names[i].substring(0,1).toUpperCase()+names[i].substring(1);
			}			
		}
		return rt;
	}
}
