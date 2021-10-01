package dao;

import models.Database;
import models.Admin;
import models.User;
import org.sql2o.Connection;

import java.util.List;

public interface UserDao {
    static List<User> all(){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    static User find(int searchId){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE id=:id")
                    .addParameter("id",searchId)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    int getId();

    String getName();

    //DAO OPERATIONS
    void save();

    List<Admin> myAdmins();

    void delete();
}
