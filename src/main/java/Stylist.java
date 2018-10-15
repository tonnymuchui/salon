import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
public class Stylist {

    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int id;
    private int cliantId;

    public Stylist(String firstName, String lastName, String email, int phoneNumber, int clientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cliantId=clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }
    public static Stylist find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists WHERE id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public static List<Stylist> all() {
        String sql = "SELECT id, firstName, lastName, email, phoneNumber,cliantId FROM stylists";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
            return false;
        } else {
Stylist newStylist= (Stylist) otherStylist;
return this.getFirstName().equals(newStylist.getFirstName()) &&
        this.getLastName().equals(newStylist.getLastName()) &&
        this.getEmail().equals(newStylist.getEmail()) &&
        this.getPhoneNumber()== (newStylist.getPhoneNumber()) &&
        this.getId()== newStylist.getId() &&
this.getCliantId() == newStylist.getCliantId();
        }
    }
    public void save() {
        try(Connection con= DB.sql2o.open()){
            String sql= "INSERT INTO stylists (id, firstName, LastName,email,phoneNumber,cliantId) VALUES ( :firstName, :lastName, :email, :phoneNumber,:cliantId)";
           this.id= (int) con.createQuery(sql, true)
            .addParameter("firstName", this.firstName)
            .addParameter("lastName", this.lastName)
            .addParameter("email", this.email)
            .addParameter("phoneNumber", this.phoneNumber)
           .addParameter("cliantId", this.cliantId)
            .executeUpdate()
           .getKey();

        }
    }
public int getCliantId(){
        return cliantId;
}
}
