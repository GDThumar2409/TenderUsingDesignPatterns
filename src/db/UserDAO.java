/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.Company;
import models.Dbcontext;
import models.User;

/**
 *
 * @author Admin
 */
public class UserDAO implements DAO<User>{
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    
    private Connection getConnection() throws SQLException {
		Connection conn;
                String connection="jdbc:mysql://localhost:3306/oose";
                Dbcontext db = new Dbcontext(connection);
		conn = db.con;
		return conn;
	}
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
    User user = new User();
    user.setId( rs.getInt("id") );
    user.setName( rs.getString("name") );
    user.setPassword( rs.getString("password") );
    user.setEmail( rs.getString("email") );
    return user;
}

    @Override
    public Optional<User> get(int id) {
        User u = null;
        try{
            String queryString = "select * from company WHERE Id=?";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()){
                u= extractUserFromResultSet(resultSet);
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                }

                catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();

                }
        }
        return Optional.ofNullable(u);
    }

    @Override
    public List<User> getAll() {
        List<User> l = new ArrayList<User>();
        try{
            String queryString = "select * from company";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);;
            resultSet = ptmt.executeQuery();
            while(resultSet.next()){
                l.add(extractUserFromResultSet(resultSet));
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }
     return l;   
    }

    @Override
    public void save(User u) {
        try {
                String queryString = "insert into user (name,Email,password) values(?,?,?)";
                //String connection="jdbc:mysql://localhost:3306/oose";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
                ptmt.setString(1, u.getName());
                ptmt.setString(2, u.getEmail());
                ptmt.setString(3, u.getPassword());
                ptmt.executeUpdate();
                ResultSet rs= ptmt.getGeneratedKeys();
                   if(rs.next()){
                       u.Id = rs.getInt(1);
                    }
                System.out.println("Data Added Successfully");
                System.out.println(u);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }
    }

    @Override
    public void update(User u) {
        try {
                String queryString = "UPDATE user SET name=?,Email=?,password=? WHERE Id=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, u.getName());
                ptmt.setString(2, u.getEmail());
                ptmt.setString(3, u.getPassword());
                ptmt.setInt(4, u.getId());
                ptmt.executeUpdate();
                System.out.println(u);
                System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                }

                catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();

                }
        }
    }

    @Override
    public void delete(int id) {
        try {
                String queryString = "DELETE FROM user WHERE Id=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setInt(1, id);
                ptmt.executeUpdate();
                System.out.println("Data deleted Successfully");
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (ptmt != null)
                                ptmt.close();
                        if (connection != null)
                                connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

    }
}
