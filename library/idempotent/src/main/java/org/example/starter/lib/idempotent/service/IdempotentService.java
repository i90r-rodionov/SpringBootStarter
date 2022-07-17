package org.example.starter.lib.idempotent.service;

public interface IdempotentService {

    void saveResponse(String key, Object responseObject);

    Object getIdempotentResponse(String key, Object requestObject) throws ClassNotFoundException;
}
