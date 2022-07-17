package org.example.starter.lib.idempotent.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.starter.lib.idempotent.annotation.IdempotentId;
import org.example.starter.lib.idempotent.annotation.IdempotentRequestPayload;
import org.example.starter.lib.idempotent.domain.entity.IdempotentRecord;
import org.example.starter.lib.idempotent.domain.repository.jpa.IdempotentRepository;
import org.example.starter.lib.idempotent.exception.ModuleException;
import org.example.starter.lib.idempotent.service.IdempotentService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotentAdvice {

    private final IdempotentService service;

    @Around("@annotation(org.example.starter.lib.idempotent.annotation.IdempotentResource)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        if (proceedingJoinPoint.getArgs().length == 0) {
            // TODO: 16.07.2022 throw exception
        }


        Object[] args = proceedingJoinPoint.getArgs();

        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }

        var signature = proceedingJoinPoint.getSignature();
        var method = proceedingJoinPoint.getTarget().getClass().getMethod(signature.getName(), parameterTypes);

        String idempotencyKey = null;
        Object requestObject = null;

        for (int i = 0; i < method.getParameterAnnotations().length; i++) {
            for (int j = 0; j < method.getParameterAnnotations()[i].length; j++) {
                var annotationType = method.getParameterAnnotations()[i][j].annotationType();
                if (annotationType.isAssignableFrom(IdempotentId.class)) {
                    if (args[i].getClass().isAssignableFrom(String.class)) {
                        idempotencyKey = (String) args[i];
                    }
                }
                if (annotationType.isAssignableFrom(IdempotentRequestPayload.class)) {
                    requestObject = args[i];
                }
            }
        }

        if (Objects.isNull(idempotencyKey)) {
            // TODO: 16.07.2022 throw exceptions
        }

        var responseObject = service.getIdempotentResponse(idempotencyKey, requestObject);

        if (Objects.nonNull(responseObject)) {
            return responseObject;
        }

        responseObject = proceedingJoinPoint.proceed();

        service.saveResponse(idempotencyKey, responseObject);

        return responseObject;
    }

}
