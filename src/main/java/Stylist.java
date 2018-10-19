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

    public Stylist(String firstName, String lastName, String email, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
        String sql = "SELECT id, firstname, lastname, email, phonenumber FROM stylists ";
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
            String sql= "INSERT INTO stylists (firstname, Lastname,email,phonenumber) VALUES (:firstName, :lastName, :email, :phoneNumber)";
           this.id= (int) con.createQuery(sql, true)
            .addParameter("firstName", this.firstName)
            .addParameter("lastName", this.lastName)
            .addParameter("email", this.email)
            .addParameter("phoneNumber", this.phoneNumber)
            .executeUpdate()
           .getKey();

        }
    }
public int getCliantId(){
        return cliantId;
}

public void update(String firstname,String lastname,String email,int phonenumber){
        try(Connection con = DB.sql2otest.open()){
            String sql = "UPDATE stylists SET firstname = :firstname, lastname = :lastname, email = :email, phonenumber = :phonenumber";
           this.id=(int) con.createQuery(sql,true)
             .addParameter("firstname", firstname)
             .addParameter("lastname", lastname)
             .addParameter("email", email)
              .addParameter("phonenumber", phonenumber)
              .executeUpdate()
            .getKey();
        }
}
public void delete(){
        try(Connection con= DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id= :id;";
            con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
        }
}
}
