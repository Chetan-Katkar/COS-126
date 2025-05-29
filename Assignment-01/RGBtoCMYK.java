public class RGBtoCMYK {
    public static void main(String[] args) {
        // variable to store RGB command line arguments.
        int red,green,blue;
        //converting string to integer.
        red = Integer.parseInt(args[0]);
        green = Integer.parseInt(args[1]);
        blue = Integer.parseInt(args[2]);
        //will store CMYK values.
        double cyan,magenta,yellow,black,white;
        white = Math.max(Math.max((red)/255.0 , (green)/255.0 ), (blue)/255.0);
        //checks if white is greater than zero.
        if (white == 0) {
            cyan = 0;
            magenta = 0;
            yellow = 0;
            black = 1;
        } else {
            //converting to equivalent CMYK values.
            cyan = (white - (red / 255.0)) / white;
            magenta = (white - (green / 255.0)) / white;
            yellow = (white - (blue / 255.0)) / white;
            black = 1 - white;
        }
        //prints the equivalent CMYK values
        System.out.println("red     = "+red);
        System.out.println("green   = "+green);
        System.out.println("blue    = "+blue);
        System.out.println("cyan    = "+cyan);
        System.out.println("magenta = "+magenta);
        System.out.println("yellow  = "+yellow);
        System.out.println("black   = "+black);
    }
}
