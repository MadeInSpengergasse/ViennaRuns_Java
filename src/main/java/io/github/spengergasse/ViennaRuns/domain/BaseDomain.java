package io.github.spengergasse.ViennaRuns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseDomain<DOMAIN_TYPE extends BaseDomain, PK_TYPE extends Number> extends AbstractPersistable<PK_TYPE> implements Comparable<DOMAIN_TYPE> {
    @Version
    private Integer version;
}
