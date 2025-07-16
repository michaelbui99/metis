package dk.michaelbui.metis.core.domain.selector;

import dk.michaelbui.metis.core.domain.DomainException;

public class SelectorException extends DomainException {

    public SelectorException() {
    }

    public SelectorException(String message) {
        super(message);
    }

    public SelectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
