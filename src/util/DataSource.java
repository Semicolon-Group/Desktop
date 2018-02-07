package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String URL = "jdbc:mysql://"+DbConfigParser.getHost()+":"+DbConfigParser.getPort()+"/"+DbConfigParser.getDbName();
    private static final String USERNAME = DbConfigParser.getUsername();
    private static final String PASSWORD = DbConfigParser.getPassword();
    
    private Connection connection;
    private static DataSource dataSource;
    
    private DataSource(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            util.Logger.writeLog(ex, DataSource.class.getName());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static DataSource getInstance(){
        if(dataSource == null){
            return dataSource = new DataSource();
        }
        return dataSource;
    }
}
