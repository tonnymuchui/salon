import java.util.ArrayList;
import java.util.List;

public class Stylist {

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private static List<Stylist> instances = new ArrayList<Stylist>();
    private  int id;

    public Stylist (String firstName, String lastName,String email, int phoneNumber) {
        this.firstName= firstName;
        this.lastName= lastName;
        this.email= email;
        this.phoneNumber= phoneNumber;
        instances.add(this);
        id=instances.size();
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
public static List<Stylist> all(){
        return instances;
}
public static void clear(){
        instances.clear();
}
    public int getId(){
        return id;
    }
    public static Stylist find(int id){
        return instances.get(id-1);
    }
}
