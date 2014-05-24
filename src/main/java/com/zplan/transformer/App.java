package com.zplan.transformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Zplan代码转换程序
 * @author eric
 *
 */
public class App {
	private static final org.slf4j.Logger log = LoggerFactory
			.getLogger(App.class);
	public void transformer() {
		try {
			// 配置FM
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File("tpl"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			// 获取数据模型
			Map<String, Object> model = this.getModel();
			// 获得模板
			Template temp = cfg.getTemplate("test.ftl");
			// 输出
			Writer out = new OutputStreamWriter(System.out);
			temp.process(model, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Object> getModel() {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("user", "Big Joe");
		Map<String, String> latest = new HashMap<String, String>();
		root.put("latestProduct", latest);
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse");
		latest.put("table","we_go_agant_test");
		return root;
	}
	/**
	 * 转换代码
	 */
	public void transfor(){
		log.info("获取数据库结构：");
		List<Map<String, Object>> tls = DBUtil.getDBModel();	
		try {
			//配置转换引擎
			Configuration freemarkerCfg = new Configuration();
			freemarkerCfg.setDirectoryForTemplateLoading(new File("tpl"));
			freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
			//转换配置文件
			Template template = freemarkerCfg.getTemplate(PropUtil.getMTemplet());
			File genfile = new File(PropUtil.getMGenFile());
			if(!genfile.getParentFile().exists()){
				genfile.getParentFile().mkdirs();
			}
			if(!genfile.exists()){				
				genfile.createNewFile();
			}
			Map<String, Object> modm = new HashMap<String, Object>();
			modm.put("tables", tls);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(genfile), "UTF-8"));
			template.process(modm, out);
			out.flush();
			log.info("生成文件："+genfile.getAbsolutePath());
			//转换页面及代码
			for (int i = 0; i < tls.size(); i++) {
				Map<String, Object> table = tls.get(i);
				String tblname = table.get("_model").toString();
				log.info("转换"+table.get("_name")+"对象.....");
				Map<String, String> fls = PropUtil.getParseList(tblname);
				Iterator<Entry<String, String>> iter = fls.entrySet().iterator(); 
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next(); 
				    String key = entry.getKey().toString(); 
				    String val = entry.getValue().toString(); 
				    template = freemarkerCfg.getTemplate(key);
					template.setEncoding("UTF-8");
					genfile = new File(val);
					if(!genfile.getParentFile().exists()){
						genfile.getParentFile().mkdirs();
					}
					if(!genfile.exists()){				
						genfile.createNewFile();
					}
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(genfile), "UTF-8"));
					template.process(table, out);
					out.flush();
					log.info("生成文件："+genfile.getAbsolutePath());
				} 
			}
		}catch (Exception e) {
			log.error("we got some problem in transfer model!!!!!");
			e.printStackTrace();
		}		
	}
	public static void main(String[] args) {
		log.info("zplan transformer start convert code:");
		log.info("init dist paht:");
		FileUtil.del(PropUtil.getProp("distpath"));
		App app = new App();
		app.transfor();
		//app.transformer();
		log.info("transformer end!");
	}
}
