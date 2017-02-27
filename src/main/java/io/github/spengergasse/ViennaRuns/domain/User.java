package io.github.spengergasse.ViennaRuns.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User extends BaseDomain<User, Long> {

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "password")
    private String password;

    @Override
    public int compareTo(User o) {
        return 0; //FIXME
    }
}
