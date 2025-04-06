public class HiFour {
    public static void main(String[] args) {
        if(args.length < 4 )
        {
            System.out.println("please enter one greeting and two names !");
            return;
        }
        System.out.println( "hii " + args[3] + "," +" " + args[2] + "," +" " + args[1] + "," + " and " + args[0] + ".");
    }
}
