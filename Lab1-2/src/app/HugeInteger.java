 
package app; 
import java.util.Random;

public class HugeInteger {
    public int[] numArr;
    public String integerVal; 

    public HugeInteger(String val) throws IllegalArgumentException { 

        if (val.charAt(0) == 45) {
        	numArr = new int[val.length() - 1]; 
            numArr[0] = -1 * Character.getNumericValue(val.charAt(1)); 
	        
	        for (int i = 2; i < val.length(); i++) {
	            if (Character.isDigit(val.charAt(i))) {
	                numArr[i - 1] = Character.getNumericValue(val.charAt(i)); 
	            } else {
	                throw new IllegalArgumentException("The entered string must be all integer values"); 
	            }
            }
            
	  
        } else {
        	numArr = new int[val.length()];
            //numArr[0] = Character.getNumericValue(val.charAt(1)); 
            
            for (int i = 0; i < val.length(); i++) {
	            if (Character.isDigit(val.charAt(i))) {
	                numArr[i] = Character.getNumericValue(val.charAt(i)); 
	            } else {
	                throw new IllegalArgumentException("The entered string must be all integer values"); 
	            }
            
        	}
        }

        StringBuilder cBuilder = new StringBuilder(); 
        for (int num : numArr) {
            cBuilder.append(num); 
        }

        integerVal = cBuilder.toString();
    }

    public HugeInteger(int n) throws IllegalArgumentException {
        Random rand = new Random(); 
        if (n < 0) {
            throw new IllegalArgumentException("Size of integer must be greater than or equal to 1"); 
        }

        boolean signVal = rand.nextBoolean(); 
        int multiplier = 1; 
        if (signVal) {
            multiplier = -1;
        }
    
        numArr = new int[n]; 

        int firstDig = rand.nextInt(9); 
        
        do {
            firstDig = rand.nextInt(9); 
        } while (firstDig == 0); 

        numArr[0] = multiplier * firstDig; 

        for (int i = 1; i < n; i++) {
            numArr[i] = rand.nextInt(9); 
        }

        StringBuilder newBuilder = new StringBuilder(); 
        for (int num : numArr) {
            newBuilder.append(num); 
        }

        integerVal = newBuilder.toString(); 

    }

    public HugeInteger add(HugeInteger h) {

        //System.out.println("working"); 

        // long l1 = customParseLong(this.integerVal); 
        // long l2 = customParseLong(h.integerVal); 
        // return new HugeInteger(String.valueOf(l1 + l2)); 
     
        int carry = 0; 
        int i = 0, j = 0; 
        int sum = 0; 
        String thisFixedVal = this.integerVal; 
        String hFixedVal = h.integerVal;    

        if (thisFixedVal.contains("-")) {thisFixedVal = new StringBuilder(this.integerVal).deleteCharAt(0).toString();}
        if (hFixedVal.contains("-")) {hFixedVal = new StringBuilder(h.integerVal).deleteCharAt(0).toString();} 

        HugeInteger absH = new HugeInteger(new StringBuilder(hFixedVal).toString()); 
        HugeInteger absThis = new HugeInteger(new StringBuilder(thisFixedVal).toString());

        int num1dig = 0; 
        int num2dig = 0; 

        StringBuilder addBuilder = new StringBuilder(); 

        for (i = thisFixedVal.length() - 1, j = hFixedVal.length() - 1; i >= 0 || j >= 0; i--, j--) {
                num1dig = i < 0 ? 0 : Character.getNumericValue(thisFixedVal.charAt(i));  
                num2dig = j < 0 ? 0: Character.getNumericValue(hFixedVal.charAt(j)); 

                sum = num1dig + num2dig + carry; 

                if (sum > 9) {
                    carry = 1; 
                    sum -= 10; 
                }  else {
                    carry = 0; 
                }

            addBuilder.append(sum); 
        }

       if (!this.integerVal.contains("-") && h.integerVal.contains("-")) {
           int sizeCheck = thisFixedVal.compareTo(absH.integerVal) > 0 ? 1 : 0; 
           String ans = "";  
          
           if (sizeCheck == 1) { 
            ans = this.subtract(absH).integerVal; 
            //System.out.println(ans); 
            return new HugeInteger(ans); 
           } else {
            ans = "-"+ (h.subtract(absThis)).integerVal;
            //System.out.println(ans); 
            return new HugeInteger(ans); 
           }

       } else if (this.integerVal.contains("-") && !h.integerVal.contains("-")) {
            int sizeCheck = absThis.integerVal.compareTo(hFixedVal) > 0 ? 1 : 0; 
            //System.out.println(sizeCheck); 
            String ans = "";  
        
            if (sizeCheck == 1) { 
                ans = "-" + this.subtract(absH).integerVal; 
                //System.out.println(ans); 
             return new HugeInteger(ans); 
            } else {
                ans = (h.subtract(absThis)).integerVal;
               // System.out.println(ans); 
             return new HugeInteger(ans); 
            }
       } else if (this.integerVal.contains("-") && h.integerVal.contains("-")) {
           String ans = ""; 
           ans = "-" + addBuilder.reverse().toString(); 
           
           //System.out.println(ans);
           return new HugeInteger(ans); 
       }
       //System.out.println(ans); 
     return new HugeInteger(addBuilder.reverse().toString()); 
    }
    
    public HugeInteger subtract(HugeInteger h) {
        int flag = 0; 

        HugeInteger absH = new HugeInteger(new StringBuilder(h.integerVal).deleteCharAt(0).toString()); 
        HugeInteger absThis = new HugeInteger(new StringBuilder(this.integerVal).deleteCharAt(0).toString());

        String thisFixedVal = new StringBuilder(absThis.integerVal).reverse().toString(); 
        String hFixedVal = new StringBuilder(absH.integerVal).reverse().toString();

        int num1dig = 0, num2dig = 0;
        String finalString = ""; 

        StringBuilder subFinal = new StringBuilder(); 

        int difference = 0; 
     

         for (int i = 0,  j = 0; i < thisFixedVal.length()  || j < hFixedVal.length() ; i++, j++) {
            num1dig = i > thisFixedVal.length() - 1 ? 0 : Character.getNumericValue(thisFixedVal.charAt(i)) - flag; 
            num2dig = j > hFixedVal.length() - 1 ? 0: Character.getNumericValue(hFixedVal.charAt(j)); 

            difference = num1dig - num2dig;

           if (difference < 0 && (i != thisFixedVal.length() - 1 || j != hFixedVal.length() - 1)) {
                flag = 1; 
                difference = (10 + num1dig) - num2dig; 
           }
                
            else if (difference >= 0 || i <= thisFixedVal.length() - 1 ||  j <= hFixedVal.length() - 1) {
               flag = 0; 
           } 
         //  System.out.println(difference); 
            subFinal.append(difference); 
        }

        //Checking for leading zeros
        int index = 0; 

        for (index = 0; index < subFinal.reverse().length(); index++) {
            if (subFinal.charAt(index) == '0') {
                continue;  
            }  else {
                break; 
            }
        }
        
        for (int j = index; j < subFinal.length(); j++) {
            finalString += subFinal.charAt(j); 
        }

        if (!this.integerVal.contains("-") && h.integerVal.contains("-")) {
            String ans = "";  

            ans = this.add(absH).integerVal; 
            System.out.println(ans); 
            return new HugeInteger(ans);

 
        } else if (this.integerVal.contains("-") && !h.integerVal.contains("-")) {
            String ans = "";  

            ans = "-" + h.add(absThis).integerVal; 
            System.out.println(ans); 
            return new HugeInteger(ans); 
  
        } else if (this.integerVal.contains("-") && h.integerVal.contains("-")) {
            String ans = ""; 
            int sizeCheck = absThis.integerVal.compareTo((absH.integerVal)) > 0 ? 1 : 0; 

            if (sizeCheck == 1) { 
              ans = "-" + subFinal.toString(); 
              System.out.println(ans); 
              return new HugeInteger(ans); 
            } else {
              ans = "-" + subFinal.toString();
              System.out.println(ans); 
              return new HugeInteger(ans); 
            }

        }
       return new HugeInteger(finalString); 
    } 
    
    
    public HugeInteger divide(HugeInteger h) throws ArithmeticException {
    //    int index = 0; 
    //    String quotient = ""; 
    //    char charArr[] = this.integerVal.toCharArray(); 
    //    long prefix = charArr[index] - '0'; 

      

    //     while (prefix <  Long.parseLong(h.integerVal)) {
    //         prefix = prefix * 10 + (charArr[++index] - '0'); 
    //         System.out.println(prefix); 
    //     }

    

    //     index++; 

    //     while (charArr.length > index) {
    //         quotient += (prefix/Long.parseLong(h.integerVal)); 
    //         System.out.println(quotient); 
    //         prefix = (prefix % Long.parseLong(h.integerVal)) * 10 + charArr[index++] - '0'; 
    //     }


        //int counter[] = new int[this.numArr.length];
    //     int index = 0; 

    //     HugeInteger ans = this; 
    //     int i = 0; 
    //     int j = 0; 
    //     //String answer = ""; 
    //    int counter = 0; 
    //    ans = ans.subtract(h); 
    //    System.out.println(ans.integerVal); 
    //     //HugeInteger zero = new HugeInteger("0"); 
    //     while (counter < 5) {
    //         ans = ans.subtract(h); 
    //         counter++;
    //     }

    //     // while (ans.compareTo(zero) >= 0) {

    //     // }

    //     System.out.println(counter); 
    //     String answer = String.valueOf(i); 


        // while (ans.compareTo(zero) >= 0) {
        //     ans = ans.subtract(h); 
        //     System.out.println(ans); 
        //     index++; 
        // }




        StringBuilder builder = new StringBuilder(); 

        long divisor = customParseLong(h.integerVal); 
        long dividend = customParseLong(this.integerVal); 

        if (divisor == 0) {
            throw new ArithmeticException("Division by zero"); 
        }
        

        builder.append(dividend/divisor); 
        

        return new HugeInteger(builder.toString()); 
       //return new HugeInteger(answer); 

    }

    public HugeInteger multiply(HugeInteger h) {

        StringBuilder builder = new StringBuilder(); 

        long hVal = customParseLong(h.integerVal); 
        long thisVal = customParseLong(this.integerVal); 
        builder.append(hVal*thisVal); 
        
        return new HugeInteger(builder.toString());

    }

    public int compareTo(HugeInteger h) {
        long thisVal = customParseLong(this.integerVal); 
        long hVal = customParseLong(h.integerVal); 

        if (thisVal > hVal) {
            return 1; 
        } else if (thisVal < hVal) {
            return -1; 
        }
        return 0; 
    }

    public String toString() {
        //System.out.println(this.integerVal); 
        return this.integerVal; 
    }




    public Long customParseLong(String h) {
        long longVal = 0; 
        int power = 0; 
        if (h.charAt(0) == '-') {
            for (int i = h.length() - 1; i >= 1; i--) {
                longVal += (h.charAt(i) - '0') * Math.pow(10, power);  
                power++; ; 
            }
            return -1*longVal; 
        } else {
            for (int i = h.length() - 1; i >= 0; i--) { 
                longVal += (h.charAt(i) - '0') * Math.pow(10, power);  
                power++; 
            }
            return longVal; 
        }
       
    }  


}   

