package org.example.starter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "TEST_ENTITY")
@Getter
@Setter
public class TestEntity extends BaseEntity {
    @Column
    private String number;
    @Column
    private String data;
}
