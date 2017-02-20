package io.github.spengergasse.ViennaRuns.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Run")
@NoArgsConstructor
@RequiredArgsConstructor
public class Run extends BaseDomain<Run, Long> {

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Column(name = "distance")
    private Float distance;

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Column(name = "duration")
    private Integer duration;

    @Getter
    @Setter
    @NonNull
    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    private User user;

    @ManyToOne
    private FeelingAfterRun feeling;

    @Override
    public int compareTo(Run o) {
        return 0; //FIXME
    }
}
