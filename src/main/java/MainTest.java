import com.rat.squad.storage.Calculator;

import javax.mail.MessagingException;

public class MainTest {
    public static void main(String[] args) throws MessagingException {
        Calculator calculator = new Calculator();
        calculator.setOperation("");
        calculator.calculate(1.1, 2.2);
    }
}
