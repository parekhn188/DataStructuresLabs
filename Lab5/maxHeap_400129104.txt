package app;

public class MaxHeap {
    public Integer[] heap; 
    private int arrSize; 
    private int heapSize = 0; 
    //Constructors
    public MaxHeap(int size) {
        heap = new Integer[size + 1]; 
        arrSize = size + 1; 
        heapSize = 0;
        heap[0] = -1; 
    }   
    public MaxHeap(Integer[] someArray) {
        arrSize = someArray.length + 1; 
        heap = new Integer[arrSize]; 
        heap[0] = -1; 
        for (int i = 0; i < someArray.length; i++) {
            //heapSize++; 
            insert(someArray[i]);
        }
    }

    // public void insert(int n) {
    //    heap[++heapSize] = n; 
    //    int index = heapSize; 
    //    while (heap[index] > heap[getParent(index)]) {
    //        swap(index, getParent(index)); 
    //        index = getParent(index); 
    //    }
    // }
    
    public void insert(int n) {

        if (heapSize >= arrSize) {
            arrSize *= 2; 
            Integer[] temp = heap; 
            heap = new Integer[arrSize]; 
            for (int i = 0; i < arrSize; i++) {
                heap[i] = temp[i]; 
            }
        }

        int index = 1; 
        while (index <= heapSize) {
            index++; 
        }
        
        heap[index] = n; 
        heapSize++; 

        while((heap[index] > heap[index/2]) && (index/2 != 0)) {
            swap(index, index/2);
            index = index/2; 
        }
    }

    private int deleteMax() {
        int max = heap[1]; 
        heap[1] = heap[heapSize]; 
        heap[heapSize] = 0; 
        //heap = truncate(heap); 
        heapSize--; 
        maxHeap(1);
        return max; 
    }

    public String toString() { 
        String elemts = new String(); 
        for (int i = 1; i < heapSize + 1; i++) {
            elemts += heap[i] + " "; 
        }
        return elemts;
    }

    public static void heapsort(Integer[] arrayToSort) {
        MaxHeap heap = new MaxHeap(arrayToSort); 
        Integer[] temp = new Integer[arrayToSort.length]; 

        int index; 

        for(index = 0; index < heap.heapSize * 6; index++) {
           int val = heap.deleteMax();  
           temp[index] = val;  
           System.out.print(val + " "); 
        }

        // for(index; i < heap.heapSize; i++) {
        //     int val = heap.deleteMax();  
        //     temp[i] = val;  
        //     System.out.print(temp[i]); 
        //  }

     
    }


    private void swap(int first, int second) {
        int temp = heap[first]; 
        heap[first] = heap[second]; 
        heap[second] = temp; 
    }

    private Integer[] truncate(Integer[] val) {
        Integer[] ret = new Integer[val.length - 1]; 

        for (int i = 0; i < val.length; i++) {
            if (val[i] != null) {
                ret[i] = val[i]; 
            }
        }
        return ret; 
    }

    private void maxHeap(int i) {
   
        // if (lastLevel(index)) return; 

        // if (heap[index] < heap[leftNode(index)] || heap[index] < heap[rightNode(index)]) {
        //     if (heap[leftNode(index)] > heap[rightNode(index)]) {
        //         swap(index, leftNode(index));
        //         maxHeap(leftNode(index));
        //     } else {
        //         swap(index, rightNode(index));
        //         maxHeap(rightNode(index));
        //     }
        // }

        while((heap[i] < heap[2*i]) && (i < arrSize-1) )
        {
            swap(i, 2*i);
            i++; 
        }

        for(int j=2; j < arrSize - 1; j++)
        {
            while((heap[j] > heap[j/2]) && (j/2 != 0) )
            {
                swap(j, j/2); 
                j = j/2;
            }
        }

    }


    private int leftNode(int index) {
        return 2 * index;
    }

    private int rightNode(int index) {
        return (2* index) + 1; 
    }

    private boolean lastLevel(int index) {
        if (index >= (heapSize/2) && index <= heapSize) {
            return true; 
        }
        return false; 
    }


}