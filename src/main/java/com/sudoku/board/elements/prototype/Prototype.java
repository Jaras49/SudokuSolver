package com.sudoku.board.elements.prototype;

public class Prototype<T> implements Cloneable {

    public T clone() throws CloneNotSupportedException {
        return (T) super.clone();
    }
}
