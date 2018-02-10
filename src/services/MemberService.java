/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import models.Address;
import models.Enumerations;
import models.Member;

/**
 *
 * @author Elyes
 */
public class MemberService extends Service implements Create<Member>, Update<Member>, Read<Member> {

    private static MemberService memberService;
    Statement st;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    private MemberService() {
        super();
    }

    public static MemberService getInstance() {
        if (memberService == null) {
            return memberService = new MemberService();
        }
        return memberService;
    }

    @Override
    public Member create(Member obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Member obj) throws SQLException {
        String query = "UPDATE user SET pseudo=?, firstname=?, lastname=?,"
                + "email=?, password=?, birth_date=?, gender=?, height=?,"
                + "body_type=?, children_number=?, relegion=?, relegion_importance=?,"
                + "smoker=?, drinker=?, min_age=?, max_age=?, proximity=?,"
                + "last_login=?, locked=?, ip=?, port=? WHERE id=?";
        PreparedStatement prepare = CONNECTION.prepareStatement(query);
        prepare.setString(1, obj.getPseudo());
        prepare.setString(2, obj.getFirstname());
        prepare.setString(3, obj.getLastname());
        prepare.setString(4, obj.getEmail());
        prepare.setString(5, obj.getPassword());
        prepare.setDate(6, obj.getBirthDate());
        prepare.setBoolean(7, obj.isGender());
        prepare.setFloat(8, obj.getHeight());
        prepare.setInt(9, obj.getBodyType().ordinal());
        prepare.setInt(10, obj.getChildrenNumber());
        prepare.setInt(11, obj.getReligion().ordinal());
        prepare.setInt(12, obj.getReligionImportance().ordinal());
        prepare.setBoolean(13, obj.isSmoker());
        prepare.setBoolean(14, obj.isDrinker());
        prepare.setInt(15, obj.getMinAge());
        prepare.setInt(16, obj.getMaxAge());
        prepare.setInt(17, obj.getProximity().ordinal());
        prepare.setTimestamp(18, new Timestamp(new Date().getTime()));
        prepare.setShort(19, obj.getLocked());
        prepare.setString(20, obj.getIp());
        prepare.setInt(21, obj.getPort());
        prepare.setInt(22, obj.getId());
        prepare.executeUpdate();
        AddressService.getInstance().update(obj.getAddress());
    }

    @Override
    public Member get(Member obj) throws SQLException {

        String condition = "";
        if (obj.getId() != 0) {
            condition = "Where id = " + obj.getId();
        } else if (obj.getPseudo() != null) {
            condition = "Where pseudo = " + obj.getPseudo();
        } else if (obj.getEmail() != null) {
            condition = "Where email = " + obj.getEmail();
        }
        String req = "Select * , date_format( birth_date ,'%d %m %Y') as birth from user " + condition;
        st = CONNECTION.createStatement();
        rs = st.executeQuery(req);
        rs.next();
        obj.setPseudo(rs.getString("pseudo"));
        obj.setFirstname(rs.getString("firstname"));
        obj.setLastname(rs.getString("lastname"));
        obj.setEmail(rs.getString("email"));
        obj.setPassword(rs.getString("password"));
        obj.setBirthDate(rs.getDate("birth_date"));
        obj.setGender(rs.getBoolean("gender"));
        obj.setHeight(rs.getFloat("height"));
        obj.setBodyType(Enumerations.BodyType.values()[rs.getInt("body_type")]);
        obj.setChildrenNumber(rs.getInt("children_number"));
        obj.setReligion(Enumerations.Religion.values()[rs.getInt("relegion")]);
        obj.setReligionImportance(Enumerations.Importance.values()[rs.getInt("relegion_importance")]);
        obj.setSmoker(rs.getBoolean("smoker"));
        obj.setDrinker(rs.getBoolean("drinker"));
        obj.setMinAge(rs.getInt("min_age"));
        obj.setMaxAge(rs.getInt("max_age"));
        obj.setProximity(Enumerations.Proximity.values()[rs.getInt("proximity")]);
        obj.setLastLogin(rs.getTimestamp("last_login"));
        obj.setLocked(rs.getShort("locked"));
        obj.setIp(rs.getString("ip"));
        obj.setPort(rs.getInt("port"));
        obj.setAddress(new Address(obj.getId()));
        
        
        
        return obj;
    }

    @Override
    public List<Member> getAll(Member obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
