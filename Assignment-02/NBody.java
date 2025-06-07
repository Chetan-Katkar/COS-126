public class NBody {
    //defining gravitaional constant.
    public static final double G = 6.67e-11;

    public static void main(String[] args) {

        //storing command line arguments.
        double T = Double.parseDouble(args[0]); // Total simulation time
        double dt = Double.parseDouble(args[1]); // Time increment (Δt)

        int n = StdIn.readInt(); // reads and stores number of planets.
        double radius = StdIn.readDouble(); // reads and stores radius of universe.

        // creating arays for storing data from planets.txt file.
        double[] position_x = new double[n];
        double[] position_y = new double[n];
        double[] velocity_x = new double[n];
        double[] velocity_y = new double[n];
        double[] bodymass = new double[n];
        String[] images = new String[n];
        double[] fx = new double[n];
        double[] fy = new double[n];
        //stroring data in arrays.
        for(int i = 0 ; i < n ; i++ ){
            position_x[i] = StdIn.readDouble();
            position_y[i] = StdIn.readDouble();
            velocity_x[i] = StdIn.readDouble();
            velocity_y[i] = StdIn.readDouble();
            bodymass[i] = StdIn.readDouble();
            images[i] = StdIn.readString();
        }

        // Set up the drawing canvas
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        // Simulation loop
        double t = 0;
        while (t < T) {

            // Calculating net forces on each planet


            for (int i = 0; i < n; i++) {
                fx[i] = 0;
                fy[i] = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double dx = position_x[j] - position_x[i];
                        double dy = position_y[j] - position_y[i];
                        double r = Math.sqrt(dx * dx + dy * dy);
                        double force = G * bodymass[i] * bodymass[j] / (r * r);
                        fx[i] += force * dx / r; // x-component
                        fy[i] += force * dy / r; //javax y-component
                    }
                }
            }

            //Updating velocities and positions
            for (int i = 0; i < n; i++) {
                double ax = fx[i] / bodymass[i];
                double ay = fy[i] / bodymass[i];
                velocity_x[i] += ax * dt;
                velocity_y[i] += ay * dt;
                position_x[i] += velocity_x[i] * dt;
                position_y[i] += velocity_y[i] * dt;
            }

            // Drawing the universe
            StdDraw.clear();
            StdDraw.picture(0, 0, "starfield.jpg"); // Background image
            for (int i = 0; i < n; i++) {
                StdDraw.picture(position_x[i], position_y[i], images[i]); // Draw planet
            }
            StdDraw.show();
            StdDraw.pause(10); // Control animation speed

            // Increment time
            t += dt;
        }

        //printing final state values of planets in universe.
        System.out.println(n); // Number of planets.
        System.out.printf("%.2e\n", radius); // Radius of the universe.
        for (int i = 0; i < n; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    position_x[i], position_y[i], velocity_x[i], velocity_y[i], bodymass[i], images[i]);
        }
        }
    }

