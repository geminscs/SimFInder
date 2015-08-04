package indi.tammy.simfinder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBManager {
	private final static String driverName = "net.sourceforge.jtds.jdbc.Driver";  //加载JDBC驱动
	private final static String dbURL = "jdbc:jtds:sqlserver://122.228.236.130:5706;DatabaseName=newhope";  //连接服务器和数据库test
	private final static String userName = "test01";  //默认用户名
	private final static String userPwd = "newhope2000";  //密码
	private static Connection dbConn;
	
	public static boolean initalize(){
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	   
	}
	
	public static List<Question> findUnCheckSimQuestions(){
 	   	PreparedStatement psm = null;
 	   	ResultSet rs=null;
 	   	Calendar calendar = Calendar.getInstance(); 
 	   	calendar.add(Calendar.DATE,-1);  
 	   	calendar.set(Calendar.HOUR_OF_DAY, 0);  
 	   	calendar.set(Calendar.MINUTE, 0);  
 	   	calendar.set(Calendar.SECOND, 0);  
 	   	calendar.set(Calendar.MILLISECOND, 0);  
 	   	long timeStart = calendar.getTimeInMillis();
 	   	System.out.println(timeStart);
 	   	calendar.set(Calendar.HOUR_OF_DAY, 24);  
 	   	long timeEnd = calendar.getTimeInMillis();
 	   	timeEnd --;
		String sql="select * from qb_formal_qbank where submit_time between ? and ?";
		try {
			psm = dbConn.prepareStatement(sql);
			psm.setLong(1, timeStart);
			psm.setLong(2, timeEnd);
			rs=psm.executeQuery();
			List<Question> l = new ArrayList<Question>();
			while(rs.next()){
				Question q = new Question();
				q.setId(rs.getInt("id"));
		        q.setContent(rs.getString("content"));
		        q.setAnswer(rs.getString("answer"));
		        q.setAnalysis(rs.getString("analysis"));
		        q.setFull(rs.getBoolean("isFull"));
		        q.setHardness(rs.getInt("hardness"));
		        q.setSubject_id(rs.getInt("subject_id"));
		        q.setType(rs.getInt("type"));
		        q.setSubmit_time(rs.getLong("submit_time"));
		        l.add(q);
		        //System.out.println(q.toString());
		    }
			rs.close();
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}    
	}
	
	public static List<Question> findAllQuestionsByPage(int pStart, int pEnd, int sim_id){
		PreparedStatement psm = null;
 	   	ResultSet rs=null;
		String sql="select top " + pEnd + " * from(select qb_formal_qbank.*, row_number() over(order by id) n from qb_formal_qbank where id != ?) t1 where t1.n >= ?";
		try {
			psm = dbConn.prepareStatement(sql);
			psm.setInt(1, sim_id);
			psm.setLong(2, pStart);
			rs=psm.executeQuery();
			List<Question> l = new ArrayList<Question>();
			while(rs.next()){
				Question q = new Question();
				q.setId(rs.getInt("id"));
		        q.setContent(rs.getString("content"));
		        q.setAnswer(rs.getString("answer"));
		        q.setAnalysis(rs.getString("analysis"));
		        q.setFull(rs.getBoolean("isFull"));
		        q.setHardness(rs.getInt("hardness"));
		        q.setSubject_id(rs.getInt("subject_id"));
		        q.setType(rs.getInt("type"));
		        q.setSubmit_time(rs.getLong("submit_time"));
		        l.add(q);
		        //System.out.println(q.toString());
		    }
			rs.close();
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void insertIntoSimQuestion(int q_id, int s_id){
		PreparedStatement psm = null;
		String sql="insert into qb_similar_question(question_id, sim_id) values(?, ?)";
		try {
			psm = dbConn.prepareStatement(sql);
			psm.setInt(1, q_id);
			psm.setInt(2, s_id);
			psm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
