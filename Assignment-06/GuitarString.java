public class GuitarString {
    int SAMPLING_RATE = 44100;
    double frequency;
    double[] Rb1; // Ringbuffer.
    double decay_factor = 0.996;
    int front = 0;//poiner to control deletion in ringbuffer
    int head = 0;//pointer to control insertion in ringbuffer

    //  creates a guitar string of the specified frequency, using a sampling rate of 44,100
    public GuitarString(double frequency) {
        this.frequency = frequency;
        int n = (int) Math.ceil(SAMPLING_RATE / frequency);
        Rb1 = new double[n];
        for (int i = 0; i < Rb1.length; i++) {
            Rb1[i] = 0;
        }
    }

    //  creates a guitar string whose length and initial values are given by the specified array
    public GuitarString(double[] init) {
        int n = init.length;
        Rb1 = new double[n];
        for (int i = 0; i < n; i++) {
            Rb1[i] = init[i];
        }
    }

    //  returns the number of samples in the ring buffer
    public int length() {
        return Rb1.length;
    }

    //  plucks this guitar string (by replacing the ring buffer with white noise)
    public void pluck() {
        double randomValue;//stores random value
        double min = -0.5;
        double max = 0.5;
        for (int i = 0; i < length(); i++) {
            Rb1[i] = min + (Math.random() * (max - min));
        }
        front = 0;
        head = length() - 1; // Reset head to the last position
    }

    //  advances the Karplus-Strong simulation one time step
    public void tic() {
        double x;
        x = ((Rb1[front] + Rb1[(front + 1) % length()]) / 2) * decay_factor;
        Rb1[front] = x;
        front = (front + 1) % length();
        head = (head + 1) % length();
    }

    //  returns the current sample
    public double sample() {
        return Rb1[front];
    }

    //  tests this class by directly calling both constructors and all instance methods
    public static void main(String[] args) {

    }

}
