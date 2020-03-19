/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface DAO<T> {
     
    Optional<T> get(int id);
     
    List<T> getAll();
     
    void save(T t);
     
    void update(T t);
     
    void delete(int id);
}

