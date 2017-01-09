package domaintests;

import domain.FeelingAfterRun;
import domain.Run;
import domain.User;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class RunTest {

    @Test
    public void getWorks() {
        long id = 1L;
        int version = 1;
        String name = "a";
        String password = "b";
        User user = new User(id, version, name, password);
        float distance = 10f;
        int duration = 100;
        Date date = Date.valueOf("2016-09-09");
        FeelingAfterRun feeling = new FeelingAfterRun(id, version, "Somestring");
        Run run = new Run(id, version, user, distance, duration, date, feeling);

        assertThat(run.getId()).isEqualTo(id);
        assertThat(run.getVersion()).isEqualTo(version);
        assertThat(run.getUser()).isEqualTo(user);
        assertThat(run.getDistance()).isEqualTo(distance);
        assertThat(run.getDuration()).isEqualTo(duration);
        assertThat(run.getDate()).isEqualTo(date);
        assertThat(run.getFeeling()).isEqualTo(feeling);
    }

    @Test
    public void setWorks() {
        long id = 1L;
        int version = 1;
        String name = "a";
        String password = "b";
        User user = new User(id, version, name, password);
        float distance = 10f;
        int duration = 100;
        Date date = Date.valueOf("2016-09-09");
        FeelingAfterRun feeling = new FeelingAfterRun(id, version, "Somestring");
        Run run = new Run(id, version, user, distance, duration, date, feeling);


        long newid = 2L;
        int newversion = 2;
        String newname = "c";
        String newpassword = "d";
        User newuser = new User(newid, newversion, newname, newpassword);
        float newdistance = 20f;
        int newduration = 200;
        Date newdate = Date.valueOf("2016-12-09");
        FeelingAfterRun newfeeling = new FeelingAfterRun(newid, newversion, "Somestring2");

        run.setId(newid);
        run.setVersion(newversion);
        run.setUser(newuser);
        run.setDistance(newdistance);
        run.setDuration(newduration);
        run.setDate(newdate);
        run.setFeeling(newfeeling);

        assertThat(run.getId()).isEqualTo(newid);
        assertThat(run.getVersion()).isEqualTo(newversion);
        assertThat(run.getUser()).isEqualTo(newuser);
        assertThat(run.getDistance()).isEqualTo(newdistance);
        assertThat(run.getDuration()).isEqualTo(newduration);
        assertThat(run.getDate()).isEqualTo(newdate);
        assertThat(run.getFeeling()).isEqualTo(newfeeling);
    }

    @Test
    public void compareToWorks() {
        Run run1 = new Run(1L, 1, new User(2L, 2, "name", "password"), 100f, 10, Date.valueOf("2017-09-09"), new FeelingAfterRun(3L, 3, "something"));
        Run run2 = new Run(1L, 1, new User(2L, 2, "name", "password"), 100f, 10, Date.valueOf("2017-09-09"), new FeelingAfterRun(3L, 3, "something"));
        Run run3 = new Run(2L, 1, new User(2L, 2, "name", "password"), 100f, 10, Date.valueOf("2017-09-09"), new FeelingAfterRun(3L, 3, "something"));

        assertTrue(run1.compareTo(run2) == 0);
        assertTrue(run2.compareTo(run1) == 0);
        assertFalse(run1.compareTo(run3) == 0);
        assertFalse(run3.compareTo(run2) == 0);
    }
}
