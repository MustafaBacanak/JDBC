public class Runner {

    public static void main(String[] args) {

        JdbcUtils.connectToDataBase();
        JdbcUtils.createStatement();
    }
}
