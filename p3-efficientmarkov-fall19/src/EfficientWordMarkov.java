import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram,ArrayList<String>> myMap;

    //default constructor
    public EfficientWordMarkov() {
        this(2);
    }

    //constructor to initialize variables
    public EfficientWordMarkov (int order){
        super(order);
        myRandom = new Random(RANDOM_SEED);
        myMap= new HashMap<>();

    }


    @Override
    public void setTraining(String text) {


        super.setTraining(text);

        //splits text into array of individual strings
        myWords = text.split("\\s+");

        //clears map each time method is called
        myMap.clear();

        int index = 0;  // location where search for key in text starts

        String PSEUDO_EOS = ""; //empty String for when end of array is reached

        for (int i = 0; i < myWords.length - myOrder + 1; i++) {

            //Initialize new ArrayList each time loop iterates
            ArrayList<String> follows = new ArrayList<String>();

            //Find substring
            WordGram key = new WordGram(myWords, i, myOrder);;

            //puts every key into array
            myMap.putIfAbsent(key,follows);

            //put empty String when loop gets to end of the array
            if(i + myOrder == myWords.length){
                myMap.get(key).add(PSEUDO_EOS);
                break;
            }

            //If keys already has a corresponding ArrayList, add new value found to ArrayList
            else {
                myMap.get(key).add(myWords[i+myOrder]);
            }

        }
    }

    /**
     * Find and return the first index >= start in words at which target occurs.
     * Each target.length() sequence of strings is converted to a WordGram
     * which is tested against target
     * @return index of first occurrence of target (>= start) or -1
     * if not found
     */

    @Override
    public ArrayList<String> getFollows(WordGram kGram) {

        //return if not found
        if(!myMap.containsKey(kGram)){
            throw new NoSuchElementException(kGram + " not in map");}

        else{
            return myMap.get(kGram);}
    }

}
