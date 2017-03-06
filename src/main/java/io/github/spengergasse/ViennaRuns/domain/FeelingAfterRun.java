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
@Table(name = "feelingafterrun")
@NoArgsConstructor
@RequiredArgsConstructor
public class FeelingAfterRun extends BaseDomain<FeelingAfterRun, Long> {

    @NonNull
    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "feeling")
    private String feeling;

    @Override
    public int compareTo(FeelingAfterRun o) {
        return super.getId().compareTo(o.getId());
    }
}
