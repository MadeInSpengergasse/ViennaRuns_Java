package service;

import domain.User;
import persistence.UserJdbcRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by lukas on 1/16/17.
 */
public class UserDetailsService extends ServiceBase {
    private final UserJdbcRepository userrep;

    public UserDetailsService(UserJdbcRepository repository) {
        this.userrep = repository;
    }

    public Optional<User> findUser(long id) throws Exception {
        return userrep.findById(getDb(), id);
    }

    public List<User> findAll() throws Exception {
        return userrep.findAll(getDb());
    }
}
