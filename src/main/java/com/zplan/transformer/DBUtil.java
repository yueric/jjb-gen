package com.zplan.transformer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

/**
 * 数据库元数据操作
 * 
 * @author eric
 * 
 */
public class DBUtil {
	private static final org.slf4j.Logger log = LoggerFactory
			.getLogger(DBUtil.class);
	static Connection con = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(PropUtil.getProp("jdbcUrl"),
					PropUtil.getProp("user"), PropUtil.getProp("password"));
		} catch (Exception ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * DatabaseMetaData一些用法
	 * 
	 * @throws Exception
	 */
	public void getDBInfo() {
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			log.info(dbmd.getDatabaseProductName());// 获取数据库产品名称
			log.info(dbmd.getDatabaseProductVersion());// 获取数据库产品版本号
			log.info(dbmd.getCatalogSeparator());// 获取数据库用作类别和表名之间的分隔符
													// 如test.user
			log.info(dbmd.getDriverVersion());// 获取驱动版本

			ResultSet rs = dbmd.getCatalogs();// 取可在此数据库中使用的类别名,在mysql中说白了就是可用的数据库名称，只有一列
			while (rs.next()) {
				log.info(rs.getString("TABLE_CAT"));
				// rs.getString(1));
			}

			rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (rs.next()) {
				log.info(rs.getString(3) + "->" + rs.getString(4));
			}

			rs = dbmd.getColumns(null, null, "biz_screen", null);

			while (rs.next()) {
				log.info(rs.getString("COLUMN_NAME") + " 类型="
						+ rs.getInt("DATA_TYPE") + " 列大小="
						+ rs.getInt("COLUMN_SIZE") + " 注释="
						+ rs.getString("REMARKS") + " 是否允许为NULL="
						+ rs.getInt("NULLABLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("we got problem in dbutil!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBUtil ut = new DBUtil();
		ut.getDBInfo();

	}

	/**
	 * 获取指定数据库表的元数据结构
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getDBModel() {
		List<Map<String, Object>> model = new ArrayList<Map<String,Object>>();//HashMap<String, Object>();
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			//取所有表结构对象
			ResultSet rs = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			Map<String, Object> table = null;
			ResultSet tbrs = null;
			List<Map<String, String>> cols = null;
			Map<String,String> col = null;
			while (rs.next()) {
				log.info("获取"+rs.getString(3)+"信息......");
				table = new HashMap<String, Object>();
				cols = new ArrayList<Map<String,String>>();
				model.add(table);
				table.put("_name", rs.getString(3));
				table.put("_model", getCamelName(rs.getString(3)));
				table.put("_package", PropUtil.getProp("package"));
				table.put("_columns", cols);
				tbrs = dbmd.getColumns(null, null, rs.getString(3), null);
				//解析表中字段
				while (tbrs.next()) {
					col = new HashMap<String,String>();
					cols.add(col);
					col.put("name", tbrs.getString("COLUMN_NAME"));
					col.put("type", tbrs.getString("DATA_TYPE"));
					col.put("comment", tbrs.getString("REMARKS"));
					col.put("nullable", tbrs.getString("NULLABLE"));
				}				
			}
		} catch (SQLException e) {
			log.error("we got problem in dbutil!");
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 从表名获取驼峰命名的方法
	 * @param mname
	 * @return
	 */
	private static String getCamelName(String mname){
		StringBuffer sb = new StringBuffer();
		if(mname!=null){
			String[] names = mname.split("_");
			for (int i = 0; i < names.length; i++) {
				if(i==0){
					sb.append(names[i]);
				}else{
					sb.append(names[i].substring(0, 1).toUpperCase());
					sb.append(names[i].substring(1));
				}
			}
		}
		return sb.toString();
		
	}
}
