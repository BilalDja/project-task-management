package me.djamelkorei.projecttaskmanagement.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error Response with status {@code 400 (Bad Request)} if resetKey not exist.
 *
 * @author Djamel Eddine Korei
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResetKeyNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResetKeyNotExistException() {
        super("No user was found for this reset key");
    }
}
