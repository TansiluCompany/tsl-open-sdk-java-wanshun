package com.tsl3060.open.extend.core.exception;

import java.io.IOException;

public class BadResourceException extends IOException {
    public BadResourceException() {
        super();
    }

    public BadResourceException(String message) {
        super(message);
    }

    public BadResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadResourceException(Throwable cause) {
        super(cause);
    }
}
