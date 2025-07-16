package dk.michaelbui.metis.server.domain.dsl;

import dk.michaelbui.metis.server.domain.DomainException;

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
