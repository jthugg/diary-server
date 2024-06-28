package com.github.jthugg.diary.data.token.storage.exception;

public class TokenNotFoundException extends TokenStorageException {

    private static final String MESSAGE = "Token not found.";

    public TokenNotFoundException() {
        super(MESSAGE);
    }

}
