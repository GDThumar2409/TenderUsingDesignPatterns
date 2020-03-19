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
import models.Auction;
import models.Company;
import models.Dbcontext;

/**
 *
 * @author Admin
 */
public class AuctionDAO implements DAO<Auction> {
    
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
    private Auction extractAuctionFromResultSet(ResultSet rs) throws SQLException {
    Auction a = new Auction();
    a.setId( rs.getInt("id") );
    a.setTenderId( rs.getInt("tenderid") );
    a.setCompanyId( rs.getInt("companyid") );
    a.setBid( rs.getDouble("bid") );
    return a;
}

    @Override
    public Optional<Auction> get(int id) {
        Auction a = null;
        try{
            String queryString = "select * from auction WHERE Id=?";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()){
                a= extractAuctionFromResultSet(resultSet);
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
        return Optional.ofNullable(a);
    }

    @Override
    public List<Auction> getAll() {
        throw new UnsupportedOperationException("Method not supported.");  
    }
    
    public List<Auction> getAll(int id) {
        List<Auction> al = new ArrayList<Auction>();
        
        try{
            String queryString = "select * from auction WHERE tender=?";
            //String connection="jdbc:mysql://localhost:3306/oose";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()){
                al.add(extractAuctionFromResultSet(resultSet));
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
        return al;
    }

    @Override
    public void save(Auction a) {
        try {
                String queryString = "insert into auction (tenderid,companyid,bid) values(?,?,?)";
                //String connection="jdbc:mysql://localhost:3306/oose";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
                ptmt.setInt(1, a.getTenderId());
                ptmt.setInt(2, a.getCompanyId());
                ptmt.setDouble(3, a.getBid());
                ptmt.executeUpdate();
                ResultSet rs= ptmt.getGeneratedKeys();
                   if(rs.next()){
                       a.Id = rs.getInt(1);
                    }
                System.out.println("Data Added Successfully");
                System.out.println(a);
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
    public void update(Auction a) {
        try {
                String queryString = "UPDATE auction SET bid=? WHERE Id=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setDouble(1, a.getBid());
                ptmt.setInt(2, a.getId());
                ptmt.executeUpdate();
                System.out.println(a);
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
                String queryString = "DELETE FROM auction WHERE tenderid=?";
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
