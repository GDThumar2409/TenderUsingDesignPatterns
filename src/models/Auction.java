/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Admin
 */
public class Auction {
    public int Id;
    public int TenderId;
    public int CompanyId;
    public Double Bid;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getTenderId() {
        return TenderId;
    }

    public void setTenderId(int TenderId) {
        this.TenderId = TenderId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public Double getBid() {
        return Bid;
    }

    public void setBid(Double Bid) {
        this.Bid = Bid;
    }
    
    
    @Override
    public String toString() {
        return "Auction{" + "Id=" + Id + ", TenderId=" + TenderId + ", CompanyId=" + CompanyId + ", Bid=" + Bid + '}';
    }

    
    
    
}
