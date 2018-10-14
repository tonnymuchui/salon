import org.junit.Test;

import static org.junit.Assert.*;

public class StylistTest {

    @Test
    public void getFname(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
assertEquals("Tony",testStylist.getFirstName());
    }
    @Test
    public void getLname(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
        assertEquals("muchui", testStylist.getLastName());
    }
    @Test
    public void getEmail(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
assertEquals("email", testStylist.getEmail());
    }
    @Test
    public void getNumber(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
assertEquals(072020202, testStylist.getPhoneNumber());
    }
    @Test
    public void all(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202);
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202);
assertTrue(Stylist.all().contains(firstStylist));
assertTrue(Stylist.all().contains(secondStylist));
    }
    @Test
    public void clear(){
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
Stylist.clear();
        assertEquals(Stylist.all().size(),0);
    }
    @Test
    public void getId(){
        Stylist.clear();
        Stylist testStylist = new Stylist("Tony","muchui","email",072020202);
assertEquals(1,testStylist.getId());
    }
    @Test
    public void find(){
        Stylist firstStylist = new Stylist("Tony","muchui","email",072020202);
        Stylist secondStylist = new Stylist("Tony","muchui","email",072020202);
assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
}