package md.ceiti.internmanager.exception;

import md.ceiti.internmanager.util.ExceptionMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> clazz) {
        super(clazz.getSimpleName() + ExceptionMessage.NOT_FOUND);
    }
}
