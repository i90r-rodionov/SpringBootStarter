package org.example.starter.lib.idempotent.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "IDEMPOTENT_RECORD")
@Getter
@Setter
public class IdempotentRecord extends BaseEntity {

    @Column(nullable = false)
    private String idempotentKey;

    @Column(nullable = false)
    private String requestType;

    @Column(nullable = false)
    @Lob
    private String requestObject;

    @Column
    private String responseType;

    @Column
    @Lob
    private String responseObject;

}
