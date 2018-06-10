package com.sudoku.board.elements.prototype;

/**
 * @deprecated
 * This class is deprecated due to refactor of solving mechanism
 * @param <T>
 */
public class Prototype<T> implements Cloneable {

    public T clone() throws CloneNotSupportedException {
        return (T) super.clone();
    }
}
