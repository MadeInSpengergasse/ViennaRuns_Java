package viennaruns.service;

import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;

import java.util.Optional;

/**
 * Created by lukas on 1/23/17.
 */
public class UserDeleteService extends ServiceBase {
    private final UserJdbcRepository userrep;

    public UserDeleteService(UserJdbcRepository repository) {
        this.userrep = repository;
    }

    public void deleteUser(long id) {
        userrep.delete(getDb(), id);
    }

    public Optional<User> findUser(long id) throws Exception {
        return userrep.findById(getDb(), id);
    }
}
