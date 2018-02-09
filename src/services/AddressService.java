package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.List;
import models.Address;

public class AddressService extends Service implements Read<Address>, Create<Address>, Update<Address>{

    private static AddressService addressService;
    
    private AddressService(){
        super();
    }
    
    public static AddressService getInstance(){
        if(addressService == null){
            return addressService = new AddressService();
        }
        return addressService;
    }
    
    @Override
    public Address get(Address obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAll(Address obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address create(Address obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address update(Address obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
