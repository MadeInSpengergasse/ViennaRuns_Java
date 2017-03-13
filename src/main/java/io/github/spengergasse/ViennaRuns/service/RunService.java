package io.github.spengergasse.ViennaRuns.service;

import io.github.spengergasse.ViennaRuns.domain.FeelingAfterRun;
import io.github.spengergasse.ViennaRuns.domain.Run;
import io.github.spengergasse.ViennaRuns.domain.User;
import io.github.spengergasse.ViennaRuns.persistence.RunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(readOnly = true)
public class RunService {

    private final RunRepository runRepository;


    public Optional<Run> createRun(User u, float distance, Duration duration, LocalDate date, FeelingAfterRun far) {
        Run r = new Run(distance, duration, date, u, far);

        return Optional.ofNullable(runRepository.save(r));
    }
}
