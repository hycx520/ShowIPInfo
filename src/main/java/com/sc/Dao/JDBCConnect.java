package com.sc.Dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class JDBCConnect {
	    static Logger logger = (Logger) LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	    static String jdbcProperties="src/main/resources/jdbc.properties";
	    /**
	     * 针对select语句返回resultset对象
	     * */
		public static ResultSet SQLConnectForSelect(String SQL) throws SQLException{
			InputStream in=null;
			try {
				in=new FileInputStream(jdbcProperties);
				logger.info("开始加载数据库");
			} catch (Exception e) {
				logger.error("未找到jdbc的配置文件");
			}
			Connection conn =null;
			Statement stat =null;
			ResultSet rs =null;
			try {
				Properties p=new Properties();
				p.load(in);
				// 1.注册驱动
				Class.forName(p.getProperty("jdbc.className"));
				// 2.获取连接对象
				String url = p.getProperty("jdbc.url");
				String user=p.getProperty("jdbc.user");
				String password=p.getProperty("jdbc.password");
				logger.info("确认账号："+p.getProperty("jdbc.user"));
				conn = DriverManager.getConnection(url, user, password);
				// 3.获取执行SQL语句
				stat = conn.createStatement();
				// 拼写SQL语句
				logger.info("准备执行SQL语句"+SQL);
				// 4.调用执行者对象方法,执行SQL语句获取结果集
				// 返回的是ResultSet接口的实现类对象,实现类在mysql驱动中
				rs = stat.executeQuery(SQL);
				if(!rs.next()) {
					logger.error("未查询到数据或没有数据受影响-------"+SQL);
				}
				while (rs.next()) {
					// 获取每列的数据,使用的是ResultSet接口的方法getXXX
					int id = rs.getInt("ID");// 相当于rs.getInt(1);这个方法有弊端
					String sname = rs.getString("NAME");
					double sprice = rs.getDouble("AGE");
					String sdesc = rs.getString("HOME");
					System.out.println(id+"\t"+sname+"\t"+sprice+"\t"+sdesc);
				}
			} catch (Exception e) {
				logger.error("连接数据库失败");
				logger.error(e);
			}finally {
				// 6.关闭资源
				conn.close();
				logger.info("资源关闭");
			}
			return rs;
		}
		public static Integer SQLConnectForOther(String SQL) throws SQLException{
			InputStream in=null;
			Integer a=0;
			try {
				in=new FileInputStream(jdbcProperties);
				logger.info("开始加载数据库");
			} catch (Exception e) {
				logger.error("未找到jdbc的配置文件");
			}
			Connection conn =null;
			Statement stat =null;
			ResultSet rs =null;
			try {
				Properties p=new Properties();
				p.load(in);
				// 1.注册驱动
				Class.forName(p.getProperty("jdbc.className"));
				// 2.获取连接对象
				String url = p.getProperty("jdbc.url");
				String user=p.getProperty("jdbc.user");
				String password=p.getProperty("jdbc.password");
				logger.info("确认账号："+p.getProperty("jdbc.user"));
				conn = DriverManager.getConnection(url, user, password);
				// 3.获取执行SQL语句
				stat = conn.createStatement();
				// 拼写SQL语句
				logger.info("准备执行SQL语句"+SQL);
				// 4.调用执行者对象方法,执行SQL语句获取结果集
				// 返回的是ResultSet接口的实现类对象,实现类在mysql驱动中
				rs = stat.executeQuery(SQL);
				
				if(!rs.next()) {
					logger.error("未查询到数据或没有数据受影响-------"+SQL);
				}else {
					a++;
				}
			} catch (Exception e) {
				logger.error("连接数据库失败");
				logger.error(e);
			}finally {
				// 6.关闭资源
				conn.close();
				stat.close();
				rs.close();
				logger.info("资源关闭");
			}
			logger.info("SQL执行成功");
			return a;
		}
		public static void main(String[] args) {
			try {
				logger.info(SQLConnectForOther("insert into TEST values ('5','孙吴','22','江苏省')"));
				logger.info(SQLConnectForSelect("select * from TEST"));
				
			} catch (SQLException e) {
				logger.error(e);
			}
		}

}
