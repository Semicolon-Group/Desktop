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
import java.util.ArrayList;
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

    public MemberService() {
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
        String query = "insert into user (pseudo, firstname, lastname, email,password,birth_date,gender,height,"
                + "body_type,children_number,relegion,relegion_importance,smoker,drinker,min_age,max_age,"
                + "proximity,last_login,locked,ip,port,role,created_at,updated_at,about,civil_status,connected,created_at)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        preparedStatement.setString(1, obj.getPseudo());
        preparedStatement.setString(2, obj.getFirstname());
        preparedStatement.setString(3, obj.getLastname());
        preparedStatement.setString(4, obj.getEmail());
        preparedStatement.setString(5, obj.getPassword());
        preparedStatement.setDate(6, obj.getBirthDate());
        preparedStatement.setBoolean(7, obj.isGender());
        preparedStatement.setFloat(8, obj.getHeight());
        preparedStatement.setInt(9, obj.getBodyType().ordinal());
        preparedStatement.setInt(10, obj.getChildrenNumber());
        preparedStatement.setInt(11, obj.getReligion().ordinal());
        preparedStatement.setInt(12, obj.getReligionImportance().ordinal());
        preparedStatement.setBoolean(13, obj.isSmoker());
        preparedStatement.setBoolean(14, obj.isDrinker());
        preparedStatement.setInt(15, obj.getMinAge());
        preparedStatement.setInt(16, obj.getMaxAge());
        preparedStatement.setInt(17, obj.getProximity().ordinal());
        preparedStatement.setTimestamp(18, obj.getLastLogin());
        preparedStatement.setShort(19, obj.getLocked());
        preparedStatement.setString(20, obj.getIp());
        preparedStatement.setInt(21, obj.getPort());
//	preparedStatement.setInt(22, Role.MEMBER.ordinal());
        preparedStatement.setTimestamp(23, new Timestamp(new Date().getTime()));
        preparedStatement.setTimestamp(24, new Timestamp(new Date().getTime()));
        preparedStatement.setString(25, obj.getAbout());
        preparedStatement.setInt(26, obj.getMaritalStatus().ordinal());
        preparedStatement.setBoolean(27, obj.isConnected());
        preparedStatement.setTimestamp(28, obj.getCreatedAt());
        preparedStatement.executeUpdate();

        String req = "SELECT MAX(id) max from user";
        ResultSet rs = CONNECTION.createStatement().executeQuery(req);
        rs.next();

        obj.getAddress().setUserId(rs.getInt("max"));
        AddressService.getInstance().create(obj.getAddress());

        return obj;

    }

    @Override
    public void update(Member obj) throws SQLException {
        String condition ="";
        if (obj.getEmail()!=null && obj.getPseudo()!=null) 
        { condition= " Where  pseudo='"+obj.getPseudo()+"' and email='"+obj.getEmail()+"'";}
        else if(obj.getId()!=0){
            condition = " Where id="+obj.getId();
        
        }
        String query = "UPDATE user SET pseudo=?, firstname=?, lastname=?,"
                + "email=?, password=?, birth_date=?, gender=?, height=?,"
                + "body_type=?, children_number=?, relegion=?, relegion_importance=?,"
                + "smoker=?, drinker=?, min_age=?, max_age=?, proximity=?,"
                + "last_login=?, locked=?, ip=?, port=?, updated_at=?, about=?, civil_status=?, connected=? "+condition;
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
        prepare.setString(23, obj.getAbout());
        prepare.setInt(24, obj.getMaritalStatus().ordinal());
        prepare.setBoolean(25, obj.isConnected());
       
        prepare.executeUpdate();
//        AddressService.getInstance().update(obj.getAddress());
    }

    @Override
    public Member get(Member obj) throws SQLException {
        String condition = "";
        if(obj.getPseudo()!=null)
             condition = " Where pseudo ='" + obj.getPseudo()+"'";
        else  if (obj.getId() != 0) {
            condition = " Where id =" + obj.getId();
        } else if (obj.getPseudo() != null && obj.getEmail() != null) {
            condition = " Where pseudo ='" + obj.getPseudo() + "' and email='" + obj.getEmail() + "'";
        } else if (obj.getPseudo() != null && obj.getPassword() != null) {
            condition = " Where pseudo ='" + obj.getPseudo() + "' and password='" + obj.getPassword() + "'";

        }
        String req = "Select * from user " + condition;
        st = CONNECTION.createStatement();
        rs = st.executeQuery(req);
        if (rs.next()) {

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
            obj.setAbout(rs.getString("about"));
            obj.setMaritalStatus(Enumerations.MaritalStatus.values()[rs.getInt("civil_status")]);
            obj.setConnected(rs.getBoolean("connected"));
            obj.setCreatedAt(rs.getTimestamp("created_at"));
//            obj.setAddress(AddressService.getInstance().get(new Address(obj.getId())));
            return obj;
        }
        return null;
    }

    @Override
    public List<Member> getAll(Member obj) throws SQLException {
        String query = "select * from user";
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        List<Member> mmbrs = new ArrayList<>();
        while (rs.next()) {

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
            mbr.setProximity(Enumerations.Proximity.values()[rs.getInt("proximity")]);
            mbr.setLastLogin(rs.getTimestamp("last_login"));
            mbr.setLocked(rs.getShort("locked"));
            mbr.setIp(rs.getString("ip"));
            mbr.setPort(rs.getInt("port"));
            mbr.setAbout(rs.getString("about"));
            mbr.setMaritalStatus(Enumerations.MaritalStatus.values()[rs.getInt("civil_status")]);
            mbr.setConnected(rs.getBoolean("connected"));
            mbr.setCreatedAt(rs.getTimestamp("created_at"));
            mbr.setAddress(AddressService.getInstance().get(new Address(mbr.getId())));

            mmbrs.add(mbr);
        }
        return mmbrs;
    }

}
