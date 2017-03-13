package io.github.spengergasse.ViennaRuns.service;

import io.github.spengergasse.ViennaRuns.domain.FeelingAfterRun;
import io.github.spengergasse.ViennaRuns.persistence.FeelingAfterRunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(readOnly = true)
public class FeelingAfterRunService {

    private FeelingAfterRunRepository feelingAfterRunRepository;

    public Optional<FeelingAfterRun> createFeeling(String feeling) {
        return Optional.ofNullable(feelingAfterRunRepository.save(new FeelingAfterRun(feeling)));

    }
}
