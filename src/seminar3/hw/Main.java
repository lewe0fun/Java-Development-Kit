package seminar3.hw;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /**
         * 1. Внедрить итератор из задания 2 в коллекцию, написанную в задании 3 таким образом,
         чтобы итератор был внутренним классом и, соответственно, объектом в коллекции.
         */
        System.out.println("---Задание #1---------");
        GenericCollection<Integer> collection2 = new GenericCollection<>(Arrays.asList(10, 12, 13, 65));
        collection2.forEachPrint();
/**
 * 2. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract(). Параметры этих методов – два
 *       числа разного типа, над которыми должна быть произведена операция.
 */
        System.out.println("\n---Задание #2---------");
        System.out.println(Calculator.sum(62.0, 56f));
        System.out.println(Calculator.multiply(62.0, 56));
        System.out.println(Calculator.divide(62.0, 56.0));
        System.out.println(Calculator.subtract(62, 56));

/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
 * и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
 */
        System.out.println("\n---Задание #3---------");
        String[] strings1 = new String[]{"werwer", "werwerwr", "12"};
        String[] strings2 = new String[]{"654", "fhy", "12"};
        String[] strings3 = new String[]{"werwer", "werwerwr", "12"};
        Integer[] integers1 =new Integer[]{12,65,998,78};
        Integer[] integers2 =new Integer[]{12,65,998,78};
        ArrayComparator.compareNPrint(strings1,strings2);
        ArrayComparator.compareNPrint(strings1,strings3);
        ArrayComparator.compareNPrint(integers1,integers2);
    }
}
