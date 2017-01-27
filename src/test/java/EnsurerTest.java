import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import viennaruns.foundation.Ensurer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
        ex.expectMessage(Matchers.is("testtag is null"));

        Ensurer.IsNotNull(null, "testtag");
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
