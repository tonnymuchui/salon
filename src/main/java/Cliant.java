import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Cliant {
    private  String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int id;

    public Cliant(String firstName, String lastName, String email, int phoneNumber){
        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
        this.phoneNumber= phoneNumber;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public int getId(){
        return id;
    }


    public static List<Cliant> all(){
        String sql = "SELECT id, firstName, lastName, email, phoneNumber FROM cliants";
        try(Connection con  = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Cliant.class);
        }
    }
    @Override
    public boolean equals(Object otherCliant){
        if(!(otherCliant instanceof Cliant)){
            return false;
        } else {
            Cliant newCliant = (Cliant) otherCliant;
            return this.getFirstName().equals(newCliant.getFirstName()) &&
                    this.getLastName().equals(newCliant.getLastName()) &&
                    this.getEmail().equals(newCliant.getEmail()) &&
                    this.getPhoneNumber()== (newCliant.getPhoneNumber()) &&
                    this.getId() == newCliant.getId();
        }
    }
  public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO cliants (firstName,lastName, email,phoneNumber VALUES (:firstName,:lastName,:email,:phoneNumber)";
          this.id= (int)  con.createQuery(sql, true)
            .addParameter("firstName", this.firstName)
            .addParameter("lastName", this.lastName)
            .addParameter("email", this.email)
            .addParameter("phoneNumber", this.phoneNumber)
            .executeUpdate()
          .getKey();
        }
  }
    public static Cliant find(int id){
       try(Connection con = DB.sql2o.open()){
           String sql = "SELECT *FROM cliants WHERE id=:id";
           Cliant cliant= con.createQuery(sql)
           .addParameter("id", id)
           .executeAndFetchFirst(Cliant.class);
           return cliant;
       }
    }

    public List<Stylist> getmyStylist(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM stylists WHERE clientId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Stylist.class);
        }
    }
}
