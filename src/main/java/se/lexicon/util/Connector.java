package se.lexicon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static String _url;
    private static String _username;
    private static String _password;

    public static void set_url(String url)
    {
        _url = url;
    }
    public static void set_username(String username)
    {
        _username = username;
    }
    public static void set_password(String password)
    {
        _password = password;
    }

    public static Connection getConnection(){
        Connection connection = null;

        try {
        connection = DriverManager.getConnection(_url, _username, _password);
        }catch(SQLException e){
            System.out.println("Cant connect to " + _url);
        }
        return connection;
    }
}
