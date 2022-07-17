package org.example.starter.api.controller;

import org.example.starter.api.dto.RequestDto;
import org.example.starter.api.dto.ResponseDto;

public interface EntityController {
    ResponseDto create(String rqId, RequestDto dto);
}
