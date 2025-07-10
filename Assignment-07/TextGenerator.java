public class TextGenerator {
    public static void main(String[] args) {
        //reading k and t from command line
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String text = StdIn.readAll();

        if (k < 0) {
            throw new IllegalArgumentException("k must be greater than 0");
        }

        if (text.length() < k) {
            throw new IllegalArgumentException("Input text length must be at least k");
        }


        //creating MarkovModel object
        MarkovModel model1 = new MarkovModel(text, k);

        if (k == 0) {
            String Kgram = text.charAt(0) + ""; // For k = 0, kgram is a single character
            System.out.print(Kgram); // Prints the initial kgram

            // Generating characters for k == 0 case
            for (int j = 0; j < t; j++) {
                Integer[] frequency = MarkovModel.st2.get(Kgram);
                if (frequency == null) {
                    throw new IllegalArgumentException("Kgram not found in model: " + Kgram);
                }

                double[] probabilities = new double[128];
                int total = MarkovModel.freq(Kgram);

                // Fill probabilities array based on frequency
                for (int i = 0; i < 128; i++) {
                    if (frequency[i] != null && frequency[i] > 0) {
                        probabilities[i] = (double) frequency[i] / total;
                    }
                    else {
                        probabilities[i] = 0;
                    }
                }

                // Generate random character
                int randomIndex = StdRandom.discrete(probabilities);
                char randomChar = (char) randomIndex;
                System.out.print(randomChar);

                // Update kgram
                Kgram = randomChar + "";
            }
        }
        else {
            // For k > 0, proceed with kgram logic
            String Kgram = text.substring(0, k);
            System.out.print(Kgram);

            for (int i = 0; i < t - k; i++) {
                char randomChar = model1.random(Kgram);
                System.out.print(randomChar);
                Kgram = Kgram.substring(1) + randomChar; // Update kgram
            }
        }
    }
}
