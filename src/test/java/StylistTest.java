import org.junit.*;
import org.sql2o.*;

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

//
    @Test
    public void all(){
        Stylist firstStylist = new Stylist("Tony","murungi","email@gmail.com",720202092);
       firstStylist.save();
        Stylist secondStylist = new Stylist("Tony","murungi","email@gmail.com",720202092);
        secondStylist.save();
        assertEquals(Stylist.all().get(0),Stylist.all().get(1));
    }
    @Test
    public void equal(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202);
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202);
        assertTrue(firstStylist.equals(secondStylist));
    }
    @Test
    public void save(){
        Stylist newStylist = new Stylist("Tony","muchui","email",072020202);
        newStylist.save();
        assertEquals(newStylist.getFirstName(),"Tony");
    }


    @Test
    public void find() {
        Stylist firstStylist = new Stylist("Tony", "muchui", "email", 072020202);
        firstStylist.save();
        Stylist secondStylist = new Stylist("Tonny", "murungi", "email", 072020202);
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    @Test
    public void update(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202);
firstStylist.save();
firstStylist.update("Tony","muchui","tonykanyinga@gmail.com",07);
assertEquals("Tony",firstStylist.getFirstName());
    }
@Test
    public void delete(){
    Stylist firstStylist = new Stylist("Tony","muchui","email",072020202);
    firstStylist.save();
    int myStylistId = firstStylist.getId();
    firstStylist.delete();
    assertEquals(null, Stylist.find(myStylistId));
}
}