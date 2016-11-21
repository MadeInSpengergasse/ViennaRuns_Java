import foundation.Ensurer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

/**
 * Created by lukas on 11/7/16.
 */
@RunWith(JUnitParamsRunner.class)
public class EnsurerTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void isNotNullBehaviourOnNullValue() {
        //expect
        Ensurer.IsNotNull(null, "");
    }

    @Test
    public void isNotNullBehaviourOnNullValueOldStyle() {
        // given
        String objectName = "basicobject";

        try {
            Ensurer.IsNotNull(null, objectName);

            fail("should not get to this point");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage()).contains(objectName);

        }
    }

    @Test
    public void isNotNullBehaviourOnNullValueImproved() {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage("arg");
        ex.expectMessage(is(equalTo("arg.l shall not be null")));

        Ensurer.IsNotNull(null, "");
    }

    @Test
    public void isNotNullBehaviourOnNonNullValue() {
        String in = "text";

        String res = Ensurer.IsNotNull(in, "IN");

        assertThat(res).isNotNull().isSameAs(in);
    }

    @Test
    @Parameters(method = "notBlankValues")
    public void isNotBlankBehaviour(String in, Optional<Class> exceptionClass) {

        String objectName = "input";
        if (exceptionClass.isPresent()) {
            ex.expect(exceptionClass.get());
            ex.expectMessage(objectName);
        }

        String res = Ensurer.IsNotBlank(in, objectName);

        assertThat(res).isNotNull().isSameAs(in);
    }

    private Object[] notBlankValues() {
        Optional<Class<IllegalArgumentException>> illegalArgEx = Optional.of
                (IllegalArgumentException.class);
        return new Object[]{
                new Object[]{null, illegalArgEx},
                new Object[]{"", illegalArgEx},
                new Object[]{" ", illegalArgEx},
                new Object[]{" ", illegalArgEx},
                new Object[]{"\t", illegalArgEx},
                new Object[]{"\r", illegalArgEx},
                new Object[]{"\n", illegalArgEx},
                new Object[]{"abc", Optional.empty()}
        };
    }
}