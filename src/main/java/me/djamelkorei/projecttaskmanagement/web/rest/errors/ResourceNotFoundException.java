package me.djamelkorei.projecttaskmanagement.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error Response with status {@code 404 (Not Found)} if resource not found.
 *
 * @author Djamel Eddine Korei
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super("Resource could not be found");
    }
}
