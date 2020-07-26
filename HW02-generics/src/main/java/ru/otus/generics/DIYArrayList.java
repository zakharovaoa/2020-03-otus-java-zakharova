package ru.otus.generics;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class DIYArrayList<E> implements List<E> {

    private Object[] itemData;

    private static final int INITIAL_CAPACITY = 10;

    private static final Object[] EMPTY_ITEMDATA = {};

    private static final Object[] INITIALCAPACITY_EMPTY_ITEMDATA = {};

    private int size;

    private int modCount;

    public DIYArrayList() {
        this.itemData = INITIALCAPACITY_EMPTY_ITEMDATA;
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

    public DIYArrayList(Collection<? extends E> c) {
        itemData = c.toArray();
        size = itemData.length;
        if (size != 0)
            itemData = Arrays.copyOf(itemData, size, Object[].class);
        else {
            this.itemData = EMPTY_ITEMDATA;
        }
    }




    public void provideCapacity(int minCapacity) {
        if (itemData.length < minCapacity
            && minCapacity <= INITIAL_CAPACITY
                && !(itemData == INITIALCAPACITY_EMPTY_ITEMDATA)) {
            increase(minCapacity);
        }
    }



    private void checkIndex(int index, int length) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
    }

    private Object[] increase(int minCapacity) {
        int oldCapacity = itemData.length;
        if (itemData != INITIALCAPACITY_EMPTY_ITEMDATA || oldCapacity > 0) {
            int newCapacity = (int) (oldCapacity * 1.5);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;
        int lastRet = -1;
        int expectedModCount = modCount;

        void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            /*checkForComodification();
            if (cursor >= size)
                throw new NoSuchElementException();
            if (cursor >= itemData.length)
                throw new ConcurrentModificationException();
            cursor ++;
            lastRet = cursor;
            return (E) DIYArrayList.this.itemData[lastRet];*/
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] itemData = DIYArrayList.this.itemData;
            if (i >= itemData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            lastRet = i;
            return (E) itemData[lastRet];
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DIYArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = DIYArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] array = itemData;
                if (i >= array.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept((E) array[i]);
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(itemData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        modCount++;
        if (itemData.length == size) {
            itemData = increase();
        }
        itemData[size] = e;
        size += 1;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);
        return itemData(index);
    }

    //
    @Override
    public E set(int index, E element) {
        checkIndex(index, size);
        E oldValue = itemData(index);
        itemData[index] = element;
        return oldValue;
    }

    E itemData(int index) {
        return (E) itemData[index];
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        final int s = size;
        Object[] itemData = DIYArrayList.this.itemData;
        if (s == itemData.length) {
            increase();
        }
        itemData = arrayCopyForAdd(itemData, index, s - index);
        itemData[index] = element;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private static Object[] arrayCopyForAdd (Object[] array, int index, int length) {
        Object temp = array[index];
        for (int i = index; i <= index + length - 1; i++ ) {
            Object temp1 = array[i + 1];
            array[i + 1] = temp;
            temp = temp1;
        }
        return array;
    }

    private static Object[] arrayCopyForRemove (Object[] array, int index, int length) {
        Object temp = array[index + length - 1];
        for (int i = index + length - 1; i >= index; i-- ) {
            Object temp1 = array[i - 1];
            array[i - 1] = temp;
            temp = temp1;
        }
        return array;
    }


    @Override
    public E remove(int index) {
        checkIndex(index, size);
        E oldValue = (E) itemData[index];
        fastRemove(itemData, index);
        return oldValue;
    }

    private void fastRemove(Object[] array, int i) {
        modCount++;
        final int newSize = size - 1;
        if (newSize > i) {
            itemData = arrayCopyForRemove(array, i + 1, newSize - i);
        }
        array[newSize] = null;
        size = newSize;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);
        return new ListItr(index);
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            if (i >= DIYArrayList.this.itemData.length)
                throw new ConcurrentModificationException();
            cursor = lastRet = i;
            return (E) DIYArrayList.this.itemData[lastRet];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            checkForComodification();
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                DIYArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();
            try {
                int i = cursor;
                DIYArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) itemData, 0, size, c);
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        modCount++;
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }
}
