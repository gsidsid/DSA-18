package your_code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 *
 */
public class MyPriorityQueue {

    private ArrayList hm = new ArrayList<>();
    private int max;
    private int max_i;
    public MyPriorityQueue() {
        max = Integer.MIN_VALUE;
        max_i = 0;
    }

    public void enqueue(int item) {
        hm.add(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        // TODO
        for(int i = 0; i < hm.size(); i ++) {
            if(max < (int) hm.get(i)) {
                max = (int) hm.get(i);
                max_i = i;
            }
        }
        max = (int) Collections.max(hm);
        hm.remove(hm.indexOf(Collections.max(hm)));
        return  max;
    }

    public static void main(String[] args) {
        MyPriorityQueue maxQueue = new MyPriorityQueue();
        maxQueue.enqueue(1);
        maxQueue.enqueue(3);
        maxQueue.enqueue(5);
        maxQueue.enqueue(2);
        System.out.println(maxQueue.dequeueMax());
        System.out.println(maxQueue.dequeueMax());
        System.out.println(maxQueue.dequeueMax());
    }
}