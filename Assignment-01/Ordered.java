public class Ordered {
    public static void main(String[] args) {
        // number1,number2 and number3 are the variable which stored the numbers taken from command-line argument.
        int  number1, number2, number3;
        // boolean variable result stores the final calculated result either true or false.
        Boolean result ;
            //converting string to an int.
            number1 = Integer.parseInt(args[0]);
            number2 = Integer.parseInt(args[1]);
            number3 = Integer.parseInt(args[2]);
            // Stores true if the integers are in increasing order (number1 < number2 < number3)
            // or decreasing order (number1 > number2 > number3); otherwise false.
            result = (number1 < number2 && number2 < number3) || (number1 > number2 && number2 > number3);

            System.out.println(result);
    }
}
