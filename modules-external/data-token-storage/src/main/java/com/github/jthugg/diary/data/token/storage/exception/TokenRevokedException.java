package com.github.jthugg.diary.data.token.storage.exception;

public class TokenRevokedException extends TokenStorageException {

    private static final String MESSAGE = "Token has been revoked.";

    public TokenRevokedException() {
        super(MESSAGE);
    }

}
