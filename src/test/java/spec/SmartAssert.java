package spec;

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

    private static List<AssertionError> failures = new CopyOnWriteArrayList<>();

    public static void assertEquals(String message, Object expected, Object actual) {
        try {
            Assert.assertEquals(message, expected, actual);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    public static void assertNotEquals(Object expected, Object actual) {
        try {
            Assert.assertNotEquals(expected, actual);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        try {
            Assert.assertEquals(expected, actual);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    public static void assertSame(Object object1, Object object2) {
        try {
            Assert.assertSame(object1, object2);
        } catch (AssertionError e) {
            failures.add(e);
        }
    }

    public static void checkResult() {
        if (failures.isEmpty()) return;

        List<Throwable> errors = new LinkedList<>(failures);
        failures.clear();
        throw new RuntimeException(new MultipleFailureException(errors));
    }
}