import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {

    }

    public static Connection connectToDataBase(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed",
                    "postgres", "4505096sql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Statement createStatement (){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
}
