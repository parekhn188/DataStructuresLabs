package app;

public class App {
    public static void main(String[] args) throws Exception {
        int[] check = {1, 2, 3, 4};
        int[] check2 = {1, 2, 4, 5, 15, 2, 7, 1}; 

        int[] check3 = {3, 8, 12, 9};
        int[] check4 = {9, 12, 15, 100}; 

        BSTSet set = new BSTSet(check); 
        BSTSet set2 = new BSTSet(check2); 

        BSTSet set3 = new BSTSet(check3); 
        BSTSet set4 = new BSTSet(check4); 
       
        set.add(6);
        //System.out.println(set.isIn(6)); 

        // set2.add(8); 
        // set2.printBSTSet();

        BSTSet union = set.union(set2);
        union.printBSTSet();

        BSTSet intersect = set.intersection(set2);
        intersect.printBSTSet();

        System.out.println(); 

    
        BSTSet diff = set3.difference(set4); 
        diff.printBSTSet();


        set2.printNonRec();

    }
}