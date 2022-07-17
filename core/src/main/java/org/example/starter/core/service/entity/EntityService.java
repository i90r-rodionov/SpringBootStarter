package org.example.starter.core.service.entity;

import org.example.starter.api.dto.RequestDto;
import org.example.starter.domain.entity.TestEntity;

public interface EntityService {
    TestEntity create(RequestDto dto);
}
