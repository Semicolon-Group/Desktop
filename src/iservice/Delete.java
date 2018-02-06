/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import java.sql.SQLException;

/**
 *
 * @author Elyes
 */
public interface Delete <T>{
    boolean delete(T object) throws SQLException;
}
