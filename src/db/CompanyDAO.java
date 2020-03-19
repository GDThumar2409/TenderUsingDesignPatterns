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

/**
 *
 * @author Admin
 */
public class CompanyDAO implements DAO<Company> {
    
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
    private Company extractCompanyFromResultSet(ResultSet rs) throws SQLException {
    Company user = new Company();
    user.setId( rs.getInt("id") );
    user.setName( rs.getString("name") );
    user.setPassword( rs.getString("password") );
    user.setEmail( rs.getString("email") );
    return user;
}

    @Override
    public Optional<Company> get(int id) {
        Company c = null;
        try{
            String queryString = "select * from company WHERE Id=?";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()){
                c= extractCompanyFromResultSet(resultSet);
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
        return Optional.ofNullable(c);
    }

    @Override
    public List<Company> getAll() {
        List<Company> l = new ArrayList<Company>();
        try{
            String queryString = "select * from company";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);;
            resultSet = ptmt.executeQuery();
            while(resultSet.next()){
                l.add(extractCompanyFromResultSet(resultSet));
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
    public void save(Company c) {
        try {
                String queryString = "insert into Company (name,Email,password) values(?,?,?)";
                //String connection="jdbc:mysql://localhost:3306/oose";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
                ptmt.setString(1, c.getName());
                ptmt.setString(2, c.getEmail());
                ptmt.setString(3, c.getPassword());
                ptmt.executeUpdate();
                ResultSet rs= ptmt.getGeneratedKeys();
                   if(rs.next()){
                       c.id = rs.getInt(1);
                    }
                System.out.println("Data Added Successfully");
                System.out.println(c);
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
    public void update(Company c) {
        try {
                String queryString = "UPDATE company SET name=?,Email=?,password=? WHERE Id=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, c.getName());
                ptmt.setString(2, c.getEmail());
                ptmt.setString(3, c.getPassword());
                ptmt.setInt(4, c.getId());
                ptmt.executeUpdate();
                System.out.println(c);
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
                String queryString = "DELETE FROM company WHERE Id=?";
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
