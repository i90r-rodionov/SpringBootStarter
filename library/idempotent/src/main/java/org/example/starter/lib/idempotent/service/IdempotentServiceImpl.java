package org.example.starter.lib.idempotent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.starter.lib.idempotent.domain.entity.IdempotentRecord;
import org.example.starter.lib.idempotent.domain.repository.jpa.IdempotentRepository;
import org.example.starter.lib.idempotent.exception.ConcurrentRequestException;
import org.example.starter.lib.idempotent.exception.ModuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdempotentServiceImpl implements IdempotentService {

    private final IdempotentRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveResponse(String key, Object responseObject) {
        // TODO: 16.07.2022 обработка ошибок

        var result = repository.findByIdempotentKey(key);
        if(result.isEmpty()) {
            return;
        }

        var record = result.get();
        record.setResponseType(responseObject.getClass().getName());
        record.setResponseObject(toJson(responseObject));
        repository.save(record);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Object getIdempotentResponse(String key, Object requestObject) throws ClassNotFoundException {

        var result = repository.findByIdempotentKey(key);
        if (result.isEmpty()) {
            var idempotentRecord = new IdempotentRecord();
            idempotentRecord.setIdempotentKey(key);
            idempotentRecord.setRequestType(requestObject.getClass().getName());
            idempotentRecord.setRequestObject(toJson(requestObject));
            repository.save(idempotentRecord);
            return null;
        }

        var record  = result.get();
        if(Objects.isNull(record.getResponseObject())){
            throw new ConcurrentRequestException(String.format("Request [key=%s] is processing", key));
        }

        return fromJson(record.getResponseObject(), Class.forName(record.getResponseType()));
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ModuleException(e.getMessage(), e);
        }
    }

    private <T> T fromJson(String content, Class<T> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new ModuleException(e.getMessage(), e);
        }
    }

}
