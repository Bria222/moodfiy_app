//package models;
//
//import org.junit.Test;
//
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.util.Date;
//
//import static org.junit.Assert.*;
//
//public class AdminTest {
//
////    @Rule
////    public DatabaseRule databaseRule = new DatabaseRule();
//
//    private Admin newSighting(){
//        return new Admin("Lion","Zone A",1);
//    }
//
//    private Admin newSighting2(){return new Admin("Zebra","Zone B",2);}
//
//    @Test
//    public void sighting_instantiatesCorrectly(){
//        Admin admin = newSighting();
//        assertTrue(admin instanceof Admin);
//    }
//
//    @Test
//    public void getAnimalId_returnAnimalName_true(){
//        Admin admin = newSighting();
//        assertEquals("Lion", admin.getAnimalName());
//    }
//
//    @Test
//    public void getLocation_returnSightingLocation_true(){
//        Admin admin = newSighting();
//        assertEquals("Zone A", admin.getPopularity());
//    }
//
//    @Test
//    public void getRangerId_returnRangerId_true(){
//        Admin admin = newSighting();
//        assertEquals(1, admin.getRangerid());
//    }
//
//    @Test
//    public void getTimestamp_returnRangerId_true(){
//        Admin admin = newSighting();
//        Timestamp testTimestamp = new Timestamp(new Date().getTime());
//        DateFormat dateFormat = DateFormat.getDateTimeInstance();
//        assertEquals(dateFormat.format(testTimestamp),dateFormat.format(admin.getTimestamp()));
//    }
//
//    @Test
//    public void save_savesSightingIntoDB_true(){
//        Admin admin = newSighting();
//        int idBefore = admin.getId();
//        admin.save();
//        assertNotEquals(idBefore, admin.getId());
//    }
//
//    @Test
//    public void find_searchForSighting_true(){
//        Admin admin1 = newSighting();
//        Admin admin2 = newSighting2();
//        admin1.save();
//        admin2.save();
//        System.out.println();
//        assertTrue(Admin.find(admin2.getId()).equals(admin2));
//    }
//
//    @Test
//    public void all_getAllSightings_true(){
//        Admin admin1 = newSighting();
//        Admin admin2 = newSighting2();
//        admin1.save();
//        admin2.save();
//        assertTrue(Admin.all().contains(admin1));
//        assertTrue(Admin.all().contains(admin2));
//    }
//
//    @Test
//    public void all_getAllLocations_true(){
//        Admin admin1 = newSighting();
//        Admin admin2 = newSighting2();
//        admin1.save();
//        admin2.save();
//        assertEquals(2, Admin.getAllLocations().size());
//    }
//
//    @Test
//    public void filter_getSightingInSingleLocation_true(){
//        Admin admin1 = newSighting();
//        Admin admin2 = newSighting2();
//        Admin admin3 = new Admin("Antelope","Zone A",3);
//        admin1.save();
//        admin2.save();
//        admin3.save();
//        assertTrue(Admin.getAllSightingsInLocation("Zone A").contains(admin1));
//        assertTrue(Admin.getAllSightingsInLocation("Zone A").contains(admin3));
//    }
//
//
//
//
//
//
//}
