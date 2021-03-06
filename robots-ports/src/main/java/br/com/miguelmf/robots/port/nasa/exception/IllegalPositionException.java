package br.com.miguelmf.robots.port.nasa.exception;

/**
 * IllegalPositionException occurs when a user tries to move a Robot
 * to a position out of the bounds of a Zone Dimension.
 *
 * @author Miguel Fontes
 */
public class IllegalPositionException extends RuntimeException {

    public IllegalPositionException(String message) {
        super(message);
    }
}
