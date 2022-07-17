package org.example.starter.lib.idempotent.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "platform")
    @GenericGenerator(
            name = "platform",
            parameters = @Parameter(name = "prefix", value = "prod"),
            strategy = "org.example.starter.lib.idempotent.domain.generator.IdGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime lastChangeDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    @Version
    private Long version;

    @PrePersist
    void onCreate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        setCreateDate(currentDateTime);
        setLastChangeDate(currentDateTime);
        setIsDeleted(Boolean.FALSE);
    }

    @PreUpdate
    void onUpdate() {
        this.setLastChangeDate(LocalDateTime.now());
    }

    public boolean isPersisted() {
        return Objects.nonNull(id);
    }

    @Override
    public String toString() {
        return String.format("[%s(id=%s)]", getClass().getSimpleName(), id);
    }

}
