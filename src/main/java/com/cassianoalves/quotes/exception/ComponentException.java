package com.cassianoalves.quotes.exception;

import com.cassianoalves.quotes.model.QuotesError;

public class ComponentException extends RuntimeException {
    public enum ErrorCode {
        GENERAL_ERROR,
        USER_EXISTS,
        ALREADY_INITIALIZED, GUESTS_ONLY, INVALID_INVITE, INVITE_EXISTS,
        INVALID_PASSWORD, USER_NOT_FOUND
    }

    private QuotesError error;

    public ComponentException(QuotesError quotesError) {
        super(quotesError.getMessage());
        error = quotesError;
    }

    public ComponentException(QuotesError quotesError, Throwable cause) {
        super(quotesError.getMessage(), cause);
        error = quotesError;
    }

    public QuotesError getError() {
        return error;
    }

    public void setError(QuotesError error) {
        this.error = error;
    }
}
