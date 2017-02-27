package io.github.spengergasse.ViennaRuns.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "feelingafterrun")
@NoArgsConstructor
@RequiredArgsConstructor
public class FeelingAfterRun extends BaseDomain<FeelingAfterRun, Long> {

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "feeling")
    private String feeling;

    @Override
    public int compareTo(FeelingAfterRun o) {
        return 0; //FIXME
    }
}
