//package models;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class HappyTest {
//    private Happy newMood() {
//        return new Happy("Instagram","Image App","Many", "good");
//    }
//
//    @Test
//    public void animal_instantiatesCorrectly_true(){
//        Happy normalAnimal = newMood();
//        assertTrue(normalAnimal instanceof Happy);
//    }
//
//    @Test
//    public void getName_returnAnimalsName(){
//        Happy normalAnimal = newMood();
//        assertEquals("Goat", normalAnimal.getAppname());
//    }
//
//    @Test
//    public void getHealth_returnAnimalsHealth(){
//        Happy normalAnimal = newMood();
//        assertEquals("Healthy", normalAnimal.getAppinfo());
//    }
//
//    @Test
//    public void getAge_returnAnimalsAge(){
//        Happy normalAnimal = newMood();
//        assertEquals("Young", normalAnimal.getAge());
//    }
//
//    @Test
//    public void getStatus_returnAnimalsStatus(){
//        Happy normalAnimal = newMood();
//        assertEquals("Not Endangered", normalAnimal.getMoodtype());
//    }
//
//    @Test
//    public void save_savedToDb_int(){
//        Happy normalAnimal = newMood();
//        normalAnimal.save();
//        assertEquals(normalAnimal.getId(), Happy.all().get(0).getId());
//    }
//
//    @Test
//    public void find_locateNormalAnimal_Name(){
//        Happy normalAnimal = newMood();
//        normalAnimal.save();
//        Happy foundAnimal = Happy.find(normalAnimal.getId());
//        assertTrue(normalAnimal.equals(foundAnimal));
//    }
//
//}
