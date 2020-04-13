package com.testJDBCdatabase;

//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.sql.*;

public class Main {
   // private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
  //  private final static String URLFIXED = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
     //       "&useLegacyDatetimeCode=false&serverTimezone=UTC";
 //   private final static String USERNAME = "root";
 //   private final static String PASSWORD = "root";


    public static void main(String [] args)   throws SQLException {
       // Connection connection;
        //без \того блока тоже работает. Здесь мы решистрируем спецаильный драйвер mysql
       // Driver driver = new com.mysql.cj.jdbc.Driver();
      //  DriverManager.registerDriver(driver);
        //----------
       /* try {
            connection = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            if(!connection.isClosed()){
                System.out.println("OK");
            } else System.out.println("Connection is closed");

           // statement.execute("INSERT INTO users(name,age,email) VALUES ('MadNax',30,'mad@esto.com')");
            //int res = statement.executeUpdate("UPDATE  users SET name = 'Sputnic' WHERE  id = 2");  //воз-ет кол-во записей в который он внес изменения
            ResultSet result = statement.executeQuery("SELECT *FROM users ");
            while(result.next()){
                System.out.println(result.getString("name"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        */
       DBwork worker = new DBwork();
       String query = "SELECT *FROM users";
       Statement statement = worker.getConnection().createStatement();
       ResultSet res = statement.executeQuery(query);

       while (res.next()){
           User user = new User();

           user.setName(res.getString("name"));
           user.setAge(res.getInt("age"));
           user.setEmail(res.getString("email"));
           user.setId(res.getInt("id"));

          System.out.println(user);

       }


    }
}
