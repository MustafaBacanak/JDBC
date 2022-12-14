import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

    private static Connection connection;
    private static Statement statement;


    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection != null) {
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Fail");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement() {


        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    //4.Adım : Query calistir

    public static Boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    // ExecuteQuery Methodu
    public static ResultSet executeQuery(String columnNames, String tableName) {
        ResultSet resultSet1 ;

        try {
            resultSet1 = statement.executeQuery("select " + columnNames + " from " + tableName);
            while (resultSet1.next()) {
                System.out.println(resultSet1.getObject(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet1;
    }

    //ExecuteUpdate Methodu
    public static int executeUpdate(String tableName, String setColumnName, String setColumnValue, String whereColumnName, String whereColumnValue) {
        int update;
        try {
            update = statement.executeUpdate("update " + tableName + " set " + setColumnName + " = " + setColumnValue + " where " + whereColumnName + "=" + whereColumnValue);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    //5.Adım : Bağlantı ve Statement'i kapat

    public static void closeAndStatement() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and Statement closed!");
            } else {
                System.out.println("Connection and Statement not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType) {
        StringBuilder columnName_dataValue = new StringBuilder("");

        for (String w : columnName_dataType) {

            columnName_dataValue.append(w).append(",");

        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length() - 1);

        try {
            statement.execute("CREATE TABLE " + tableName + "(" + columnName_dataValue + ")");
            System.out.println("Table " + tableName + " succesfully created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table'a veri ekleme methodu
    public static void insertTable(String value1, String value2, String value3) {
        try {
            statement.executeUpdate("INSERT INTO companies VALUES(" + value1 + "," + "'" + value2 + "'" + "," + value3 + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Sütun Değerlerini List'e Ekleme Methodu
    public static void assertToList(String columnName, String tableName) {
        String sql1 = "select " + columnName + " from " + tableName;
        ResultSet resultSet1;
        List<Object> selectToList = new ArrayList<>();
        try {
            resultSet1 = statement.executeQuery(sql1);
            while (resultSet1.next()) {
                selectToList.add(resultSet1.getObject(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Select To List: " + selectToList);
    }
}
