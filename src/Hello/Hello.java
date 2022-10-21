package Hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hello {

	public static void main(String[] args) {

		MysqlA mysql = new MysqlA();
		mysql.deleteData("ali@gmail.com");
	}
}