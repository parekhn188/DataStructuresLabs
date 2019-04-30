package app; 

public class Stack {
    private int[] elemts;
    private int top = -1; 

    public Stack() {
        elemts = new int[100];  
    } 

    public void push(int e) {
        if (top == elemts.length - 1) {
            int[] newArr = new int[2 * elemts.length]; 
            for (int i = 0; i < elemts.length; i++) {
                newArr[i] = elemts[i]; 
            }
            elemts = newArr; 
        }
        elemts[++top] = e; 
    }

    public int pop() { 
        int temp = elemts[top]; 
        elemts[top--] = 0; 
        return temp;
    }
}