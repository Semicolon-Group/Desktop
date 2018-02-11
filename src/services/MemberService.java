/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Enumerations;
import models.Member;


/**
 *
 * @author Elyes
 */
public class MemberService extends Service implements Create<Member>,Update<Member>,Read<Member>{

    private static MemberService memberService;
    
    private MemberService(){
        super();
    }
    
    public static MemberService getInstance(){
        if(memberService == null){
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
                + "last_login=?, locked=?, ip=?, port=?, updated_at=? WHERE id=?";
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
        prepare.setTimestamp(18, obj.getLastLogin());
        prepare.setShort(19, obj.getLocked());
        prepare.setString(20, obj.getIp());
        prepare.setInt(21, obj.getPort());
        prepare.setTimestamp(22, new Timestamp(new Date().getTime()));
        prepare.setInt(22, obj.getId());
        prepare.executeUpdate();
        AddressService.getInstance().update(obj.getAddress());
    }

    @Override
    public Member get(Member obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Member> getAll(Member obj) throws SQLException {
	String query = "select * from user";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Member> mmbrs = new ArrayList<>();
        while(rs.next()){
            
            Member mbr = new Member();
            
            mbr.setId(rs.getInt("id"));
            mbr.setPseudo(rs.getString("pseudo"));
            mbr.setFirstname(rs.getString("firstname"));
            mbr.setLastname(rs.getString("lastname"));
            mbr.setEmail(rs.getString("Email"));
            mbr.setPassword(rs.getString("password"));
            mbr.setBirthDate(rs.getDate("birth_date"));
            mbr.setGender(rs.getBoolean("gender"));
            mbr.setHeight(rs.getFloat("height"));
            mbr.setBodyType((Enumerations.BodyType.values()[rs.getInt("body_type")]));
            mbr.setChildrenNumber(rs.getInt("children_number"));
            mbr.setReligion((Enumerations.Religion.values()[rs.getInt("relegion")]));
            mbr.setReligionImportance((Enumerations.Importance.values()[rs.getInt("relegion_importance")]));
            mbr.setSmoker(rs.getBoolean("smoker"));
            mbr.setDrinker(rs.getBoolean("drinker"));
            mbr.setMinAge(rs.getInt("min_age"));
            mbr.setMaxAge(rs.getInt("max_age"));
            mbr.setProximity(Enumerations.Proximity.values()[rs.getInt("setProximity")]);
            mbr.setLastLogin(rs.getTimestamp("last_login"));
            mbr.setLocked(rs.getShort("locked"));
            mbr.setIp(rs.getString("ip"));
            mbr.setPort(rs.getInt("port"));
           
            mmbrs.add(mbr);
        }
        return mmbrs;
    }

    
}
