package dao;

import entities.UserEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jason
 */
public class DataAccess {
    
    private final String url = "jdbc:derby://localhost:1527/user";
    private final String table = "\"USERS\"";
    private Statement stmt = null;
    
    public DataAccess () {
        
    }
    
    private Connection getConnection() throws Exception {    
        Connection conn = null;        
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Properties props = new Properties();
        props.put("user", "nbuser");
        props.put("password", "nbuser");
        conn = DriverManager.getConnection(url, props);
        System.out.println("Connected to database");
        
        return conn;
    } 
    
    public List<UserEntity> getUsers () {
        List<UserEntity> users = new ArrayList<>();
        try {
            try (Connection conn = getConnection()) {
                String sql = "SELECT * FROM " + this.table;
                stmt = conn.createStatement();
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    users = convertToList(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    
    public UserEntity getUser(int id) {        
        UserEntity user = new UserEntity();
        try {
            try (Connection conn = getConnection()) {
                String sql = "SELECT * FROM " + this.table + " WHERE ID = " + id;
                stmt = conn.createStatement();
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    user = convertToUser(rs);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public int addUser(UserEntity user) {
        int rowsAffected = -1;
        try {
            try (Connection conn = getConnection()) {
                String sql = "INSERT INTO " + this.table +    
                        " (FIRSTNAME, LASTNAME, AGE)" +
                        " VALUES('" + user.getFirstname() + "','" + user.getLastname() + "'," + user.getAge() + ")";
                stmt = conn.createStatement();
                rowsAffected = stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }
    
    public int editUser(UserEntity user) {
        int rowsAffected = -1;
        try {
            try (Connection conn = getConnection()) {
                String sql = "UPDATE " + this.table + 
                        " SET FIRSTNAME = '" + user.getFirstname() + "'," +
                        " LASTNAME = '" + user.getLastname() + "'," +
                        " AGE = " + user.getAge() + 
                        " WHERE ID = " + user.getId();
                stmt = conn.createStatement();
                rowsAffected = stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        
        return rowsAffected;
    }
    
    public int deleteUser(int id) {
        int rowsAffected = -1;
        try {
            try (Connection conn = getConnection()) {
                String sql = "DELETE FROM " + this.table +
                        " WHERE ID = " + id;
                stmt = conn.createStatement();
                rowsAffected = stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return rowsAffected;
    }
    
    private List<UserEntity> convertToList (ResultSet rs) throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        while (rs.next()) {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("ID"));
            user.setFirstname(rs.getString("FIRSTNAME"));
            user.setLastname(rs.getString("LASTNAME"));
            user.setAge(rs.getInt("AGE"));
            users.add(user);
        }
        return users;
    }
    
    private UserEntity convertToUser (ResultSet rs) throws SQLException {
        UserEntity user = new UserEntity();
        while (rs.next()) {
            user.setId(rs.getInt("ID"));
            user.setFirstname(rs.getString("FIRSTNAME"));
            user.setLastname(rs.getString("LASTNAME"));
            user.setAge(rs.getInt("AGE"));            
        }
        return user;
    }
}
