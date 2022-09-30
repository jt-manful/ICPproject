// importing libraries
package com.company;
import java.util.Arrays;

//main class
public class Tester {

    /**
     * displays the multiplication table for that number up to n*12.
     * @param n the multipling factor
     * @return returns an array of n up to n*12.
     */

    public static int[] multiplicationTable(int n){
        // declares an Array of integers.

        int[] results = new int[12];

        for (int i = 0; i < results.length ; i++){
            results[i] = n * (i + 1);
        }
        return results;
    }

    /**
     * calculates the factorial of a given number
     * @param fact the number for the factorial
     * @return returns the value of the factorial
     */
    public static int factorial(int fact){
        if (fact == 0)
            return 1;
        else
            return(fact * factorial(fact-1));
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(multiplicationTable(7)));
        System.out.println(factorial(4));
    }
}
