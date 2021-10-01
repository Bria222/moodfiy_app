//package models;//import models.Ranger;
//import dao.UserDao;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//@SuppressWarnings("ConstantConditions")
//public class UserTest {
//
//
//
//    private User newRanger(){
//        return new User("Wendy");
//    }
//
//    @Test
//    public void ranger_instantiatesCorrectly(){
//        User user = newRanger();
//        assertTrue(user instanceof User);
//    }
//
//    @Test
//    public void getName_returnRangerName_true(){
//        User user = newRanger();
//        assertEquals("Wendy", user.getName());
//    }
//
//    @Test
//    public void save_getRangerId(){
//        User user = newRanger();
//        int idBefore = user.getId();
//        user.save();
//        assertNotEquals(idBefore, user.getId());
//    }
//
//    @Test
//    public void all_getAllRangers(){
//        User user = newRanger();
//        User user1 = new User("Stark");
//        user.save();
//        user1.save();
//        assertTrue(UserDao.all().contains(user));
//        assertTrue(UserDao.all().contains(user1));
//    }
//
//    @Test
//    public void find_getParticularRanger(){
//        User user = newRanger();
//        User user1 = new User("Alice");
//        user.save();
//        user1.save();
//        assertTrue(UserDao.find(user.getId()).equals(user));
//    }
//
//    @Test
//    public void add_preventDuplicateRanger(){
//        User user1 = newRanger();
//        User user2 = newRanger();
//        user1.save();
//        user2.save();
//        for(User user : UserDao.all()){
//            if (user2.equals(user)){
//                user2.delete();
//                break;
//            }
//        }
//        assertEquals(1, UserDao.all().size());
//    }
//
//}