import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestThemAll {
    private Calculations calculations;

    @BeforeEach
    public void init(){
        calculations = new Calculations();
    }

//-------------первого примера
    @Test
    public void testArray(){
        Integer[] result = {1, 7};
        Integer[] testedArray = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assertions.assertArrayEquals(result,calculations.checkArray(testedArray));
    }
    //проверка на выброс исключения при отсутствии 4 в массиве
    @Test
    public void testException() throws  RuntimeException{
        Integer[] array2 = {1, 2, 2, 3, 1, 7};
        Assertions.assertThrows(RuntimeException.class,()->{
           calculations.checkArray(array2);
        });
    }
    //проверка когда 4а первая
    @Test
    public void testArray2(){
        Integer[] result = {2, 9, 0, 2, 3, 1, 7};
        Integer[] testedArray = {4, 2, 9, 0, 2, 3, 1, 7};
        Assertions.assertArrayEquals(result,calculations.checkArray(testedArray));
    }
    //проверка когда 4ка последняя
    @Test
    public void testArray3(){
        Integer[] result = {8, 2, 9, 0, 2, 3, 1, 4};
        Integer[] testedArray = {8, 2, 9, 0, 2, 3, 1, 4};
        Assertions.assertArrayEquals(result,calculations.checkArray(testedArray));
    }

//----------------------------------------------2ой пример
    //нету 1
    @Test
    public void testArrayFalse(){
        Integer[] array3 = {4, 2, 2, 3, 4, 7};
        Assertions.assertEquals(false,calculations.hasItOneOrFour(array3));
    }
    //есть и 1 и 4
    @Test
    public void testArrayTrue(){
        Integer[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};

        Assertions.assertEquals(true,calculations.hasItOneOrFour(array));
    }
    //нету 4
    @Test
    public void testArray2False(){
        Integer[] array = {1, 2, 1, 1, 2, 3, 0, 1, 7};

        Assertions.assertEquals(false,calculations.hasItOneOrFour(array));
    }
      //все 1ы и 4ки в массиве
    @Test
    public void testArray2True(){
        Integer[] array = {1, 1, 4, 4, 4,4, 4, 1, 1};

        Assertions.assertEquals(true,calculations.hasItOneOrFour(array));
    }
}
