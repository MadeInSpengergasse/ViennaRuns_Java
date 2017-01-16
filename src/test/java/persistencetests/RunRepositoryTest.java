package persistencetests;

import domain.FeelingAfterRun;
import domain.Run;
import domain.User;
import org.junit.Assert;
import org.junit.Test;
import persistence.FeelingAfterRunJdbcRepository;
import persistence.RunJdbcRepository;
import persistence.UserJdbcRepository;
import service.ServiceBase;

import java.sql.Connection;
import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by Jan Wadsak on 11/12/2016.
 */
public class RunRepositoryTest {

    private ServiceBase serviceBase = new ServiceBase();

    @Test
    public void testInsert() throws Exception {
        UserJdbcRepository u_repo = new UserJdbcRepository();
        FeelingAfterRunJdbcRepository f_repo = new FeelingAfterRunJdbcRepository();
        RunJdbcRepository r_repo = new RunJdbcRepository();
        Connection db = serviceBase.getTestDb();

        User u = new User(
                "inserted_name",
                "inserted_password");

        FeelingAfterRun f = new FeelingAfterRun("tobeinserted");

        Run r = new Run(u, 100f, 100, Date.valueOf("2016-09-09"), f);

        u_repo.insert(db, u);
        f_repo.insert(db, f);
        r_repo.insert(db, r);

        Assert.assertNotNull(r_repo.findById(db, r.getId()));
    }
/*
    @Test
    public void testUpdate() throws Exception {
        UserJdbcRepository repo = new UserJdbcRepository();
        Connection db = serviceBase.getTestDb();

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
        Connection db = serviceBase.getTestDb();

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

    @Test
    public void testFindByNameLike() throws Exception {
        UserJdbcRepository repo = new UserJdbcRepository();
        Connection db = serviceBase.getTestDb();

        User u = new User(
                "usertobefound",
                "somepassword");

        repo.insert(db, u);

        assertFalse(repo.findByNameLike(db, "tobefound").isEmpty());
        assertTrue(repo.findByNameLike(db, "nonexistinguser").isEmpty());
    }
    */
}
