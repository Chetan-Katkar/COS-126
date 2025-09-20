
import java.util.LinkedList;

public class Blob {
    LinkedList<int[]> pixels ;

    //  creates an empty blob
    public        Blob() {
        pixels = new LinkedList<>();
    }

    //  adds pixel (x, y) to this blob
    public   void add(int x, int y){
        pixels.add(new int[]{x,y});
    }

    //  number of pixels added to this blob
    public    int mass(){
        return pixels.size();
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that){
        double Sum_cx= 0,Sum_cy=0,that_cx,that_cy,this_cx,this_cy,euclidean_distance;
        //caculates the cx and cy for this blob.
        for(int[] pixel : pixels){
            Sum_cx += pixel[0];
            Sum_cy += pixel[1];
        }
        this_cx = Sum_cx / mass();
        this_cy = Sum_cy / mass();

        //calculates the cx and cy for that blob.
        Sum_cx = 0 ;
        Sum_cy = 0 ;
        for(int[] pixel : that.pixels){
            Sum_cx += pixel[0];
            Sum_cy += pixel[1];
        }
        that_cx = Sum_cx / that.mass();
        that_cy = Sum_cy / that.mass();

        euclidean_distance = Math.sqrt(Math.pow(that_cx - this_cx, 2)+ Math.pow(that_cy - this_cy, 2));

        return euclidean_distance;
    }

    //  string representation of this blob.
    public String toString(){
        double Sum_cx= 0,Sum_cy=0,this_cx,this_cy;
        //caculates the cx and cy for this blob.
        for(int[] pixel : pixels){
            Sum_cx += pixel[0];
            Sum_cy += pixel[1];
        }
        this_cx = Sum_cx / mass();
        this_cy = Sum_cy / mass();
        return String.format("%2d (%8.4f, %8.4f)", mass(), this_cx, this_cy);
    }

    public static void main(String[] args) {
        Blob blob = new Blob();

        blob.add(10, 20);
        blob.add(30, 40);
        blob.add(50, 60);

        System.out.println("Number of pixels in blob: " + blob.mass());

        System.out.println(blob);

        Blob blob2 = new Blob();

        blob2.add(20,40);
        blob2.add(60,30);

        System.out.println(blob.distanceTo(blob2));
    }
}
