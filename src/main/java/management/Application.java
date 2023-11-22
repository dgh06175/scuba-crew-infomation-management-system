package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
    private static final String DB_NAME = "scubaDB";
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/scubaDB";
        String userName = "root";
        String password = "0000";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from scubaDB.club_member_information;");

            resultSet.next();
            String name = resultSet.getString("name");
            System.out.println(name);

            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("연결 성공");
        } catch (SQLException e) {
            System.out.println("SQLException 오류 발생");
            throw new RuntimeException(e);
        }
    }
}
