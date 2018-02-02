package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        // TODO
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        // TODO
        Node n;
        if(size != 0) {
            n = new Node(c,tail,null);
            this.tail.next = n;
            this.tail = n;

        } else {
            n = new Node(c);
            this.head = n;
            this.tail = n;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        // TODO
        Node n;
        if(size != 0) {
            n = new Node(c,null,head);
            this.head.prev = n;
            this.head = n;
        } else {
            n = new Node(c);
            this.head = n;
            this.tail = n;
        }
        size++;
    }

    public Chicken get(int index) {
        // TODO
        if(index <= size && index >= 0) {
            Node pointer = this.head;
            for (int i=0;i < index; i++) {
                pointer = pointer.next;
            }
            return pointer.val;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Chicken remove(int index) {
        // TODO
        if(index <= size && index >= 0) {
            Node pointer = this.head;
            for (int i=0;i < index; i++) {
                pointer = pointer.next;
            }
            Node t = pointer;

            if(pointer.next != null && pointer.prev != null) {
                pointer.prev.next = pointer.next;
                pointer.next.prev= pointer.prev;
            }

            size--;
            return t.val;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Chicken removeFirst() {
        // TODO
        if (head == null)
            return null;
        else {
            if (head == tail) {
                Node a = head;
                head = null;
                tail = null;
                size--;
                return a.val;
            } else {
                Node a = head;
                head = head.next;
                size--;
                return a.val;
            }
        }
    }

    public Chicken removeLast() {
        // TODO
        if (tail == null)
            return null;
        else {
            if (head == tail) {
                Node a = tail;
                head = null;
                tail = null;
                size--;
                return a.val;
            } else {
                Node a = tail;
                tail = tail.prev;
                size--;
                return a.val;
            }
        }
    }

}