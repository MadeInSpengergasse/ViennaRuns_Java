package io.github.spengergasse.ViennaRuns.persistence;


import io.github.spengergasse.ViennaRuns.domain.FeelingAfterRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeelingAfterRunRepository extends JpaRepository<FeelingAfterRun, Long> {
    Optional<FeelingAfterRun> findById(Long id);

    List<FeelingAfterRun> findAll();

    Optional<FeelingAfterRun> findByFeeling(String f);
}
