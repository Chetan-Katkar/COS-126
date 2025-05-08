import java.util.Random;

public class RandomWalker {
    public static void main(String[] args) {
        //n stores the value taken from command line argument.
        //next_x and next_y variables will store the value of next location.
        //'i' is the looping variable.
        //index variable will store the randomly selected value by the method.
        int n , next_x,next_y , i , index;

        //initialise the starting position as origin.
        int initial_x = 0,initial_y = 0;

        //converting string to int.
        n = Integer.parseInt(args[0]);

        //prints the initial position.
        System.out.println("(" +initial_x+  ", "  +initial_y + ")");

        next_x = initial_x;
        next_y = initial_y;
        for( i = 0 ; i < n ; i++){
            //for every iteration index will be assigned with one
            // of the random value between 0 to 3(including 3).
            index = (int) (Math.random() * 4);
            if (index == 0) {
                next_x = next_x+1;
                System.out.println("(" +next_x+  ", "  +next_y + ")");
            }
            else if(index == 1){
                next_x = next_x-1;
                System.out.println("(" +next_x+  ", "  +next_y + ")");
            }
            else if(index == 3){
                next_y = next_y+1;
                System.out.println("(" +next_x+  ", "  +next_y + ")");
            }
            else {
                next_y = next_y-1;
                System.out.println("(" +next_x+  ", "  +next_y + ")");
            }
        }

        //finding Euclidean distance.
        //result variable will store the value of calculated Euclidean distance.
        int result = (int) (Math.pow((next_x-initial_x), 2) + Math.pow((next_y-initial_y),2));
        System.out.println("squared distance = "+result);
    }
}
