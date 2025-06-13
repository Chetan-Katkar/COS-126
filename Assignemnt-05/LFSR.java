public class LFSR {

    String seed;
    int tap;

    // Creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap){
        this.seed = seed;
        this.tap = tap;
    }

    // Returns the number of bits in the LFSR
    public int length(){
        return seed.length();
    }

    // Returns bit i as 0 or 1, adjusted for reverse order
    public int bitAt(int i){
        if( seed.charAt(i) == '1' )
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    // Returns a string representation of this LFSR
    public String toString(){
        return seed;
    }

    // Simulates one step of this LFSR and returns the new bit as 0 or 1
    public int step(){
        // XOR the bit at the tap position (9th from the right) and the rightmost bit (0th bit)
        int bit = bitAt(length() - tap) ^ bitAt(0);

        seed = seed.substring(1) + bit;

        return bit;
    }

    // simulates k steps of this LFSR and return the k bits as a k-bit integer
    public int generate(int k){
        int x = 0;
        for (int i=0; i < k ; i++) {
            int bit = step();
            x = 2*x + bit ;
        }
         return x;
    }


    // Tests this class by directly calling all instance methods
    public static void main(String[] args){

    }
}
