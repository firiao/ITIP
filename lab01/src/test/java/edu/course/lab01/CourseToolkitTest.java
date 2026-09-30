package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {
    /**                      TEST FOR isEven                                */
    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }
    
    @Test
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test 
    void returnsTrueForNegativeEvenNumber() {
        boolean result =  CourseToolkit.isEven(-8);
    
        assertTrue(result);
    }


    /**                      TEST FOR isPrime                                */


    @Test 
    void returnsTrueFor43() {
        boolean result = CourseToolkit.isPrime(43);

        assertTrue(result);
    }

    @Test 
    void returnsTrueFor59() {
        boolean result = CourseToolkit.isPrime(59);

        assertTrue(result);
    }

    @Test 
    void returnsFalseForNumberBelowTwo() {
        boolean result = CourseToolkit.isPrime(1);

        assertFalse(result);
    }

    @Test 
    void returnsFalseForSquaredSeven() {
        boolean result = CourseToolkit.isPrime(49);

        assertFalse(result);
    }


     /**                      TEST FOR isPalindrome                           */


    @Test
    void returnTrueForPalindrome() {
        boolean result = CourseToolkit.isPalindrome("aboba");
        assertTrue(result);
    }
    
    @Test 
    void returnFalseForNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CourseToolkit.isPalindrome(null);
    });
    }

    @Test 
    void returnFalseForNotPalindrome() {
        boolean result = CourseToolkit.isPalindrome("Aboba");
        assertFalse(result);
    }
    

     /**                      TEST FOR average                                */
    
    
     @Test 
    void returnTrueForExactAvg(){
        int[] array = {1, 2, 3, 4};
        double result = CourseToolkit.average(array);
    
        assertEquals(2.5, result);
    }

    @Test 
    void returnTrueForArrayWithNegatives(){
        int[] array = {-2, -1, 0, 1, 2};
        double result = CourseToolkit.average(array);
    
        assertEquals(0, result);
    }

    @Test 
    void returnFalseForArray(){
        int[] array = {2, 2, 3, 5};
        double result = CourseToolkit.average(array);
    
        assertNotEquals(5, result);
    }
}
