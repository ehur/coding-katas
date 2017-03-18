import lambda.LambdaExamples;
import org.junit.Test;
import sun.util.calendar.Gregorian;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
/* Remember: Method References should be assignable to a FunctionalInterface */

public class LambdaTest {

    static final LambdaExamples ex = new LambdaExamples();

    @Test
    public void testLambdaFormStatic(){
        System.out.println("1. lambda form....");
        ex.oper((a,b) -> LambdaExamples.mult(a, b),1,1);
        ex.oper((a,b) -> ex.mult(a,b),1,1);
    }

    @Test
    public void testMethodReferenceStatic() {
        IntBinaryOperator thing = LambdaExamples::mult;
        System.out.println("2. method ref form....");
        ex.oper(LambdaExamples::mult,1,2);
        ex.oper(thing,1,2);
    }

    @Test
    public void testLambdaFormInstance() {
        System.out.println("3. lambda form Instance ...");
        ex.oper((a,b) -> ex.add(a,b),1,2);
    }

    @Test
    public void testMethodReferenceInstance() {
        System.out.println("4. method ref Instance ....");
        ex.oper(ex::add,1,2);
    }

    @Test
    public void testInstanceMethodUsingClassNameLambda() {
        System.out.println("5. instance method using class name lambda....");
        ex.operS(a -> a.toLowerCase(), "EjKrKe");
    }

    @Test
    public void testInstanceMethodUsingClassNameMethodRef() {
        System.out.println("6. instance method using class name method ref....");
        ex.operS(String::toLowerCase,"$RTYUU");
    }

    @Test
    public void testConstructorLambda() {
        System.out.println("7. constructor lambda ....");
        GregorianCalendar calendar = ex.operC(() -> new GregorianCalendar());
        System.out.println(calendar.toZonedDateTime());
    }

    @Test
    public void testConstructorMethodRef(){
        System.out.println("8. constructor method ref ....");
        GregorianCalendar calendar = ex.operC(GregorianCalendar::new);
        System.out.println(calendar.toZonedDateTime());
    }

    @Test
    public void forEachLambdaVsDoubleColonExample() {
        List<Integer> theList = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("...... lambda forEach....");
        theList.forEach(n -> System.out.println(n));
        System.out.println("...... method ref forEach....");
        theList.forEach(System.out::println);
    }

    @Test
    public void predicateExamples(){
        List<Integer> theList = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println("print all -->");
        LambdaExamples.evaluate(theList,(n) -> true);
    }

    public void methodReferenceAgain(){
        PrintStream out = System.out;
        calculateAndDo(System.out::println);
        calculateAndDo(out::println);
    }

    private void calculateAndDo(Consumer<String> consumer) {
        consumer.accept("4");
    }


}
