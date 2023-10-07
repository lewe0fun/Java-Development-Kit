package seminar2.hw2;

public class MyGenericClass<T> implements Summable{
    private final T value1;
    private final T value2;

    public MyGenericClass(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "(" + value1 + ","+ value2 + ')';
    }
    @Override
    public Object sum() {
        if(value1 instanceof Integer) return (int)value1+(int)value2;
        if(value1 instanceof String)  return value1.toString()+value2.toString();
        return null;
    }

}
