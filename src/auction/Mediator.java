/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import models.Tender;

/**
 *
 * @author Admin
 */
public interface Mediator { 
  
    // The mediator interface 
    public void addBuyer(int tenderid,int companyid,Double bid); 
    public void findHighestBidder(); 
}
