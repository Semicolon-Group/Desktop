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
    public Member update(Member obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Member get(Member obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Member> getAll(Member obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
