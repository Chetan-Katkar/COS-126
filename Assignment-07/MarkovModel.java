public class MarkovModel {
    private static final int ASCII = 128;
    static int k;
    String text;
    public static ST<String, Integer> st1;//first symbol table
    public static ST<String, Integer[]> st2;
    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k) {
        this.text = text;
        this.k = k;
        String Circular_text;
        if (k == 0) {
            Circular_text = text + text.charAt(0);
        }
        else {
            Circular_text = text + text.substring(0, k - 1);
        }
        st1 = new ST<String, Integer>();
        st2 = new ST<>();
        String key;
        int value;
        int n = Circular_text.length();//length of circular_text

        // Initialize the symbol table 1 and symbol table 1.
        if (k == 0) {
            for (int i = 0; i < n ; i++) {
                key = Circular_text.charAt(i) + "";
                if (!st1.contains(key)) {
                    st1.put(key, 0);
                }
                if (!st2.contains(key)) {
                    st2.put(key, new Integer[ASCII]);
                }
            }
        }
        else{
            for (int i = 0; i <= n - k; i++) {
                key = Circular_text.substring(i, i + k);
                if (!st1.contains(key)) {
                    st1.put(key, 0);
                }
                if (!st2.contains(key)) {
                    st2.put(key, new Integer[ASCII]);
                }
            }
        }

        //assigns values to keys in symbol table 1
        if(k == 0){
            for (int j = k; j < n; j++) {
                key = Circular_text.charAt(j) + "";
                if (st1.contains(key)) {
                    st1.put(key, st1.get(key) + 1);
                }
            }
        }
        else {
            for (int j = k; j <= n; j++) {
                key = Circular_text.substring(j - k, j);
                if (st1.contains(key)) {
                    st1.put(key, st1.get(key) + 1);
                }
            }
        }

        int next_char;
        //assigns values to keys in symbol table 2
        if (k == 0) {
            int j = k+1;
            for (int i = k; i <= n; i++) {
                key = Circular_text.charAt(i) + "";
                if (st2.contains(key)) {
                    if(i!=n) {
                        next_char = Circular_text.charAt(i);//stores the character after given kgram
                    }
                    else{
                        next_char = Circular_text.charAt(j);
                    }
                    if (st2.get(key)[next_char] == null) {
                        st2.get(key)[next_char] = 0; // Initialize array element to 0 if null
                    }
                    st2.get(key)[next_char] += 1;//increment the count
                }
            }
        }
        else {
            int j = k-1;
            for (int i = k; i <= n; i++) {
                key = Circular_text.substring(i - k, i);
                if (st2.contains(key)) {
                    if(i!=n) {
                        next_char = Circular_text.charAt(i);//stores the character after given kgram
                    }
                    else{
                        next_char = Circular_text.charAt(j);
                    }
                    if (st2.get(key)[next_char] == null) {
                        st2.get(key)[next_char] = 0; // Initialize array element to 0 if null
                    }
                    st2.get(key)[next_char] += 1;//increment the count
                }
            }
        }
    }

    // returns the order k of this Markov model
    public int order() {
        return k;
    }

    // returns a string representation of the Markov model
    public String toString(){
        char c;//stores character
        int count;//stores count of character
        int ascii_value;
        // st2 is the second symbol table (corresponding to the two-argument freq() method)
        String result = "";
        for (String key : st2.keys()) {
            result+=key + ": ";

            // get the character frequency array
            Integer[] frequency = st2.get(key);

            // for each non-zero entry, append the character and the frequency
            for(int i = 0 ; i < ASCII ; i++){
                if(frequency[i] != null ){
                    ascii_value = i;//storing ascii value
                    c = (char)ascii_value;//converting ascii value to character
                    count = frequency[i];//storing count of that character
                    result += c + " " +count+ " ";
                }
            }

            // append a newline character
            result += "\n";
        }
        return result;
    }
    // returns the number of times the specified kgram appears in the text
    public static int freq(String kgram){
        if (kgram.length() != k) {
            throw new IllegalArgumentException("kgram must be of length " + k);
        }
        int frequency = 0;
        for (String key : st1.keys()){
            if(key.equals(kgram)){
                frequency = st1.get(key);
                break;
            }
        }
        return frequency;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c){
        int count = 0;
        for (String key : st2.keys()){
            if(key.equals(kgram)){
                Integer frequency[] = st2.get(key);
                if(frequency[c] != null ){
                    count = frequency[c];
                }
                break;
            }
        }
        return count;
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram){
        Integer[] frequency = st2.get(kgram);
        if (frequency == null) {
            throw new IllegalArgumentException("kgram not found");
        }

        int total = freq(kgram);
        if (total == 0) {
            throw new IllegalArgumentException("no valid character follows the kgram");
        }

        double[] probabilities = new double[ASCII];


        for (int i = 0; i < ASCII; i++) {
            if (frequency[i] != null && frequency[i] > 0) {
                probabilities[i] = (double) frequency[i] / total;
            } else {
                probabilities[i] = 0;
            }
        }


        int randomIndex = StdRandom.discrete(probabilities);
        return (char) randomIndex;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {

    }

}
