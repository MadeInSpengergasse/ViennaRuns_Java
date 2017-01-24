package viennaruns.service;

import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;

/**
 * Created by lukas on 1/16/17.
 */
public class UserAddService extends ServiceBase {
    private final UserJdbcRepository userrep;

    public UserAddService(UserJdbcRepository repository) {
        this.userrep = repository;
    }

    public void createUser(String name, String password) {
        if (!(name.equals("") || password.equals(""))) {
            userrep.insert(getDb(), new User(name, password));
        } else {
            System.out.println("ERROR");
        }
    }
}
