import dao.UserDao;
import models.Admin;
import models.Sad;
import models.Happy;
import models.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static <Bytea> void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");


        //get: index page
        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Admin> allFeelings = Admin.all();
            model.put("admins", allFeelings);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //get:sad mood
        get("/mood/sad",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sad", Sad.all());
            return new ModelAndView(model,"sad.hbs");
        },new HandlebarsTemplateEngine());

        //get: happy mood
        get("/mood/happy",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("happy", Happy.all());
            return new ModelAndView(model,"happy.hbs");
        },new HandlebarsTemplateEngine());

        //get: new admin input form-view
        get("/admin/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("admins", Admin.all());
            return new ModelAndView(model,"admin-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post: post admin input
        post("/admin/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
          String userName = request.queryParams("userName").trim();
          //  File appimage = new File(request.queryParams("appimage"));
            String appname = request.queryParams("appname").trim();
            String appinfo = request.queryParams("appinfo").trim();
            String downloads = request.queryParams("downloads").trim();
            String rating = request.queryParams("rating").trim();
            String popularity = request.queryParams("popularity").trim();
            String moodType = request.queryParams("moodtype").trim();

            User newUser = new User(userName);
            newUser.save();

            if(moodType.equalsIgnoreCase("sad")){
                Sad sad = new Sad(  appname,  appinfo,  downloads, rating);
                sad.save();
                Admin newAdmin = new Admin(sad.getAppname(),popularity, newUser.getId());
                newAdmin.save();
            }
            else{
                Happy happy = new Happy(  appname,  appinfo,  downloads, rating);
                happy.save();
                Admin newAdmin = new Admin(happy.getAppname(),popularity, newUser.getId());
                newAdmin.save();
            }
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        //get: admin input per popularity view
        get("/admins",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("admins", Admin.all());
            return new ModelAndView(model,"admin-popularities.hbs");
        },new HandlebarsTemplateEngine());

        //get:admin input per popularity form
        get("/admins/:location/details",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String filter = request.params("location");
            model.put("popularity",filter);
            model.put("admins", Admin.getAllAdminsInPopularity(filter));
            return new ModelAndView(model,"admin-popularity-details.hbs");
        },new HandlebarsTemplateEngine());

        //get: users admin input
        get("/users/:id/details",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            User foundUser = UserDao.find(id);
            List<Admin> myFeelings = foundUser.myAdmins();
            model.put("user", foundUser);
            model.put("admins", myFeelings);
            return new ModelAndView(model,"userlist.hbs");
        },new HandlebarsTemplateEngine());

        //get: all users
        get("/users",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("users", UserDao.all());
            return new ModelAndView(model,"userlist.hbs");
        },new HandlebarsTemplateEngine());

        //get:admin input per user
        get("/users/:id/admin/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            User specificUser = UserDao.find(id);
            model.put("specificUser", specificUser);
            model.put("admins", Admin.all());
            return new ModelAndView(model,"admin-form.hbs");
        },new HandlebarsTemplateEngine());

        //post:admin input per user
        post("/users/:id/admin/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            User specificUser = UserDao.find(id);
            File appimage = new File(request.queryParams("appimage"));
            String appname = request.queryParams("appname").trim();
            String appinfo = request.queryParams("appinfo").trim();
            String downloads = request.queryParams("downloads").trim();
            String rating = request.queryParams("rating").trim();
            String popularity = request.queryParams("popularity").trim();
            String moodType = request.queryParams("moodtype").trim();

            if(moodType.equalsIgnoreCase("sad")){
                Sad sad = new Sad( appname,  appinfo,  downloads, rating);
                sad.save();
                Admin newAdmin = new Admin(sad.getAppname(),popularity, specificUser.getId());
                newAdmin.save();
            }
            else{
                Happy happy = new Happy(  appname,  appinfo,  downloads, rating);
                happy.save();
                Admin newAdmin = new Admin(happy.getAppname(),popularity, specificUser.getId());
                newAdmin.save();
            }

            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

    }
    }
