package com.br.user_service.exception.enumeration;

import com.br.user_service.exception.interfaces.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorApiCode implements ErrorCode {

    DEPARTMENT_NOT_FOUND("messages.error.department-not-found"),
    USER_EXIST_WITH_EMAIL("messages.error.user-exists-with-email"),
    USER_NOT_FOUND("messages.error.user-not-found");

    private final String messageKey;

}
