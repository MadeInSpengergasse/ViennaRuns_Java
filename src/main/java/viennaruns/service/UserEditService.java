package viennaruns.service;

import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;

import java.util.Optional;

/**
 * Created by lukas on 1/24/17.
 */
public class UserEditService extends  ServiceBase{

    private final UserJdbcRepository userrep;

    public UserEditService(UserJdbcRepository repository) {
        this.userrep = repository;
    }

    public Optional<User> findUser(long id) throws Exception {
        return userrep.findById(getDb(), id);
    }

    public void updateUser (User u){
        userrep.update(getDb(), u);
    }

}

