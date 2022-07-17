package org.example.starter.lib.idempotent.domain.repository.jpa;

import org.example.starter.lib.idempotent.domain.entity.IdempotentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdempotentRepository extends JpaRepository<IdempotentRecord, UUID> {
    Optional<IdempotentRecord> findByIdempotentKey(String idempotentKey);
}
