//package models;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class SadTest {
//
//
//    private Sad newMood() {
//        return new Sad("Goat","Healthy","Young","bad");
//    }
//
//    @Test
//    public void animal_instantiatesCorrectly_true(){
//        Sad sadAnimal = newMood();
//        assertTrue(sadAnimal instanceof Sad);
//    }
//
//    @Test
//    public void getName_returnAnimalsName(){
//        Sad sadAnimal = newMood();
//        assertEquals("Goat", sadAnimal.getAppname());
//    }
//
//    @Test
//    public void getHealth_returnAnimalsHealth(){
//        Sad sadAnimal = newMood();
//        assertEquals("Healthy", sadAnimal.getAppinfo());
//    }
//
//    @Test
//    public void getAge_returnAnimalsAge(){
//        Sad sadAnimal = newMood();
//        assertEquals("Young", sadAnimal.getAge());
//    }
//
//    @Test
//    public void getStatus_returnAnimalsStatus(){
//        Sad sadAnimal = newMood();
//        assertEquals("Endangered", sadAnimal.getMoodtype());
//    }
//
//    @Test
//    public void save_savedToDb_int(){
//        Sad sadAnimal = newMood();
//        sadAnimal.save();
//        assertEquals(sadAnimal.getId(), Sad.all().get(0).getId());
//    }
//
//    @Test
//    public void find_locateEndangeredAnimal_Name(){
//        Sad sadAnimal = newMood();
//        sadAnimal.save();
//        Sad foundAnimal = Sad.find(sadAnimal.getId());
//        assertEquals(sadAnimal,foundAnimal);
//    }
//
//}