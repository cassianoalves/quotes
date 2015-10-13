package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import org.springframework.mail.MailException;

public interface ErrorComponent {
    ComponentException getComponentException(ComponentException.ErrorCode errorCode);
    ComponentException getComponentException(ComponentException.ErrorCode errorCode, Throwable cause);
    ComponentException getComponentException(Throwable cause);
}
