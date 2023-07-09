package com.test.berlin_clock.exception;

public class IncorrectBerlinClockTimeException extends RuntimeException {
    public IncorrectBerlinClockTimeException() {
        super("The received time doesn't respect the format");
    }
}
