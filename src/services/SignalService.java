/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Signal;

/**
 *
 * @author Elyes
 */
public class SignalService extends Service implements Create<Signal>,Update<Signal>,Read<Signal>{

    private static SignalService signalService;
    
    private SignalService(){
        super();
    }
    
    public static SignalService getInstance(){
        if(signalService == null){
            return signalService = new SignalService();
        }
        return signalService;
    }

    @Override
    public Signal create(Signal obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Signal obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Signal get(Signal obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Signal> getAll(Signal obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
