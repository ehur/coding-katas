package lambda;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExamples {

    public int add(int a,int b) {
        return a + b;
    }

    public static int mult(int a, int b){
        return a* b;
    }

    public void oper(IntBinaryOperator operator, int a, int b) {
        System.out.println(operator.applyAsInt(a,b));
    }

    public void operS(Function<String,String> stringOperator, String a) {
        System.out.println(stringOperator.apply(a));
    }

    public GregorianCalendar operC(Supplier<GregorianCalendar> supplier) {
        return supplier.get();
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n: list) {
            if (predicate.test(n))
                System.out.println(n);
        }
    }

//    public void operF(Function<(List<Integer>, Predicate<Integer>), T>)
}
