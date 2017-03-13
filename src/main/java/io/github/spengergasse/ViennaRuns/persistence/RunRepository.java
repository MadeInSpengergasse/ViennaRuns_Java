package io.github.spengergasse.ViennaRuns.persistence;


import io.github.spengergasse.ViennaRuns.domain.Run;
import io.github.spengergasse.ViennaRuns.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
    Optional<Run> findById(Long id);

    List<Run> findAll();

    Optional<Run> findByUser(User u);
}
