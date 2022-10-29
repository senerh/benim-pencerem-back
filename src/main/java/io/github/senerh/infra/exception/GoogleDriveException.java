package io.github.senerh.infra.exception;

public class GoogleDriveException extends RuntimeException {

    public GoogleDriveException() {
        super();
    }

    public GoogleDriveException(String message) {
        super(message);
    }

    public GoogleDriveException(Throwable cause) {
        super(cause);
    }

    public GoogleDriveException(String message, Throwable cause) {
        super(message, cause);
    }
}
