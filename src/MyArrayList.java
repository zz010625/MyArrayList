import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E>{
    private int DEFAULT_SIZE = 10;//数组默认实际大小
    private int size = 0;//集合大小
    private Object[] elementData;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        }
    }

    public MyArrayList(Collection<? extends E> collection) {
        elementData = collection.toArray();
        size = elementData.length;
        elementData = Arrays.copyOf(elementData, size);
    }

    //用新元素替换该位置中的元素
    public void set(int index, E element) {
        if (rangeCheck(index) && element != null) {
            elementData[index] = element;
        }
    }

    //将元素添加到此数组的尾部
    public void add(E element) {
        ensureCapacity(size + 1);
        elementData[size] = element;
        size++;
    }

    /**
     * 将元素插入到数组中
     * 如果当前位置有元素 则向右移动当前位于该位置的元素以及所有后续元素
     *
     * @param index   插入位置
     * @param element 插入元素
     */
    public void add(int index, E element) {
        if (rangeCheck(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    //在尾部添加Collection元素
    public void addAll(Collection<? extends E> collection) {
        Object[] data = collection.toArray();
        ensureCapacity(size + data.length);//若数组长度不足 则进行扩容
        System.arraycopy(data, 0, elementData, size, data.length);
        size += data.length;
    }

    /**
     * 将Collection元素插入到数组中
     * 如果当前位置有元素 则向右移动当前位于该位置的元素以及所有后续元素
     *
     * @param index
     */
    public void addAll(int index, Collection<? extends E> collection) {
        if (rangeCheck(index))
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        Object[] data = collection.toArray();
        ensureCapacity(size + data.length);//若数组长度不足 则进行扩容
        int moveBackNum = size - index;//插入位置 后的元素数量
        if (moveBackNum > 0) {
            //将插入位置后的元素都向后 移动Collection元素长度的距离
            System.arraycopy(elementData, index, elementData, index + data.length, moveBackNum);
        }
        System.arraycopy(data, 0, elementData, index, data.length);//将插入的Collection元素放入空位中
        size += data.length;
    }

    //返回指定位置元素
    public E get(int index) {
        if (rangeCheck(index)) {
            return (E) elementData[index];
        } else {
            return null;
        }
    }
    //删除指定位置的元素
    public void remove(int index){
        if (rangeCheck(index)){
            System.arraycopy(elementData, index+1, elementData, index, size - index - 1);
            elementData[size-1]=null;//将最尾端的重复元素设为空
            size--;
        }
    }
    public int getSize() {
        return size;
    }

    private void ensureCapacity(int minCapacity) {
        if (elementData.length < minCapacity) {
            int newCapacity = elementData.length * 3 / 2 + 1;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private boolean rangeCheck(int index) {
        if (index >= size||index<0) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
        } else {
            return true;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }
    private class MyListIterator implements Iterator<E>{
        int index=-1;

        @Override
        public boolean hasNext() {
            index++;
            return index<size;
        }

        @Override
        public E next() {
            return (E) elementData[index];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(index);
            index--;
        }
    }
}

