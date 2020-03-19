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
import models.Dbcontext;
import models.Tender;
import models.User;

/**
 *
 * @author Admin
 */
public class TenderDAO implements DAO<Tender> {
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
    private Tender extractTenderFromResultSet(ResultSet rs) throws SQLException {
    Tender t = new Tender();
    t.setId( rs.getInt("id") );
    t.setDescription(rs.getString("description") );
    t.setLocation(rs.getString("location"));
    t.setBaseprice(rs.getDouble("baseprice"));
    t.setUserId(rs.getInt("UserId"));
    return t;
}

    @Override
    public Optional<Tender> get(int id) {
        Tender t = null;
        try{
            String queryString = "select * from tender WHERE Id=?";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()){
                t= extractTenderFromResultSet(resultSet);
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
        return Optional.ofNullable(t);
    }

    @Override
    public List<Tender> getAll() {
        List<Tender> l = new ArrayList<Tender>();
        try{
            String queryString = "select * from tender";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);;
            resultSet = ptmt.executeQuery();
            while(resultSet.next()){
                l.add(extractTenderFromResultSet(resultSet));
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
    public void save(Tender t) {
        try {
                String queryString = "insert into tender (description,location,baseprice,UserId) values(?,?,?,?)";
                //String connection="jdbc:mysql://localhost:3306/oose";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
                ptmt.setString(1, t.getDescription());
                ptmt.setString(2, t.getLocation());
                ptmt.setDouble(3, t.getBaseprice());
                ptmt.setInt(4, t.getUserId());
                ptmt.executeUpdate();
                ResultSet rs= ptmt.getGeneratedKeys();
                   if(rs.next()){
                       t.Id = rs.getInt(1);
                    }
                System.out.println("Data Added Successfully");
                System.out.println(t);
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
    public void update(Tender t) {
        try {
                String queryString = "UPDATE tender SET description=?,location=?,baseprice=?,UserId=? WHERE Id=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, t.getDescription());
                ptmt.setString(2, t.getLocation());
                ptmt.setDouble(3, t.getBaseprice());
                ptmt.setInt(4, t.getUserId());
                ptmt.setInt(5, t.getId());
                ptmt.executeUpdate();
                System.out.println(t);
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
                String queryString = "DELETE FROM tender WHERE Id=?";
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
