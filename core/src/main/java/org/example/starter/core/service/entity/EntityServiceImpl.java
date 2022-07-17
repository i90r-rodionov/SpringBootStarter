package org.example.starter.core.service.entity;

import org.example.starter.api.dto.RequestDto;
import org.example.starter.domain.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EntityServiceImpl implements EntityService {

    @Override
    public TestEntity create(RequestDto dto) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(UUID.randomUUID());
        testEntity.setData("bla-bla-bla");
        return testEntity;
    }
}
