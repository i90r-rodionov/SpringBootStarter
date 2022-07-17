package org.example.starter.core.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.starter.api.controller.EntityController;
import org.example.starter.api.dto.RequestDto;
import org.example.starter.api.dto.ResponseDto;
import org.example.starter.core.service.entity.EntityService;
import org.example.starter.domain.entity.TestEntity;
import org.example.starter.lib.idempotent.annotation.IdempotentId;
import org.example.starter.lib.idempotent.annotation.IdempotentRequestPayload;
import org.example.starter.lib.idempotent.annotation.IdempotentResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class EntityControllerImpl implements EntityController {

    private final EntityService service;

    @PostMapping("entity")
    @ResponseStatus(HttpStatus.CREATED)
    @IdempotentResource
    @Override
    public ResponseDto create(@RequestHeader("X-Request-ID") @IdempotentId String rqId,
                              @RequestBody @IdempotentRequestPayload RequestDto requestDto) {
        TestEntity testEntity = service.create(requestDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(testEntity.getId().toString());
        responseDto.setData(testEntity.getData());
        return responseDto;
    }
}
