import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class StylistTest {

    @Before
    public void setup(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/salon_test", "tonny", "postgres22");
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String deleteStylistQuery = "DELETE FROM stylists *;";
            String deleteClientQuery= "DELETE FROM cliants *;";
            con.createQuery(deleteStylistQuery).executeUpdate();
            con.createQuery(deleteClientQuery).executeUpdate();
        }
    }

    @Test
    public void getFname(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202,1);
assertEquals("Tony",testStylist.getFirstName());
    }
    @Test
    public void getLname(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202,1);
        assertEquals("muchui", testStylist.getLastName());
    }
    @Test
    public void getEmail(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202,1);
assertEquals("email", testStylist.getEmail());
    }
    @Test
    public void getNumber(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202,1);
assertEquals(072020202, testStylist.getPhoneNumber());
    }
    @Test
    public void all(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202,1);
       firstStylist.save();
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202,2);
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertTrue(Stylist.all().get(1).equals(secondStylist));
    }
    @Test
    public void equal(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202,1);
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202,2);
        assertTrue(firstStylist.equals(secondStylist));
    }
    @Test
    public void save(){
        Cliant secondCliant= new Cliant("tony","muchui","email", 0720);
        secondCliant.save();
        Stylist newStylist = new Stylist("Tony","muchui","email",072020202,2);
        newStylist.save();
        Stylist savedStylist = Stylist.find(newStylist.getId());
        assertEquals(savedStylist.getCliantId(), secondCliant.getId());
    }

//    @Test
//    public void clear(){
//        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
//Stylist.clear();
//        assertEquals(Stylist.all().size(),0);
//    }
    @Test
    public void getId(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202,1);
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
    }
    @Test
    public void find(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202,1);
        firstStylist.save();
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202,2);
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    public void update(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202,1);
firstStylist.save();
firstStylist.update("Tony","muchui","email",072020202);
assertEquals("Tony,muchui,email,072020202",Stylist.find(firstStylist.getId()).getEmail());
    }
@Test
    public void delete(){
    Stylist firstStylist = new Stylist("Tony","muchui","email",072020202,1);
    firstStylist.save();
    int myStylistId = firstStylist.getId();
    firstStylist.delete();
    assertEquals(null, Stylist.find(myStylistId));
}
}