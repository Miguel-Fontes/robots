package br.com.miguelmf.robots.api.rest;

/**
 * ErrorResponse is a utilitary class with a Inner string field that
 * represents a error message
 *
 * @author Miguel Fontes
 */
public class ErrorResponse {
    private final Integer code;
    private final String error;

    /**
     * Constructor for compatibility with frameworks.
     * <b>DO NOT USE IT DIRECTLY!</b>
     */
    @Deprecated
    public ErrorResponse() {
        code = null;
        error = null;
    }

    /**
     * Builds a ErrorResponse
     *
     * @param code http status code of the response
     * @param error the error message
     */
    public ErrorResponse(Integer code, String error) {
        this.code = code;
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
     * Gets code
     *
     * @return value of code
     */
    public Integer getCode() {
        return code;
    }
}