
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionTest {

    public static void main(String[] args) {
      //  Assert.assertEquals(myCalc(5,5), 10);
    }
    public  static int myCalc(int a, int b){
        return a+b;
    }

    public  static boolean myValue(){

        return true;
    }
/*

 //   @Test
    public void testCalc(){
        Assert.assertThrows(ArithmeticException.class, ()-> myTest());
    }
  //  @Test
    public  void testDevideByZero() {
        Assert.assertThrows(Exception.class, () -> myTest());
    }
    public static int myTest(){
        return 10/0;
    }
*/

/*   @Test
    public void failTest(){
        int actualResult = someFunction();
        int expectedResult =10;
        Assert.fail("The test is fail ....."); //Этот вызов явно заставляет тест считаться неудачным и завершается с сообщением "The test is fail ....." без проверки условий.
        Assert.assertEquals(actualResult,expectedResult,"my comment");

    }*//*

    public static int someFunction(){
        return 10;
    }

    */
/**
     * Таким образом, этот тест проверяет, что при выполнении метода myTest() действительно выбрасывается исключение
     * ArithmeticException, что является ожидаемым поведением в случае деления на ноль.
     *//*

  //  @Test
    public void testDivideByZero() {
        Assert.assertThrows(ArithmeticException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() {
                myTest();
            }
        });
    }

*/
}

