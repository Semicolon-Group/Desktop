
import java.sql.SQLException;
import java.util.ArrayList;
import models.Enumerations.BodyType;
import models.Enumerations.Religion;
import models.Enumerations.LastLogin;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import services.Filter;
import services.MemberService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elyes
 */
public class test {
    public static void main(String[] args) throws SQLException {
        Filter f = new Filter();
        f.setAgeMax(22);
        f.setAgeMin(18);
        f.setHeightMax(1.8f);
        f.setHeightMin(1.75f);
        f.setSmokes(0);
        f.setDrinks(0);
        
        ArrayList<BodyType> b = new ArrayList();
        b.add(BodyType.MINCE);
        b.add(BodyType.SURPOID);
        f.setBodyType(b);
        
        ArrayList<Religion> r = new ArrayList();
        r.add(Religion.ISLAM);
        r.add(Religion.JUDAISME);
        f.setReligion(r);
        
        f.getMaritalStatus().add(MaritalStatus.MARRIE);
        f.getMaritalStatus().add(MaritalStatus.CELIBATAIRE);
        
        System.out.println(MemberService.getInstance().getFiltered(f));
    }
}
