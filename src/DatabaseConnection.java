import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/bibliotheque";
    private static final String  USER = "root";
    private static final String PASSWORD = "";
    public Connection connect(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return  connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return  null;
    }
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
