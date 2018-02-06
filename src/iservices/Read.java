/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Elyes
 */
public interface Read <T>{
    T get(T obj) throws SQLException;
    List<T> getAll(T obj) throws SQLException;
}
