package net.dancier.chatdancer.utils;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}