package ru.otus.generics;

import java.util.*;
import java.util.function.UnaryOperator;

public class DIYArrayList<E> implements List<E> {

    private Object[] itemData;

    private static final int INITIAL_CAPACITY = 10;

    private static final Object[] EMPTY_ITEMDATA = {};

    private static final Object[] INITIALCAPACITY_EMPTY_ITEMDATA = {};

    private int size;

    public DIYArrayList() {
        this.itemData = INITIALCAPACITY_EMPTY_ITEMDATA;
    //new Object[10];
    }

    public DIYArrayList(int beginCapacity) {
        if (beginCapacity == 0) {
            this.itemData = EMPTY_ITEMDATA;
        } else if (beginCapacity > 0) {
            this.itemData = new Object[beginCapacity];
        } else {
            throw new IllegalArgumentException("Invalid value beginCapacity " + beginCapacity);
        }
    }

    public void provideCapacity(int minCapacity) {
        if (itemData.length < minCapacity
            && minCapacity <= INITIAL_CAPACITY
                && !(itemData == INITIALCAPACITY_EMPTY_ITEMDATA)) {
            increase(minCapacity);
        }
    }

    private Object[] increase(int minCapacity) {
        int oldcapacity = itemData.length;
        if (itemData != INITIALCAPACITY_EMPTY_ITEMDATA || oldcapacity > 0) {
            int newCapacity = (int) (oldcapacity * 1.5);
            return itemData = Arrays.copyOf(itemData, newCapacity);
        } else {
            return itemData = new Object[Math.max(INITIAL_CAPACITY, minCapacity)];
        }
    }

    private Object[] increase() {
        return increase(size + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if (itemData.length == size) {
            itemData = increase();
        }
        itemData[size] = e;
        size += 1;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return itemData(index);
    }

    //
    @Override
    public E set(int index, E element) {
        E oldValue = itemData(index);
        itemData[index] = element;
        return oldValue;
    }

    E itemData(int index) {
        return (E) itemData[index];
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        final int s = size;
        Object[] itemData = this.itemData;
        if (s == itemData.length) {
            increase();
        }
        itemData = offset(itemData, index, s - index);
        itemData[index] = element;
        size = s + 1;
    }

    private static Object[] offset (Object[] array, int index, int length) {
        Object temp = array[index];
        for (int i = index; i <= index + length; i++ ) {
            Object temp1 = array[i + 1];
            array[i + 1] = temp;
            temp = temp1;
        }
        return array;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }
}
