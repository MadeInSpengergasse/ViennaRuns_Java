package service;

import domain.FeelingAfterRun;
import domain.User;
import persistence.FeelingAfterRunJdbcRepository;
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
        System.out.println("HELLO"); // TODO: Remove System.out.println
//        userRepository.insert(getDb(), new User(0L, 0, "Lukas", "somepassword"));
        new FeelingAfterRunJdbcRepository().insert(getDb(), new FeelingAfterRun(0L, 0, "Great"));
        String safeSearchString = Optional.ofNullable(searchString)
                .filter(s -> !s.trim().isEmpty())
                .orElse(ASTERISK);
        try {
            if (ASTERISK.equals(safeSearchString))
                return userRepository.findAll(getDb());
            //                : userRepository.findByNameLike(safeSearchString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
