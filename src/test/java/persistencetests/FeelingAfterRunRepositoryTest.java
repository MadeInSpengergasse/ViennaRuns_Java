package persistencetests;

import org.junit.Assert;
import org.junit.Test;
import viennaruns.domain.FeelingAfterRun;
import viennaruns.persistence.FeelingAfterRunJdbcRepository;
import viennaruns.service.ServiceBase;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by Jan Wadsak on 11/12/2016.
 */
public class FeelingAfterRunRepositoryTest {

    private ServiceBase serviceBase = new ServiceBase();

    @Test
    public void testInsert() throws Exception {
        FeelingAfterRunJdbcRepository repo = new FeelingAfterRunJdbcRepository();
        Connection db = serviceBase.getTestDb();

        FeelingAfterRun f = new FeelingAfterRun("tobeinserted");

        repo.insert(db, f);

        Assert.assertNotNull(repo.findById(db, f.getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        FeelingAfterRunJdbcRepository repo = new FeelingAfterRunJdbcRepository();
        Connection db = serviceBase.getTestDb();

        FeelingAfterRun f = new FeelingAfterRun("shouldbeupdated");

        repo.insert(db, f);
        Long oldId = f.getId();
        f.setFeeling("updatedfeeling");

        repo.update(db, f);

        FeelingAfterRun f2 = repo.findById(db, f.getId()).get();

        assertEquals(oldId, f2.getId());

        assertEquals(f.getFeeling(), f2.getFeeling());
    }

    @Test
    public void testDelete() throws Exception {
        FeelingAfterRunJdbcRepository repo = new FeelingAfterRunJdbcRepository();
        Connection db = serviceBase.getTestDb();

        FeelingAfterRun f = new FeelingAfterRun("shouldbedeleted");

        repo.insert(db, f);
        // if present
        assertTrue(repo.findById(db, f.getId()).isPresent());

        repo.delete(db, f);
        //if not present anymore
        assertFalse(repo.findById(db, f.getId()).isPresent());
    }
}
