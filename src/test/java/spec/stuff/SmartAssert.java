package spec.stuff;

import org.junit.Assert;
import org.junit.internal.runners.model.MultipleFailureException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Где стоит использовать этот способ проверки?
 *
 * В простых юнит тестах, которые ранаются быстро, особенно
 * если там используется approvals подход с
 * assertEquals("expected data", actual.toString()) нет
 * надобности в SmartAssert. А вот если тест интеграционный
 * спринговый, рест например, когда время его выполнения десятки
 * секунд, когда в тесте несколько assert проверок таких, что их
 * нельзя объединить (approvals подходом в одну) - то лучше
 * использовать SmartAssert.
 *
 * SmartAssert в каждом своем assertEquals накапливает
 * возражения, а потом в tearDown теста методом
 * SmartAssert.checkResult() делается проверка и слетают
 * все "expected but was actual" сообщения.
 */
public class SmartAssert {

    private static List<AssertionError> failures;

    static {
        setup();
    }

    public static void setup() {
        failures = new CopyOnWriteArrayList<>();
    }

    public static void assertEquals(Object expected, Object actual) {
        failNotInitialized();
        try {
            Assert.assertEquals(expected, actual);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    private static void failNotInitialized() {
        if (failures == null) {
            Assert.fail("Do not use SmartAssert.assertEquals() after tearDown(). Use SmartAssert.setup() in setup().");
        }
    }

    public static void fail(String message) {
        failNotInitialized();
        try {
            Assert.fail(message);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    public static void checkResult() throws Exception {
        List<AssertionError> toProcess = failures;
        failures = null;
        if (toProcess.isEmpty()) return;

        List<Throwable> errors = new LinkedList<>(toProcess);
        throw new MultipleFailureException(errors);
    }
}