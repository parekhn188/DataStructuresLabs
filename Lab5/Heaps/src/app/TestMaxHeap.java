package app;

public class TestMaxHeap {
    public static void main(String[] args) throws Exception {
        Integer[] test = {10, 8, 9, 5, 2, 7}; 
        Integer[] sortTest = {2, 4, 1, 3, 1, 15}; 
      //  Integer[] test2 = {10, 8, 9, 5, 7, 7, 12}; 

        MaxHeap h = new MaxHeap(test); 
       // MaxHeap h2 = new MaxHeap(test2); 

        // for(int i = 0; i < h.heap.length; i++) {
        //     System.out.print(h.heap[i]); 
        // }

       // h.deleteMax(); 
        System.out.print('\n'); 
        System.out.println(h.toString()); 
        System.out.print('\n'); 
       // System.out.println(h2.toString());
        // for(int i = 0; i < h.heap.length; i++) {
        //     System.out.print(h.heap[i]); 
        // }
        MaxHeap.heapsort(sortTest);
        // for(int i = 0; i < sortTest.length; i++) {
        //     System.out.println(sortTest[i]); 
        // }
    }
}