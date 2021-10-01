//package models;
//
//import java.util.List;
//import java.util.Objects;
//
//public class Ranger {
//
//    private int id;
//    private String name;
//
//    public Ranger(String name) {
//        this.name = name;
//    }
//
//    public static Ranger find(int id) {
//    }
//
//    public static Object all() {
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ranger ranger = (Ranger) o;
//        return name.equals(ranger.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void save() {
//    }
//
//    public List<Sighting> mySightings() {
//    }

//    public String getRangerName(){
//        return Ranger.find(rangerid).getName();
//    }











//    private String name;
//    private int id;
//
//
//    public Ranger(String name )
//    {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Ranger)) return false;
//        Ranger ranger = (Ranger) o;
//        return id == ranger.id &&
//                Objects.equals(name, ranger.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, id);
//    }
//}
package models;

import dao.UserDao;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class User implements UserDao {
    private int id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

   //DAO OPERATIONS
    @Override
    public void save(){
        if(!crossCheck()){
            String sql = "INSERT INTO users(name) VALUES(:name)";
            try(Connection con = Database.sql2o.open()){
                this.id = (int) con.createQuery(sql,true)
                        .addParameter("name",this.name)
                        .executeUpdate()
                        .getKey();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public List<Admin> myAdmins(){
        String sql = "SELECT * FROM admins WHERE userid=:id";
        try(Connection con = Database.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeAndFetch(Admin.class);
        }
    }

    @Override
    public void delete(){
        try(Connection con = Database.sql2o.open()){
            con.createQuery("DELETE FROM users WHERE id=:id")
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }

    private boolean crossCheck(){
        for(User user : UserDao.all()){
            if(this.getName().equals(user.getName())){
                this.id = user.id;
                this.name = user.name;
                return true;
            }
        }
        return false;
    }


}
