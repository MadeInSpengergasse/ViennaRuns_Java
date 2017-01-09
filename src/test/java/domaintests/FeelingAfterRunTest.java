package domaintests;

import domain.FeelingAfterRun;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class FeelingAfterRunTest {

    @Test
    public void getWorks() {
        long id = 1L;
        int version = 1;
        String feeling = "hello";
        FeelingAfterRun feelingAfterRun = new FeelingAfterRun(id, version, feeling);

        assertThat(feelingAfterRun.getId()).isEqualTo(id);
        assertThat(feelingAfterRun.getVersion()).isEqualTo(version);
        assertThat(feelingAfterRun.getFeeling()).isEqualTo(feeling);
    }

    @Test
    public void setWorks() {
        long id = 1L;
        int version = 1;
        String feeling = "hello";
        FeelingAfterRun feelingAfterRun = new FeelingAfterRun(id, version, feeling);

        long newid = 2L;
        int newversion = 2;
        String newfeeling = "likeonacloud";

        feelingAfterRun.setId(newid);
        feelingAfterRun.setVersion(newversion);
        feelingAfterRun.setFeeling(newfeeling);

        assertThat(feelingAfterRun.getId()).isEqualTo(newid);
        assertThat(feelingAfterRun.getVersion()).isEqualTo(newversion);
        assertThat(feelingAfterRun.getFeeling()).isEqualTo(newfeeling);
    }

    @Test
    public void compareToWorks() {
        FeelingAfterRun feeling1 = new FeelingAfterRun(1L, 1, "a");
        FeelingAfterRun feeling2 = new FeelingAfterRun(1L, 1, "a");
        FeelingAfterRun feeling3 = new FeelingAfterRun(2L, 2, "b");

        assertTrue(feeling1.compareTo(feeling2) == 0);
        assertTrue(feeling2.compareTo(feeling1) == 0);
        assertFalse(feeling1.compareTo(feeling3) == 0);
        assertFalse(feeling3.compareTo(feeling2) == 0);
    }
}
