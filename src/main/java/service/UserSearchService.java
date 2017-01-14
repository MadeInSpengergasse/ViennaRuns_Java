package service;

import domain.User;
import persistence.UserJdbcRepository;

import java.util.List;
import java.util.Optional;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class UserSearchService extends ServiceBase {
    public static final String ASTERISK = "*";

    private final UserJdbcRepository userRepository;

    public UserSearchService(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * null and empty strings will be mapped to wildcard * search
     *
     * @param searchString
     * @return
     */
    public List<User> search(String searchString) {
        String safeSearchString = Optional.ofNullable(searchString)
                .filter(s -> !s.trim().isEmpty())
                .orElse(ASTERISK);
        try {
            return (ASTERISK.equals(safeSearchString))
                    ? userRepository.findAll(getDb())
                    : userRepository.findByNameLike(getDb(), safeSearchString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
