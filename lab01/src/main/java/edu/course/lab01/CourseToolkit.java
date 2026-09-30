package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number){
        if (number < 2) {
            return false;
        }
        for (int i = 2; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
        
    public static boolean isPalindrome(String text){
    if (text == null) {
        throw new IllegalArgumentException("text must not be null");
    }
        char[] charArray = text.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[charArray.length - 1 - i];
            charArray[charArray.length - 1 - i] = temp;
        }
        String reversed = new String(charArray);
        return reversed.equals(text);
    }

    public static double average(int[] values) {
    if (values == null || values.length == 0) {
        throw new IllegalArgumentException("result is null or massive doesn't includes any values");
    }
    
    int sum = 0;
    for (int num : values) {
        sum += num;
    }
    return (double) sum / values.length;        
    }
}
