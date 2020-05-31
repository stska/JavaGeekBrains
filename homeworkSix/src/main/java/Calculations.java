/*
2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть новый массив,
который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */


import java.util.ArrayList;
import java.util.Collections;

public class Calculations {
    public static void main(String[] args) {
        /*
        Все убрал от сюда, так как будет копировать функционал Теста. Может даже не было смысла делать как класс с main и лучше сделать было как класс с 2мя функция и всё.
         */

    }

    static Integer[] checkArray(Integer array[]) {
        ArrayList<Integer> tmpArray = new ArrayList<Integer>();
        int counter = 0;
        int i;
        for (i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                counter++;
                break;
            }
            tmpArray.add(array[i]);
        }
        if(i == array.length - 1){
            return array;    //так как нету услвия, что делать если 4а последняя, то оставил за собой право, как поступить. Просто возвращаю весь массив
        }else if (counter > 0) {
            Collections.reverse(tmpArray);
            return tmpArray.toArray(new Integer[tmpArray.size()]);
        } else throw new RuntimeException();
    }
     static boolean hasItOneOrFour(Integer[] array){
        boolean flagOne = false;
        boolean flagFour = false;

        for (Integer element : array) {
           if(element == 1){
               flagOne = true;
           }else if(element == 4){
               flagFour = true;
           }if(flagFour && flagOne){
               return true;
            }
        }
        return flagFour & flagOne;
    }
}
