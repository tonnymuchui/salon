import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
//
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
//
//        setPort(port);

        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model= new HashMap<String, Object>();

//    Cliant cliant= Cliant.find(Integer.parseInt(request.queryParams("clientId")));

            String firstName= request.queryParams("firstName");
            String lastName= request.queryParams("lastName");
            String email= request.queryParams("email");
            String phoneNumber= request.queryParams("phoneNumber");
            int telPhone= Integer.parseInt(phoneNumber);

            Stylist newStylist = new Stylist(firstName,lastName,email,telPhone);
            newStylist.save();

            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/new", (request, response) -> {
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("template", "templates/index.vtl");
                    return new ModelAndView(model, layout);
                },new VelocityTemplateEngine());


//        get("stylists", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//        model.put("stylists", Stylist.all());
//        model.put("template", "templates/stylists.vts");
//        return new ModelAndView(model, layout);
//        },new VelocityTemplateEngine());



post("/client", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String firstName= request.queryParams("firstName");
            String lastName= request.queryParams("lastName");
            String email= request.queryParams("email");
            String phoneNumber= request.queryParams("phoneNumber");
            int telPhone= Integer.parseInt(phoneNumber);
            Cliant newClient= new Cliant(firstName,lastName,email,telPhone);
            newClient.save();
            model.put("template", "templates/client-success.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

get("clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
//    model.put("clients", Cliant.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
},new VelocityTemplateEngine());

        get("client", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Cliant.all());
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

get("/clients/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
Cliant client= Cliant.find(Integer.parseInt(request.params(":id")));
model.put("client", client);
model.put("template", "templates/client.vtl");
return new ModelAndView(model, layout);
},new VelocityTemplateEngine());

//Post method for deleting client
        post("/client/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Cliant client = Cliant.find(Integer.parseInt(request.params(":id")));
            client.delete();
            model.put("clients", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Post Method to delete stylist
        post("/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            stylist.delete();
            model.put("stylists", stylist);
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//get("clients/:id/stylists/new", (request, response) -> {
//    Map<String, Object> model = new HashMap<String, Object>();
//Cliant client= Cliant.find(Integer.parseInt(request.params(":id")));
//Stylist stylist= Stylist.find(Integer.parseInt(request.params(":id")));
//model.put("stylist", stylist);
//model.put("client", client);
//model.put("template", "templates/client-stylist-form.vtl");
//return new ModelAndView(model, layout);
//},new VelocityTemplateEngine());
//
//post("/stylists", (request, response) -> {
//    Map<String, Object> model = new HashMap<String, Object>();
//Cliant client= Cliant.find(Integer.parseInt(request.queryParams("clientId")));
//
//    String firstName= request.queryParams("firstName");
//    String lastName= request.queryParams("lastName");
//    String email= request.queryParams("email");
//    String phoneNumber= request.queryParams("phoneNumber");
//    int telPhone= Integer.parseInt(phoneNumber);
//
//    Stylist newStylist = new Stylist(firstName,lastName,email,telPhone);
//    client.getmyStylist();
//    model.put("client", client);
//    model.put("template", "templates/success.vtl");
//    return new ModelAndView(model, layout);
//},new VelocityTemplateEngine());
    }
}
