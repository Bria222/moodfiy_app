package models;

import org.sql2o.Connection;

import java.io.File;
import java.util.List;

public class Happy extends Mood {
    private static final String STATUS = "happy";

    public Happy( String appname, String appinfo, String downloads, String rating) {
//       this.appimage = appimage;
        this.appname = appname;
        this.appinfo = appinfo;
        this.downloads = downloads;
        this.moodtype = STATUS;
        this.rating = rating;
    }



    //DAO OPERATIONS
    public static List<Happy> all(){
        String sql = "SELECT * FROM moods where moodtype=:moodtype";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("moodtype", STATUS)
                    .executeAndFetch(Happy.class);
        }
    }

    public static Happy find(int searchId){
        String sql = "SELECT * FROM moods where (id=:id AND moodtype=:moodtype)";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",searchId)
                    .addParameter("moodtype", STATUS)
                    .executeAndFetchFirst(Happy.class);
        }
    }


}