package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.QuotesError;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ErrorComponentImpl implements ErrorComponent {
    private Map<ComponentException.ErrorCode, QuotesError> errorMap = new ImmutableMap.Builder<ComponentException.ErrorCode, QuotesError>()
            .put(ComponentException.ErrorCode.GENERAL_ERROR, new QuotesError(0, "Unknown error"))
            .put(ComponentException.ErrorCode.INVITE_EXISTS, new QuotesError(100, "Invite already sent"))
            .put(ComponentException.ErrorCode.USER_EXISTS, new QuotesError(101, "User already exists"))
            .put(ComponentException.ErrorCode.INVALID_INVITE, new QuotesError(102, "Invite is invalid"))
            .put(ComponentException.ErrorCode.INVALID_PASSWORD, new QuotesError(103, "Invalid password"))
            .put(ComponentException.ErrorCode.USER_NOT_FOUND, new QuotesError(104, "User not found"))
            .put(ComponentException.ErrorCode.ALREADY_INITIALIZED, new QuotesError(1, "System already initialized"))
            .put(ComponentException.ErrorCode.GUESTS_ONLY, new QuotesError(2, "At moment, guests only. Sorry."))
            .build();

    @Override
    public ComponentException getComponentException(ComponentException.ErrorCode errorCode) {
        QuotesError quotesError = errorMap.get(errorCode);
        if(quotesError == null) {
            return new ComponentException(errorMap.get(ComponentException.ErrorCode.GENERAL_ERROR));
        }
        return new ComponentException(quotesError);
    }

    @Override
    public ComponentException getComponentException(ComponentException.ErrorCode errorCode, Throwable cause) {
        QuotesError quotesError = errorMap.get(errorCode);
        if(quotesError == null) {
            return new ComponentException(errorMap.get(ComponentException.ErrorCode.GENERAL_ERROR), cause);
        }
        return new ComponentException(quotesError, cause);
    }

    @Override
    public ComponentException getComponentException(Throwable cause) {
        return new ComponentException(errorMap.get(ComponentException.ErrorCode.GENERAL_ERROR), cause);
    }


}
