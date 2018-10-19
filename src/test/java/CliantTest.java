import org.junit.*;
import org.sql2o.*;

import java.util.Arrays;

import static org.junit.Assert.*;


public class CliantTest {

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/salon_test","tonny","postgres22");
    }
@After
public void tearDown(){
        try(Connection con= DB.sql2o.open()){
            String deleteStylistQuery = "DELETE FROM stylists *;";
            String deleteCliantQuery = "DELETE FROM cliants *;";
            con.createQuery(deleteStylistQuery).executeUpdate();
            con.createQuery(deleteCliantQuery).executeUpdate();
        }
}

    @Test
    public void getFname(){
        Cliant newCliant= new Cliant("tony","muchui","email", 0720);
        assertEquals("tony",newCliant.getFirstName());
    }

    @Test
    public void getLname(){
        Cliant newCliant= new Cliant("tony","muchui","email", 0720);
        assertEquals("muchui", newCliant.getLastName());
    }
    @Test
    public void Getemail(){
        Cliant newCliant= new Cliant("tony","muchui","email", 0720);
        assertEquals("email",newCliant.getEmail());
    }
    @Test
    public void getPnumber(){
        Cliant newCliant= new Cliant("tony","muchui","email", 0720);
        assertEquals(0720,newCliant.getPhoneNumber());
    }
    @Test
    public void getAll(){
        Cliant firstCliant= new Cliant("tony","muchui","email", 0720);
        firstCliant.save();
        Cliant secondCliant= new Cliant("tony","muchui","email", 0720);
        secondCliant.save();
        assertEquals(Cliant.all().get(0),Cliant.all().get(1));

    }
    @Test
    public void  equals(){
        Cliant firstCliant= new Cliant("tony","muchui","email", 0720);
        Cliant secondCliant= new Cliant("tony","muchui","email", 0720);
        assertTrue(firstCliant.equals(secondCliant));
    }
    @Test
    public void save(){
        Cliant newCliant= new Cliant("tony","muchui","email", 0720);
        newCliant.save();
        Cliant savedCliant = Cliant.all().get(0);
        assertEquals(newCliant.getId(), savedCliant.getId());
    }
    @Test
    public void delete(){
        Cliant firstStylist = new Cliant("Tony","muchui","email",072020202);
        firstStylist.save();
        int myStylistId = firstStylist.getId();
        firstStylist.delete();
        assertEquals(null, Stylist.find(myStylistId));
    }


//    @Test
//    public void getStylist(){
//        Cliant firstCliant= new Cliant("tony","muchui","email", 720);
//        firstCliant.save();
//        Stylist firstStylist = new Stylist("Tony","muchui","email",72020202,firstCliant.getId());
//        firstStylist.save();
//        Stylist secondStylist = new Stylist("Ton","muchu","emal",7202020,firstCliant.getId());
//       secondStylist.save();
//        Stylist[] stylists = new Stylist[] {firstStylist, secondStylist};
//    assertTrue(firstCliant.getmyStylist().containsAll(Arrays.asList(stylists)));
//    }
}