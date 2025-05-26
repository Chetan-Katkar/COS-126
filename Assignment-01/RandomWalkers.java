public class RandomWalkers {
    public static void main(String[] args) {
        //'trials' variable stores the count of trials to be performed.
        //sum variable will store the sum of squared distance for all trials.
        int trials = Integer.parseInt(args[1]);
        int sum =0;
        //n stores the value taken from command line argument.
        //next_x and next_y variables will store the value of next location.
        //index variable will store the randomly selected value by the method.
        int n , next_x,next_y , i , index;

        //initialise the starting position as origin.
        int initial_x = 0,initial_y = 0;

        //converting string to int.
        n = Integer.parseInt(args[0]);


        for(int j =0 ; j < trials ; j++){
            //initialising position to origin after every trail.
            next_x = initial_x;
            next_y = initial_y;

            //running randomwalker for n steps.
            for( i = 0 ; i < n ; i++){

                index = (int) (Math.random() * 4);
                if (index == 0) {
                    next_x = next_x+1;
                }
                else if(index == 1){
                    next_x = next_x-1;
                }
                else if(index == 3){
                    next_y = next_y+1;
                }
                else {
                    next_y = next_y-1;
                }
            }

            //finding Euclidean distance.
            //result variable will store the value of calculated Euclidean distance.
            int result = (int) (Math.pow((next_x-initial_x), 2) + Math.pow((next_y-initial_y),2));
            sum = sum + result;
        }
        //displaying mean squared distance.
        System.out.println("mean squared distance = " +((double)sum/trials));
    }
}
