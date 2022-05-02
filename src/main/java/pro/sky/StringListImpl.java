package pro.sky;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final int DEFAULT_CAPACITY = 5;
    private String[] elementData;
    private int size;

    public StringListImpl() {
        this.elementData = new String[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public String add(String item) {
        if (size == elementData.length - 1) {
            elementData = Arrays.copyOf(elementData, elementData.length << 1);
        }
        elementData[size++] = item;
        return elementData[size];
    }

    @Override
    public String add(int index, String item) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(String
                    .format("Задан некоректный индекс. Длина StringList=%d, запарашиваемый индекс=%d", size, index));
        }
        if (size == elementData.length - 1) {
            elementData = Arrays.copyOf(elementData, elementData.length << 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, elementData.length - 1 - index);
        elementData[index] = item;
        size++;
        return elementData[index];
    }

    @Override
    public String set(int index, String item) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(String
                    .format("Задан некоректный индекс. Длина StringList=%d, запарашиваемый индекс=%d", size, index));
        }
        elementData[index] = item;
        return elementData[index];
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(item)) {
                remove(i);
                return item;
            }
        }
        throw new IllegalArgumentException("Элемент отсутствует в списке.");
    }

    @Override
    public String remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(String
                    .format("Задан некоректный индекс. Длина StringList=%d, запарашиваемый индекс=%d", size, index));
        }
        String item = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - 1 - index);
        size--;
        if (size < elementData.length >> 1 && elementData.length > DEFAULT_CAPACITY) {
            elementData = Arrays.copyOf(elementData, elementData.length >>> 1);
        }
        return item;
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i > -1; --i) {
            if (elementData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(String
                    .format("Задан некоректный индекс. Длина StringList=%d, запарашиваемый индекс=%d", size, index));
        }
        return elementData[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Переданные StringList = null");
        }
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        while (size != 0) {
            remove(0);
        }
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StringListImpl={");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
