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
public class Tender {
    
    public int Id;
    public String Description;
    public Double Baseprice;
    public String location;
    public int UserId;
    public int CompanyId;

    public Tender() {
    }

    public Tender(String Description, Double Baseprice, String location, int UserId) {
        this.Description = Description;
        this.Baseprice = Baseprice;
        this.location = location;
        this.UserId = UserId;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Double getBaseprice() {
        return Baseprice;
    }

    public void setBaseprice(Double Baseprice) {
        this.Baseprice = Baseprice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    @Override
    public String toString() {
        return "Tender{" + "Id=" + Id + ", Description=" + Description + ", Baseprice=" + Baseprice + ", location=" + location + ", UserId=" + UserId + ", CompanyId=" + CompanyId + '}';
    }
    
    
    
}
