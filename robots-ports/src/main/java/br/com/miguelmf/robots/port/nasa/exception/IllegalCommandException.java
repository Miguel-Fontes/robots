package br.com.miguelmf.robots.port.nasa.exception;

/**
 * IllegalCommandException is Throwed when a client requests the execution
 * of an invalid command
 *
 * @author Miguel Fontes
 */
public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException(String message) {
        super(message);
    }
}
