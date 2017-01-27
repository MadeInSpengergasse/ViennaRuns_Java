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

    @Test
    public void testUpdate() throws Exception {
        RunJdbcRepository r_repo = new RunJdbcRepository();
        UserJdbcRepository u_repo = new UserJdbcRepository();
        FeelingAfterRunJdbcRepository f_repo = new FeelingAfterRunJdbcRepository();

        Connection db = serviceBase.getTestDb();

        User u = new User(
                "inserted_name",
                "inserted_password");

        FeelingAfterRun f = new FeelingAfterRun("tobeinserted");

        Run r = new Run(u, 100f, 100, Date.valueOf("2016-09-09"), f);

        u_repo.insert(db, u);
        f_repo.insert(db, f);
        r_repo.insert(db, r);
        Long oldId = r.getId();

        r.setDate(Date.valueOf("2016-09-10"));
        r.setDistance(11.3f);
        r.setDuration(45);
        r.setFeeling(f);

        r_repo.update(db, r);

        Run r2 = r_repo.findById(db, r.getId()).get();

        assertEquals(oldId, r2.getId());

        assertEquals(r.getDistance(), r2.getDistance());
        assertEquals(r.getDate(), r2.getDate());
    }

    @Test
    public void testDelete() throws Exception {
        RunJdbcRepository repo_r = new RunJdbcRepository();
        UserJdbcRepository repo_u = new UserJdbcRepository();
        FeelingAfterRunJdbcRepository repo_f = new FeelingAfterRunJdbcRepository();

        Connection db = serviceBase.getTestDb();

        User u = new User("naame", "paassword");
        FeelingAfterRun f = new FeelingAfterRun("horrible!");
        Run r = new Run(u, 2000f, 200, Date.valueOf("2016-09-05"), f);

        repo_u.insert(db, u);
        repo_f.insert(db, f);
        repo_r.insert(db, r);
        // if present
        assertTrue(repo_r.findById(db, r.getId()).isPresent());

        repo_r.delete(db, r);
        //if not present anymore
        assertFalse(repo_r.findById(db, r.getId()).isPresent());
    }
}