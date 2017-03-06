package io.github.spengergasse.ViennaRuns.persistence;


import io.github.spengergasse.ViennaRuns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    List<User> findAll();

    Optional<User> findByName(String name);

    List<User> findByNameLike(String like);
}
