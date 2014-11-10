public class MaxVariablesDemo {
    public static void main(String args[]) {

        // integers
        byte largestByte = Byte.MAX_VALUE; // 2 xy 7
        short largestShort = Short.MAX_VALUE; // 2 xy 15
        int largestInteger = Integer.MAX_VALUE; // 2 xy 31
        long largestLong = Long.MAX_VALUE; // 2 xy 63

        // real numbers
        float largestFloat = Float.MAX_VALUE;
        /**
         * float: The float data type is a single-precision 32-bit IEEE 754 floating point. 
         * Its range of values is beyond the scope of this discussion, but is specified in the Floating-Point Types, 
         * Formats, and Values section of the Java Language Specification. As with the recommendations for 
         * byte and short, use a float (instead of double) if you need to save memory in large arrays of floating 
         * point numbers. This data type should never be used for precise values, such as currency. For that, 
         * you will need to use the java.math.BigDecimal class instead. Numbers and Strings covers BigDecimal and 
         * other useful classes provided by the Java platform.
         */
        
        double largestDouble = Double.MAX_VALUE;
        /**
         * double: The double data type is a double-precision 64-bit IEEE 754 floating point. Its range of values 
         * is beyond the scope of this discussion, but is specified in the Floating-Point Types, Formats, and Values 
         * section of the Java Language Specification. For decimal values, this data type is generally the default 
         * choice. As mentioned above, this data type should never be used for precise values, such as currency.
         */

        // other primitive types
        char aChar = 'S';
        /**
         * Literals of types char and String may contain any Unicode (UTF-16) characters. If your editor and file 
         * system allow it, you can use such characters directly in your code. If not, you can use a "Unicode escape" 
         * such as '\u0108' (capital C with circumflex), or "S\u00ED Se\u00F1or" (Sí Señor in Spanish). 
         * Always use 'single quotes' for char literals and "double quotes" for String literals. Unicode escape 
         * sequences may be used elsewhere in a program (such as in field names, for example), not just in char or String literals.
         * 
         * The Java programming language also supports a few special escape sequences for char and String literals: 
         * \b (backspace), \t (tab), \n (line feed), \f (form feed), \r (carriage return), \" (double quote), \' (single quote), and \\ (backslash).
         */
        
        boolean aBoolean = true;

        // display them all
        System.out.println("The largest byte value is " + largestByte);
        System.out.println("The largest short value is " + largestShort);
        System.out.println("The largest integer value is " + largestInteger);
        System.out.println("The largest long value is " + largestLong);

        System.out.println("The largest float value is " + largestFloat);
        System.out.println("The largest double value is " + largestDouble);

        if (Character.isUpperCase(aChar)) {
            System.out.println("The character " + aChar + " is upper case.");
        } else {
            System.out.println("The character " + aChar + " is lower case.");
        }
        System.out.println("The value of aBoolean is " + aBoolean);
        
        
        
        
        
        
        String[] financialInstitutions = {
        		 "Royal Bank of Scotland", "Bradford & Bingley",
        		 "Barclays Bank", "Bank of New York", "ING Bank",
        		 "Bank of China"};
        System.out.println(financialInstitutions.length);
        financialInstitutions[4];
        
        
        
        
        
    }
}