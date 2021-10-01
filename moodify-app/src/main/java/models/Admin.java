//package models;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.util.List;
//import java.util.Objects;
//import java.util.Date;
//
//
//public class Sighting {
//
//    private String animalName;
//    private int rangerid;
//    private String location;
//    private Timestamp timestamp;
//    private int id;
//
//    public Sighting(String animalName, String location, int rangerid) {
//        this.animalName = animalName;
//        this.location = location.trim();
//        this.timestamp = new Timestamp(new Date().getTime());
//        this.rangerid = rangerid;
//    }
//
//    public static List<Sighting> all() {
//    }
//
//    public static Object getAllSightingsInLocation(String filter) {
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sighting sighting = (Sighting) o;
//        return animalName.equals(sighting.animalName) &&
//                rangerid == sighting.rangerid &&
//                Objects.equals(location, sighting.location);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(animalName, location, rangerid);
//    }
//
//    public String getAnimalName() {
//        return animalName;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public Timestamp getTimestamp() {
//        return timestamp;
//    }
//
//    public String getReadableTimestamp(){
//        return DateFormat.getDateTimeInstance().format(getTimestamp());
//    }
//
//    public int getRangerid() {
//        return rangerid;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void save() {
//    }


//    private String description;
//    private int categoryId;
//    private String animal;
//    private int id;
//    private String age;
//    private String health;
//    private String zone;
//    private String location;
//    private Timestamp timestamp;
//
//    public Sighting(String description, int categoryId, String animal, String age, String health, String zone) {
//        this.description = description;
//        this.categoryId = categoryId;
//        this.animal = animal;
//        this.age = age;
//        this.health = health;
//        this.zone = zone;
////        this.location = location.trim();
////        this.timestamp = new Timestamp(new Date().getTime());
//
//    }
//
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Sighting)) return false;
//        Sighting sighting = (Sighting) o;
//        return id == sighting.id &&
//                Objects.equals(description, sighting.description) &&
//                Objects.equals(categoryId, sighting.categoryId) &&
//                Objects.equals(animal, sighting.animal) &&
//                Objects.equals(age, sighting.age) &&
//                Objects.equals(health, sighting.health) &&
//                Objects.equals(zone, sighting.zone);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, description, categoryId, animal, age, health, zone);
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public String getHealth() {
//        return health;
//    }
//
//    public String getZone() {
//        return zone;
//    }
//
//    public String getAnimal() {
//        return animal;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }


//}

package models;

import dao.UserDao;
import dao.AdminDao;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Admin implements AdminDao {
    private String moodName;
    private int userid;
    private String popularity;
    private Timestamp timestamp;
    private int id;

    public Admin(String moodName, String popularity, int userid) {
        this.moodName = moodName;
        this.popularity = popularity.trim();
        this.timestamp = new Timestamp(new Date().getTime());
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return moodName.equals(admin.moodName) &&
                userid == admin.userid &&
                Objects.equals(popularity, admin.popularity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moodName, popularity, userid);
    }

    public String getMoodName() {
        return moodName;
    }

    public String getPopularity() {
        return popularity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReadableTimestamp(){
        return DateFormat.getDateTimeInstance().format(getTimestamp());
    }

    public int getUserid() {
        return userid;
    }

    public int getId() {
        return id;
    }

   //DAO OPERATIONS
    public void save(){
        String sql = "INSERT INTO admins(moodName,popularity,userid) values (:moodName,:popularity,:userid)";
        try(Connection con = Database.sql2o.open()){
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("moodName",this.moodName)
                    .addParameter("popularity",this.popularity)
//                    .addParameter("timestamp",this.timestamp)
                    .addParameter("userid",this.userid)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public String getUserName(){
        return UserDao.find(userid).getName();
    }

    public static List<Admin> all(){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM admins")
                    .executeAndFetch(Admin.class);
        }
    }

    public static Admin find(int searchId){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM admins WHERE id=:id")
                    .addParameter("id",searchId)
                    .executeAndFetchFirst(Admin.class);
        }
    }

    public static List<String> getAllPopularities(){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT popularity FROM admins")
                    .executeAndFetch(String.class);
        }
    }

    public static List<Admin> getAllAdminsInPopularity(String popularityFilter){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM admins where popularity = :popularity")
                    .addParameter("popularity",popularityFilter)
                    .executeAndFetch(Admin.class);
        }
    }

}


