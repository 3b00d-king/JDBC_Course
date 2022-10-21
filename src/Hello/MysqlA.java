package Hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MysqlA {

    private static final String ROOT = "root";
    private static final String PASS = "";
    private static final String HOST = "jdbc:mysql://localhost/works";
    private static Connection connection;

    // connect
    public static Connection getConnect(){
        try {
            connection = DriverManager.getConnection(HOST, ROOT, PASS);
        }catch (SQLException ex){

        }

        return connection;
    }

    //Insert
    public void insertData(String name, String email, String password){

        Connection con = MysqlA.getConnect();
        if(!(con == null)){
            String query = "INSERT INTO Users(name,email,password)VALUES(?,?,?)";
            try{
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, password);

                statement.executeUpdate();
            } catch (SQLException ex){
                System.out.println("error");
            }
        }
    }

    //Update
    public void updateData(String name, String email, String password){

        Connection con = MysqlA.getConnect();
        String query = "UPDATE Users SET name=?,email=?,password=? WHERE email=?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, email);
        } catch (SQLException e){
            System.out.println("error");
        }
    }

    //Read
    public void readAll(){

        Connection con = MysqlA.getConnect();
        String query = "SELECT * FROM Users";
        ArrayList<String> list = new ArrayList<>();
        try{
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                list.add(name);
                list.add(email);
                list.add(password);

                System.out.println(name);
                System.out.println(email);
                System.out.println(password);
            }
        } catch (SQLException e ){
            System.out.println("error");
        }
    }

    //Delete
    public void deleteData(String email){
        Connection con = MysqlA.getConnect();
        String query = "DELETE FROM Users WHERE email=?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, email);

            statement.executeUpdate();
        } catch(SQLException e){
            System.out.println("error");
        }
    }
}
