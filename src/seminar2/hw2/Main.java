package seminar2.hw2;

public class Main {
    public static void main(String[] args) {
        MyGenericClass<Integer> digits = new MyGenericClass<>(10, 20);
        MyGenericClass<String> strings = new MyGenericClass<>("10", "20");

        System.out.println(digits + " = " + digits.sum());
        System.out.println(strings + " = " + strings.sum());
    }
}
