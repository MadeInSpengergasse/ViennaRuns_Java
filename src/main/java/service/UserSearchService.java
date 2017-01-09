package service;

import domain.FeelingAfterRun;
import domain.Run;
import domain.User;
import persistence.FeelingAfterRunJdbcRepository;
import persistence.RunJdbcRepository;
import persistence.UserJdbcRepository;

import java.sql.Date;
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
        User a = new User(5L, 0, "Lukas", "somepassword");
//        userRepository.insert(getDb(), a);
        FeelingAfterRun b = new FeelingAfterRun(3L, 0, "Great");
//        new FeelingAfterRunJdbcRepository().insert(getDb(), b);
        new RunJdbcRepository().insert(getDb(), new Run(0L, 0, a, 200.5f, 100, Date.valueOf("2017-01-01"), b));
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
