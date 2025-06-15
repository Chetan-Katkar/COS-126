public class GuitarHero {
    public static void main(String[] args) {
        String keyboard="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        double base_frequency = 440.0;
        char key ; //variable used to stored the retrived keys from the input.
        int index;//will stored the index of key retrived from the keyboard string.
        int n = keyboard.length();

        // Create and initialize the array of GuitarString
        GuitarString[] strings = new GuitarString[n];
        for (int i = 0; i < n; i++) {
            double frequency = base_frequency * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }


        while(true){
            // check if the user has typed a key; if so, process it
            if(StdDraw.hasNextKeyTyped()){
                key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);

                if(index >=0 ){
                    strings[index].pluck();
                }
            }

            //calculating superpostion.
            double sample = 0.0;
            for(int i =0 ; i < n ; i++){
                sample = sample + strings[i].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(int i = 0 ; i < n ; i++){
                strings[i].tic();
            }
        }
    }
}
