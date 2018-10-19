import org.sql2o.*;


//public class DB {
//    public static Sql2o sql2o= new Sql2o("jdbc:postgresql://localhost:5432/salon","tonny","postgres22"  );
//    public static Sql2o sql2otest= new Sql2o("jdbc:postgresql://localhost:5432/salon_test","tonny","postgres22"  );
//}

        import org.sql2o.*;
        import java.net.URI;
        import java.net.URISyntaxException;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
public class DB {
    private static URI dbUri;
    public static Sql2o sql2o;
    static Logger logger = LoggerFactory.getLogger(DB.class);
    static {

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/salon");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? "tonny" : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? "postgres22" : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }
    }
}
