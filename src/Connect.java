import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Denis on 23.10.2016.
 */
public class Connect {
    private static String url = "";
    private static String user = "";
    private static String pass = "";
    private static Connection con;

    public static void main(String[] args) {
        konfigEinlesen();
        try {
            verbindenDB();
            System.out.println(con.getMetaData().getDatabaseProductName() + " " + con.getMetaData().getDatabaseProductVersion());
            System.out.println("Enter drücken");
            String s = new Scanner(System.in
            ).nextLine();
            disconnect();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    private static void verbindenDB() throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
    }

    private static void konfigEinlesen() {
        Properties prop = new Properties();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("src/konfig.properties"));
            prop.load(bfr);
            bfr.close();
            url = prop.getProperty("db_url");
            user = prop.getProperty("db_user");
            pass = prop.getProperty("db_pass");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() throws SQLException {
        con.close();
    }
}
