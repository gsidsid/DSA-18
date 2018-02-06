import java.util.Arrays;

public class MyArrayList {
    private Cow[] elems;
    private int size;


    // TODO: Runtime: O(1):idk
    public MyArrayList() {
        // TODO
        elems = new Cow[1]; // so when it doubles it actually doubles?
    }

    // TODO: Runtime: O(1):idk
    public MyArrayList(int capacity) {
        // TODO
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1):as the array expands, the array is less likely to need to change in size
    public void add(Cow c) {
        // TODO
        if(size == elems.length) {
            Cow[] internalBuffer = new Cow[elems.length*2];
            System.arraycopy(elems,0,internalBuffer,0,elems.length);
            elems = internalBuffer;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1):retrieving primitive variable from stack
    public int size() {
        // TODO
        return size;
    }

    // TODO: Runtime: O(1):indexed retrieval of object reference from array
    public Cow get(int index) {
        // TODO
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // TODO: Runtime: O(N) if not last element else O(1):Equal to number of elements in leftward shift post-deletion
    public Cow remove(int index) {
        // TODO
        if(index >= size  || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Cow a = elems[index];
        if(size < elems.length/4) {
            Cow[] internalBuffer = new Cow[elems.length/2];
            System.arraycopy(elems,0,internalBuffer,0,elems.length/2);
            elems = internalBuffer;
        }

        for(int i = index+1; i < size; i++) {
            elems[i-1] = elems[i]; // leftward shift
        }

        size--;
        return a;
    }

    // TODO: Runtime: O(N):System.arraycopy
    public void add(int index, Cow c) {
        // TODO
        if(index >= size  || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(size == elems.length) {
            Cow[] internalBuffer = new Cow[elems.length*2];
            System.arraycopy(elems,0,internalBuffer,0,elems.length);
            elems = internalBuffer;
        }
        for(int i = size; i > index; i--) {
            elems[i] = elems[i-1]; // rightward shift
        }
        elems[index] = c;
        size++;
    }

    public static void main(String[] args) {

        //why is testing not working??

        Cow DELILAH = new Cow("Delilah", 10, "blue");
        Cow GEORGY = new Cow("Georgy", 8, "green");
        Cow LILY = new Cow("Lily", 4, "yellow");
        Cow JIMY = new Cow("Jimi", 13, "black");
        Cow JAQUAN = new Cow("Jaquan", 10, "transparent");
        Cow LEGOS = new Cow("Legos", 400, "rainbow");

        MyArrayList cows = new MyArrayList(4);

        // add
        System.out.println("Testing add(c): Adding 4 cows");
        cows.add(DELILAH);
        cows.add(GEORGY);
        cows.add(LILY);
        cows.add(JIMY);

        // size
        System.out.println("    Testing size:");
        System.out.println("    " + cows.size());
        System.out.println("");

        // get
        System.out.println("Testing get()");
        System.out.println("EXPECTED GEORGY, GOT " + cows.get(1).name);
        System.out.println("");

        // remove
        System.out.println("Testing remove()");
        Cow a = cows.remove(2);
        System.out.println("EXPECTED TO REMOVE LILY, GOT " + a.name);
        System.out.println(cows.size());
        System.out.println(cows.get(0).name);
        System.out.println(cows.get(1).name);
        System.out.println(cows.get(2).name);
        System.out.println("");

        System.out.println("Testing add(i,c): Adding LILY to index 2");
        cows.add(2,LILY);
        System.out.println(cows.size());
        System.out.println(cows.get(0).name);
        System.out.println(cows.get(1).name);
        System.out.println(cows.get(2).name);
        System.out.println(cows.get(3).name);
        System.out.println("");

        System.out.println("Testing resizeCows...");
        MyArrayList resizeCows = new MyArrayList();
        for (int i = 0; i < 1000; i++) {
            String name = "Cow" + i;
            int age = i;
            String color = "Color" + i;
            resizeCows.add(new Cow(name,age,color));
        }
        Cow findCow = new Cow("Cow996", 996, "Color996");
        System.out.println("");
        if(resizeCows.get(996).equals(findCow)) {
            System.out.println("Cow 996 found.");
        }
    }
}
