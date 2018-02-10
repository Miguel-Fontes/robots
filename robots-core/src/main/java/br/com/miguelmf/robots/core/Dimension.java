package br.com.miguelmf.robots.core;

import java.util.Objects;

/**
 * Dimension captures a tuple (length, height) representing the size
 * of a two dimension Zone (normally show as LxH; Ex: 5x5).
 *
 * @author Miguel Fontes
 */
public class Dimension {
    private final Integer length;
    private final Integer height;

    public Dimension(int length, int height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return Objects.equals(length, dimension.length) &&
                Objects.equals(height, dimension.height);
    }

    @Override
    public int hashCode() {

        return Objects.hash(length, height);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "length=" + length +
                ", height=" + height +
                '}';
    }
}
