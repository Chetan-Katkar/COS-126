public class Checkerboard {
    public static void main(String[] args) {
        int i,j; //loop control variables.
        int n = Integer.parseInt(args[0]);//n stores the command-line argument.
        for( i = 0 ; i<n ; i++) {//runs of n rows.
            for( j = 0 ; j<n ; j++) {//for each row it runs for n columns.
                if( i%2 == 0 ){//check if the row is even.
                    System.out.print("* ");
                }
                else {
                    System.out.print(" *");
                }
            }
            System.out.println();//Moves to the next line.
        }
    }
}
