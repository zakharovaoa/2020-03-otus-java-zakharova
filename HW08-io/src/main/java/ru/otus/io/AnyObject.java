package ru.otus.io;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class AnyObject {
    private final char charCode;
    private final boolean sign;
    private final int[] array;
    private final Collection<String> list;

    public AnyObject(char charCode, boolean sign, int[] array, Collection<String> list) {
        this.charCode = charCode;
        this.sign = sign;
        this.array = array;
        this.list = list;
    }

    @Override
    public String toString() {
        return "AnyObject{" +
                "charCode=" + charCode +
                ", sign='" + sign + '\'' +
                ", array=" + Arrays.toString(array) +
                ", list=" + list.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyObject that = (AnyObject) o;
        return charCode == that.charCode &&
                sign == that.sign &&
               Arrays.equals(array, that.array) &&
               Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charCode, sign, array, list);
    }
}