package org.example.starter.domain.repository.jpa;

import org.example.starter.domain.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {
}
