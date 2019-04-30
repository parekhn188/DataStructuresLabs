package app;

import java.util.Arrays;

public class BSTSet {
    private TNode root; 

    public BSTSet() {root = null;}
    
    public BSTSet(int[] input) {
        if (input.length == 0) {root = null;} else {

        int[] tempArr = new int[input.length];
        Arrays.sort(input);

        int j = 0; 
        int matchCounter = 0; 

       for(int i = 0; i < input.length - 1; i++) {
        if(input[i] != input[i+1]) {
            tempArr[j++] = input[i]; 
        } else {
            matchCounter++; 
        }
       }

       tempArr[j++] = input[input.length - 1]; 

       for (int i = 0; i < tempArr.length; i++) {
        input[i] = tempArr[i]; 
       }

       //for(int num : input) {System.out.print(num + " ");}
       
       root = new TNode(input[(input.length - matchCounter)/2], null, null); 
       TNode nodePtr = root; 

        for (int i = (input.length - matchCounter)/2 - 1; i >= 0; i--) {
            //if (input[i] == 0 && !this.isIn(0)) {continue;}
            nodePtr.left = new TNode(input[i], null, null); 
            nodePtr = nodePtr.left; 
            //System.out.print(nodePtr.element + " "); 
        }
        
        nodePtr = root;

        for (int i = (input.length - matchCounter)/2 + 1; i < input.length - matchCounter; i++) {
            nodePtr.right = new TNode(input[i], null, null); 
            nodePtr = nodePtr.right; 
        }
      }
    }

    //isIn//
    public boolean isIn(int v) {
       return lookup(v, this.root); 
    }  

    private boolean lookup(int v, TNode t) {
        boolean ans = false; 
        if (t == null) {
            return ans; 
        } else if (t.element > v) {
            ans = lookup(v, t.left); 
        } else if (t.element < v) {
            ans = lookup(v, t.right); 
        } else {
            return true; 
        } 
        return ans; 
    }

    //Add//
    public void add(int v) {
        if(isIn(v)) {
            return;
        } else {
            add(v, this.root); 
        }
    }

    private void add(int v, TNode node) {
        if(node == null) {
            node = new TNode(v, null, null); 
            System.out.println(node.element);
        } else if (node.element > v) {
            if (node.left == null) {
                node.left = new TNode(v, null, null);
                return;   
            }
            node = node.left; 
            this.add(v, node); 
        } else if (node.element < v) {
            if (node.right == null) {
                node.right = new TNode(v, null, null);
                return;   
            }
            node = node.right; 
            this.add(v, node); 
        }
        return; 
    }

    //Remove//
    public boolean remove(int v) {
        if(!isIn(v)) {
            return false; 
        } else {
            if(this.root != null) {
                this.delTree(v, this.root); 
            } if (this.isIn(v) == false) {
                return true; 
            } 
        }
        return false; 
    }

    private TNode delTree(int v, TNode node) {
        if (v < node.element) {
            node.left = delTree(v, node.left); 
        } else if (v > node.element) {
            node.right = delTree(v, node.right); 
        } else if (node.left != null && node.right != null) {
            node.element = (findMin(node.right)).element; 
            node.right = removeMin(node.right);
        } else {
            node = (node.left != null) ? node.left : node.right; 
        }
        return node; 
    }

    private TNode findMin(TNode node) throws IllegalArgumentException {
        TNode minNode = node.left; 
        while(minNode.left != null) {
            minNode = minNode.left; 
        }
        return minNode; 
    }

    private TNode removeMin(TNode node) throws IllegalArgumentException {
        if (node == null) {
            throw new IllegalArgumentException("Tree is empty"); 
        } else if (node.left != null) {
            node.left = removeMin(node.left); 
        } else {
            node = node.right; 
        }
        return node; 
    }
   

    //Union//
    public BSTSet union (BSTSet s) {
        int[] setArr = new int[this.size() + s.size()]; 
        int index = 0; 
        for (int num : this.toArr()) {
            setArr[index++] = num; 
        } 
        for (int num : s.toArr()) {
            setArr[index++] = num; 
        }
        return new BSTSet(setArr); 
    }

    //Intersection// 
    public BSTSet intersection (BSTSet s) {
        int[] temp = new int[this.size() + s.size()]; 
        int[] thisArr = this.toArr(); 
        int[] sArr = s.toArr(); 

        int intersectCount = 0; 

        int index = 0; 
        for (int i = 0; i < thisArr.length; i++) {
            for (int j = 0; j < sArr.length; j++) {
                if (thisArr[i] == sArr[j]) {
                    temp[index++] = thisArr[i]; 
                    intersectCount++; 
                } 
            }
        }

        if(temp.length == 0) {
            return new BSTSet(); 
        } 

        int[] setArr = new int[intersectCount]; 

        for (int i = 0; i < intersectCount; i++) {
            if (!this.isIn(0) && !s.isIn(0) && temp[i] == 0) {continue;}
            setArr[i] = temp[i]; 
        }

        return new BSTSet(setArr); 
    }

    //Difference//
    public BSTSet difference (BSTSet s) {
        int[] temp = new int[this.size() + s.size()]; 
        int[] thisArr = this.toArr(); 
        int[] sArr = s.toArr(); 

        int index = 0; 
        int diffCount = 0; 
        boolean matchFound = false;

        for (int i = 0; i < thisArr.length; i++) {
            for (int j = 0; j < sArr.length; j++) {
                if (thisArr[i] == sArr[j]) {
                    matchFound = true;
                }
            }
            if (!matchFound) {
                temp[index++] = thisArr[i];
                diffCount++; 
            }
            matchFound = false; 
        }
        
       // System.out.println(diffCount); 
        int[] setArr = new int[diffCount]; 

        for(int i = 0; i < diffCount; i++) {
            setArr[i] = temp[i];
        }

       // for(int num : setArr) {System.out.print(num + " ");}

        return new BSTSet(setArr); 
    }


    //Height//
    public int height() {
        return height(this.root);
    }

    private int height(TNode node) { 
        if (node == null) {return -1;}
        TNode nodeptr = node; 
        int lcounter = 0; 
        int rcounter = 0; 

        while(nodeptr.left != null) {
            nodeptr = nodeptr.left; 
            lcounter++; 
        } 
        nodeptr = node; 

        while(nodeptr.right != null) {
            nodeptr = nodeptr.right; 
            rcounter++; 
        }
        return lcounter > rcounter ? lcounter : rcounter;  
    }


    private int[] toArr() throws IllegalArgumentException {
        int setArr[] = new int[this.size()];
        int emptyArr[] = {}; 
        TNode t = this.root; 
        int index = 0; 

       if (t == null) {return emptyArr;}
       
       setArr[index] = this.root.element; 
       index++;

        while(t.left != null) {
            t = t.left; 
            setArr[index] = t.element; 
            index++; 
        } 
        t = this.root; 
        while(t.right != null) {
            t = t.right; 
            setArr[index] = t.element; 
            index++; 
           
        } 
        return setArr; 
    }



    //size
    public int size() {
        return size(this.root);
    }

    public int size(TNode t) {
        if(t != null) {
           return size(t.left) + size(t.right) + 1;
        }
        return 0; 
    }


    //Print//
    public void printBSTSet() {
        if(root == null) {
            System.out.println("The set is empty"); 
        } else {
            System.out.print("The set elements are: "); 
            printBSTSet(this.root);
            System.out.println("\n"); 
        }
    }

    public void printBSTSet(TNode t) {
        if(t != null) {
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", "); 
            printBSTSet(t.right);
        }
    }

    //Non-Recursive Print
    public void printNonRec() throws IllegalArgumentException {
        Stack stack = new Stack();  
        int[] stackArr = new int[this.size()]; 
        int index = 0; 
        TNode nodeptr = this.root; 
        int stackCount = 0; 
        if (nodeptr == null) {throw new IllegalArgumentException("Set is empty");}

        while(nodeptr.right != null) {
            nodeptr = nodeptr.right; 
            stackArr[index++] = nodeptr.element;  
            stack.push(nodeptr.element); 

            //stackCount++; 
        }

        nodeptr = this.root; 
        stack.push(nodeptr.element);
        stackCount++; 

        while(nodeptr.left != null) {
            nodeptr = nodeptr.left; 
            stack.push(nodeptr.element);
            stackCount++; 
        } 

        for (int i = 0; i < stackCount; i++) {
            System.out.print(stack.pop() + " ");
        }

        for(int i = 0; i < stackArr.length - stackCount; i++) {
            System.out.print(stackArr[i] + " "); 
        }
    }

}