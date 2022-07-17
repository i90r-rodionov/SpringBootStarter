package org.example.starter.domain.repository.jpa;

import org.example.starter.domain.entity.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends BaseRepository<TestEntity> {
}
