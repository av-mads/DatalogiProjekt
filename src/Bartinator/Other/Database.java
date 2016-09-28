package Bartinator.Other;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by martin on 9/21/16.
 *
 */
public class Database {
    private String endpoint = "datalogiprojektruc2016-bartinator.chcbu6lph5q9.eu-central-1.rds.amazonaws.com";
    private MysqlDataSource dataSource;
    /// setupBartinator.users
    // http://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
    public void setup(String user, String password){
        dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerName(endpoint);
    }

    public boolean verifyLogin(int userid, String password){
        boolean success = false;
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Bartinator.users WHERE id=%d", userid));

            while (rs.next()){
                if(rs.getInt("password")==password.hashCode()) {
                    System.out.println(rs.getString("name")+" has logged in");
                    success = true;
                    break;
                } else {
                    System.out.println("incorrect password");
                }
            }
            System.out.println("user not found");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void test(){
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Bartinator.users");

            while (rs.next()){
                System.out.println(rs.getString("name"));
                if(rs.getInt("id")==55069 && rs.getInt("password")=="hello world".hashCode()) {
                    System.out.println(rs.getString("name")+" has logged in");
                }
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}