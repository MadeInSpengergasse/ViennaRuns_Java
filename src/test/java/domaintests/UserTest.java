package domaintests;

import domains.User;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class UserTest {

    @Test
    public void getWorks() {
        long id = 1L;
        int version = 1;
        String name = "a";
        String password = "b";
        User user = new User(id, version, name, password);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getVersion()).isEqualTo(version);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void setWorks() {
        long id = 1L;
        int version = 1;
        String name = "a";
        String password = "b";
        User user = new User(id, version, name, password);

        long newid = 2L;
        int newversion = 2;
        String newname = "c";
        String newpassword = "d";

        user.setId(newid);
        user.setVersion(newversion);
        user.setName(newname);
        user.setPassword(newpassword);

        assertThat(user.getId()).isEqualTo(newid);
        assertThat(user.getVersion()).isEqualTo(newversion);
        assertThat(user.getName()).isEqualTo(newname);
        assertThat(user.getPassword()).isEqualTo(newpassword);
    }

    @Test
    public void compareToWorks() {
        User user1 = new User(1L, 1, "a", "b");
        User user2 = new User(1L, 1, "a", "b");
        User user3 = new User(2L, 1, "a", "b");

        assertTrue(user1.compareTo(user2) == 0);
        assertTrue(user2.compareTo(user1) == 0);
        assertFalse(user1.compareTo(user3) == 0);
        assertFalse(user3.compareTo(user2) == 0);
    }
}
