import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        MyArrayList myArrayList = new MyArrayList();
        for (int i = 0; i < 15; i++) {
            myArrayList.add("a");
        }
        //将Collection元素插入到数组中
        myArrayList.addAll(arrayList);
        Iterator iterator = myArrayList.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
//            iterator.remove();
        }

        System.out.println(myArrayList.getSize());
        
    }
}
