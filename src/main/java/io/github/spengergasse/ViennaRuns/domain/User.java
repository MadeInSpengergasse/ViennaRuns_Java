package io.github.spengergasse.ViennaRuns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User extends BaseDomain<User, Long> {

    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name")
    private String name;

    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "password")
    private String password;

    @Override
    public int compareTo(User o) {
        return super.getId().compareTo(o.getId());
    }
}
