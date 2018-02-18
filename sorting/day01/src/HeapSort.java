public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {

        if(rightChild(i) < size && leftChild(i) < size) { // CASE 1: both children exist

            if(heap[rightChild(i)] > heap[leftChild(i)]) { // If the right child is larger than the left child
                if(heap[i] < heap[rightChild(i)]) {
                    swap(heap,i, rightChild(i));
                    sink(rightChild(i));
                } else {
                    return;
                }
            } else { // If the left child is larger than the right child 
                if(heap[i] < heap[leftChild(i)]) { // If the left child is larger
                    swap(heap,i, leftChild(i));
                    sink(leftChild(i));
                } else {
                    return;
                }
            }

        } else if (rightChild(i) < size && leftChild(i) >= size) { // CASE 2: right child only exists

            if(heap[i] < heap[rightChild(i)]) {
                swap(heap,i,rightChild(i));
                sink(rightChild(i));
            } else {
                return;
            }

        } else if (rightChild(i) >= size && leftChild(i) < size) { // CASE 3: left child only exists

            if(heap[i] < heap[leftChild(i)]) {
                swap(heap,i, leftChild(i));
                sink(leftChild(i));
            } else {
                return;
            }

        } else { // CASE 4: no children exist, bottom of heap
            return;
        }


    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        for (int i=size-1; i>0; i--) {
            sink(0);
            swap(array,i,0);
            size--;
        }
        return array;
    }
}
