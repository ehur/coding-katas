import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LambdaTutorial {

    @Test
    public void shouldDoWithOrWithoutTypeDeclaration(){
        MathOperation addition = (int a, int b) -> a+b;
        MathOperation subtraction = (a, b) -> a - b;
        int additionResult = addition.doStuff(1,4);
        int subtractionResult = subtraction.doStuff(6,2);
        assertEquals(5,additionResult);
        assertEquals(4,subtractionResult);
    }

    int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.doStuff(a, b);
    }

    interface MathOperation {
        int doStuff(int a, int b);
    }
}
