/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oose;

import db.CompanyDAO;
import db.DAO;
import db.TenderDAO;
import db.UserDAO;
import java.util.Iterator;
import java.util.List;
import models.Company;
import models.Tender;
import models.User;

/**
 *
 * @author Admin
 */
public class OOSE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        DAO dao = new CompanyDAO();
//        Company c = new Company("gunjan1","gunjan1","gunjan1");
//        dao.save(c);
//        c.Email = "gdthumar@gmail.com";
//        dao.update(c);
//        dao.delete(c.id);
//        System.out.println(dao.get(5).get());
//        List<Company> l = dao.getAll();
//        Iterator<Company> itr = l.iterator();
//        while(itr.hasNext()){
//            System.out.println(itr.next());
//        }

//        DAO dao = new UserDAO();
//        User c = new User("gunjan2","gunjan2","gunjan2");
//        dao.save(c);
//        c = new User("gunjan1","gunjan1","gunjan1");
//        dao.save(c);
//        c.Email = "gdthumar@gmail.com";
//        dao.update(c);
//        dao.delete(c.Id);
//        System.out.println(dao.get(5).get());
//        List<Company> l = dao.getAll();
//        Iterator<Company> itr = l.iterator();
//        while(itr.hasNext()){
//          System.out.println(itr.next());

        DAO dao = new TenderDAO();
        Tender t = new Tender("Demo",10000.00,"Dhoraji",1);
        dao.save(t);
        t.Baseprice = 11000.00;
        dao.update(t);
        dao.delete(t.Id);
        t = new Tender("Demo",10000.00,"Dhoraji",1);
        dao.save(t);
        System.out.println(dao.get(t.Id).get());
        List<Company> l = dao.getAll();
        Iterator<Company> itr = l.iterator();
        while(itr.hasNext()){
          System.out.println(itr.next());
        }
        
    }
    
}
