package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getInstance() {

        String url = "jdbc:mysql://localhost:3306/baseball_management";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("디버그 : DB 연결 성공");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}