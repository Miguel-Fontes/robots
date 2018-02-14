package br.com.miguelmf.robots.api.rest;

/**
 * ErrorResponse is a utilitary class with a Inner string field that
 * represents a error message
 *
 * @author Miguel Fontes
 */
public class ErrorResponse {
    String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    /**
     * Gets error
     *
     * @return value of error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets error
     */
    public void setError(String error) {
        this.error = error;
    }
}