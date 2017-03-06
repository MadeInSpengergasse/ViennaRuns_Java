package io.github.spengergasse.ViennaRuns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "run")
@NoArgsConstructor
@RequiredArgsConstructor
public class Run extends BaseDomain<Run, Long> {

    @NonNull
    @NotNull
    @Column(name = "distance")
    private Float distance;

    @NonNull
    @NotNull
    @Column(name = "duration")
    private Duration duration;

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
        return date.compareTo(o.getDate());
    }
}
