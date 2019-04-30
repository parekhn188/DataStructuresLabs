package app; 

public class HashTableLin {
    private Integer[] table; 
    private int tableSize; 
    private int numKeys; 
    private float currentLoad; 
    private double maxLoad; 


    public HashTableLin(int maxNum, double load) {
        int requirement = (int) (maxNum/load); 
        table = new Integer[closestPrime(requirement)]; 
        maxLoad = load; 
        tableSize = table.length; 
        for(int i = 0; i < tableSize; i++) {
            table[i] = -1; 
        }
        numKeys = 0; 
        currentLoad = 0; 
    }

    public void insert(int n) {
        if(isIn(n)) return; 

        if ((double) (numKeys + 1)/tableSize < maxLoad) {
            int index = n%tableSize; 
            while (table[index] > -1) {
                index++; 
                if (index >= tableSize) 
                    index %= tableSize;
            }
            table[index] = n; 
            numKeys++; 
            currentLoad = numKeys/tableSize; 
        } else {
            rehash();
            int index = n%tableSize; 
            while (table[index] > -1) {
                index++; 
                if (index >= tableSize) 
                    index %= tableSize;
            }
            table[index] = n; 
            numKeys++; 
            currentLoad = numKeys/tableSize; 
        }
    }

    public int insertCount(int n) {
        int probeNum = 0; 

        if(table[n%tableSize] == n) 
            return 1;

        if ((double) (numKeys + 1)/tableSize < maxLoad) {
            int index = n%tableSize; 
            while (table[index] > -1) {
                index++; 
                probeNum++; 
                if (index >= tableSize) 
                    index %= tableSize;
            }
            table[index] = n; 
            probeNum++; 
            numKeys++; 
            currentLoad = numKeys/tableSize; 
        } else {
            rehash();
            int index = n%tableSize; 
            while (table[index] > -1) {
                index++; 
                probeNum++; 
                if (index >= tableSize) 
                    index %= tableSize;
            }
            table[index] = n; 
            probeNum++; 
            numKeys++; 
            currentLoad = numKeys/tableSize; 
        }
        return probeNum;
    }

    private void rehash() {
        Integer[] newTable = new Integer[closestPrime(2*this.tableSize)]; 

        for (int i = 0; i < newTable.length; i++) {
            if(i > tableSize - 1) 
                newTable[i] = -1; 
            else 
                newTable[i] = table[i]; 
        }
        this.table = newTable; 
        this.tableSize = newTable.length; 
        this.maxLoad = numKeys/tableSize; 
    }

    public boolean isIn(int n) {
        for (int i = 0; i < tableSize; i++) {
            if (table[i] == n) {
                return true; 
            }
        }
        return false; 
        // int index = n%tableSize; 
        // if (table[index] == n) 
        //     return true; 
        
        // while(table[index] > -1) {
        //     index++; 

        //     if(table[index] == n) 
        //         return true; 

        //     if (index >= tableSize)
        //         index %= tableSize;
        // }

        // return false; 
    }


    public void printKeys() {
        for (int i = 0; i < tableSize; i++) {
            if (table[i] > -1) 
                System.out.println(table[i]); 
        }
    }

    public void printKeysAndIndexes() {
        for (int i = 0; i < tableSize; i++) {
            if (table[i] > -1) 
                System.out.println("Index: " + i + " " +  "Key: " + table[i] + '\n'); 
        }
    }

    public int getNumKeys() {
        return this.numKeys;
    }

    public int getTableSize() { 
        return this.tableSize; 
    }

    public double getMaxLoad() { 
        return (double) this.maxLoad; 
    }



    public static int closestPrime(int requirement) {
        boolean notPrime = false; 
        int num = 0; 
        if (requirement == 1) {return 1;}
        if (requirement == 2) {return 2;}
        for (int i = 2; i <= requirement/2; i++) {
            if (requirement % i == 0) {
                notPrime = true;  
               // System.out.println(notPrime); 
                break; 
            }
        }
        if(notPrime) {
           num = closestPrime(requirement + 1);
           return num; 
        } 
        return requirement; 
    }


}