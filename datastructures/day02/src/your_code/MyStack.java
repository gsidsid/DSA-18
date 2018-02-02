package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private Integer max;

    public MyStack() {
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if (ll.size() == 0) {
            max = e;
            ll.addLast(e);
        } else if (e > max){
            Integer encodedMax = 2*e-max; //neat way of saving a pointer to the last maximum: like a Node but encoded mathematically
            max = e;
            ll.addLast(encodedMax);
        } else {
            ll.addLast(e);
        }

    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeLast();
        if(pop > max) {
            max = 2*max-pop; //set current max to decode the popped value's pointer to it
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return max;
    }

    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(4);
        s.push(1);
        s.push(5);
        System.out.println(s.maxElement()); // this should print 5
        s.pop(); // this pops the 5 off the stack
        System.out.println(s.maxElement()); // this should print 4, since it is the largest remaining element on the stack
    }
}
