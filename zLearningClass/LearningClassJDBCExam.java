package zLearningClass;

import java.sql.*;
public class LearningClassJDBCExam {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*String username = "root";
		String pwd = "root";
		String h_ip = "jdbc:mysql://localhost";
		
		Connection conn = DriverManager.getConnection(h_ip, username, pwd);
		System.out.println(conn.getSchema());*/
		int a = 10000;
		char c = (char)a;
		a = c;
		System.out.println("a is " + a + "\nc is " + c);
	}

}
