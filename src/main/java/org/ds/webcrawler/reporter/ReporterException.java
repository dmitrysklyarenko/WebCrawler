package org.ds.webcrawler.reporter;

public class ReporterException extends Exception {

    public ReporterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReporterException(String message) {
        super(message);
    }
}
