import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "ŞİFRENİZ");
        Statement st = con.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.

        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean sql1b = st.execute(sql1);
        System.out.println("sql1 = " + sql1b);

        // Recordlari gormek icin ExecuteQuery() metodunu kullanmaliyiz

        System.out.println("- - - - - - -");

        ResultSet result = st.executeQuery(sql1);
        while (result.next()) {
            // get****() methodu parantezi icerisine stun numarasi ya da stun ismi koyulacak.
            System.out.println(result.getString(1));
        }

        String sql2 = "SELECT country_name,country_id from countries WHERE region_id>2";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        System.out.println("- - - - - - -");

        ResultSet result1 = st.executeQuery(sql2);
        while (result1.next()) {

            System.out.println(result1.getString("country_name") + " -- " + result1.getString("country_id"));
        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3 = "SELECT * FROM companies ORDER BY number_of_employees LIMIT 1";

        System.out.println(" - - - - - - ");

        ResultSet result2 = st.executeQuery(sql3);
        while (result2.next()) {
            System.out.print(result2.getInt(1) + " - " + result2.getString(2) + " - " + result2.getInt(3));

        }
        con.close();
        st.close();
        result.close();
        result1.close();
        result2.close();
    }
}
