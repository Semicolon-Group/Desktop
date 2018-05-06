package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String query = "select * from address where id = "+obj.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        rs.next();
        obj.setCity(rs.getString("city"));
        obj.setCountry(rs.getString("country"));
        obj.setLatitude(rs.getDouble("latitude"));
        obj.setLongitude(rs.getDouble("longitude"));
        return obj;
    }

    @Override
    public List<Address> getAll(Address obj) throws SQLException {
        String query = "select * from address";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Address> addresses = new ArrayList<>();
        while(rs.next()){
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setCity(rs.getString("city"));
            address.setCountry(rs.getString("country"));
            address.setLatitude(rs.getDouble("latitude"));
            address.setLongitude(rs.getDouble("longitude"));
            addresses.add(address);
        }
        return addresses;
    }

    @Override
    public Address create(Address obj) throws SQLException {
        String query = "insert into address values(?, ?, ?, ?, ?)";
        PreparedStatement pst = CONNECTION.prepareStatement(query);
        pst.setInt(1, obj.getId());
        pst.setDouble(2, obj.getLongitude());
        pst.setDouble(3, obj.getLatitude());
        pst.setString(4, obj.getCountry());
        pst.setString(5, obj.getCity());
        pst.executeUpdate();
        return obj;
    }

    @Override
    public void update(Address obj) throws SQLException {
        String query = "UPDATE address SET longitude = ? , latitude = ? , country = ? , city = ? WHERE id = ?";
        PreparedStatement pst = CONNECTION.prepareStatement(query);
        pst.setDouble(1, obj.getLongitude());
        pst.setDouble(2, obj.getLatitude());
        pst.setString(3, obj.getCountry());
        pst.setString(4, obj.getCity());
        pst.setInt(5, obj.getId());
        pst.executeUpdate();
    }

}
