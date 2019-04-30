package app;
//import java.util.Random;

import java.math.BigInteger;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        
        // HugeInteger huge1, huge2; 
        // int huge3 = 0; 
        // long startTime = 0, endTime = 0; 
        // double runtime = 0.0; 
        // int MAXNUMITS = 100; 
        // int MAXRUN = 70; 

        // for (int numInts = 0; numInts < MAXNUMITS; numInts++) {
        //     huge1 = new HugeInteger(10);
        //     huge2 = new HugeInteger(10);
            
        //     startTime = System.currentTimeMillis(); 
        //     for (int numRun = 0; numRun < MAXRUN; numRun ++) {
        //         huge3 = huge1.compareTo(huge2);
        //     }
        //     endTime = System.currentTimeMillis(); 
        //     runtime += (double) (endTime - startTime)/ ((double) MAXRUN); 
    
       
        // runtime = runtime/((double) MAXNUMITS); 
        // System.out.println(runtime);

        BigInteger huge1, huge2; 
        int huge3; 
        long startTime = 0, endTime = 0; 
        double runtime = 0.0; 
        int MAXNUMITS = 800; 
        int MAXRUN = 500000000; 
        Random rand = new Random(); 

        for (int numInts = 0; numInts < MAXNUMITS; numInts++) {
            huge1 = new BigInteger(5, rand);
            huge2 = new BigInteger(5, rand);
            
            startTime = System.currentTimeMillis(); 
            for (int numRun = 0; numRun < MAXRUN; numRun ++) {
                huge3 = huge1.compareTo(huge2);
            }
            endTime = System.currentTimeMillis(); 
            runtime += (double) (endTime - startTime)/ ((double) MAXRUN); 
            //System.out.println(endTime - startTime); 
        }
       
        runtime = runtime/((double) MAXNUMITS); 
        
        System.out.println(runtime);
        
    



        //Constructor tests 
        // HugeInteger newVal = new HugeInteger("129834741982749198234"); 
        // HugeInteger newVal2 = new HugeInteger("-1234981274498124"); 
        // System.out.println((newVal.subtract(newVal2)).integerVal); 

        // BigInteger newVa3 = new BigInteger("129834741982749198234"); 
        // BigInteger newVal4 = new BigInteger("-1234981274498124"); 
        // System.out.println((newVa3.subtract(newVal4)).toString()); 




        //Testing multiplication & addition 
        //HugeInteger newVal3 = new HugeInteger("-12349821349023148981"); 
        //HugeInteger newVal4 = new HugeInteger("82134279811489"); 
        //System.out.println(newVal3.compareTo(newVal4)); 

        // HugeInteger newint = new HugeInteger("1234554"); 
        // HugeInteger int2 = new HugeInteger("1243"); 
        // HugeInteger divide = newint.divide(int2); 
        // System.out.println(divide.integerVal);

    //     HugeInteger newVal5 = new HugeInteger("-19237891237481273489712328471234891232748910234789127344891273483487123498"); 
    //     HugeInteger newVal6 = new HugeInteger("-21389347"); 

    //     HugeInteger summons3 = newVal5.divide(newVal6); 
    //     //System.out.println(summons3.integerVal); 

    //     HugeInteger newVal7 = new HugeInteger("-1234982023148012348"); 
    //     HugeInteger newVal8 = new HugeInteger("-8213427981148309"); 

    //     HugeInteger summons4 = newVal7.divide(newVal8); 
    //    // System.out.println(summons4.integerVal); 


    //     HugeInteger newVal9 = new HugeInteger("123498202314898113214"); 
    //     HugeInteger newVal10 = new HugeInteger("-8213427981148199"); 

    //     HugeInteger summons5 = newVal9.multiply(newVal10); 
    //     //System.out.println(summons5.integerVal); 

    //     HugeInteger newVal11 = new HugeInteger("-1234982023148012348"); 
    //     HugeInteger newVal12 = new HugeInteger("-8213427981148309"); 

    //     HugeInteger summons6 = newVal11.multiply(newVal12); 
    //     //System.out.println(summons6.integerVal); 


    //     HugeInteger newVal13 = new HugeInteger("-1234982023148012348"); 
    //     HugeInteger newVal14 = new HugeInteger("0"); 

    //     HugeInteger summons7 = newVal13.multiply(newVal14); 
        //System.out.println(summons7.integerVal); 


       // System.out.println(newVal11.toString());
        //Runtime Tests 
     

    }
}



