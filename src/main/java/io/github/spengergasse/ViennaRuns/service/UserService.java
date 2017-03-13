package io.github.spengergasse.ViennaRuns.service;

import io.github.spengergasse.ViennaRuns.domain.User;
import io.github.spengergasse.ViennaRuns.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> createUser(@NotNull String name, @NotNull String password) {
        return Optional.ofNullable(userRepository.save(new User(name, password)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // TODO: user stories should be fulfilled here (user registers)
}