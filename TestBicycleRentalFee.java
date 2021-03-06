import Exceptions.UserAlreadyExists;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

public class TestBicycleRentalFee {


    private BikeRentalSystem bRental;
    private static final int rentalFee = 25;

    /**
     * Existe já um User com IDUser = 2 e com um rentalProgram = 1
     *
     */
    @BeforeEach
    public void setUp(){

        bRental = new BikeRentalSystem(rentalFee);
        try {
            bRental.registerUser(2, "Teste", 1);
    } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }

        bRental.addLock(1,1);
        bRental.addBicycle(1,1,1);

    }


     @Test
    public void testBicycleRentalFee1(){
        Assertions.assertEquals(0, bRental.bicycleRentalFee(0,1,2,2), "Deve retornar 0.");
    }


    @Test
    public void testBicycleRentalFee2(){
        Assertions.assertEquals(0, bRental.bicycleRentalFee(Integer.MAX_VALUE,1,2,2), "Deve retornar 0.");

    }


    @Test
    public void testBicycleRentalFee3(){
        int endTime = 1, startime = 2, calc = (endTime - startime) * rentalFee;

        //Assertions.assertTrue(bRental.bicycleRentalFee(1, startime, endTime, 3) < 0);
        Assertions.assertNotEquals(calc, bRental.bicycleRentalFee(1, startime, endTime, 3));
    }


    @Test
    public void testBicycleRentalFee4(){
        int endTime = 2, startTime = 1, nRentals = 2, calc = (endTime - startTime) * rentalFee;
        Assertions.assertEquals(calc, bRental.bicycleRentalFee(1, startTime, endTime, nRentals));
    }



     @Test
    public void testBicycleRentalFee5(){
        int endTime = 2, startTime = 1, nRentals = 20;
        Assertions.assertEquals(0, bRental.bicycleRentalFee(2, startTime, endTime, nRentals));

    }


   @Test
    public void testBicycleRentalFee6(){
        int endTime = 5, startTime = 15, nRentals = 3, calc = rentalFee * (endTime - startTime);
        Assertions.assertEquals(calc, bRental.bicycleRentalFee(2, startTime, endTime, nRentals));
    }


      @Test
    public void testBicycleRentalFee7(){
        int endTime = 5, startTime = 16, nRentals = 3, calc = 10*rentalFee + ((endTime - startTime)-10)* rentalFee;
        Assertions.assertEquals(calc, bRental.bicycleRentalFee(2, startTime, endTime, nRentals));
    }

     @Test
    public void testBicycleRentalFee8(){
        System.out.println(bRental.bicycleRentalFee(2, 5, 10, -1));
        Assertions.assertNotEquals(-1, bRental.bicycleRentalFee(2, 5, 10, -1));

    }

}
