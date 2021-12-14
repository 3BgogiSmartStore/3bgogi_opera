package com.gogi.proj.db.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DBController {

	@Resource(name = "databaseProperties")
	private Properties databaseProperties;

	@RequestMapping(value = "/db_backup_test.do", method = RequestMethod.GET)
	public String tagBackUp() {

		String userName = databaseProperties.getProperty("Globals.UserName");
		String password = databaseProperties.getProperty("Globals.Password");
		String dbName = databaseProperties.getProperty("Globals.dbName");

		try {
			Runtime runtime = Runtime.getRuntime();
			File backupFile = new File("C:\\mysql_dump\\backup_test.sql");
			FileWriter fw = new FileWriter(backupFile);
			Process child = runtime.exec("mysqldump --user=root --password=!ww123123 --all-databases");
			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);

			String line;
			while ((line = br.readLine()) != null) {
				fw.write(line + "\n");
			}
			fw.close();
			irs.close();
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/db_backup_test_all.do", method = RequestMethod.GET)
	public String testBackup() {

		try {

			String userName = databaseProperties.getProperty("Globals.UserName");
			String password = databaseProperties.getProperty("Globals.Password");
			String Url = databaseProperties.getProperty("Globals.Url");

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(Url, userName, password);

			String LINE_SEPARATOR = System.getProperty("line.separator");

			System.out.println("LINE_SEPARATOR = " + LINE_SEPARATOR);

			String get_table_name_query = "show tables";

			PreparedStatement tableNameStmt = conn.prepareStatement(get_table_name_query);

			ResultSet tableNameResultSet = tableNameStmt.executeQuery();

			File entire_query = new File("C:\\mysql_dump\\backup_test.sql");

			FileOutputStream entire_fos = new FileOutputStream(entire_query);

			FileWriter entire_fw = new FileWriter(entire_query, true);

			while (tableNameResultSet.next()) {
				String tableName = tableNameResultSet.getString(1);

				StringBuffer header = new StringBuffer();
				StringBuffer body = new StringBuffer();

				header.append("INSERT INTO " + tableName + "(");
				body.append("VALUES");
				
				String sql = "select * from " + tableName; //+ " limit 0, 10";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCnt = rsmd.getColumnCount(); // 컬럼의 수

				System.out.println("columnCnt = " + columnCnt);

				boolean firstHeader = true;
				boolean headerCount = false;
				while (rs.next() && tableName.equals("orders")) {

					boolean firstBody = true;
					boolean bodyCount = false;
					body.append("(");

					for (int i = 1; i <= columnCnt; i++) {

						if (headerCount == false) {
							if (firstHeader == true) {
								header.append(rsmd.getColumnName(i));
								firstHeader = false;
							} else if (firstHeader == false && i != columnCnt) {
								header.append("," + rsmd.getColumnName(i));
							} else {
								header.append("," + rsmd.getColumnName(i) + ")\n");
							}
						} else {

						}

						boolean type = isNumericType(rsmd.getColumnType(i),rsmd.getColumnName(i), rs );
						String value;
						
						if(type) {
							value = rs.getString(rsmd.getColumnName(i));
						}else {
							if( rs.getString(rsmd.getColumnName(i)) == null) {
								value = rs.getString(rsmd.getColumnName(i));
							}else {
								
								value = "'"+rs.getString(rsmd.getColumnName(i))+"'";
							}
						}
						
						
						if (firstBody == true) {
							
							body.append(value);
							firstBody = false;
						} else if (firstBody == false && i != columnCnt) {
							body.append("," + value);
						} else {
							body.append("," + value + "),\n");
						}

					}

					headerCount = true;
				}

				if (tableName.indexOf("tmp_") < 0) { // 제외단어 추가

					String get_scheme_query = "show create table " + tableName;

					PreparedStatement schemeStmt = conn.prepareStatement(get_scheme_query);

					ResultSet schemeResultSet = schemeStmt.executeQuery();

					while (schemeResultSet.next()) {

						String result_table_name = schemeResultSet.getString(1);

						String result_create_scheme = schemeResultSet.getString(2);

						System.out.println("\n" + result_table_name + " : " + result_create_scheme + "\n");

						File f = new File("c:\\mysql_dump\\" + result_table_name + "_schema.sql");

						FileOutputStream fos = new FileOutputStream(f);

						FileWriter fw = new FileWriter(f);
						
						fw.write(header.toString() + body.toString().substring(0, body.toString().length() - 2) +";");

						fw.flush();

						entire_fw.write(header.toString() + "\n" + body.toString() + ";" + LINE_SEPARATOR);

						// localConn.prepareStatement(result_create_scheme).executeUpdate();

					}

				}

			}

			/*
			 * while (tableNameResultSet.next()) {
			 * 
			 * String tableName = tableNameResultSet.getString(1);
			 * 
			 * System.out.println(tableName);
			 * 
			 * if (tableName.indexOf("tmp_") < 0) { // 제외단어 추가
			 * 
			 * String get_scheme_query = "show create table " + tableName;
			 * 
			 * PreparedStatement schemeStmt = conn.prepareStatement(get_scheme_query);
			 * 
			 * ResultSet schemeResultSet = schemeStmt.executeQuery();
			 * 
			 * while (schemeResultSet.next()) {
			 * 
			 * String result_table_name = schemeResultSet.getString(1);
			 * 
			 * String result_create_scheme = schemeResultSet.getString(2);
			 * 
			 * System.out.println("\n" + result_table_name + " : " + result_create_scheme +
			 * "\n");
			 * 
			 * File f = new File("c:\\mysql_dump\\" + result_table_name + "_schema.sql");
			 * 
			 * FileOutputStream fos = new FileOutputStream(f);
			 * 
			 * FileWriter fw = new FileWriter(f);
			 * 
			 * fw.write(result_create_scheme);
			 * 
			 * fw.flush();
			 * 
			 * entire_fw.write(result_create_scheme + ";" + LINE_SEPARATOR);
			 * 
			 * // localConn.prepareStatement(result_create_scheme).executeUpdate();
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return "";
	}

	public boolean isNumericType(int columnType, String columnName, ResultSet rs) throws Exception {
		if (columnType == Types.NUMERIC || columnType == Types.INTEGER || columnType == Types.BIGINT || columnType == Types.DOUBLE || columnType == Types.FLOAT) {
			return true;
		}else {
			
			return false;
		}
	}

}
