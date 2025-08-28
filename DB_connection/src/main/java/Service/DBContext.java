package Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private final String serverName = "DESKTOP-331CJKC";
    private final String dbName = "LTWEB_ST5_ST6";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String userID= "sa";
    private final String password= "123";
    public Connection getConnection() throws Exception {
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";encrypt=true;trustServerCertificate=true";
        } else {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
                    + ";databaseName=" + dbName
                    + ";encrypt=true;trustServerCertificate=true";
        }

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public static void main(String[] args) {
        try {
            System.out.println(new DBContext().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
