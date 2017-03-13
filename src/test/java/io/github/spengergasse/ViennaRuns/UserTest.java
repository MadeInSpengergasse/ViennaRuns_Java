package io.github.spengergasse.ViennaRuns;

import io.github.spengergasse.ViennaRuns.domain.User;
import io.github.spengergasse.ViennaRuns.persistence.UserRepository;
import io.github.spengergasse.ViennaRuns.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private UserRepository repository;

    private UserService service;

    @Before
    public void setup() {
        assertThat(repository).isNotNull();
        service = new UserService(repository);
    }

    @Test
    public void ensureCreateAlbumThrowsExceptionOnDuplicateName() {
        // given
        String username = "user 1";
        String password = "verysecuremuchwow";
        User user = new User(username, password);
        when(repository.save(user)).thenReturn(user);
    }

    @Test
    public void ensureCreateUserThrowsOnTooShortParameters() {
        // given
        String username = "u";
        String password = "p";
        User user = new User(username, password);
        when(repository.save(user)).thenReturn(user);
    }

    @Test
    public void ensureToFindABetterName() {
        String username = "user 1";
        String password = "verysecuremuchwow";
// doesn't work like this, change!
//        User b = repository.save(new User(username,password));
//        Optional<User> a = repository.findByName(username);
//        System.out.println();

    }

}
