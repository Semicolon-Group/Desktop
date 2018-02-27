/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static controller.GlobalViewController.online;
import iservice.Create;
import iservice.Read;
import iservice.Update;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Address;
import models.Admin;
import models.Enumerations;
import models.Enumerations.Role;
import models.Enumerations.LastLogin;
import models.Member;
import models.User;
import static util.GoogleDistanceMatrixAPI.getDistance;

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
                + "phone,last_login,locked,ip,port,role,created_at,updated_at,about,civil_status,connected)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        preparedStatement.setInt(17, obj.getPhone());
        preparedStatement.setTimestamp(18, obj.getLastLogin());
        preparedStatement.setShort(19, obj.getLocked());
        preparedStatement.setString(20, obj.getIp());
        preparedStatement.setInt(21, obj.getPort());
	preparedStatement.setInt(22, Role.MEMBER.ordinal());
        preparedStatement.setTimestamp(23, new Timestamp(new Date().getTime()));
        preparedStatement.setTimestamp(24, new Timestamp(new Date().getTime()));
        preparedStatement.setString(25, obj.getAbout());
        preparedStatement.setInt(26, obj.getMaritalStatus().ordinal());
        preparedStatement.setBoolean(27, obj.isConnected());
        preparedStatement.executeUpdate();

        String req = "SELECT MAX(id) max from user";
        ResultSet rs = CONNECTION.createStatement().executeQuery(req);
        rs.next();
        
        obj.setId(rs.getInt("max"));
        obj.getAddress().setUserId(obj.getId());
        AddressService.getInstance().create(obj.getAddress());
        insertPreferedRelations(obj);
        insertPreferedStatus(obj);
        
        obj = get(obj);
        return obj;
    }

    //methode update pour modifichier l'attribut locked , bannir un membre
    public void updatelock(int id, short locked) throws SQLException {
        String query = "UPDATE user SET  locked=? WHERE id=?";
        PreparedStatement prepare = CONNECTION.prepareStatement(query);
        prepare.setShort(1, locked);
        prepare.setInt(2, id);
        prepare.executeUpdate();

    }

    @Override
    public void update(Member obj) throws SQLException {
        String query = "UPDATE user SET pseudo=?, firstname=?, lastname=?,"
                + "email=?, password=?, birth_date=?, gender=?, height=?,"
                + "body_type=?, children_number=?, relegion=?, relegion_importance=?,"
                + "smoker=?, drinker=?, min_age=?, max_age=?, phone=?,"
                + "last_login=?, locked=?, ip=?, port=?, updated_at=?, about=?, civil_status=?, connected=? WHERE id=?";
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
        prepare.setInt(17, obj.getPhone());
        prepare.setTimestamp(18, obj.getLastLogin());
        prepare.setShort(19, obj.getLocked());
        prepare.setString(20, obj.getIp());
        prepare.setInt(21, obj.getPort());
        prepare.setTimestamp(22, new Timestamp(new Date().getTime()));
        prepare.setString(23, obj.getAbout());
        prepare.setInt(24, obj.getMaritalStatus().ordinal());
        prepare.setBoolean(25, obj.isConnected());
        prepare.setInt(26, obj.getId());
        prepare.executeUpdate();
        AddressService.getInstance().update(obj.getAddress());
        deletePreferedRelations(obj.getId());
        insertPreferedRelations(obj);
        deletePreferedStatus(obj.getId());
        insertPreferedStatus(obj);
    }
    
    private Member getPreferedStatus(Member member) throws SQLException{
        String query = "SELECT * FROM prefered_status where user_id = "+member.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        while(rs.next()){
            member.getPreferedStatuses().add(Enumerations.MaritalStatus.values()[rs.getInt("status")]);
        }
        return member;
    }

    @Override
    public Member get(Member obj) throws SQLException {
        String condition = "Where role = 0";
        if (obj.getId() != 0) {
            condition += " and id = " + obj.getId();
        } else if (obj.getPseudo() != null) {
            condition += " and pseudo = '" + obj.getPseudo() + "'";
        } else if (obj.getEmail() != null) {
            condition += " and email = '" + obj.getEmail() + "'";
        }
        String req = "Select * from user " + condition;
        st = CONNECTION.createStatement();
        rs = st.executeQuery(req);

        if (rs.next()) {
            obj.setId(rs.getInt("id"));
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
            obj.setPhone(rs.getInt("phone"));
            obj.setLastLogin(rs.getTimestamp("last_login"));
            obj.setLocked(rs.getShort("locked"));
            obj.setIp(rs.getString("ip"));
            obj.setPort(rs.getInt("port"));
            obj.setAbout(rs.getString("about"));
            obj.setMaritalStatus(Enumerations.MaritalStatus.values()[rs.getInt("civil_status")]);
            obj.setConnected(rs.getBoolean("connected"));
            obj.setCreatedAt(rs.getTimestamp("created_at"));
            obj.setAddress(AddressService.getInstance().get(new Address(obj.getId())));
            obj = getPreferedRelations(obj);
            obj = getPreferedStatus(obj);
            return obj;
        }
        return null;
    }
    
    public Admin getAdmin(Admin obj) throws SQLException {
        if (obj.getPseudo() == null) return null;
        String query = "Select * from user Where role=1 and pseudo = '" + obj.getPseudo() + "'";
        st = CONNECTION.createStatement();
        rs = st.executeQuery(query);
        if (rs.next()) {
            obj.setId(rs.getInt("id"));
            obj.setPseudo(rs.getString("pseudo"));
            obj.setFirstname(rs.getString("firstname"));
            obj.setLastname(rs.getString("lastname"));
            obj.setEmail(rs.getString("email"));
            obj.setPassword(rs.getString("password"));
            obj.setIp(rs.getString("ip"));
            obj.setPort(rs.getInt("port")); 
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
            mbr.setPhone(rs.getInt("phone"));
            mbr.setLastLogin(rs.getTimestamp("last_login"));
            mbr.setLocked(rs.getShort("locked"));
            mbr.setIp(rs.getString("ip"));
            mbr.setPort(rs.getInt("port"));
            mbr.setAbout(rs.getString("about"));
            mbr.setMaritalStatus(Enumerations.MaritalStatus.values()[rs.getInt("civil_status")]);
            mbr.setConnected(rs.getBoolean("connected"));
            mbr.setCreatedAt(rs.getTimestamp("created_at"));
            mbr.setAddress(AddressService.getInstance().get(new Address(mbr.getId())));
            mbr = getPreferedRelations(mbr);
            mbr = getPreferedStatus(mbr);
            
            mmbrs.add(mbr);
        }
        return mmbrs;
    }

    public Map<Member,Map.Entry<Double,Integer>> getFiltered(Filter F) throws SQLException {
        String req = "SELECT *,TIMESTAMPDIFF(day,last_login,Sysdate()) as login FROM user WHERE ";
        
        req += "(TIMESTAMPDIFF(year,birth_date,Sysdate()) BETWEEN " + F.getAgeMin() + " AND " + F.getAgeMax() + ") ";
        
        switch (F.getLastLogin()) {
            case UN_JOUR:
                req += "AND (TIMESTAMPDIFF(day,last_login,Sysdate()) = 0) ";
                break;
            case SEMAINE:
                req += "AND (TIMESTAMPDIFF(week,last_login,Sysdate()) = 0) ";
                break;
            case MOIS:
                req += "AND (TIMESTAMPDIFF(month,last_login,Sysdate()) = 0) ";
                break;
            default:
                req += "AND (TIMESTAMPDIFF(year,last_login,Sysdate()) = 0) ";
                break;
        }

        req += "AND ((height * 100 >= " + F.getHeightMin() + ") and (height * 100 <= " + F.getHeightMax() + ")) ";

        if (!F.getBodyType().isEmpty()) {
            req += "and (body_type in (";
            for (Enumerations.BodyType bt : F.getBodyType()) {
                req += bt.ordinal() + ",";
            }
            req = req.substring(0, req.length() - 1) + ")) ";
        }

        if (!F.getReligion().isEmpty()) {
            req += " and (relegion in (";
            for (Enumerations.Religion r : F.getReligion()) {
                req += r.ordinal() + ",";
            }
            req = req.substring(0, req.length() - 1) + ")) ";
        }

        if (!F.getMaritalStatus().isEmpty()) {
            req += " and (civil_status in (";
            for (Enumerations.MaritalStatus m : F.getMaritalStatus()) {
                req += m.ordinal() + ",";
            }
            req = req.substring(0, req.length() - 1) + ")) ";
        }

        req += " and (gender = " + !online.isGender() + ") ";

        if (F.getSmokes() != -1) {
            req += "and (smoker = " + F.getSmokes() + ") ";
        }

        if (F.getDrinks() != -1) {
            req += "and (drinker = " + F.getDrinks() + ") ";
        }

        ResultSet rs = CONNECTION.createStatement().executeQuery(req);
        Map<Member,Map.Entry<Double,Integer>> mmbrs = new HashMap<>();
        while (rs.next()) {
            Member mbr = new Member();
            mbr.setId(rs.getInt("id"));
            mbr.setAddress(AddressService.getInstance().get(new Address(mbr.getId())));
            
            Double distance = getDistance(online.getAddress(), mbr.getAddress());
            if(F.getDistance() != -1){
                if(distance > F.getDistance()){
                    continue;
                }
            }
            Integer login = rs.getInt("login");
            
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
            mbr.setPhone(rs.getInt("phone"));
            mbr.setLastLogin(rs.getTimestamp("last_login"));
            mbr.setLocked(rs.getShort("locked"));
            mbr.setIp(rs.getString("ip"));
            mbr.setPort(rs.getInt("port"));
            mbr.setAbout(rs.getString("about"));
            mbr.setMaritalStatus(Enumerations.MaritalStatus.values()[rs.getInt("civil_status")]);
            mbr.setConnected(rs.getBoolean("connected"));
            mbr.setCreatedAt(rs.getTimestamp("created_at"));
            mbr = getPreferedRelations(mbr);
            mbr = getPreferedStatus(mbr);
            
            mmbrs.put(mbr,new AbstractMap.SimpleEntry(distance,login));
        }
        return mmbrs;
    }
    
    
    
    //Prefered relations CRUD
    private void deletePreferedRelations(int userId) throws SQLException{
        String query = "delete from prefered_relation where user_id = "+userId;
        CONNECTION.createStatement().executeUpdate(query);
    }
    
    private void insertPreferedRelations(Member member) throws SQLException{
        String query ="";
        for(Enumerations.RelationType type : member.getPreferedRelations()){
            query = "insert into prefered_relation values(?,?)";
            PreparedStatement prepare = CONNECTION.prepareStatement(query);
            prepare.setInt(1, member.getId());
            prepare.setInt(2, type.ordinal());
            prepare.executeUpdate();
        }
    }
    
    private Member getPreferedRelations(Member member) throws SQLException{
        String query = "SELECT * FROM prefered_relation where user_id = "+member.getId();
        ResultSet rs = CONNECTION.createStatement().executeQuery(query);
        while(rs.next()){
            member.getPreferedRelations().add(Enumerations.RelationType.values()[rs.getInt("relation")]);
        }
        return member;
    }
    
    //Prefered Statuses CRUD
    private void deletePreferedStatus(int userId) throws SQLException{
        String query = "delete from prefered_status where user_id = "+userId;
        CONNECTION.createStatement().executeUpdate(query);
    }
    
    private void insertPreferedStatus(Member member) throws SQLException{
        String query ="";
        for(Enumerations.MaritalStatus status : member.getPreferedStatuses()){
            query = "insert into prefered_status values(?,?)";
            PreparedStatement prepare = CONNECTION.prepareStatement(query);
            prepare.setInt(1, member.getId());
            prepare.setInt(2, status.ordinal());
            prepare.executeUpdate();
        }
    }

    public ResultSet getStats() throws SQLException {
        PreparedStatement stat = CONNECTION.prepareStatement("select concat(MONTH(created_at)) as created from user");
        return stat.executeQuery();
    }

    public ResultSet getGender() throws SQLException {
        PreparedStatement stat = CONNECTION.prepareStatement("select gender as sex from user");
        return stat.executeQuery();
    }

    public ResultSet getLike() throws SQLException {
        PreparedStatement stat = CONNECTION.prepareStatement("select concat(MONTH(date)) as created1 from user_like");
        return stat.executeQuery();
    }

    public ResultSet getBlock() throws SQLException {
        PreparedStatement stat = CONNECTION.prepareStatement("select concat(MONTH(date)) as created2 from user_block");
        return stat.executeQuery();
    }

    public ResultSet getSignal() throws SQLException {
        PreparedStatement stat = CONNECTION.prepareStatement("select concat(MONTH(date)) as created3 from user_signal");
        return stat.executeQuery();
    }

}
