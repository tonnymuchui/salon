import org.sql2o.*;


public class DB {
    public static Sql2o sql2o= new Sql2o("jdbc:postgresql://localhost:5432/salon","tonny","postgres22"  );
    public static Sql2o sql2otest= new Sql2o("jdbc:postgresql://localhost:5432/salon_test","tonny","postgres22"  );
}
