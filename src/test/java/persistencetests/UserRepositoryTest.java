package persistencetests;

import domain.User;
import org.junit.Assert;
import org.junit.Test;
import persistence.UserJdbcRepository;
import service.ServiceBase;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jan Wadsak on 11/12/2016.
 */
public class UserRepositoryTest {

    private ServiceBase serviceBase = new ServiceBase();

    @Test
    public void testInsert() throws Exception {
        UserJdbcRepository repo = new UserJdbcRepository();
        Connection db = serviceBase.getDb();

        User u = new User(
                "inserted_name",
                "inserted_password");

        repo.insert(db, u);

        Assert.assertNotNull(repo.findById(db, u.getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        UserJdbcRepository repo = new UserJdbcRepository();
        Connection db = serviceBase.getDb();

        User u = new User(
                "shouldbeupdated",
                "shouldbeupdated_password");

        repo.insert(db, u);
        Long oldId = u.getId();
        u.setName("updatedname");
        u.setPassword("updatedpassword");

        repo.update(db, u);

        User u2 = repo.findById(db, u.getId()).get();

        assertEquals(oldId, u2.getId());

        assertEquals(u.getName(), u2.getName());
        assertEquals(u.getPassword(), u2.getPassword());
    }

    @Test
    public void testDelete() throws Exception {
        UserJdbcRepository repo = new UserJdbcRepository();
        Connection db = serviceBase.getDb();

        User u = new User(
                "shouldbedeleted",
                "shouldbedeleted_password");

        repo.insert(db, u);
        // if present
        assertTrue(repo.findById(db, u.getId()).isPresent());

        repo.delete(db, u);
        //if not present anymore
        assertFalse(repo.findById(db, u.getId()).isPresent());
    }
}
