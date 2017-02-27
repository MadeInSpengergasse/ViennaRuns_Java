package io.github.spengergasse.ViennaRuns.service;

import io.github.spengergasse.ViennaRuns.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(readOnly = true)
public class UserSearchService {

    private final UserRepository userRepository;

    // TODO: user stories should be fulfilled here (user registers)
}