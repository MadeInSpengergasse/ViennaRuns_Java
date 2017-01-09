package persistencetests;

import domain.User;
import org.junit.Assert;
import persistence.UserJdbcRepository;
import service.ServiceBase;

import java.sql.Connection;

/**
 * Created by Jan Wadsak on 11/12/2016.
 */
public class CreateUpdateDeleteIT {
    private void createUpdateDeleteIT() {
        UserJdbcRepository repo = new UserJdbcRepository();
        try {
            ServiceBase serviceBase = new ServiceBase();
            Connection db = serviceBase.getDb();

            User u = new User(
                    1L,
                    1,
                    "name",
                    "password");

            repo.insert(db, u);

            Assert.assertEquals(repo.findById(db, u.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
