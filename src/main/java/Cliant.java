import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

import static spark.route.HttpMethod.get;

public class Cliant {
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int id;

    public Cliant(String firstName, String lastName, String email, int phoneNumber) {
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


    public static List<Cliant> all() {
        String sql = "SELECT id, firstname, lastname, email, phonenumber FROM cliants";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Cliant.class);
        }
    }

    @Override
    public boolean equals(Object otherCliant) {
        if (!(otherCliant instanceof Cliant)) {
            return false;
        } else {
            Cliant newCliant = (Cliant) otherCliant;
            return this.getFirstName().equals(newCliant.getFirstName()) &&
                    this.getLastName().equals(newCliant.getLastName()) &&
                    this.getEmail().equals(newCliant.getEmail()) &&
                    this.getPhoneNumber() == (newCliant.getPhoneNumber()) &&
                    this.getId() == newCliant.getId();
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO cliants (firstname,lastname, email,phonenumber) VALUES (:firstName,:lastName,:email,:phoneNumber)";
            this.id = (int) con.createQuery(sql)
                    .addParameter("firstName", this.firstName)
                    .addParameter("lastName", this.lastName)
                    .addParameter("email", this.email)
                    .addParameter("phoneNumber", this.phoneNumber)
                    .executeUpdate()
                    .getKey();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Cliant find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT *FROM cliants WHERE id=:id";
            System.out.println(sql);
            Cliant cliant = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cliant.class);
            return cliant;
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM cliants WHERE id= :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Stylist> getmyStylist() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists WHERE clientId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Stylist.class);
        }
    }

    public void update(String firstname, String lastname, String email, int phonenumber) {
        try (Connection con = DB.sql2otest.open()) {
            String sql = "UPDATE cliants SET firstname = :firstname, lastname = :lastname, email = :email, phonenumber = :phonenumber";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("firstname", firstname)
                    .addParameter("lastname", lastname)
                    .addParameter("email", email)
                    .addParameter("phonenumber", phonenumber)
                    .executeUpdate()
                    .getKey();
        }
    }
}