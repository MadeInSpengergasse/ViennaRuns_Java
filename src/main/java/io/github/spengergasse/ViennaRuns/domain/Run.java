package io.github.spengergasse.ViennaRuns.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "run")
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
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "feeling")
    private FeelingAfterRun feeling;

    @Override
    public int compareTo(Run o) {
        return 0; //FIXME
    }
}
