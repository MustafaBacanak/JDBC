
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan
        JdbcUtils.connectToDataBase("localhost","techproed","postgres","4505096sql");

        //3.Adım: Statement olustur.
        Statement statement = JdbcUtils.createStatement();

        //4.Adım: Query calistir
        //JdbcUtils.execute("CREATE TABLE student (name VARCHAR(20), id INT, address VARCHAR(20))");

        JdbcUtils.createTable("BU_JDBC_CANIMIZI_YEDİ", "classes VARCHAR (20)","teacher_name VARCHAR(20)","id INT");

        //5.Adım: Bağlantı ve Statement'i kapat
        JdbcUtils.closeAndStatement();
    }
}