import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by sangeshi on 2/4/2015.
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(testsuit.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
