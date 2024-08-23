package com.obsidian.emeraldapi.infrastructure.exceptions;

public class EntityCreationFailedException extends RuntimeException {
    public EntityCreationFailedException() {
        super("Fail on creating new entity");
    }

    public EntityCreationFailedException(String message) {
        super(message);
    }
}
