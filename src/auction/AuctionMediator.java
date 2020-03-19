/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import db.AuctionDAO;
import db.CompanyDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.Auction;
import models.Company;
import models.Tender;

/**
 *
 * @author Admin
 */
public class AuctionMediator implements Mediator{
    
    public AuctionMediator() 
    { 
    } 
  
    @Override
    public void addBuyer(int tenderid,int companyid,Double bid) 
    { 
        Auction a=new Auction();
        a.setCompanyId(companyid);
        a.setTenderId(tenderid);
        a.setBid(bid);
        
        AuctionDAO adao = new AuctionDAO();
        adao.save(a);
        
    } 
  
    @Override
    public void findHighestBidder() 
    {
        int winner=0;
        double maxBid = 0; 
        List<Auction> al=new ArrayList<Auction>();
        for (Auction a : al) { 
            if (a.getBid() > maxBid) { 
                maxBid = a.getBid(); 
                winner = a.getCompanyId(); 
            } 
        }
        CompanyDAO cdao=new CompanyDAO();
        Company c = cdao.get(winner).get();
        System.out.println("The auction winner is " + c +  
        ". He paid " + maxBid  + "$ for the item."); 
    } 
}
