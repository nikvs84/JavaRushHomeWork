package com.javarush.test.level20.lesson10.bonus04;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/


import java.io.*;
import java.util.*;

public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable {

    private ArrayList<Node<String>> heap = new ArrayList<>();
    private int heapSize = 0;

    public Solution() {
        add("0");
    }

    private class Itr implements Iterator {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 1;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        int expectedModCount = modCount;

        public Itr() {
            while (cursor < heap.size())
                if (heap.get(cursor) == null)
                    cursor++;
                else break;
        }

        @Override
        public boolean hasNext() {
            return cursor != heap.size() && (heapSize > 1);
        }

        @Override
        public Object next() {
            checkForComodification();
            try {
                int i = cursor;
                String next = heap.get(i).item;
                lastRet = i;
                cursor++;
                while (cursor < heap.size() && heap.get(cursor) == null)
                    cursor++;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException:");
                System.out.println("Cursor = " + cursor);
                for (int i = 0; i < heap.size(); i++) {
                    System.out.println(heap.get(i));
                }
                throw e;
            }
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                Solution.this.remove(heap.get(lastRet).item);
                if (lastRet == cursor) {
                    while (cursor < heap.size() || heap.get(cursor) == null)
                        cursor++;
                }
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private static class Node<E> implements Serializable{
        E item;
        int arrayListIndex;
        Node<E> nextL;
        Node<E> nextR;
        Node<E> parent;

        Node(Node<E> nextL, Node<E> nextR, E element, Node<E> parent) {
            this.item = element;
            this.nextL = nextL;
            this.nextR = nextR;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Value = " + item + ", heapIndex = " + arrayListIndex + ";";
        }
    }

    private Node<String> lastParent() {
        for (int i = (heap.size() - 1) / 2; i < heap.size(); i++) {
            if (heap.get(i) != null && (heap.get(i).nextL == null || heap.get(i).nextR == null ))
                return heap.get(i);
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) != null && heap.get(i).item.equals((String)o)){
                result = removeWithChilds((Node<String>)heap.get(i));
                break;
            }
        }

        //Trim heap by deleting last null elements
        for (int i = heap.size() - 1; i > 0 ; i--) {
            if (heap.get(i) == null)
                heap.remove(i);
            else break;
        }
        return result;
    }

    //V0.1
    /*public boolean removeWithChilds(Node<String> node) {
        if (node.nextR != null)
            removeWithChilds(node.nextR);
        if (node.nextL != null)
            removeWithChilds(node.nextL);
        if (node.arrayListIndex == heap.size() - 1)
            heap.remove(heap.size() - 1);
        Node<String> parent = node.parent;

        if (parent != null) {
            if (parent.nextL == node)
                parent.nextL = null;
            if (parent.nextR == node)
                parent.nextR = null;
        }
        if (node.arrayListIndex < heap.size())
            heap.set(node.arrayListIndex, null);
        node = null;
        heapSize--;
        return true;
    }*/

    //V0.2 with left aligment
    public boolean removeWithChilds(Node<String> node) {
        if (node.nextR != null)
            removeWithChilds(node.nextR);
        if (node.nextL != null)
            removeWithChilds(node.nextL);
        if (node.arrayListIndex == heap.size() - 1)
            heap.remove(heap.size() - 1);
        Node<String> parent = node.parent;

        if (parent != null) {
            if (parent.nextL == node) {
                parent.nextL = parent.nextR;
                parent.nextR = null;
            }
            if (parent.nextR == node)
                parent.nextR = null;
        }
        if (node.arrayListIndex < heap.size())
            heap.set(node.arrayListIndex, null);
        node = null;
        heapSize--;
        return true;
    }

    /**
     * Appends the specified element to the end of this heap.
     *
     * @param s element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(String s) {
        final Node<String> p = lastParent();
        final Node<String> newNode = new Node<>(null, null, s, p);
        if (p != null)
            if (p.nextL == null) p.nextL = newNode;
            else p.nextR = newNode;
        newNode.arrayListIndex = heap.size();
        heap.add(newNode);
        heapSize++;
        return true;
    }

    @Override
    public int size() {
        if (heapSize == 0)
            return 0;
        else return heapSize - 1;
    } //don't count root (zero node)

    public String getParent(String value) {
        //have to be implemented
        String parent = null;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) != null && heap.get(i).item.equals(value)) {
                parent = heap.get(i).parent.item;
            }
        }
        if (parent != null && parent.equals("0")) parent = null;
        return parent;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
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
    public void clear() {
        Iterator<String> iterator = this.iterator();
        while (iterator.hasNext()) {
            remove(iterator.next());
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this.size() != ((Solution) o).size())
            return false;
        Iterator<String> iterator1 = this.iterator();
        Iterator<String> iterator2 = ((Solution) o).iterator();
        while (iterator1.hasNext()) {
            if (!iterator1.next().equals(iterator2.next()))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < heap.size(); i++)
            if (heap.get(i) != null && heap.get(i).item.equals((String)o)){
                return true;
            }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public String toString() {
        return super.toString();
    }

  /*  @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oOS = new ObjectOutputStream(out);
            oOS.writeObject(this);
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream oIS = new ObjectInputStream(in);
            return oIS.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public Solution clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oOS = new ObjectOutputStream(out);
            oOS.writeObject(this);
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream oIS = new ObjectInputStream(in);
            return (Solution)oIS.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));

        // Testing Iterator
        for (String s:list) {
            System.out.print(s + " ");
        }
        System.out.println();

        // checking serialization
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream("object"));
            oOS.writeObject(list);
            oOS.close();

            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("object"));
            List<String> loadedList = (List<String>)oIS.readObject();
            oIS.close();
            loadedList.remove("7");
            for (String s:loadedList) {
                System.out.print(s + " ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //checking method clone()
        try {
            Solution clonedList = (Solution)((Solution) list).clone();
            for (String s:clonedList) {
                System.out.print(s + " ");
            }
            System.out.println();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        javisTest();
    }

    public static void javisTest() throws CloneNotSupportedException, IOException, ClassNotFoundException{
        List<String> listTree = new Solution();
        System.out.println("Check isEmpty: " + listTree.isEmpty() + " Size: " + listTree.size());

        for (int i = 1; i < 16; i++) {
            listTree.add(String.valueOf(i));
        }
        System.out.println(listTree);

        System.out.println("Check isEmpty: " + listTree.isEmpty() + " Size: " + listTree.size());
        List<String> list2222 = new Solution();
        System.out.println("Check isEmpty: " + list2222.isEmpty() + " Size: " + list2222.size());
        list2222.add("test");
        System.out.println("Check isEmpty: " + list2222.isEmpty() + " Size: " + list2222.size());
        List<String> list1111 = new Solution();
        System.out.println("Check isEmpty: " + list1111.isEmpty() + " Size: " + list1111.size());

        System.out.println("\nExpected 3, actual is " + ((Solution) listTree).getParent("8"));
        listTree.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) listTree).getParent("11"));
        listTree.clear();
        for (int i = 1; i < 16; i++) {
            listTree.add(String.valueOf(i));
        }

        //For additional check correct work clone method
        Solution list = ((Solution)listTree).clone();

        System.out.println("Start value with using clone: " + listTree);
        System.out.println("\n===== REMOVE Remove 2 and 9 =====");
        list.remove("2");
        list.remove("9");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== ADD 16, 17, 18, 19, 20 =====");
        list.add("16");
        list.add("17");
        list.add("18");
        list.add("19");
        list.add("20");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== REMOVE 18 and 20 =====");
        list.remove("18");
        list.remove("20");
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n===== ADD 21 - 32 =====");
        list.add("21");
        list.add("22");
        list.add("23");
        list.add("24");
        list.add("25");
        list.add("26");
        list.add("27");
        list.add("28");
        list.add("29");
        list.add("30");
        list.add("31");
        list.add("32");
        //list.add(null);
        for (String s : list) System.out.print(s+ " ");
        System.out.println("\n---------------------------------------");
        System.out.println("3 Expected 1, actual is " + ((Solution) list).getParent("3"));
        System.out.println("4 Expected 1, actual is " + ((Solution) list).getParent("4"));
        System.out.println("8 Expected 3, actual is " + ((Solution) list).getParent("8"));
        System.out.println("11 Expected null, actual is " + ((Solution) list).getParent(null));
        System.out.println("15 Expected 7, actual is " + ((Solution) list).getParent("15"));
        System.out.println("16 Expected 7, actual is " + ((Solution) list).getParent("16"));
        System.out.println("10 Expected 4, actual is " + ((Solution) list).getParent("10"));
        System.out.println("17 Expected 8, actual is " + ((Solution) list).getParent("17"));
        System.out.println("19 Expected 10, actual is " + ((Solution) list).getParent("19"));
        System.out.println("21 Expected 10, actual is " + ((Solution) list).getParent("21"));
        System.out.println("22 Expected 15, actual is " + ((Solution) list).getParent("22"));
        System.out.println("--->> By task and add more item:");
        System.out.println("23 Expected 15, actual is " + ((Solution) list).getParent("23"));
        System.out.println("24 Expected 16, actual is " + ((Solution) list).getParent("24"));
        System.out.println("25 Expected 16, actual is " + ((Solution) list).getParent("25"));
        System.out.println("26 Expected 17, actual is " + ((Solution) list).getParent("26"));
        System.out.println("27 Expected 17, actual is " + ((Solution) list).getParent("27"));
        System.out.println("28 Expected 19, actual is " + ((Solution) list).getParent("28"));
        System.out.println("29 Expected 19, actual is " + ((Solution) list).getParent("29"));
        System.out.println("30 Expected 21, actual is " + ((Solution) list).getParent("30"));
        System.out.println("31 Expected 21, actual is " + ((Solution) list).getParent("31"));
        System.out.println("32 Expected 22, actual is " + ((Solution) list).getParent("32"));
        System.out.println("---------------------------------------");
        System.out.println("Size array = " + list.size() + " expected = 22");
        System.out.println();

        System.out.println("=============== Clone test ==================");

        System.out.println("Object: " + list + " --> Size = " + list.size());
        Solution sol = list.clone();
        //list.remove("7"); //Select for test
        System.out.println("Clone object: " + sol + " --> Size = " + sol.size());
        System.out.println("Result: " + list.containsAll(sol));

        System.out.println("\nTest addAll: ");
        Solution add = new Solution();
        add.addAll(sol);
        System.out.println(add + " --> Size: " + add.size() + " = " + sol.size());

        System.out.println("=============== Iterator test ===============");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String a = itr.next();
            System.out.print(a + " ");
        }
        System.out.println("\nExpected size 22 = " + list.size());

        System.out.println("\nIter remove");
        Iterator<String> itr2 = list.iterator();
        while (itr2.hasNext()) {
            if (itr2.next().contains("31")) {
                itr2.remove();
            }
        }
        System.out.println("For test " + list + " --> Size = " + list.size());
        System.out.println("Collect size " + list.size() + " Expected 21");

        System.out.println("\n===== SERIALIZATION and DESERIALIZATION =====");
        System.out.println("Collect before serializable " + list);
        System.out.print("Save list");
        FileOutputStream fos = new FileOutputStream("file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
        System.out.println(" Serializable done");
        System.out.print("Load list");
        FileInputStream fis = new FileInputStream("file");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<String> list2 = (List<String>) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(" Deserializable done");
        System.out.println("Collect after deserializable " + list2);

        System.out.println("\n================ Clear test =================");
        System.out.println("Before: " + listTree);
        listTree.clear();
        System.out.println("After clear: " + listTree + (listTree.isEmpty() ? " OK" : " Badly"));
    }
}


//Все работает, но не проходит проверку

/*
public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable {

    private static Entry header;
    private static int size;
    private static int level;
    private static Entry lastElement;
//    private Entry element;
//    private Solution prev;
//    private Solution next;
//    private Solution[] entryItem = new Solution[2];
//    private Solution currentItem = this;
//    private Solution parent;
//    private String value;

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
//        solution.add()
        SolutionIterator iterator = ((Solution) list).first();
//        printList((Solution) list);
        System.out.println();
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }

    public Solution() {
        header = new Entry("header");
        lastElement = header;
    }

    public boolean isEmpty() {
        return header.next == null;
    }

    public void makeEmpty() {
        header.next = null;
    }

    public SolutionIterator zeroth() {
        return new SolutionIterator(header);
    }

    public SolutionIterator first() {
        return new SolutionIterator(header.next);
    }

    public SolutionIterator getLast(){
        Entry itr = header;
        Entry last = null;
        while (itr != null) {
            last = itr;
            itr = itr.next;
        }
        return new SolutionIterator(last);
    }

*/
/*
    public void add(Entry element) {
        SolutionIterator itr = getLast();
        itr.current.next = element;
        element.prev = itr.current;
        size = size++;
    }
*//*


    public void insertAfter(Entry element, SolutionIterator iterator) {
        if ((element != null) && (iterator != null))
            iterator.current.next = new Entry(element, iterator.current, iterator.current.next);
    }

    public SolutionIterator findElement(Object element) {
        Entry itr = header.next;
        while ((itr != null) && (!itr.element.equals(element)))
            itr = itr.next;
//        if (!itr.element.equals(element))
//            return null;
        return new SolutionIterator(itr);
    }

    public SolutionIterator findPrevious(Entry element) {
        Entry itr = header.next;

        while ((itr != null) && (!itr.next.equals(element)))
            itr = itr.next;
        return new SolutionIterator(itr);
    }

*/
/*
    public void remove(Entry element) {
        if (element.next != null) {
            element.prev.next = element.next;
            element.next.prev = element.prev;
        } else element.prev.next = null;
    }
*//*


    public boolean remove(Object element) {
        if (element == null)
            return false;
        SolutionIterator rem = findElement(element);
        if (rem == null)
            return false;
        Entry remElement = rem.current;
        Entry prevElement = remElement.prev;
        Entry nextElement = remElement.next;
        prevElement.next = nextElement;
        nextElement.prev = prevElement;
        for (int i = 0; i < remElement.child.length; i++) {
            if (remElement.child[i] != null)
            remove(remElement.child[i].element);
        }
        remElement = null;
        return true;
    }

    public static void printList(Solution list) {
        if (list.isEmpty())
            System.out.println("List is empty");
        else {
            SolutionIterator itr = list.first();
            for (; itr.isValid(); itr.advance()) {
                System.out.print(itr.retrieve() + " ");
                System.out.println(itr.current.parent.element);
            }
        }
    }

    public int listSize(Solution list) {
        int size = 0;
        SolutionIterator itr;

        for (itr = list.first(); itr.isValid(); itr.advance()) {
            size++;
        }
        return size;
    }

    public int getMaxLevel(Solution list) {
        int level = 0;
        SolutionIterator itr;
        for (itr = list.first(); itr.isValid(); itr.advance()) {
            if (itr.current.getLevel() > level)
                level = itr.current.getLevel();
        }
        return level;
    }

    public int getMaxLevel() {
        return getMaxLevel(this);
    }

//    public Solution(String value, Solution prev, Solution next) {
//        this.value = value;
//        this.prev = prev;
//        this.next = next;
//    }

    public String getParent(String value) {
        //have to be implemented
        Entry element = getParentLink(value);
        if (element == null)
            return null;
        String result = (String) element.element;
        return result;
    }

    public Entry getParentLink(String value) {
        if (value == null)
            return null;
        Entry element = findElement(value).current;
        if (element == null)
            return null;
        return element.parent != null ? element.parent : null;
    }

    public SolutionIterator findElementToAdd() {
        SolutionIterator itr = zeroth();
        int maxLevel = getMaxLevel();
        for (int i = maxLevel - 1; i <= maxLevel; i++) {
            if (i < 0)
                continue;
            for (itr = zeroth(); itr.isValid(); itr.advance()) {
                Entry element = itr.current;
                if (element.getLevel() == i) {
                    for (int j = 0; j < element.child.length; j++) {
                        Entry e = element.child[j];
                        if (e == null) {
//                            itr.current.child[j] = new Entry(null);
//                            itr = new SolutionIterator(itr.current.child[j]);
                            return itr;
                        }
                    }
                }
            }
        }
        return itr;
    }

    public boolean add(String value) {
        Entry element = new Entry(value);
        int maxLevel = getMaxLevel();
//        SolutionIterator itr = getLast();
        SolutionIterator itr = findElementToAdd();
//        SolutionIterator p = new SolutionIterator(element);
//        System.out.println(p.retrieve());
//        p.advance();
//        if (element.parent != null)
//        System.out.println("parent = " + element.parent.toString());
//        p.back();
//        System.out.println("prev = " + element.prev.toString());
//        itr.current.next = element;
//        element.prev = itr.current;
//        itr.current = element;

        element.parent = itr.current;
        for (int i = 0; i < itr.current.child.length; i++) {
            if (itr.current.child[i] == null) {
                itr.current.child[i] = element;
                break;
            }
        }
        int level;
        level = itr.current.getLevel() + 1;
        element.setLevel(level);
//        System.out.println(itr.retrieve());
        lastElement.next = element;
        element.prev = lastElement;
        lastElement = element;
        size++;
        return true;
    }

    @Override
    public String get(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<E> {
        //Внутренний класс
        E element;
        Entry<E> prev;
        Entry<E> next;
        Entry<E> parent;
        Entry<E> [] child;
//        String value;
        int level;

        public Entry(E element) {
            this(element, null, null);
        }

        public Entry(E element, Entry<E> prev, Entry<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
            this.child = new Entry[2];
            this.level = 0;
        }

        @Override
        public String toString() {
            return (String) element;
        }

        public int getLevel() {
            return this.level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        */
/*
        public void add(E element) {
            new Entry<E>(element, prev, next);
        }
*//*

    }

    public class SolutionIterator {
        //Итератор
        public Entry current; //Текущий элемент/(позиция)

        SolutionIterator(Entry element) {
            current = element;
        }

        public boolean isValid() {
            return current != null;
        }

        public Object retrieve() {
            return isValid() ? current.element : null;
        }

        public void advance() {
            if (isValid())
                current = current.next;
        }

        public void back() {
            if (isValid())
                current = current.prev;
        }

    }


}
*/
