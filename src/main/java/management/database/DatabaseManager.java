package management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_NAME = "scubaDB";
    private static final int port = 3306;
    private static final String url = "jdbc:mysql://127.0.0.1:" + port + "/" + DB_NAME;
    private static final String userName = "root";
    private static final String password = "0000";

    /**
     * 데이터베이스 연결
     * @return Connection
     */
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("[ERROR] 데이터베이스 연결 실패: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 쿼리 실행을 위한 메서드
     * 이 메서드의 반환값을 사용한 뒤에는 반드시 close 해야한다.
     * @param query 질의 문자열
     * @return ResultSet
     */
    public ResultSet executeQuery(String query) throws RuntimeException{
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("[ERROR] 쿼리 실행 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] 자원 해제 오류: " + e.getMessage());
        }
    }
}
