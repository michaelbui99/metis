package dk.michaelbui.metis.core.dsl;

import dk.michaelbui.metis.core.domain.DomainException;

public class ParseException extends DomainException {
    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
