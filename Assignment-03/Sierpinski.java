import java.awt.Color;

public class Sierpinski {
    //  Height of an equilateral triangle whose sides are of the specified length.
    public static double height(double length){
        return (Math.sqrt(3)/2)*length ;
    }

    //  Draws a filled equilateral triangle whose bottom vertex is (x, y)
    //  of the specified side length.
    public static void filledTriangle(double x, double y, double length){

        double temp_l = length / 2;

        double[] temp_X = {x,x-temp_l/2,x+temp_l/2} ;
        double[] temp_Y = {y,y+height(temp_l),y+height(temp_l)};

        StdDraw.filledPolygon(temp_X,temp_Y);
    }

    //  Draws a Sierpinski triangle of order n, such that the largest filled
    //  triangle has bottom vertex (x, y) and sides of the specified length.
    public static void sierpinski(int n, double x, double y, double length){
        //base case.
        if (n == 0) return;

        filledTriangle(x , y,length);

        double temp_l = length / 2;

        // Recursive calls
        sierpinski(n - 1, x+(temp_l/2), y, temp_l); // Right
        sierpinski(n - 1, x - temp_l / 2, y, temp_l); // Left
        sierpinski(n - 1, x, y + height(temp_l), temp_l); // Top
    }

    //  draws a Sierpinski triangle of order n that fits snugly inside the outline
    public static void main(String[] args) {

        int n =  Integer.parseInt(args[0]);

        //  draws the outline of an equilateral triangle (pointed upwards) of length 1;
        //  whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0)
        double length = 1;
        double x0 = 0,x1=1,x2= (x0 + x1)/2 ;
        double y0 = 0,y1=0,y2= (y0 + y1)/2 + height(length);

        double[] x = {x0,x1,x2};
        double[] y = {y0,y1,y2};

        StdDraw.enableDoubleBuffering();

        StdDraw.setPenColor(Color.black);
        StdDraw.polygon(x,y);

        //start of recurcive function call.
        sierpinski(n,x0+(length/2),y0,length);

        StdDraw.show();
    }
}
