import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01{

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con;
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","ŞİFRENİZ");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();


        //4. Adım: Query çalıştır

    /*
        execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
        1) Eğer execute() methodu DDL için kullanılırsa 'false' return yapar.
        2) Eğer execute() methodu DQL için kullanılırsa ResultSet alındığında 'true' aksi hale 'false' verir.
    */
        // 1. Ornek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

        boolean sql1 = st.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");

        System.out.println("sql1 = "+sql1);//false return eder cunku data cagirmayiz


        // 2. Ornek: Table'a worker_address sütunu ekleyerek alter yapın.

        String sql2="ALTER TABLE workers add worker_adress VARCHAR(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);


        // 3. Ornek: Drop workers table (workers adlı tabloyu sil)

        String sql3 = "DROP TABLE workers";
        boolean sql3b=st.execute(sql3);
        System.out.println("sql3b = " + sql3b);


        // 5. Adım: Bağlantı ve Statement'ı kapat        String sql3 = "DROP TABLE workers";
        //        st.execute(sql3);.

        con.close();
        st.close();
    }
}
