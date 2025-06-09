public class Transform2D {
    // Returns a new array object that is an exact copy of the given array.
    // The given array is not mutated.
    public static double[] copy(double[] array){
        int n = array.length;
        double[] copy_array = new double[n];
        for(int i = 0 ; i< n ; i++){
            copy_array[i] = array[i];
        }
        return copy_array;
    }

    // Scales the polygon by the factor alpha.
    public static void scale(double[] x, double[] y, double alpha){
        for(int i = 0; i < x.length ; i++){
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }
    }

    // Translates the polygon by (dx, dy).
    public static void translate(double[] x, double[] y, double dx, double dy){
        for(int i = 0; i < x.length ; i++){
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    // Rotates the polygon theta degrees counterclockwise, about the origin.
    public static void rotate(double[] x, double[] y, double theta){
        double radian = Math.toRadians(theta);
        for(int i = 0; i < x.length ; i++){
            x[i] = x[i] * Math.cos(radian) - y[i] * Math.sin(radian);
            y[i] = y[i] * Math.cos(radian) + x[i] * Math.sin(radian);
        }
    }
    // Tests each of the API methods by directly calling them.
    public static void main(String[] args){
    }
}
