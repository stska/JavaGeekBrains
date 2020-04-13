package com.testJDBCdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBwork {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String URLFIXED = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private Connection connection;

    public DBwork(){
        try{
            connection = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
            if(!connection.isClosed()){
                System.out.println("Successful connection");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }


}
