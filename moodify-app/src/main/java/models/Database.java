package models;

import org.sql2o.Sql2o;

public class Database {
//    To run app locally
     public static String connectionString = "jdbc:postgresql://localhost:5432/moodify";
  public static    Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");
}
