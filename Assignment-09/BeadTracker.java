public class BeadTracker {
    public static void main(String[] args) {
        //Storing cammand line arguments.
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        for(int i = 3 ; i < args.length - 1 ; i++) {
            //stores two concevutive frames .
            Picture frame_1 = new Picture(args[i]);
            Picture frame_2 = new Picture(args[i+1]);

            // identifies the beads
            BeadFinder beads_frame1 = new BeadFinder(frame_1, tau);
            BeadFinder beads_frame2 = new BeadFinder(frame_2, tau);
            Blob[] beads1 = beads_frame1.getBeads(min);
            Blob[] beads2 = beads_frame2.getBeads(min);

            //calculates the distance between beads in two concecutive frames
            for (Blob bead2 : beads2) {
                double minDistance = Double.MAX_VALUE;

                for (Blob bead1 : beads1) {
                    double distance = bead1.distanceTo(bead2);
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }

                // Print distance if it is within the delta threshold
                if (minDistance <= delta) {
                    System.out.printf("%.4f\n", minDistance);
                }
            }
        }
    }
}
