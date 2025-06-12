import java.awt.Color;

public class Art {

    // Function to calculate the new coordinates after rotation
    private static double[] rotate(double x, double y, double angle) {
        double rad = Math.toRadians(angle);
        double newX = x * Math.cos(rad) - y * Math.sin(rad);
        double newY = x * Math.sin(rad) + y * Math.cos(rad);
        return new double[]{newX, newY};
    }

    // Recursive function to draw triangles
    private static void drawTriangles(double len, double angle, double angleIncrement, int iterations,
                                      int totalIterations, double lengthDecrement, double transparency) {
        // Calculate the three points of the triangle
        double pt1x = len * Math.cos(Math.toRadians(angle));
        double pt1y = len * Math.sin(Math.toRadians(angle));
        double[] pt2 = rotate(pt1x, pt1y, 120);
        double[] pt3 = rotate(pt2[0], pt2[1], 120);

        // Set color with transparency
        Color color = new Color(0, 0, 0, (float) transparency);
        StdDraw.setPenColor(color);
        StdDraw.polygon(new double[]{0, pt1x, pt2[0], pt3[0]},
                        new double[]{0, pt1y, pt2[1], pt3[1]});

        // Base case: stop recursion if iterations are complete
        if (iterations - 1 > 0) {
            drawTriangles(len * (1 - lengthDecrement), angle + angleIncrement, angleIncrement,
                          iterations - 1, totalIterations, lengthDecrement, transparency);
        }
    }

    public static void main(String[] args) {
        // Inputs
        int r = Integer.parseInt(args[0]); // Number of iterations
        double angleIncrement = Double.parseDouble(args[1]); // Rotation angle increment
        double lengthDecrement = Double.parseDouble(args[2]); // Length decrement ratio
        double transparency = Double.parseDouble(args[3]); // Transparency (0 to 1)

        // Setup the drawing canvas
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1.5, 1.5); // Adjust scale for better visualization
        StdDraw.clear();

        // Draw fractal triangles
        drawTriangles(1.0, 0.0, angleIncrement, r, r, lengthDecrement, transparency);

        // Display the drawing
        StdDraw.show();
    }
}

